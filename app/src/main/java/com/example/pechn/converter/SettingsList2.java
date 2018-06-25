package com.example.pechn.converter;

public class SettingsList2 {
    private  String text1;
    private  String text2;
    public static boolean box;

    public SettingsList2(String text1, String text2, boolean box){
        this.text1=text1;
        this.text2=text2;
        this.box=box;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public boolean isBox() {
        return box;
    }

    public void setBox(boolean box) {
        this.box = box;
    }
}
