package com.example.tyolani.everlastingstorypro;

import android.media.Image;

/**
 * Created by Kevin on 2017-10-16.
 */

public class Contribution {

    //
    //--------------------------------- Global Variables ---------------------------------
    //
    private Image imageContent;
    private String textContent;
    private boolean containsImageContent;
    private String author;

    //
    //--------------------------------- Contructors ---------------------------------
    //
    public Contribution(){
        imageContent = null;
        textContent = "";
        author = "";
        containsImageContent = false;
    }
    public Contribution(Image i, String a){
        imageContent = i;
        textContent = "";
        author = a;
        containsImageContent = true;
    }
    public Contribution(String s, String a){
        imageContent = null;
        textContent = s;
        author = a;
        containsImageContent = false;
    }


    //
    //--------------------------------- Methods ---------------------------------
    //
    public void setImageContent(Image i){
        imageContent = i;
        containsImageContent = true;
    }
    public void setTextContent(String s){
        textContent = s;
        containsImageContent = false;
    }
    public void setAuthor(String a){
        author = a;
    }
    public Image getImageContent(){
        return imageContent;
    }
    public String getAuthor(){
        return author;
    }
    public String getTextContent(){
        if(!containsImageContent){
            return textContent;
        }
        else{
            return null;
        }
    }
    public boolean isImageContribution(){
        return containsImageContent;
    }



}
