package edu.udistrital.plantae.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import edu.udistrital.plantae.R;
import edu.udistrital.plantae.logicadominio.datosespecimen.Fotografia;

/**
 * Created by hghar on 11/30/14.
 */
public class PictureDetailFragment extends Fragment {

    private static final String IMAGE_POSITION = "image_position";
    private int imagePosition;
    private ImageView imageView;

    public PictureDetailFragment() {
    }

    static PictureDetailFragment newInstance(int position) {
        final PictureDetailFragment pictureDetailFragment = new PictureDetailFragment();
        final Bundle args = new Bundle();
        args.putInt(IMAGE_POSITION, position);
        pictureDetailFragment.setArguments(args);
        return pictureDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imagePosition = getArguments() != null ?
                getArguments().getInt(IMAGE_POSITION) : -1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_picture_detail, container, false);
        imageView = (ImageView) view.findViewById(R.id.image_detail_image_view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (PictureViewActivity.class.isInstance(getActivity())) {
            ((PictureViewActivity) getActivity()).loadPicture(imagePosition, imageView);
        }
    }
}