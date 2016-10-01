package com.ibm.mobileappbuilder.storesreview20160225105920.ui;

import android.support.v4.app.Fragment;
import android.util.SparseArray;

import ibmmobileappbuilder.ui.DrawerActivity;

import com.ibm.mobileappbuilder.storesreview20160225105920.ds.NaturalDSItem;
import com.ibm.mobileappbuilder.storesreview20160225105920.ds.StoresDSItem;
import com.ibm.mobileappbuilder.storesreview20160225105920.R;

public class MapMain extends DrawerActivity {

    private final SparseArray<Class<? extends Fragment>> sectionFragments = new SparseArray<>();
    {
                sectionFragments.append(R.id.entry0, StoresMapFragment.class);
            sectionFragments.append(R.id.entry1, MonumentsFragment.class);
            sectionFragments.append(R.id.entry2, NaturalparksFragment.class);
            sectionFragments.append(R.id.entry3, RankingFragment.class);
    }

    @Override
    public SparseArray<Class<? extends Fragment>> getSectionFragmentClasses() {
      return sectionFragments;
    }

}

