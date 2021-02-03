package com.example.Viewer;

import com.example.models.FileModel;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.*;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.text.resources.et.CollationData_et;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@CrossOrigin
public class JGITcontroller {

    @ResponseBody
    @GetMapping("/persons")
    public List<FileModel> jGit() {
        List<FileModel> returned;
        File dirs = new File("C:\\repositories\\");
        List<File> files = Arrays.asList(dirs.listFiles());
        returned = files.stream().map(file -> new FileModel(file.getName(), "/persons/" + file.getName())).collect(Collectors.toList());
        return returned;
    }

    @ResponseBody
    @GetMapping(value = {"/persons/{student}", "/persons/{student}/{repo}", "/persons/{student}/{repo}/"})
    public List<FileModel> variable(
            @PathVariable String student,
            @PathVariable Optional<String> repo,
            @PathVariable Optional<String> task) throws IOException {

        if (!repo.isPresent()) {
            File dirs = new File("C:\\repositories\\" + student);
            Optional<File[]> optionalFileList = Optional.ofNullable(dirs.listFiles());
            List<FileModel> returned = new ArrayList<>();
            optionalFileList.ifPresent(files -> Arrays.stream(files)
                    .map(File -> new FileModel(File.getName(), "/persons/" + File.getName()))
                    .forEach(returned::add));
            return returned;
        } else if (!task.isPresent()) {
            Repository existingRepo = new FileRepositoryBuilder()
                    .setGitDir(new File("C:\\repositories\\" + student + "\\" + repo.get()))
                    .build();
            Optional<File[]> optionalFileList = Optional.ofNullable(existingRepo.getDirectory().listFiles());
            List<FileModel> returned = new ArrayList<>();
            optionalFileList.ifPresent(files -> Arrays.stream(files)
                    .map(File -> new FileModel(File.getName(), "/persons/" + File.getName()))
                    .forEach(returned::add));
            return returned;
        }
        return null;
    }

    public static String labuda;
    @GetMapping("/persons/{student}/{repo}/git")
    @ResponseBody
    public Object Jgit(
            @PathVariable String student,
            @PathVariable Optional<String> repo,
            @RequestParam Optional<String> map) throws IOException, GitAPIException {
        labuda=null;
        Git git = Git.open(new File("C:\\repositories\\" + student + "\\" + repo.orElse("none")));

        Iterable<RevCommit> call = git.log().call();
        RevCommit next = call.iterator().next();

        Repository repository = git.getRepository();
        RevCommit revCommit = repository.parseCommit(next);

        TreeWalk treeWalk = new TreeWalk(repository);
        treeWalk.addTree(revCommit.getTree());
        treeWalk.setRecursive(false);

        List<String> stringList = new ArrayList<>();

        if(!map.isPresent()) {
            while (treeWalk.next()){
               stringList.add(treeWalk.getNameString());
            }
        } else {
            String[] split = map.orElse("").split("/");
            for (String s : split) {
                walker(treeWalk, s, map.orElse(""), repository);
            }
            while (treeWalk.next()) {
                if (treeWalk.getPathString().split("/").length > split.length) {
                    stringList.add(treeWalk.getNameString());
                }
            }
        }
        if (labuda!=null){
            return labuda;
        }
        return stringList.toString();
    }

    public static TreeWalk walker(TreeWalk treeWalk, String str, String file, Repository repo) throws IOException {
        while (treeWalk.next()) {
            if (treeWalk.isSubtree()) {
                if (treeWalk.getNameString().equals(str)) {
                    treeWalk.enterSubtree();
                    return treeWalk;
                }
            } else if (treeWalk.getPathString().equals(file)) {
                ObjectId objectId = treeWalk.getObjectId(0);
                ObjectLoader loader = repo.open(objectId);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                loader.copyTo(stream);
                labuda = stream.toString();
            }
        }
        return treeWalk;
    }
}
