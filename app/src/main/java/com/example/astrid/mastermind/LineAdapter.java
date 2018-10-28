package com.example.astrid.mastermind;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by Astrid.
 */

public class LineAdapter extends ArrayAdapter<Line> {
    LineAdapter(Context context, ArrayList<Line> lines){
        super(context,0,lines);
    }

    @SuppressLint("ViewHolder")
    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent){
        Line line = getItem(position);
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.line_item,parent, false);

        Button btncase1 = convertView.findViewById(R.id.itemcase1);
        Button btncase2 = convertView.findViewById(R.id.itemcase2);
        Button btncase3 = convertView.findViewById(R.id.itemcase3);
        Button btncase4 = convertView.findViewById(R.id.itemcase4);
        Button btnrb1 = convertView.findViewById(R.id.itemrb1);
        Button btnrb2 = convertView.findViewById(R.id.itemrb2);
        Button btnrb3 = convertView.findViewById(R.id.itemrb3);
        Button btnrb4 = convertView.findViewById(R.id.itemrb4);
        if (line != null) {
            btncase1.setBackground(line.getDrawableCase1());
            btncase2.setBackground(line.getDrawableCase2());
            btncase3.setBackground(line.getDrawableCase3());
            btncase4.setBackground(line.getDrawableCase4());
            btnrb1.setBackground(line.getDrawableVerif1());
            btnrb2.setBackground(line.getDrawableVerif2());
            btnrb3.setBackground(line.getDrawableVerif3());
            btnrb4.setBackground(line.getDrawableVerif4());
        }

        return convertView;
    }
}
