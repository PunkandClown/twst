package com.example.Viewer;

import com.example.models.FileModel;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@CrossOrigin
public class JGITcontroller {

    @ResponseBody
    @GetMapping("/persons")
    public List<FileModel> jGit(){
        List<FileModel> returned;
        File dirs = new File("C:\\repositories\\");
        List<File> files = Arrays.asList(dirs.listFiles());
        returned = files.stream().map(file -> new FileModel(file.getName(), "/persons/" + file.getName())).collect(Collectors.toList());
        return returned;
    }

    @ResponseBody
    @GetMapping(value = {"/persons/{student}", "/persons/{student}/{repo}","/persons/{student}/{repo}" })
    public List<FileModel> variable(@PathVariable String student, @PathVariable Optional<String> repo) throws GitAPIException, IOException {
        if (!repo.isPresent()){
            File dirs = new File("C:\\repositories\\" + student);
            Optional<File[]> optionalFileList = Optional.ofNullable(dirs.listFiles());
            List<FileModel> returned = new ArrayList<>();
            optionalFileList.ifPresent(files -> Arrays.stream(files)
                            .map(File -> new FileModel(File.getName(), "/persons/" + File.getName()))
                            .forEach(returned::add));
            return returned;
        } else {
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

    }
}
