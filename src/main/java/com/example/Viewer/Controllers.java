package com.example.Viewer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class Controllers {

    String pathname = "C:/Users/rasbw/Desktop/viewerbase/users/";

    @GetMapping("/view/{user}/{directory}/{branch}/{task}/{page}")
    @ResponseBody

    public String view(@PathVariable String user,
                       @PathVariable String directory,
                       @PathVariable String branch,
                       @PathVariable String task,
                       @PathVariable String page) throws IOException {
        try {
            Document index = Jsoup.connect(
                    "https://raw.githubusercontent.com/"
                            + user + "/"
                            + directory + "/"
                            + branch + "/"
                            + task + "/"
                            + page ).get();

            String linkStylePath = index.select("link[href]").select("[rel^=stylesheet]").attr("href");

            if (!linkStylePath.equals("")) {

                List<String> pathList = new ArrayList<>(Arrays.asList(linkStylePath.split("/")));
                String cssFileName = pathList.get(pathList.size() - 1);

                if(cssFileName.contains(".css")){

                    Document css = Jsoup.connect(
                            "https://raw.githubusercontent.com/"
                                    + user + "/"
                                    + directory + "/"
                                    + branch + "/"
                                    + task + "/CSS/" + cssFileName).get();

                    File dir = new File(pathname + user);
                    if (!dir.exists()) {
                        dir.mkdirs();
                    }
                    File cssFile = new File(dir, cssFileName);
                    FileWriter writer = new FileWriter(cssFile);
                    writer.write(css.text());
                    writer.close();

                    index.getElementsByTag("link").first().attr("href", "/users/" + user + "/" + cssFileName);
                }
            }
            Elements aElements = index.getElementsByTag("a");
            for (Element aElement : aElements){


            }

            Elements imageElements = index.getElementsByTag("img");

            for (Element imgElement : imageElements) {

                String src = imgElement.attr("src");
                List<String> imgPathList = new ArrayList<>(Arrays.asList(src.split("/")));
                String imgFileName = imgPathList.get(imgPathList.size() - 1);

                String imageUrl =
                        "https://raw.githubusercontent.com/" + user + "/"
                                + directory + "/"
                                + branch + "/"
                                + task + "/img/"
                                + imgFileName;

                imageDownloader(imageUrl, user, imgFileName, pathname);
                index.getElementsByTag("img").attr("src", "/users/" + user + "/" + imgFileName);
            }
            System.out.println(index);
            return index.toString();

        } catch (IOException e) {
            return "html not found";
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