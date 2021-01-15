package com.example.Viewer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootApplication
public class ViewerApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ViewerApplication.class, args);

		//Document img = Jsoup.connect("https://raw.githubusercontent.com/PunkandClown/test/main/img.jpg").get();

//		String pathname = "C:/Users/rasbw/Desktop/students/users/PunkandClown";
//		File dir = new File(pathname);
//		if(!dir.exists()){
//			dir.mkdirs();
//		}
//		File cssFile = new File(dir,"img.jpg");
//		FileWriter writer = new FileWriter(cssFile);
//		writer.write(img.text());
//		writer.close();
	}
}
