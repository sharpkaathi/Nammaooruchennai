
package com.ibm.mobileappbuilder.storesreview20160225105920.ui;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.ibm.mobileappbuilder.storesreview20160225105920.R;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.filter.Filter;
import java.util.Arrays;
import com.ibm.mobileappbuilder.storesreview20160225105920.ds.StoresDSItem;
import com.ibm.mobileappbuilder.storesreview20160225105920.ds.StoresDS;

public class RankingDetailFragment extends ibmmobileappbuilder.ui.DetailFragment<StoresDSItem>  {

    private Datasource<StoresDSItem> datasource;
    public static RankingDetailFragment newInstance(Bundle args){
        RankingDetailFragment fr = new RankingDetailFragment();
        fr.setArguments(args);

        return fr;
    }

    public RankingDetailFragment(){
        super();
    }

    @Override
    public Datasource<StoresDSItem> getDatasource() {
      if (datasource != null) {
        return datasource;
      }
       datasource = StoresDS.getInstance(new SearchOptions());
        return datasource;
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);

    }

    // Bindings

    @Override
    protected int getLayout() {
        return R.layout.rankingdetail_detail;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final StoresDSItem item, View view) {
    }

    @Override
    protected void onShow(StoresDSItem item) {
        // set the title for this fragment
        getActivity().setTitle(null);
    }
}

