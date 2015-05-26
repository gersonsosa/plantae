package edu.udistrital.plantae.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import edu.udistrital.plantae.R;

/**
 * Created by Gerson Sosa on 6/24/14.
 */
public class SpecimenPagesAdapter extends FragmentPagerAdapter {

    public static final int SPECIMEN_SINGLE = 0;
    public static final int SPECIMEN_DETAILED = 1;

    private static final int NUM_FRAGMENTS_DETAILED = 11;
    private static final int NUM_FRAGMENTS_SINGLE = 6;
    private static int NUM_FRAGMENTS = NUM_FRAGMENTS_SINGLE;

    private Fragment[] fragments;
    private String[] fragmentNames;
    private Activity activity;
    private Bundle fragmentsBundle;

    public SpecimenPagesAdapter(FragmentManager fm, int specimenType, Activity activity, Bundle fragmentsBundle) {
        super(fm);
        if (specimenType == SPECIMEN_DETAILED){
            NUM_FRAGMENTS = NUM_FRAGMENTS_DETAILED;
        }
        this.activity = activity;
        this.fragmentsBundle = fragmentsBundle;
        configureFragments();
    }

    private void configureFragments(){
        fragments = new Fragment[NUM_FRAGMENTS];
        fragmentNames = new String[NUM_FRAGMENTS];
        fragments[0] = new CollectingInformationFragment();
        fragmentNames[0] = activity.getString(R.string.collecting_information_title);
        fragments[1] = Fragment.instantiate(activity, LocalityInformationFragment.class.getName());
        fragmentNames[1] = activity.getString(R.string.locality_information_title);
        fragments[2] = Fragment.instantiate(activity, TaxonInformationFragment.class.getName());
        fragmentNames[2] = activity.getString(R.string.taxon_information_title);
        fragments[3] = Fragment.instantiate(activity, HabitatInformationFragment.class.getName());
        fragmentNames[3] = activity.getString(R.string.habitat_information_title);
        fragments[4] = Fragment.instantiate(activity, PlantAttributesFragment.class.getName());
        fragmentNames[4] = activity.getString(R.string.plants_attributes_information_title);
        fragments[5] = Fragment.instantiate(activity, FlowerInformationFragment.class.getName());
        fragmentNames[5] = activity.getString(R.string.flower_information_title);
        if (NUM_FRAGMENTS > NUM_FRAGMENTS_SINGLE){
            fragments[6] = Fragment.instantiate(activity, FruitInformationFragment.class.getName());
            fragmentNames[6] = activity.getString(R.string.fruits_information_title);
            fragments[7] = Fragment.instantiate(activity, InflorescenceInformationFragment.class.getName());
            fragmentNames[7] = activity.getString(R.string.inflorescence_information_title);
            fragments[8] = Fragment.instantiate(activity, RootInformationFragment.class.getName());
            fragmentNames[8] = activity.getString(R.string.root_information_title);
            fragments[9] = Fragment.instantiate(activity, LeavesInformationFragment.class.getName());
            fragmentNames[9] = activity.getString(R.string.leaves_information_title);
            fragments[10] = Fragment.instantiate(activity, StemInformationFragment.class.getName());
            fragmentNames[10] = activity.getString(R.string.stem_information_title);
        }
        for (Fragment fragment : fragments){
            fragment.setArguments(fragmentsBundle);
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return fragmentNames[position];
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
