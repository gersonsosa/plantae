package edu.udistrital.plantae.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;
import edu.udistrital.plantae.R;
import edu.udistrital.plantae.logicadominio.datosespecimen.Fotografia;
import edu.udistrital.plantae.ui.adapter.PicturesAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hghar on 11/26/14.
 */
public class PictureGalleryActivity extends ActionBarActivity implements View.OnClickListener {

    private GridView gridView;
    private Fotografia[] fotografias;
    private List<Fotografia> fotografiaList;
    private String picturePath;
    private Activity parentActivity;
    private String numeroColeccion;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            picturePath = savedInstanceState.getString("photo_uri");
        }
        setContentView(R.layout.activity_picture_gallery);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.left);
        setSupportActionBar(toolbar);

        numeroColeccion = getIntent().getStringExtra("numeroColeccion");

        fotografiaList = new ArrayList<Fotografia>();
        for (Parcelable parcelable : getIntent().getParcelableArrayListExtra("fotografias")) {
            fotografiaList.add((Fotografia) parcelable);
        }
        gridView = (GridView) findViewById(R.id.gridView);
        LoadPictures loadPictures = new LoadPictures();
        fotografias = new Fotografia[fotografiaList.size()];
        loadPictures.execute(fotografiaList.toArray(fotografias));
    }

    private void reLoadPictures() {
        LoadPictures loadPictures = new LoadPictures();
        fotografias = new Fotografia[fotografiaList.size()];
        loadPictures.execute(fotografiaList.toArray(fotografias));
        Intent intent = new Intent();
        intent.putParcelableArrayListExtra("fotografias", (ArrayList<? extends Parcelable>) fotografiaList);
        setResult(RESULT_OK, intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.picture_gallery_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_photo:
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (cameraIntent.resolveActivity(getPackageManager()) != null) {
                    Uri fileUri = CreateSpecimenActivity.getOutputMediaFileUri(CreateSpecimenActivity.MEDIA_TYPE_IMAGE); // create a file to save the image
                    if (fileUri != null) {
                        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri); // set the image file name
                        picturePath = fileUri.getPath();
                        startActivityForResult(cameraIntent, CreateSpecimenActivity.CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
                    }else{
                        Toast.makeText(this, R.string.plantae_failed_create_directory, Toast.LENGTH_LONG).show();
                    }
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case CreateSpecimenActivity.CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE:
                if (resultCode == RESULT_OK) {
                    // Image captured and saved to fileUri specified in the Intent
                    Toast.makeText(this, "Image saved to:\n" +
                            picturePath, Toast.LENGTH_LONG).show();
                    addPicture();
                    reLoadPictures();
                } else if (resultCode == RESULT_CANCELED) {
                    // User cancelled the image capture
                    Toast.makeText(this, "No image saved", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, "Image capture failed", Toast.LENGTH_LONG).show();
                }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void addPicture() {
        if (fotografiaList == null) {
            fotografiaList = new ArrayList<Fotografia>(1);
        }
        Fotografia fotografia = new Fotografia();
        fotografia.setContexto(numeroColeccion);
        fotografia.setRutaArchivo(picturePath);
        fotografiaList.add(fotografia);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        if (picturePath != null) {
            outState.putString("photo_uri", picturePath);
        }
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View v) {
        final Intent intent = new Intent(getApplicationContext(), PictureViewActivity.class);
        intent.putExtra("fotografias", fotografias);
        intent.putExtra("fotografiaaSeleccionada", gridView.getPositionForView(v));
        startActivity(intent);
    }

    class LoadPictures extends AsyncTask<Fotografia, Void, List<PictureItem>> {

        @Override
        protected List<PictureItem> doInBackground(Fotografia... params) {
            List<PictureItem> pictureItems = new ArrayList<PictureItem>(params.length);
            for (Fotografia fotografia:params) {
                pictureItems.add(new PictureItem(fotografia.getContexto(), fotografia.getRutaArchivo()));
            }

            return pictureItems;
        }
        @Override
        protected void onPostExecute(List<PictureItem> result) {
            setAdapterToGridView(result);
        }

    }

    private void setAdapterToGridView(List<PictureItem> result) {
        gridView.setAdapter(new PicturesAdapter(getApplicationContext(),result, this, getResources()));
    }
}