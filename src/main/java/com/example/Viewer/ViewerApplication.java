package com.example.Viewer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

 // <link rel="stylesheet" type="text/css" href="bread/bread/cssfol.css">
@SpringBootApplication
public class ViewerApplication {

	public static void main(String[] args) throws IOException {

		SpringApplication.run(ViewerApplication.class, args);

//		try {
//			Document index = Jsoup.connect(
//					"https://raw.githubusercontent.com/PunkandClown/test/main/br/html/index.html").get();
//
//			String path = index.select("link[href]").select("[rel^=stylesheet]").attr("href");
//			System.out.println(path);
//
//			if(!path.equals("")){
//				List<String> pathList = new ArrayList<>(Arrays.asList(path.split("/")));
//				String cssFileName = pathList.get(pathList.size() - 1);
//				System.out.println(cssFileName);
//			}
//
//
//		} catch (IOException e){
//			System.out.println("fff");
//		}

//		String linkStylePath = "bread/cssfol.cs";
//		List<String> pathList = new ArrayList<>(Arrays.asList(linkStylePath.split("/")));
//		String cssFileName = pathList.get(pathList.size() - 1);
//		System.out.println(cssFileName);
//
//		if(cssFileName.contains(".css")){
//			System.out.println("yes");
//		}

	}
}
