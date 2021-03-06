package com.example.tyolani.everlastingstorypro;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.StringTokenizer;


public class BookViewAdapter extends BaseAdapter {
    Context context;
    ArrayList<Chapter> chapters;
    private static LayoutInflater inflater = null;

    public BookViewAdapter(Context context, ArrayList<Chapter> chapters) {
        this.context = context;
        this.chapters = chapters;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return chapters.size();
    }

    @Override
    public Object getItem(int position) {
        return chapters.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootview = convertView;
        if (rootview == null) rootview = inflater.inflate(R.layout.chapter_item, null);

        TextView bookviewtitle = rootview.findViewById(R.id.tv_bookview_title);
        TextView bookviewcontent = rootview.findViewById(R.id.tv_bookview_content);
        ImageView lockImage = rootview.findViewById(R.id.iv_chapter_locked);

        bookviewtitle.setText("Chapter " + String.valueOf(position + 1) + ": " +  chapters.get(position).getName());
        for (int i = 0; i<chapters.get(position).getContributions().size(); i++){
            bookviewcontent.append(chapters.get(position).getContributions().get(i).getTextContent());
        }
        if (chapters.get(position).isFinished()) {
            lockImage.setImageResource(R.drawable.ic_chapter_lock);
        }

        return rootview;
    }
}
