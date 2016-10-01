

package com.ibm.mobileappbuilder.storesreview20160225105920.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.ibm.mobileappbuilder.storesreview20160225105920.R;

import ibmmobileappbuilder.ui.BaseListingActivity;
/**
 * NaturalparksActivity list activity
 */
public class NaturalparksActivity extends BaseListingActivity {

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getString(R.string.naturalparksActivity));
    }

    @Override
    protected Class<? extends Fragment> getFragmentClass() {
        return NaturalparksFragment.class;
    }

}

