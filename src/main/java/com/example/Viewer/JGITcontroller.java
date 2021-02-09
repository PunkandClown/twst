package com.example.Viewer;

import com.example.models.CommitModel;
import com.example.models.FileModel;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
@CrossOrigin
@RequestMapping(value = "/persons", method = RequestMethod.GET)
public class JGITcontroller {

    @Value("${file.directory}")
    public String path;

    @ResponseBody
    @GetMapping
    public List<FileModel> persons() {
        List<FileModel> returned;
        File dirs = new File(path);
        List<File> files = Arrays.asList(Objects.requireNonNull(dirs.listFiles()));
        returned = files.stream().map(file -> new FileModel(file.getName(), "/persons/" + file.getName())).collect(Collectors.toList());
        return returned;
    }

    @ResponseBody
    @GetMapping("{student}")
    public List<FileModel> repositories(
            @PathVariable String student) {
        File dirs = new File(path + student);
        Optional<File[]> optionalFileList = Optional.ofNullable(dirs.listFiles());
        List<FileModel> returned = new ArrayList<>();
        optionalFileList.ifPresent(files -> Arrays.stream(files)
                .map(File -> new FileModel(File.getName(), "/persons/" + student + "/" + File.getName()))
                .forEach(returned::add));
        return returned;
    }

    @GetMapping("{student}/{repo}")
    @ResponseBody
    public List<CommitModel> commits(
            @PathVariable String student,
            @PathVariable Optional<String> repo) throws IOException, GitAPIException {
        Git git = Git.open(new File(path + student + "\\" + repo.orElse("none")));
        List<CommitModel> returned = new ArrayList<>();
        Iterable<RevCommit> call = git.log().call();
        StreamSupport
                .stream(call.spliterator(), false)
                .map(cit -> new CommitModel(
                        cit.name(),
                        cit.getFullMessage(),
                        cit.getAuthorIdent().getName(),
                        String.valueOf(cit.getCommitTime()),
                        student + "/" + repo.orElse("") +"/git/" + cit.getName() + "?map="))
                .forEach(returned::add);
        return returned;
    }

    @GetMapping(value = {"{student}/{repo}/git", "{student}/{repo}/git/{commit}"})
    @ResponseBody
    public Object jgit(
            @PathVariable String student,
            @PathVariable Optional<String> repo,
            @PathVariable Optional<String> commit,
            @RequestParam Optional<String> map) throws IOException, GitAPIException {
        Git git = Git.open(new File(path + student + "\\" + repo.orElse("none")));
        Iterable<RevCommit> call = git.log().call();

        List<String> stringList = new ArrayList<>();
        List<FileModel> FolderList = new ArrayList<>();

        RevCommit next = call.iterator().next();

        Optional<RevCommit> targetCommit = StreamSupport
                .stream(call.spliterator(), false)
                .filter(cit -> cit.getName().equals(commit.orElse(next.getName()))).findFirst();

        Repository repository = git.getRepository();
        RevCommit revCommit = repository.parseCommit(targetCommit.orElse(next));
        TreeWalk treeWalk = new TreeWalk(repository);
        treeWalk.addTree(revCommit.getTree());
        treeWalk.setRecursive(false);

        if (map.isPresent() && map.get().equals("")) {
            while (treeWalk.next()) {
                FolderList.add(new FileModel(treeWalk.getNameString(), student + "/" + repo.orElse("") + "/git/" + commit.orElse("") + "?map=" + treeWalk.getNameString()));
            }
        } else {
            String[] split = map.orElse("").split("/");
            for (String str : split) {
                walker(treeWalk, str, map.orElse(""));
            }
            if (!treeWalk.isSubtree() && treeWalk.getPathString().equals(map.orElse(""))) {
                ObjectId objectId = treeWalk.getObjectId(0);
                ObjectLoader loader = repository.open(objectId);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                loader.copyTo(stream);
                return stream.toString();
            }
            while (treeWalk.next()) {
                if (treeWalk.getPathString().split("/").length > split.length) {
                    FolderList.add(new FileModel(treeWalk.getNameString(), student + "/" + repo.orElse("") + "/git/" + commit.orElse("") + "?map=" + treeWalk.getNameString()));
                }
            }
        }
        return FolderList;
    }

    public static TreeWalk walker(TreeWalk treeWalk, String str, String map) throws IOException {
        while (treeWalk.next()) {
            if (treeWalk.isSubtree()) {
                if (treeWalk.getNameString().equals(str)) {
                    treeWalk.enterSubtree();
                    return treeWalk;
                }
            } else if (treeWalk.getPathString().equals(map)) {
                return treeWalk;
            }
        }
        return treeWalk;
    }
}
