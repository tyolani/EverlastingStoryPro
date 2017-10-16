package com.example.tyolani.everlastingstorypro;

import android.media.Image;

import java.util.ArrayList;

/**
 * Created by hedholm on 2017-10-12.
 */

public class Book {

    private int mContributorCount;
    private int mPageCount;
    private String mOverview;
    private String mTitle;
    private ArrayList<Chapter> mChapters; //TODO: Should take Chapter objects.

    public Book(String overview, String title) {
        this.mOverview = overview;
        this.mTitle = title;
    }
    public Book(String title){
        mTitle = title;
    }
    public Book(){
        // Just an empty book
    }

    /**
     * Increases the contributor count by 1
     */
    public void newContributor() {
        this.mContributorCount += 1;
    }

    public boolean addNewChapter(Chapter chapterToAdd){
        return mChapters.add(chapterToAdd);
    }
    public boolean createNewChapter(String name, String initialText, String author){
        Contribution tempContribution = new Contribution(initialText);
        Chapter tempChapter = new Chapter(author,tempContribution,name);
        return mChapters.add(tempChapter);
    }
    public boolean createNewChapter(String name, Image initialImage, String author){
        Contribution tempContribution = new Contribution(initialImage);
        Chapter tempChapter = new Chapter(author,tempContribution,name);
        return mChapters.add(tempChapter);
    }
    public int getChapterIndex(String chapterName){
        for(int i = 0; i < mChapters.size(); i++){
            if(mChapters.get(i).getName().equals(chapterName)){
                return i;
            }
        }
        return -1;
    }


}
