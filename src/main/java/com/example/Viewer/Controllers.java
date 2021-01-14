package com.example.Viewer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

        String pathname = "C:\\Users\\rasbw\\Desktop\\students\\css";
        String cssFileName = "CSS1.css";

        File dir = new File(pathname);
        File file2 = new File(dir,cssFileName);
        FileWriter writer = new FileWriter(file2);
        writer.write(offal);
        writer.close();

        Document html = Jsoup.connect("https://raw.githubusercontent.com/PunkandClown/test/main/index.html")
                .get();

        html.getElementsByTag("link").attr("href", "\\css" + "\\" + cssFileName);

        System.out.println(html);

        return html.toString();
    }



    @GetMapping("/view")
    @ResponseBody
    public String view(){
        /// FIXME: 14.01.2021
        //Юзер из url
        String user = "PunkandClown";


        return "s";
    }
}