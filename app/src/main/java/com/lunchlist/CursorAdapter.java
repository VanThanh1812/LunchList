package com.lunchlist;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by vanthanhbk on 17/04/2017.
 */

public class CursorAdapter extends android.widget.CursorAdapter {

    private Context context;
    private Cursor c;

    public CursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
        this.c = c;
        this.context = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View row = LayoutInflater.from(context).inflate(R.layout.item_listview, null);
        RestaurantHolder holder=new RestaurantHolder(row);
        row.setTag(holder);
        return row;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        RestaurantHolder holder = (RestaurantHolder) view.getTag();
        holder.setValue(cursor);
    }

    static class RestaurantHolder{
        private TextView tv_name=null;
        private TextView tv_address=null;
        private ImageView icon=null;
        private TextView tv_type=null;
        private TextView tv_notes=null;

        public RestaurantHolder(View v){
            tv_name = (TextView) v.findViewById(R.id.tv_item_name);
            tv_address = (TextView) v.findViewById(R.id.tv_item_addr);
            tv_notes = (TextView) v.findViewById(R.id.tv_item_note);
            tv_type = (TextView) v.findViewById(R.id.tv_item_type);
        }

        public void setValue(Cursor c){
            Log.d("show", c.getString(1));
            tv_name.setText(c.getString(1).toString());
            tv_address.setText(c.getString(2).toString());
            /*tv_type.setText(c.getString(3).toString());*/
            tv_notes.setText(c.getString(4).toString());
        }
    }
}


