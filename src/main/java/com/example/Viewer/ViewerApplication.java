package com.example.Viewer;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.dircache.DirCache;
import org.eclipse.jgit.dircache.DirCacheBuildIterator;
import org.eclipse.jgit.dircache.DirCacheIterator;
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
        RevCommit revCommit = repository.parseCommit(next);

        TreeWalk treeWalk = new TreeWalk(repository);
        treeWalk.addTree(revCommit.getTree());
        treeWalk.setRecursive(false);
        //treeWalk.setFilter(PathFilter.create("src"));

//        while (treeWalk.next()){
//            System.out.println(
//                    treeWalk.getPathString());
//        }

        String path = "src/main/resources";
        String[] split = path.split("/");
        int iterator = 0;
        while (treeWalk.next()) {
            if (treeWalk.isSubtree()) {
                if (treeWalk.getNameString().equals(split[iterator])) {
                    treeWalk.enterSubtree();
                    iterator++;
                    if (iterator == split.length) {
                        while (treeWalk.next()) {
                            System.out.println(treeWalk.getNameString());
                        }
                    }
                }

            }
        }


    }

    public static Object walker(Repository repo, TreeWalk treeWalk) throws IOException {
        while (treeWalk.next()) {
            if (treeWalk.isSubtree()) {
                treeWalk.enterSubtree();
                while (treeWalk.next()) {
                    if (treeWalk.isSubtree()) {
                        DirCache i = repo.lockDirCache();
                        return new DirCacheIterator(i);
                    } else {
                        System.out.println(treeWalk.getNameString());
                    }
                }
            } else {
                System.out.println(treeWalk.getNameString());
            }
        }
        return "s";
    }
}
