package com.example.Viewer;

import jdk.nashorn.internal.objects.NativeString;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
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

		//SpringApplication.run(ViewerApplication.class, args);


		try {
			Document index = Jsoup.connect(
					"https://raw.githubusercontent.com/PunkandClown/test/main/br/index.html").get();

			Elements imageElements = index.getElementsByTag("link");

			Controllers.urlSubstitute(index, imageElements, "href", "https://raw.githubusercontent.com/PunkandClown/test/main/br/CSS", "PunkandClown");

			System.out.println(index);
		} catch (IOException e){
			System.out.println("fff");
		}



	}
}
