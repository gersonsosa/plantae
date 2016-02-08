package edu.udistrital.plantae.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import edu.udistrital.plantae.R;
import edu.udistrital.plantae.ui.ListItem;

/**
 * Created by Gerson Sosa on 4/10/14.
 */
public class ListItemImageAdapter extends BaseAdapter {

    private Context context;
    private View.OnClickListener onClickListener;
    private List<ListItem> objects;

    public ListItemImageAdapter(Context context, List<ListItem> objects, View.OnClickListener onClickListener) {
        this.context = context;
        this.objects = objects;
        this.onClickListener = onClickListener;
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
            convertView = mInflater.inflate(R.layout.list_item_image, null);
            viewHolder = new ViewHolder();
            viewHolder.itemTitleView = (TextView) convertView.findViewById(R.id.item_title);
            viewHolder.itemImageView = (ImageView) convertView.findViewById(R.id.item_image);
            viewHolder.itemDescriptionView = (TextView) convertView.findViewById(R.id.item_description);
            viewHolder.subitemCountView = (TextView) convertView.findViewById(R.id.subitem_count);
            viewHolder.itemLocatedView = (ImageView) convertView.findViewById(R.id.item_located);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ListItem listItem = objects.get(position);
        viewHolder.itemImageView.setImageResource(listItem.getImage());
        viewHolder.itemTitleView.setText(listItem.getTitle());
        viewHolder.itemDescriptionView.setText(listItem.getDescriptionText());
        viewHolder.subitemCountView.setText(listItem.getSubitemCount());
        viewHolder.itemLocatedView.setSelected(listItem.isLocated());

        viewHolder.itemImageView.setOnClickListener(onClickListener);

        return convertView;
    }

    static class ViewHolder{
        TextView itemTitleView;
        ImageView itemImageView;
        TextView itemDescriptionView;
        TextView subitemCountView;
        ImageView itemLocatedView;
    }

    @Override
    public int getCount() {
        return objects.size();
    }
}
