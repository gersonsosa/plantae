package edu.udistrital.plantae.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Display;
import android.widget.ImageView;
import edu.udistrital.plantae.R;
import edu.udistrital.plantae.logicadominio.datosespecimen.Fotografia;
import edu.udistrital.plantae.ui.adapter.PicturesAdapter;

import java.io.File;
import java.lang.ref.WeakReference;

/**
 * Created by hghar on 11/28/14.
 */
public class PictureViewActivity extends FragmentActivity {

    private int pictureWidth;
    private int pictureHeight;
    private Parcelable[] fotografiaArray;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_view);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        pictureWidth = size.x;
        pictureHeight = size.y;
        fotografiaArray = getIntent().getExtras().getParcelableArray("fotografias");
        PicturePageradapter picturePageradapter = new PicturePageradapter(getSupportFragmentManager(), fotografiaArray.length);
        ViewPager viewPager = (ViewPager) findViewById(R.id.picture_view_pager);
        viewPager.setAdapter(picturePageradapter);
        viewPager.setCurrentItem(getIntent().getExtras().getInt("fotografiaaSeleccionada"));
    }

    public void loadPicture(int imagePosition, ImageView imageView) {
        String picturePath = ((Fotografia) fotografiaArray[imagePosition]).getRutaArchivo();
        Bitmap bitmapPlaceHolder = Bitmap.createBitmap(pictureWidth, pictureHeight, Bitmap.Config.ARGB_8888);
        bitmapPlaceHolder.eraseColor(Color.BLACK);
        imageView.setImageBitmap(bitmapPlaceHolder);
        LoadPictureTask loadPictureTask = new LoadPictureTask(imageView);
        loadPictureTask.execute(picturePath);
    }

    class LoadPictureTask extends AsyncTask<String, Void, Bitmap> {

        private WeakReference<ImageView> imageViewReference;
        private String fotografia;

        public LoadPictureTask(ImageView imageView) {
            this.imageViewReference = new WeakReference<ImageView>(imageView);
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
                int pictureHeight = (pictureWidth * bmOptions.outHeight) / bmOptions.outWidth;
                bmOptions.inSampleSize = PicturesAdapter.calculateInSampleSize(bmOptions, pictureWidth, pictureHeight);
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
                imageView.setImageBitmap(result);
            }
        }

    }

    public static class PicturePageradapter extends FragmentStatePagerAdapter {

        private int size;

        public PicturePageradapter(FragmentManager fm, int size) {
            super(fm);
            this.size = size;
        }

        @Override
        public Fragment getItem(int position) {
            return PictureDetailFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return size;
        }
    }
}