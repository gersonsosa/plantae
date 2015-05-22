package edu.udistrital.plantae.ui.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.List;

import edu.udistrital.plantae.R;
import edu.udistrital.plantae.ui.PictureItem;

/**
 * Created by hghar on 11/26/14.
 */
public class PicturesAdapter extends BaseAdapter {

    private Context context;
    private View.OnClickListener onClickListener;
    private List<PictureItem> objects;
    private int pictureWidth;
    private Resources resources;
    private final Bitmap bitmapPlaceHolder;

    public PicturesAdapter(Context context, List<PictureItem> objects, View.OnClickListener onClickListener, Resources resources) {
        this.context = context;
        this.objects = objects;
        this.onClickListener = onClickListener;
        this.resources = resources;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        pictureWidth = (width / 3) - dpToPx(4);
        bitmapPlaceHolder = Bitmap.createBitmap(pictureWidth, pictureWidth, Bitmap.Config.ARGB_8888);
        bitmapPlaceHolder.eraseColor(Color.GRAY);
    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
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
            convertView = mInflater.inflate(R.layout.picture_item, null);
            viewHolder = new ViewHolder();
            viewHolder.picureContextView = (TextView) convertView.findViewById(R.id.picture_context);
            viewHolder.pictuerImageView = (ImageView) convertView.findViewById(R.id.specimen_picture);
            viewHolder.pictuerImageView.setLayoutParams(new FrameLayout.LayoutParams(pictureWidth, pictureWidth));
            viewHolder.pictuerImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        PictureItem pictureItem = objects.get(position);

        if (cancelPotentialWork(pictureItem.getImagePath(), viewHolder.pictuerImageView)) {
            final LoadPictureTask loadPictureTask = new LoadPictureTask(viewHolder.pictuerImageView);
            final AsyncDrawable asyncDrawable = new AsyncDrawable(resources, bitmapPlaceHolder, loadPictureTask);
            viewHolder.pictuerImageView.setImageDrawable(asyncDrawable);
            loadPictureTask.execute(pictureItem.getImagePath());
        }

        viewHolder.picureContextView.setText(pictureItem.getContext());

        viewHolder.pictuerImageView.setOnClickListener(onClickListener);

        return convertView;
    }

    static class ViewHolder{
        TextView picureContextView;
        ImageView pictuerImageView;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    class LoadPictureTask extends AsyncTask<String, Void, Bitmap> {

        private WeakReference<ImageView> imageViewReference;
        private String fotografia;

        public LoadPictureTask(ImageView imageView) {
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
                bmOptions.inSampleSize = calculateInSampleSize(bmOptions, pictureWidth, pictureWidth);
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
                final LoadPictureTask loadPictureTask = getLoadPictureTask(imageView);

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
        private final WeakReference<LoadPictureTask> loadPictureTaskWeakReference;

        public AsyncDrawable(Resources res, Bitmap bitmap,
                             LoadPictureTask loadPictureTask) {
            super(res, bitmap);
            loadPictureTaskWeakReference =
                    new WeakReference<>(loadPictureTask);
        }

        public LoadPictureTask getLoadPictureTask() {
            return loadPictureTaskWeakReference.get();
        }
    }

    public static boolean cancelPotentialWork(String path, ImageView imageView) {
        final LoadPictureTask loadPictureTask = getLoadPictureTask(imageView);

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

    private static LoadPictureTask getLoadPictureTask(ImageView imageView) {
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
