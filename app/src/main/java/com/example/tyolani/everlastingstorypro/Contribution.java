package com.example.tyolani.everlastingstorypro;

import android.media.Image;

import java.io.Serializable;

/**
 * Created by Kevin on 2017-10-16.
 */

public class Contribution implements Serializable{

    //
    //--------------------------------- Global Variables ---------------------------------
    //
    private String imageContent;
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

    public Contribution(String content, String author1, boolean containsImage){
        if(containsImage){
            imageContent = content;
            author = author1;
            containsImageContent = true;
        }else{
            textContent = content;
            author = author1;
            containsImageContent = false;
        }
    }


    //
    //--------------------------------- Methods ---------------------------------
    //
    public void setImageContent(String i){
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
    public String getImageContent(){
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
