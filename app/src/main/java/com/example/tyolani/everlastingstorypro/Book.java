package com.example.tyolani.everlastingstorypro;

import java.util.ArrayList;

/**
 * Created by hedholm on 2017-10-12.
 */

public class Book {

    private int mContributorCount;
    private int mPageCount;
    private String mOverview;
    private String mTitle;
    private ArrayList<?> mChapters; //TODO: Should take Chapter objects.

    public Book(String overview, String title) {
        this.mOverview = overview;
        this.mTitle = title;
    }

    /**
     * Increases the contributor count by 1
     */
    public void newContributor() {
        this.mContributorCount += 1;
    }
}
