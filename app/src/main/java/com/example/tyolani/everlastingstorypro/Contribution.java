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

    //
    //--------------------------------- Contructors ---------------------------------
    //
    public Contribution(){
        imageContent = null;
        textContent = "";
        containsImageContent = false;
    }
    public Contribution(Image i){
        imageContent = i;
        textContent = "";
        containsImageContent = true;
    }
    public Contribution(String s){
        imageContent = null;
        textContent = s;
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
    public Image getImageContent(){
        return imageContent;
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
