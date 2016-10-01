
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

public class StoresMapDetailFragment extends ibmmobileappbuilder.ui.DetailFragment<StoresDSItem>  {

    private Datasource<StoresDSItem> datasource;
    public static StoresMapDetailFragment newInstance(Bundle args){
        StoresMapDetailFragment fr = new StoresMapDetailFragment();
        fr.setArguments(args);

        return fr;
    }

    public StoresMapDetailFragment(){
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
        return R.layout.storesmapdetail_detail;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final StoresDSItem item, View view) {
        if (item.monuments != null){
            
            TextView view0 = (TextView) view.findViewById(R.id.view0);
            view0.setText(item.monuments);
            
        }
        if (item.ranking != null){
            
            TextView view1 = (TextView) view.findViewById(R.id.view1);
            view1.setText(" ★" + item.ranking);
            
        }
    }

    @Override
    protected void onShow(StoresDSItem item) {
        // set the title for this fragment
        getActivity().setTitle("Monuments");
    }
}

