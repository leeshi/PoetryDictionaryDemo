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

            StringBuilder shangxiSB = new StringBuilder();
            StringBuilder fanyiSB = new StringBuilder();
            String backGround = new String();

            Elements sonElements = doc.getElementsByAttributeValue("class", "sons");
            String baseUrl = "https://so.gushiwen.org/shiwen2017/ajax";
            for (Element element : sonElements) {
                if(element.getElementsByTag("h2").text().contains("背景")) {
                    backGround = element.getElementsByAttributeValue("class","contyishang").text();
                    continue;
                }else if(element.getElementsByTag("h2").text().contains("译文")&&!element.text().contains("展开")) {//提取不需要展开的信息
                    fanyiSB.append(element.getElementsByAttributeValue("class","contyishang").text());
                    continue;
                }else if(element.getElementsByTag("h2").text().contains("赏析")&&!element.text().contains("展开")) {//提取不需要展开的信息
                    shangxiSB.append(element.getElementsByAttributeValue("class","contyishang").text());
                    continue;
                }


                String id = element.id();
                //使用正则表达式提取id
                String number = id.replaceAll("[^0-9]", "");
                //使用正则表达式提取模式
                //包括翻译，赏析等等
                //必须排除不需要展开的情况
                String mode = id.replaceAll("[^A-Za-z]", "");
                //contains "quan"是播放js，与内容无关
                if (!number.isEmpty()&&id.contains("fanyi")&&!id.contains("quan")) {
                    String newUrl = baseUrl + mode + ".aspx?id=" + number;
                    //添加索引，不然会被封
                    Document fanyiDoc = Jsoup.connect(newUrl).header("User-Agent", "chrome").referrer(url).cookie("ASP.NET_SessionId","bqodoezoibimiwe4arvepsip").get();
                    System.out.println(newUrl);
                    String fanyiItem = fanyiDoc.getElementsByAttributeValue("class","contyishang").get(0).text();
                    fanyiSB.append(fanyiItem);
                }
                else if (!number.isEmpty()&&id.contains("shangxi")&&!id.contains("quan")) {

                    String newUrl = baseUrl + mode + ".aspx?id=" + number;
                    Document shangxiDoc = Jsoup.connect(newUrl).header("User-Agent", "chrome").referrer(url).get();
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
