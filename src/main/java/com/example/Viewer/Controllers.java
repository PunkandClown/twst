package com.example.Viewer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class Controllers {

    @GetMapping("/greeting")
    @ResponseBody
    public String greeting() throws IOException {

        Document css = Jsoup.connect("https://raw.githubusercontent.com/PunkandClown/test/main/CSS1.css")
                .get();

        String offal = css.text();

        System.out.println(offal);

        String pathname = "C://Users/rasbw/Desktop/students/css";
        String cssFileName = "CSS1.css";

        File dir = new File(pathname);
        File file2 = new File(dir,cssFileName);
        FileWriter writer = new FileWriter(file2);
        writer.write(offal);
        writer.close();

        Document html = Jsoup.connect("https://raw.githubusercontent.com/PunkandClown/test/main/index.html")
                .get();

        html.getElementsByTag("link").attr("href", "css" + "/" + cssFileName);

        System.out.println(html);

        return html.toString();
    }



    @GetMapping("/view/{user}/{directoryPath}")
    @ResponseBody
    public String view(@PathVariable String user, @PathVariable String directoryPath) throws IOException {

//        Document directory = Jsoup.connect("https://raw.githubusercontent.com/" + user + "/" + directoryPath).get();
//        System.out.println(directory.select("a.js-navigation-open link-gray-dark"));

        //Document directory = Jsoup.connect("https://github.com/" + user + "/" + directoryPath).get();

        //System.out.println(directory.select("a[title].js-navigation-open").text());

        Document index = Jsoup.connect("https://raw.githubusercontent.com/" + user + "/" + directoryPath + "/main/index.html").get();

        String path = index.select("link[href]").attr("href");
        List<String> pathList = new ArrayList<>(Arrays.asList(path.split("/")));
        String cssFileName = pathList.get(pathList.size()-1);


        Document css = Jsoup.connect("https://raw.githubusercontent.com/" + user + "/" + directoryPath + "/main/" + cssFileName).get();

        String pathname = "C:/Users/rasbw/Desktop/students/users/" + user;
        File dir = new File(pathname);
        if(!dir.exists()){
            dir.mkdirs();
        }
        File cssFile = new File(dir,cssFileName);
        FileWriter writer = new FileWriter(cssFile);
        writer.write(css.text());
        writer.close();

        index.getElementsByTag("link").attr("href", "/users/" + user + "/" + cssFileName);

        System.out.println(index);

        return index.toString();
    }
}