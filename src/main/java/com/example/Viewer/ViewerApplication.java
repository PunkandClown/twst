package com.example.Viewer;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.revwalk.RevWalk;
import org.eclipse.jgit.storage.file.FileRepositoryBuilder;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;

@SpringBootApplication
public class ViewerApplication {

    public static void main(String[] args) throws IOException {
        //SpringApplication.run(ViewerApplication.class, args);

        File directory = Git.open(new File("C:\\repositories\\root\\Student1.git")).getRepository().getDirectory();
//
//        ObjectId lastCommit = repo.resolve(Constants.HEAD);
//
//        try (RevWalk revWalk = new RevWalk(repo)) {
//            RevCommit commit = revWalk.parseCommit(lastCommit);
//            RevTree tree = commit.getTree();
//            System.out.println("Having tree: " + tree);
//        }
    }
}
