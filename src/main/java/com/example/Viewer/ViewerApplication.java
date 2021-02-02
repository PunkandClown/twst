package com.example.Viewer;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.eclipse.jgit.treewalk.filter.PathFilter;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

@SpringBootApplication
public class ViewerApplication {

    public static void main(String[] args) throws IOException, GitAPIException {
        //SpringApplication.run(ViewerApplication.class, args);

        Git git = Git.open(new File("C:\\repositories\\Student1\\Task1.git"));


        Iterable<RevCommit> call = git.log().call();
        RevCommit next = call.iterator().next();

        Repository repository = git.getRepository();
        RevCommit revCommit = repository.parseCommit(next.getTree());

    }

    public static Object walker(TreeWalk treeWalk) throws IOException {

        while (treeWalk.next()) {
            if (treeWalk.isSubtree()) {
                treeWalk.enterSubtree();
                while (treeWalk.next()) {
                    if (treeWalk.isSubtree()) {
                        System.out.println("----folder---- " + treeWalk.getPathString());
                    } else {
                        System.out.println(treeWalk.getNameString());
                    }
                }
            } else {
                System.out.println(treeWalk.getNameString());
            }
        }
        return "D";
    }
}
