package com.example.Viewer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@Controller
public class CommitsController {

    @GetMapping("commits/{user}/{directory}/{branch}/{task}")
    @ResponseBody
    public String commits(@PathVariable String user,
                          @PathVariable String directory,
                          @PathVariable String branch,
                          @PathVariable String task) throws IOException {

        Document index = Jsoup.connect("https://github.com/"+ user + "/"+directory+"/commits/" + branch).get();
        Elements aElements = index.getElementsByTag("a");

        StringBuilder sb = new StringBuilder();

        for(Element element: aElements){
            String result = element.select("[data-pjax]").select(".link-gray-dark").attr("href").replaceAll("\n", "");
            String str = element.select("[data-pjax]").select(".link-gray-dark").text();
            String url = "https://raw.githubusercontent.com" +result.replaceAll("/commit", "") + "/" + task + "/index.html";

            if(!result.equals("") && !str.equals("")){
                sb.append("{" + "\"URL\":" + '\"' + url + "\",");
                sb.append( "\"Commit\":" + '\"' + str + "\"},\n");
            }
        }
        sb.deleteCharAt(sb.length()-2);

        return sb.toString();
    }











}
