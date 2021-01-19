package com.example.Viewer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.*;
import java.net.URL;

@Controller
public class ViewerController {

    static String pathname = "C:/Users/user/Desktop/viewerbase/users/";

    @GetMapping("/view/{user}/{directory}/{branch}/{task}/{page}")
    @ResponseBody

    public String view(@PathVariable String user,
                       @PathVariable String directory,
                       @PathVariable String branch,
                       @PathVariable String task,
                       @PathVariable String page){

        String url = "https://raw.githubusercontent.com/"
                + user + "/"
                + directory + "/"
                + branch + "/"
                + task + "/";
        try {
            Document index = Jsoup.connect( url + page ).get();

            Elements css = index.getElementsByTag("link");
            urlSubstitute(css, "href", url + "CSS", user);

            Elements aElements = index.getElementsByTag("a");
            for (Element aElement : aElements){
                String href = aElement.attr("href");
                if(href.contains("/")){
                    String fileName = href.substring(href.lastIndexOf("/"));
                    aElement.attr("href", fileName);
                }
            }

            Elements imageElements = index.getElementsByTag("img");
            for (Element imgElement : imageElements) {

                String src = imgElement.attr("src");
                String fileName = src.substring(src.lastIndexOf("/"));

                String imageUrl = url + "img/" + fileName;

                imageDownloader(imageUrl, user, fileName, pathname);
                imgElement.attr("src", "/users/" + user + fileName);
            }

            return index.toString();

        } catch (IOException e) {
            return "html not found";
        }
    }

    public static void urlSubstitute(Elements elements, String link, String url, String user) throws IOException {
        for (Element element : elements) {

            String href = element.attr(link);
            String fileName = href.substring(href.lastIndexOf("/"));
            Document document = Jsoup.connect(url + fileName).get();

            File dir = new File(pathname + user);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(dir, fileName);
            FileWriter writer = new FileWriter(file);
            writer.write(document.text());
            writer.close();

            element.attr(link, "/users/" + user + fileName);
        }
    }

    public static void imageDownloader(String imageUrl, String user, String imgName, String pathname) throws IOException {

        URL url = new URL(imageUrl);

        InputStream is = url.openStream();
        byte[] buffer = new byte[12430];
        int n = -1;

        OutputStream os =
                new FileOutputStream(pathname + "/" + user + "/" + imgName);
        while ((n = is.read(buffer)) != -1) {
            os.write(buffer, 0, n);
        }
        os.close();
    }
}