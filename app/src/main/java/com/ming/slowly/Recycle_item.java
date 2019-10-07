package com.ming.slowly;

/**
 * Created by 鸣 on 2018/2/22.
 */

public class Recycle_item {

    private int imageId;
    private String text;
    public Recycle_item(String text, int imageId){
        this.imageId=imageId;
        this.text= text;
    }

    public String getText() {
        return text;
    }

    public int getImageId() {
        return imageId;
    }
}
