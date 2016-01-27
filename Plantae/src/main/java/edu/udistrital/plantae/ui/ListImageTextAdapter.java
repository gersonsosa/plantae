package edu.udistrital.plantae.ui;

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
import edu.udistrital.plantae.logicadominio.recoleccion.Viaje;
import edu.udistrital.plantae.persistencia.ViajeDao;

/**
 * Created by Gerson Sosa on 4/10/14.
 */
public class ListImageTextAdapter extends BaseAdapter {

    private static final int ITEM_VIEW_TYPE_NAVDRAWERITEM = 0;
    private static final int ITEM_VIEW_TYPE_SEPARATOR = 1;
    private static final int ITEM_VIEW_TYPE_COUNT = 2;

    private Context context;
    private List<NavigationDrawerItem> objects;
    private ViajeDao viajeDao;

    public ListImageTextAdapter(Context context, List<NavigationDrawerItem> objects, ViajeDao viajeDao) {
        this.context = context;
        this.objects = objects;
        this.viajeDao = viajeDao;
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
        final int type = getItemViewType(position);

        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(type ==  ITEM_VIEW_TYPE_SEPARATOR ? R.layout.separator_list_item : R.layout.drawer_list_item, null);
            viewHolder = new ViewHolder();
            if (type == ITEM_VIEW_TYPE_SEPARATOR) {
                viewHolder.titleView = (TextView) convertView.findViewById(R.id.separator_text);
            } else {
                viewHolder.titleView = (TextView) convertView.findViewById(R.id.title_travel_list_item);
                viewHolder.imageIconView = (ImageView) convertView.findViewById(R.id.imageView1);
                viewHolder.countView = (TextView) convertView.findViewById(R.id.especimen_count);
            }
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (type == ITEM_VIEW_TYPE_NAVDRAWERITEM) {
            viewHolder.titleView.setText(objects.get(position).getName());
            viewHolder.imageIconView.setImageResource(objects.get(position).getImage());

            // displaying count
            // check whether it set visible or not
            int especimenCount=-1;
            NavigationDrawerItem navigationDrawerItem = objects.get(position);
            if (navigationDrawerItem != null && navigationDrawerItem.getId() != null){
                Viaje viaje = viajeDao.load(navigationDrawerItem.getId());
                especimenCount = viaje.getEspecimenes() != null ? viaje.getEspecimenes().size() : 0;
            }
            if(especimenCount > 0){
                viewHolder.countView.setText(Integer.toString(especimenCount));
            }else{
                // hide the counter view
                viewHolder.countView.setVisibility(View.GONE);
            }
        } else {
            viewHolder.titleView.setText(((NavigationDrawerItem)getItem(position)).getName());
        }

        return convertView;
    }

    @Override
    public boolean isEnabled(int position) {
        return getItemViewType(position) != ITEM_VIEW_TYPE_SEPARATOR;
    }

    @Override
    public int getItemViewType(int position) {
        return (((NavigationDrawerItem)getItem(position)).getImage() == 0) ? ITEM_VIEW_TYPE_SEPARATOR : ITEM_VIEW_TYPE_NAVDRAWERITEM;
    }

    @Override
    public int getViewTypeCount() {
        return ITEM_VIEW_TYPE_COUNT;
    }

    static class ViewHolder{
        TextView titleView;
        ImageView imageIconView;
        TextView countView;
    }

    @Override
    public int getCount() {
        return objects.size();
    }
}
