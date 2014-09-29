package edu.udistrital.plantae;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import edu.udistrital.plantae.objetotransferenciadatos.EspecimenDTO;

/**
 * Created by hghar on 6/24/14.
 */
public class SpecimenPagesAdapter extends FragmentPagerAdapter {

    public static final int SPECIMEN_SINGLE = 0;
    public static final int SPECIMEN_DETAILED = 1;

    private static final int NUM_FRAGMENTS_DETAILED = 11;
    private static final int NUM_FRAGMENTS_SINGLE = 6;
    private static int NUM_FRAGMENTS = NUM_FRAGMENTS_SINGLE;

    private Fragment[] fragments;
    private Activity activity;
    private EspecimenDTO especimenDTO;

    public SpecimenPagesAdapter(FragmentManager fm, int specimenType, Activity activity, EspecimenDTO especimenDTO) {
        super(fm);
        if (specimenType == SPECIMEN_DETAILED){
            NUM_FRAGMENTS = NUM_FRAGMENTS_DETAILED;
        }
        this.activity = activity;
        this.especimenDTO = especimenDTO;
        configureFragments();
    }

    private void configureFragments(){
        fragments = new Fragment[NUM_FRAGMENTS];
        fragments[0] = Fragment.instantiate(activity, CollectingInformationFragment.class.getName());
        fragments[1] = Fragment.instantiate(activity, LocalityInformationFragment.class.getName());
        fragments[2] = Fragment.instantiate(activity, TaxonInformationFragment.class.getName());
        fragments[3] = Fragment.instantiate(activity, HabitatInformationFragment.class.getName());
        fragments[4] = Fragment.instantiate(activity, PlantAttributesFragment.class.getName());
        fragments[5] = Fragment.instantiate(activity, FlowersInformationFragment.class.getName());
        if (NUM_FRAGMENTS > NUM_FRAGMENTS_SINGLE){
            fragments[6] = Fragment.instantiate(activity, FruitInformationFragment.class.getName());
            fragments[7] = Fragment.instantiate(activity, InflorescenceInformationFragment.class.getName());
            fragments[8] = Fragment.instantiate(activity, RootInformationFragment.class.getName());
            fragments[9] = Fragment.instantiate(activity, LeavesInformationFragment.class.getName());
            fragments[10] = Fragment.instantiate(activity, StemInformationFragment.class.getName());
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable("especimen", especimenDTO);
        for (Fragment fragment : fragments){
            fragment.setArguments(bundle);
        }
    }

    @Override
    public Fragment getItem(int i) {
        return fragments[i];
    }

    @Override
    public int getCount() {
        return NUM_FRAGMENTS;
    }
}
