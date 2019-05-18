package com.lishi.demo.poetrydictionarydemo.item;

public class PoetryItem {
    public final String serial;
    public final String title;
    public final String source;
    public final  String content;

    public PoetryItem(String title,String source,String content,String serial){
        this.content = content;
        this.serial = serial;
        this.source = source;
        this.title = title;
    }

    @Override
    public String toString(){
        return "\n" + title + "\n" + source + "\n" + content + "\n";
    }
}
