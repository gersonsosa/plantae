package edu.udistrital.plantae.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import edu.udistrital.plantae.R;
import edu.udistrital.plantae.ui.ListItem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Gerson Sosa on 4/10/14.
 */
public class ListItemCheckAdapter extends ArrayAdapter<ListItem> {

    private Context context;
    private View.OnClickListener onClickListener;
    private List<ListItem> objects;

    public ListItemCheckAdapter(Context context, int resource, View.OnClickListener onClickListener, List<ListItem> objects) {
        super(context, resource, objects);
        this.context = context;
        this.onClickListener = onClickListener;
        this.objects = objects;
    }

    public ListItemCheckAdapter(Context context, int resource, View.OnClickListener onClickListener, ListItem[] objects) {
        super(context, resource, objects);
        this.context = context;
        this.onClickListener = onClickListener;
        this.objects = new ArrayList<>();
        Collections.addAll(this.objects, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.list_item_check, null);
            viewHolder = new ViewHolder();
            viewHolder.itemTitleView = (TextView) convertView.findViewById(R.id.item_title);
            viewHolder.checkBoxView = (CheckBox) convertView.findViewById(R.id.checkbox);
            viewHolder.itemDescriptionView = (TextView) convertView.findViewById(R.id.item_description);
            viewHolder.subitemCountView = (TextView) convertView.findViewById(R.id.subitem_count);
            viewHolder.itemLocatedView = (ImageView) convertView.findViewById(R.id.item_located);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ListItem listItem = objects.get(position);
        viewHolder.checkBoxView.setChecked(listItem.isChecked());
        viewHolder.itemTitleView.setText(listItem.getTitle());
        viewHolder.itemDescriptionView.setText(listItem.getDescriptionText());
        viewHolder.subitemCountView.setText(listItem.getSubitemCount());
        viewHolder.itemLocatedView.setSelected(listItem.isLocated());
        viewHolder.checkBoxView.setOnClickListener(onClickListener);

        return convertView;
    }

    static class ViewHolder{
        TextView itemTitleView;
        CheckBox checkBoxView;
        TextView itemDescriptionView;
        TextView subitemCountView;
        ImageView itemLocatedView;
    }

}
