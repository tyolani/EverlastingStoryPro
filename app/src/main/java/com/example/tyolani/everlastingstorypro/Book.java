package com.example.tyolani.everlastingstorypro;

import android.media.Image;

import java.util.ArrayList;

/**
 * Created by hedholm on 2017-10-12.
 */

public class Book {

    private int mContributorCount;
    private int mPageCount;
    private String mGenre;
    private String mOverview;
    private String mTitle;
    private ArrayList<Chapter> mChapters;

    public Book(String overview, String genre, String title) {
        mGenre = genre;
        this.mOverview = overview;
        this.mTitle = title;
<<<<<<< HEAD
        mChapters = new ArrayList<Chapter>();
    }
    public Book(String genre, String title){
        mGenre = genre;
        mTitle = title;
        mChapters = new ArrayList<Chapter>();
=======
        newContributor();
    }
    public Book(String title){
        this.mTitle = title;
>>>>>>> 21e3c0c363b51b45295e2f043a094ee9e330b4ac
    }
    public Book(){
        // Just an empty book
        mChapters = new ArrayList<Chapter>();
    }

    /**
     * Increases the contributor count by 1
     */
    public void newContributor() {
        this.mContributorCount += 1;
    }

    public boolean addNewChapter(Chapter chapterToAdd){
        if(getNumberOfOpenChapters() <= 3){
            return mChapters.add(chapterToAdd);
        }
        else {
            return false;
        }
    }

    public boolean createNewChapter(String name, String initialText, String author){
        if(getNumberOfOpenChapters() <= 3){
            Contribution tempContribution = new Contribution(initialText, author);
            Chapter tempChapter = new Chapter(tempContribution,name);
            return mChapters.add(tempChapter);
        }
        else {
            return false;
        }
    }

    public boolean createNewChapter(String name, Image initialImage, String author){
        if(getNumberOfOpenChapters() <= 3){
            Contribution tempContribution = new Contribution(initialImage, author);
            Chapter tempChapter = new Chapter(tempContribution,name);
            return mChapters.add(tempChapter);
        }
        else {
            return false;
        }
    }

    public int getChapterIndex(String chapterName){
        for(int i = 0; i < mChapters.size(); i++){
            if(mChapters.get(i).getName().equals(chapterName)){
                return i;
            }
        }
        return -1;
    }
    //Returns the number of pages of all chapters combined
    public int getPageCount(){
        int totalPages = 0;
        for(int i = 0; i < mChapters.size(); i++){
            totalPages += mChapters.get(i).getPageCount();
        }
        return totalPages;
    }
    public int getAuthorCount() {
        int totalAuthors = 0;

        for (Chapter c : mChapters) {
            totalAuthors += c.getNumberOfAuthors();
        }
        return totalAuthors;
    }
    public int getNumberOfOpenChapters(){
        int totalOpen = 0;
        for(int i = 0; i < mChapters.size(); i++){
            if(!mChapters.get(i).isFinished()){
                totalOpen++;
            }
        }
        return totalOpen;
    }
<<<<<<< HEAD
    public int getNumberOfAuthors(){
        int totalAuthors = 0;
        for(int i = 0; i < mChapters.size(); i++){
            totalAuthors += mChapters.get(i).getNumberOfAuthors();
        }
        return totalAuthors;
=======
    public String getBookOverview() {
        return this.mOverview;
    }
    public String getBookTitle() {
        return this.mTitle;
    }

    public ArrayList<Chapter> getChapters() {
        return this.mChapters;
>>>>>>> 21e3c0c363b51b45295e2f043a094ee9e330b4ac
    }

    public ArrayList<Chapter> getmChapters(){
        return mChapters;
    }

    public String getGenre(){
        return mGenre;
    }
    public String getTitle(){
        return mTitle;
    }
    public String getOverview(){
        return mOverview;
    }
    public void setTitle(String s){
        mTitle = s;
    }
    public void setOverview(String s){
        mOverview = s;
    }
    public void setGenre(String s){
        mGenre = s;
    }
}
