package com.example.inlocker;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/*
As I have a layout for the list items, this class is to receive values from activity_stores.java,
display the values using the layout store_list_layout.xml
 */

public class storeListView extends ArrayAdapter<String> {

    private String[] storeName;
    private Integer[] imgID;
    private Activity context;

    public storeListView(Activity context, String[] storeName, Integer[] imgID) {
        super(context, R.layout.store_list_layout, storeName);

        this.context = context;
        this.storeName = storeName;
        this.imgID = imgID;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View r = convertView;
        ViewHolder viewHolder = null;
        if (r == null) {
            LayoutInflater layoutInflater = context.getLayoutInflater();
            r = layoutInflater.inflate(R.layout.store_list_layout, null, true);

            viewHolder = new ViewHolder(r);
            r.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) r.getTag();
        }
        viewHolder.ivw.setImageResource(imgID[position]);
        viewHolder.tvw1.setText(storeName[position]);

        return r;
    }

    class ViewHolder {
        TextView tvw1;
        ImageView ivw;

        ViewHolder(View v) {
            tvw1 = v.findViewById(R.id.store_name);
            ivw = v.findViewById(R.id.store_image);
        }
    }

}
