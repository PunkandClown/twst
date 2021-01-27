package com.example.Viewer;

import jdk.nashorn.internal.objects.NativeString;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class ViewerApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(ViewerApplication.class, args);
	}
}
