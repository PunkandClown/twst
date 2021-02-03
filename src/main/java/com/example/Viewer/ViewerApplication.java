package com.example.Viewer;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.dircache.DirCache;
import org.eclipse.jgit.dircache.DirCacheBuildIterator;
import org.eclipse.jgit.dircache.DirCacheIterator;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectLoader;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.revwalk.RevTree;
import org.eclipse.jgit.treewalk.TreeWalk;
import org.eclipse.jgit.treewalk.filter.PathFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

@SpringBootApplication
public class ViewerApplication {

    public static void main(String[] args){
        SpringApplication.run(ViewerApplication.class, args);
    }
}
       /* Git git = Git.open(new File("C:\\repositories\\Student1\\Task1.git"));


        Iterable<RevCommit> call = git.log().call();
        RevCommit next = call.iterator().next();

        Repository repository = git.getRepository();
        RevCommit revCommit = repository.parseCommit(next);

        TreeWalk treeWalk = new TreeWalk(repository);
        treeWalk.addTree(revCommit.getTree());
        treeWalk.setRecursive(false);

//        while (treeWalk.next()){
//            System.out.println(treeWalk.getNameString());
//        }

        String path = "src/main/resources/templates/login.html";
        String[] split = path.split("/");

        for (String s : split) {
            walker(treeWalk, s, path, repository);
        }


        while (treeWalk.next()) {
            if (treeWalk.getPathString().split("/").length > split.length) {
                System.out.println(treeWalk.getNameString());

            }
        }
    }
    public static TreeWalk walker(TreeWalk treeWalk, String str, String file, Repository repo) throws IOException {
        while (treeWalk.next()) {
            if (treeWalk.isSubtree()) {
                if (treeWalk.getNameString().equals(str)) {
                    treeWalk.enterSubtree();
                    return treeWalk;
                }
            } else if(
                treeWalk.getPathString().equals(file)){
                ObjectId objectId = treeWalk.getObjectId(0);
                ObjectLoader loader = repo.open(objectId);
                loader.copyTo(System.out);
            }
        }
        return treeWalk;
    }*/
