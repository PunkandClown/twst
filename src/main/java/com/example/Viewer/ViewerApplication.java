package com.example.Viewer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootApplication
public class ViewerApplication {

	public static void main(String[] args) throws IOException {
		//SpringApplication.run(ViewerApplication.class, args);

		String user = "PunkandClown";
		String directoryPath = "test";
		//Document directory = Jsoup.connect("https://github.com/" + user + "/" + directoryPath).get();

		//System.out.println(directory.select("a[title].js-navigation-open").text());

		Document index = Jsoup.connect("https://raw.githubusercontent.com/" + user + "/" + directoryPath + "/main/index.html").get();

		String path = index.select("link[href]").attr("href");

		List<String> pathList = new ArrayList<>(Arrays.asList(path.split("/")));
		String cssFileName = pathList.get(pathList.size()-1);

		System.out.println(cssFileName);

		String pathname = "C:/Users/rasbw/Desktop/students/css/" + user;

		Document css = Jsoup.connect("https://raw.githubusercontent.com/" + user + "/" + directoryPath + "/main/" + cssFileName).get();

		File dir = new File(pathname);
		if(!dir.exists()){
			dir.mkdirs();
		}

		File cssFile = new File(dir,cssFileName);
		FileWriter writer = new FileWriter(cssFile);
		writer.write(css.text());
		writer.close();

		index.getElementsByTag("link").attr("href", "css/" + user + "/" + cssFileName);

		System.out.println(index);


	}

}
