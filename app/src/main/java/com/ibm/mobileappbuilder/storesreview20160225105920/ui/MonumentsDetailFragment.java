
package com.ibm.mobileappbuilder.storesreview20160225105920.ui;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.ibm.mobileappbuilder.storesreview20160225105920.ds.StoresDSService;
import com.ibm.mobileappbuilder.storesreview20160225105920.presenters.MonumentsDetailPresenter;
import com.ibm.mobileappbuilder.storesreview20160225105920.R;
import ibmmobileappbuilder.actions.ActivityIntentLauncher;
import ibmmobileappbuilder.actions.MapsAction;
import ibmmobileappbuilder.behaviors.FabBehaviour;
import ibmmobileappbuilder.ds.restds.AppNowDatasource;
import ibmmobileappbuilder.mvp.presenter.DetailCrudPresenter;
import ibmmobileappbuilder.util.ColorUtils;
import ibmmobileappbuilder.util.Constants;
import ibmmobileappbuilder.util.image.ImageLoader;
import ibmmobileappbuilder.util.image.PicassoImageLoader;
import ibmmobileappbuilder.util.StringUtils;
import java.net.URL;
import static ibmmobileappbuilder.util.image.ImageLoaderRequest.Builder.imageLoaderRequest;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.filter.Filter;
import java.util.Arrays;
import com.ibm.mobileappbuilder.storesreview20160225105920.ds.StoresDSItem;
import com.ibm.mobileappbuilder.storesreview20160225105920.ds.StoresDS;

public class MonumentsDetailFragment extends ibmmobileappbuilder.ui.DetailFragment<StoresDSItem>  {

    private CrudDatasource<StoresDSItem> datasource;
    public static MonumentsDetailFragment newInstance(Bundle args){
        MonumentsDetailFragment fr = new MonumentsDetailFragment();
        fr.setArguments(args);

        return fr;
    }

    public MonumentsDetailFragment(){
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
        // the presenter for this view
        setPresenter(new MonumentsDetailPresenter(
                (CrudDatasource) getDatasource(),
                this));
        // Edit button
        addBehavior(new FabBehaviour(this, R.drawable.ic_edit_white, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((DetailCrudPresenter<StoresDSItem>) getPresenter()).editForm(getItem());
            }
        }));

    }

    // Bindings

    @Override
    protected int getLayout() {
        return R.layout.monumentsdetail_detail;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final StoresDSItem item, View view) {
        
        ImageView view0 = (ImageView) view.findViewById(R.id.view0);
        URL view0Media = ((AppNowDatasource) getDatasource()).getImageUrl(item.picture);
        if(view0Media != null){
          ImageLoader imageLoader = new PicassoImageLoader(view0.getContext());
          imageLoader.load(imageLoaderRequest()
                                   .withPath(view0Media.toExternalForm())
                                   .withTargetView(view0)
                                   .fit()
                                   .build()
                    );
        	
        } else {
          view0.setImageDrawable(null);
        }
        if (item.address != null){
            
            TextView view1 = (TextView) view.findViewById(R.id.view1);
            view1.setText(item.address);
            bindAction(view1, new MapsAction(
            new ActivityIntentLauncher()
            , "http://maps.google.com/maps?q=" + item.address));
        }
        if (item.openinghours != null){
            
            TextView view2 = (TextView) view.findViewById(R.id.view2);
            view2.setText(item.openinghours);
            
        }
        if (item.detail != null){
            
            TextView view3 = (TextView) view.findViewById(R.id.view3);
            view3.setText(item.detail);
            
        }
    }

    @Override
    protected void onShow(StoresDSItem item) {
        // set the title for this fragment
        getActivity().setTitle(item.monuments);
    }

    @Override
    public void navigateToEditForm() {
        Bundle args = new Bundle();

        args.putInt(Constants.ITEMPOS, 0);
        args.putParcelable(Constants.CONTENT, getItem());
        args.putInt(Constants.MODE, Constants.MODE_EDIT);

        Intent intent = new Intent(getActivity(), StoresDSItemFormActivity.class);
        intent.putExtras(args);
        startActivityForResult(intent, Constants.MODE_EDIT);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.delete_menu, menu);

        MenuItem item = menu.findItem(R.id.action_delete);
        ColorUtils.tintIcon(item, R.color.textBarColor, getActivity());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_delete){
            ((DetailCrudPresenter<StoresDSItem>) getPresenter()).deleteItem(getItem());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

