package com.lunchlist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by vanthanhbk on 21/02/2017.
 */

public class RestaurentAdapter extends ArrayAdapter<Restaurent> {

    private Context context;
    private ArrayList<Restaurent> arr_Restaurents;

    public RestaurentAdapter(Context context, ArrayList<Restaurent> objects) {
        super(context, 0, objects);
        this.context = context;
        this.arr_Restaurents = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.item_listview,null);

        Restaurent restaurent = arr_Restaurents.get(position);
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_item_name);
        TextView tv_addr = (TextView) convertView.findViewById(R.id.tv_item_addr);
        TextView tv_type = (TextView) convertView.findViewById(R.id.tv_item_type);
        ImageView img_icon = (ImageView) convertView.findViewById(R.id.img_icon);
        TextView tv_note = (TextView) convertView.findViewById(R.id.tv_item_note);

        tv_type.setText(restaurent.getType());
        tv_name.setText(restaurent.getName());
        tv_addr.setText(restaurent.getAddress());
        tv_note.setText(restaurent.getNotes());

        return convertView;
    }
}
