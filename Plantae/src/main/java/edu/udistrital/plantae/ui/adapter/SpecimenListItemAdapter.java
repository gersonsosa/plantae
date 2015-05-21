package edu.udistrital.plantae.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import edu.udistrital.plantae.R;
import edu.udistrital.plantae.ui.SpecimenListItem;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.List;

/**
 * Created by Gerson Sosa on 4/10/14.
 */
public class SpecimenListItemAdapter extends BaseAdapter {

    private final Resources resources;
    private final Bitmap bitmapPlaceHolder;
    private Context context;
    private View.OnClickListener onClickListener;
    private List<SpecimenListItem> objects;
    private int thumbnailWidth;
    private int thumbnailHeight;

    public SpecimenListItemAdapter(Context context, List<SpecimenListItem> objects, View.OnClickListener onClickListener, Resources resources) {
        this.context = context;
        this.objects = objects;
        this.onClickListener = onClickListener;
        this.resources = resources;
        bitmapPlaceHolder = BitmapFactory.decodeResource(resources, R.drawable.plantae);
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
        final ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.specimen_list_item, null);
            viewHolder = new ViewHolder();
            viewHolder.specimenImageView = (ImageView) convertView.findViewById(R.id.specimen_image);
            viewHolder.specimenTitleView = (TextView) convertView.findViewById(R.id.specimen_title);
            viewHolder.specimenScientificName = (TextView) convertView.findViewById(R.id.scientific_name);
            viewHolder.specimenLocality = (TextView) convertView.findViewById(R.id.specimen_locality);
            viewHolder.specimenDescription = (TextView) convertView.findViewById(R.id.specimen_description);
            viewHolder.itemLocatedView = (ImageView) convertView.findViewById(R.id.item_located);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final SpecimenListItem specimenListItem = objects.get(position);
        if (specimenListItem.getImagePath() == null) {
            viewHolder.specimenImageView.setImageResource(specimenListItem.getSpecimenImage());
        } else {
            final ViewTreeObserver vto = viewHolder.specimenImageView.getViewTreeObserver();
            vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                public boolean onPreDraw() {
                    vto.removeOnPreDrawListener(this);
                    thumbnailHeight = viewHolder.specimenImageView.getMeasuredHeight();
                    thumbnailWidth = viewHolder.specimenImageView.getMeasuredWidth();
                    if (cancelPotentialWork(specimenListItem.getImagePath(), viewHolder.specimenImageView)) {
                        final LoadThumbnailTask loadThumbnailTask = new LoadThumbnailTask(viewHolder.specimenImageView);
                        final AsyncDrawable asyncDrawable = new AsyncDrawable(resources, bitmapPlaceHolder, loadThumbnailTask);
                        viewHolder.specimenImageView.setImageDrawable(asyncDrawable);
                        thumbnailWidth = viewHolder.specimenImageView.getMeasuredWidth();
                        loadThumbnailTask.execute(specimenListItem.getImagePath());
                    }
                    return true;
                }
            });

        }
        viewHolder.specimenTitleView.setText(specimenListItem.getSpecimenTitle());
        viewHolder.specimenScientificName.setText(specimenListItem.getScientificName());
        viewHolder.specimenLocality.setText(specimenListItem.getSpecimenLocality());
        viewHolder.specimenDescription.setText(specimenListItem.getSpecimenDescription());
        viewHolder.itemLocatedView.setSelected(specimenListItem.isLocated());
        if (onClickListener != null) {
            viewHolder.specimenImageView.setOnClickListener(onClickListener);
        }

        return convertView;
    }

    static class ViewHolder{
        ImageView specimenImageView;
        TextView specimenTitleView;
        TextView specimenScientificName;
        TextView specimenLocality;
        TextView specimenDescription;
        ImageView itemLocatedView;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    class LoadThumbnailTask extends AsyncTask<String, Void, Bitmap> {

        private WeakReference<ImageView> imageViewReference;
        private String fotografia;

        public LoadThumbnailTask(ImageView imageView) {
            this.imageViewReference = new WeakReference<>(imageView);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            fotografia = params[0];
            Bitmap bitmap = null;
            File file = new File(fotografia);
            if(file.exists()){
                BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                bmOptions.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(file.getAbsolutePath(), bmOptions);
                bmOptions.inSampleSize = calculateInSampleSize(bmOptions, thumbnailWidth, thumbnailWidth);
                bmOptions.inJustDecodeBounds = false;
                bitmap = BitmapFactory.decodeFile(file.getAbsolutePath(), bmOptions);
            }

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            if (isCancelled()) {
                result = null;
            }
            if (imageViewReference != null && result != null) {
                final ImageView imageView = imageViewReference.get();
                final LoadThumbnailTask loadPictureTask = getLoadTHumbnailTask(imageView);

                if (this == loadPictureTask && imageView != null) {
                    imageView.setImageBitmap(result);
                }
            }
        }

    }

    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return inSampleSize;
    }

    static class AsyncDrawable extends BitmapDrawable {
        private final WeakReference<LoadThumbnailTask> loadPictureTaskWeakReference;

        public AsyncDrawable(Resources res, Bitmap bitmap,
                             LoadThumbnailTask loadPictureTask) {
            super(res, bitmap);
            loadPictureTaskWeakReference =
                    new WeakReference<>(loadPictureTask);
        }

        public LoadThumbnailTask getLoadPictureTask() {
            return loadPictureTaskWeakReference.get();
        }
    }

    public static boolean cancelPotentialWork(String path, ImageView imageView) {
        final LoadThumbnailTask loadPictureTask = getLoadTHumbnailTask(imageView);

        if (loadPictureTask != null) {
            final String bitmapPath = loadPictureTask.fotografia;
            // If bitmapPath is not yet set or it differs from the new path
            if (bitmapPath == null || !bitmapPath.equals(path)) {
                // Cancel previous task
                loadPictureTask.cancel(true);
            } else {
                // The same work is already in progress
                return false;
            }
        }
        // No task associated with the ImageView, or an existing task was cancelled
        return true;
    }

    private static LoadThumbnailTask getLoadTHumbnailTask(ImageView imageView) {
        if (imageView != null) {
            final Drawable drawable = imageView.getDrawable();
            if (drawable instanceof AsyncDrawable) {
                final AsyncDrawable asyncDrawable = (AsyncDrawable) drawable;
                return asyncDrawable.getLoadPictureTask();
            }
        }
        return null;
    }
}
