package com.example.tyolani.everlastingstorypro;

import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Kevin on 2017-10-16.
 */

public class Chapter {

    //
    //--------------------------------- Global Variables ---------------------------------
    //
    private ArrayList<Contribution> contributions = new ArrayList<Contribution>();
    private boolean isFinished;
    private String name;

    //
    //--------------------------------- Contructors ---------------------------------
    //
    public Chapter(Contribution cont, String newname){
        contributions = new ArrayList<Contribution>();
        contributions.add(cont);
        name = newname;
        isFinished = false;
    }
    public Chapter(String newname){
        name = newname;
        isFinished = false;
    }
    public Chapter(){
        //Just to be able to initialize a chapter without any contribution
        isFinished = false;
    }

    //
    //--------------------------------- Methods ---------------------------------
    //
    public ArrayList<Contribution> getContributions(){
        return contributions;
    }
    //Concats all of the contributions to a string and returns it, concats images just as "Here goes image X".
    public String getText(){
        String tempString = "";

        for (int i = 0; i<contributions.size(); i++) {

            if(!contributions.get(i).isImageContribution()){
                tempString +=(contributions.get(i).getTextContent());
            }
            else{
                tempString +=("Here goes image " + i + ".");
            }
            tempString +=(contributions.get(i).getTextContent());
        }

        return tempString;
    }
    public ArrayList<String> getAuthors(){
        ArrayList<String> tempAuthors = new ArrayList<String>();
        for(int i = 0; i < contributions.size(); i++){
            if(!tempAuthors.contains(contributions.get(i).getAuthor())){
                tempAuthors.add(contributions.get(i).getAuthor());
            }
        }
        return tempAuthors;
    }
    public int getNumberOfAuthors(){
        ArrayList<String> tempAuthors = new ArrayList<String>();
        for(int i = 0; i < contributions.size(); i++){
            if(!tempAuthors.contains(contributions.get(i).getAuthor())){
                tempAuthors.add(contributions.get(i).getAuthor());
            }
        }
        return tempAuthors.size();
    }
    public int getNumberOfContributions(){
        return contributions.size();
    }
    public int getPageCount(){
        //Here goes fancy math to calculate the pagecount depending on the arraylist of contributions
        return 1337;
    }
    public String getName(){
        return name;
    }
    public boolean isFinished(){
        return isFinished;
    }
    //Returns true if it was able to close the chapter and it had more than 4 contributions, else return false
    public boolean closeChapter(){
        if(contributions.size() >= 4){
            isFinished = true;
            return true;
        }
        else{
            return false;
        }
    }
    //Returns true if success, false if the chapter is closed
    public boolean addContribution(Contribution c){
        //If chapter is not closed, add a contribution
        if((!isFinished) && canContribute(c.getAuthor())){
            contributions.add(c);

            return true;
        }
        else{
            return false;
        }
    }
    public boolean canContribute(String autorName){
        if(contributions.get(contributions.size()-1).getAuthor().equals(autorName)){
            return false;
        }
        else{
            return true;
        }
    }







}
