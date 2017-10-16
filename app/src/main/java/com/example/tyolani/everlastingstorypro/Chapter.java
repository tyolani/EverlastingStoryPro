package com.example.tyolani.everlastingstorypro;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Kevin on 2017-10-16.
 */

public class Chapter {

    //
    //--------------------------------- Global Variables ---------------------------------
    //
    private ArrayList<Contribution> contributions;
    private boolean isFinished;
    private ArrayList<String> authors;
    private String name;

    //
    //--------------------------------- Contructors ---------------------------------
    //
    public Chapter(String author, Contribution cont, String newname){
        authors.add(author);
        contributions.add(cont);
        name = newname;
        isFinished = false;
    }
    public Chapter(String author, String newname){
        authors.add(author);
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
        String tempString = "<br>";

        for (int i = 0; i<contributions.size(); i++) {
            if(!contributions.get(i).isImageContribution()){
                tempString.concat(contributions.get(i).getTextContent());
            }
            else{
                tempString.concat("Here goes image " + i + ".");
            }
            tempString.concat("<br><br>");
        }

        return tempString;
    }
    public ArrayList<String> getAuthors(){
        return authors;
    }
    public int getNumberOfAuthors(){
        return authors.size();
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
    public void finishChapter(){
        isFinished = true;
    }
    //Returns true if success, false if the chapter is closed
    public boolean addContribution(Contribution c, String a){
        //If chapter is not closed, add a contribution
        if(!isFinished){
            contributions.add(c);
            authors.add(a);

            return true;
        }
        else{
            return false;
        }
    }







}
