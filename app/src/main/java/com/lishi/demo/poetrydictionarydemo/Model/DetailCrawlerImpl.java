package com.lishi.demo.poetrydictionarydemo.Model;

import android.util.Log;

import org.jsoup.nodes.Document;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class DetailCrawlerImpl implements Crawler {
    @Override
    public void search(String serial,final OnLoadListener onLoadListener) {
        String url = "https://so.gushiwen.org/shiwenv_"+ serial + ".aspx";

        try {
            Document doc = Jsoup.connect(url).header("", "").get();
            //古诗文内容
            String contson = doc.getElementsByAttributeValue("class", "contson").get(0).text();
            //获取题目
            String title = doc.getElementsByAttribute("h1").get(0).text();
            //获取朝代与作者
            Element source = doc.getElementsByAttributeValue("class", "source").get(0);
            String time = source.getAllElements().get(0).text();
            String poet = source.getAllElements().get(1).text();
            //获取注释和赏析，逻辑比较复杂

            //有时候，翻译会有两个部分，需要加以区分
            //boolean fanyiTwo = false;

            StringBuilder shangxiSB = new StringBuilder();
            StringBuilder fanyiSB = new StringBuilder();

            Elements sonElements = doc.getElementsByAttributeValue("class", "sons");
            String baseUrl = "https://so.gushiwen.org/shiwen2017/ajax";
            for (Element element : sonElements) {
                String id = element.id();
                //使用正则表达式提取id
                String number = id.replaceAll("[^0-9]", "");
                //使用正则表达式提取模式
                //包括翻译，赏析等等
                //必须排除不需要展开的情况
                String mode = id.replaceAll("[^A-Za-z]", "");
                //contains "quan"是播放js，与内容无关
                if (!number.isEmpty()&&id.contains("fanyi")&&!id.contains("quan")) {//翻译

                    String newUrl = baseUrl + mode + ".aspx?id=" + number;
                    Document fanyiDoc = Jsoup.connect(newUrl).header("User-Agent", "chrome").get();
                    System.out.println(newUrl);
                    String fanyiItem = fanyiDoc.getElementsByAttributeValue("class","contyishang").get(0).text();
                    fanyiSB.append(fanyiItem);
                }
                else if (!number.isEmpty()&&id.contains("shangxi")&&!id.contains("quan")) {//赏析

                    String newUrl = baseUrl + mode + ".aspx?id=" + number;
                    Document shangxiDoc = Jsoup.connect(newUrl).header("User-Agent", "chrome").get();
                    System.out.println(newUrl);
                    String shangxiItem = shangxiDoc.getElementsByAttributeValue("class","contyishang").get(0).text();
                    shangxiSB.append(shangxiItem);
                }
            }

        }
        catch(IOException e){
            e.printStackTrace();
            Log.e("DetailCrawlerImpl","failed to load");
            onLoadListener.loadFailed();
        }finally {
            onLoadListener.onFinish();
        }
    }
}
