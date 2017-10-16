package com.example.tyolani.everlastingstorypro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/** Adapter for the Overview activity.
 *
 * Used to fill the overview activity's listview with closed, or open, rows.
 *
 * Created by hedholm on 2017-10-16.
 */

public class OverviewAdapter extends BaseAdapter {

    Context context;
    ArrayList<Chapter> chapters;
    private static LayoutInflater inflater = null;

    public OverviewAdapter(Context context, ArrayList<Chapter> chapters) {
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
        View vi = convertView;
        if (vi == null) {
            if (chapters.get(position).isFinished()) {
                vi = inflater.inflate(R.layout.row_chapter_closed, null);
            } else {
                vi = inflater.inflate(R.layout.row_chapter_open, null);
            }
        }
        TextView chapter_id = vi.findViewById(R.id.row_chapter_closed_id);
        TextView chapter_name = vi.findViewById(R.id.row_chapter_closed_name);

        String chapter = chapters.get(position).getName();

        chapter_id.setText(String.valueOf(position + 1));
        chapter_name.setText(chapter);
        return vi;
    }
}
