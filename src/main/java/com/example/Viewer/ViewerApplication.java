package com.example.Viewer;

import jdk.nashorn.internal.objects.NativeString;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
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
		SpringApplication.run(ViewerApplication.class, args);
//
//		Document index = Jsoup.connect("https://github.com/PunkandClown/test/commits/main").get();
//		Elements aElements = index.getElementsByTag("a");
//		StringBuilder sb = new StringBuilder();
//		for(Element element: aElements){
//			String result = element.select("[data-pjax]").select(".link-gray-dark").attr("href").replaceAll("\n", "");
//			String str = element.select("[data-pjax]").select(".link-gray-dark").text();
////https://raw.githubusercontent.com/PunkandClown/test/7c13d78971426deafa019fb389661092f2ffd77f/br/index.html
//			String url = "https://raw.githubusercontent.com" +result.replaceAll("/commit", "") + "br/index.html";
//
//			//PunkandClown/test/commit/7c13d78971426deafa019fb389661092f2ffd77f
//
//
//			if(!result.equals("") && !str.equals("")){
//				sb.append("{" + "\"URL\":" + '\"' + url + "\",");
//				sb.append( "\"Commit\":" + '\"' + str + "\"},\n");
//			}
//		}
//		sb.deleteCharAt(sb.length()-2);
//		System.out.println(sb);
	}
}
