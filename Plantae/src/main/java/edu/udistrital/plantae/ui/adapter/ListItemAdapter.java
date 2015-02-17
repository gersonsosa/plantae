package edu.udistrital.plantae.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import edu.udistrital.plantae.R;
import edu.udistrital.plantae.ui.ListItem;

/**
 * Created by hghar on 2/12/15.
 */
public class ListItemAdapter extends BaseAdapter {

    private Context context;
    private List<ListItem> objects;

    public ListItemAdapter(Context context, ListItem[] objects) {
        this.context = context;
        this.objects = new ArrayList<>(objects.length);
        Collections.addAll(this.objects, objects);
    }

    public ListItemAdapter(Context context, List<ListItem> objects) {
        this.context = context;
        this.objects = objects;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.itemTitleView = (TextView) convertView.findViewById(R.id.item_title);
            viewHolder.itemDescriptionView = (TextView) convertView.findViewById(R.id.item_description);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ListItem listItem = objects.get(position);
        viewHolder.itemTitleView.setText(listItem.getTitle());
        viewHolder.itemDescriptionView.setText(listItem.getDescriptionText());

        return convertView;
    }

    static class ViewHolder{
        TextView itemTitleView;
        TextView itemDescriptionView;
    }
}
