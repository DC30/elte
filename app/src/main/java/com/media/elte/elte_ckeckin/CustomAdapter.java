package com.media.elte.elte_ckeckin;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by wstev on 4/11/2016.
 */
public class CustomAdapter extends ArrayAdapter<String>{

    private final Activity context;
    private final String[] text;
    private final Integer[] imageId;
    public CustomAdapter(Activity context,
                         String[] text, Integer[] imageId) {
        super(context, R.layout.list_single, text);
        this.context = context;
        this.text = text;
        this.imageId = imageId;

    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_single, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
        txtTitle.setTextSize(22);
        txtTitle.setGravity(Gravity.CENTER);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText(" " + text[position]);

        imageView.setImageResource(imageId[position]);
        return rowView;
    }
}