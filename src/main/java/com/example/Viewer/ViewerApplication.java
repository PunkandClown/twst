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
		//SpringApplication.run(ViewerApplication.class, args);

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

		imageDownloader("https://raw.githubusercontent.com/PunkandClown/test/main/img.jpg");
	}
	public static void imageDownloader(String imageUrl, String user, String) throws IOException {
		String imgName = imageUrl.substring(imageUrl.lastIndexOf("/")+1);

		URL url = new URL(imageUrl);
		InputStream is = url.openStream();

		byte[] buffer = new byte[12430];
		int n = -1;
		OutputStream os =
				new FileOutputStream(  "C:/Users/rasbw/Desktop/students/users/PunkandClown" + "/" + strImageName );





	}



	private static void downloadImage(String strImageURL){

		//get file name from image path
		String strImageName =
				strImageURL.substring( strImageURL.lastIndexOf("/") + 1 );

		System.out.println("Saving: " + strImageName + ", from: " + strImageURL);

		try {

			//open the stream from URL
			URL urlImage = new URL(strImageURL);
			InputStream in = urlImage.openStream();

			byte[] buffer = new byte[4096];
			int n = -1;

			OutputStream os =
					new FileOutputStream(  "C:/Users/rasbw/Desktop/students/users/PunkandClown" + "/" + strImageName );

			//write bytes to the output stream
			while ( (n = in.read(buffer)) != -1 ){
				os.write(buffer, 0, n);
			}

			//close the stream
			os.close();

			System.out.println("Image saved");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}








}
