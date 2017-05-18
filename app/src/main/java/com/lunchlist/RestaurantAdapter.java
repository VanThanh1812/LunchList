package com.lunchlist;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by vanthanhbk on 21/02/2017.
 */

public class RestaurantAdapter extends ArrayAdapter<Items> {

    private Context context;
    private ArrayList<Items> arr_Itemses;
    private RadioGroup rdGroup;
    public RestaurantAdapter(Context context, ArrayList<Items> objects) {
        super(context, 0, objects);
        this.context = context;
        this.arr_Itemses = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(context).inflate(R.layout.item_listview,null);

        Items items = arr_Itemses.get(position);
        if ((position % 2) == 0){
            convertView.setBackgroundColor(Color.parseColor("#4fc3f7"));
        }else{
            convertView.setBackgroundColor(Color.parseColor("#e57373"));
        }
        TextView tv_name = (TextView) convertView.findViewById(R.id.tv_item_name);
        TextView tv_addr = (TextView) convertView.findViewById(R.id.tv_item_addr);
        TextView tv_type = (TextView) convertView.findViewById(R.id.tv_item_type);
        ImageView img_icon = (ImageView) convertView.findViewById(R.id.img_icon);
        TextView tv_note = (TextView) convertView.findViewById(R.id.tv_item_note);

        if (items.getId_giamgia().equals( "Giảm 25%")){
            img_icon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_favourist));
        }else if (items.getId_giamgia().equals("Giảm 50%")){
            img_icon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_info));
        }else {
            img_icon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_ignore));
        }

        tv_type.setText(items.getUnit_price());
        tv_name.setText(items.getName());
        tv_addr.setText(items.getNumber());
        tv_note.setText(items.getBarCode());

        return convertView;
    }
}
