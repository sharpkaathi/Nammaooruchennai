
package com.ibm.mobileappbuilder.storesreview20160225105920.ui;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.ibm.mobileappbuilder.storesreview20160225105920.ds.NaturalDSService;
import com.ibm.mobileappbuilder.storesreview20160225105920.presenters.NaturalparksDetailPresenter;
import com.ibm.mobileappbuilder.storesreview20160225105920.R;
import ibmmobileappbuilder.actions.ActivityIntentLauncher;
import ibmmobileappbuilder.actions.MapsAction;
import ibmmobileappbuilder.behaviors.FabBehaviour;
import ibmmobileappbuilder.behaviors.ShareBehavior;
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
import com.ibm.mobileappbuilder.storesreview20160225105920.ds.NaturalDSItem;
import com.ibm.mobileappbuilder.storesreview20160225105920.ds.NaturalDS;

public class NaturalparksDetailFragment extends ibmmobileappbuilder.ui.DetailFragment<NaturalDSItem> implements ShareBehavior.ShareListener  {

    private CrudDatasource<NaturalDSItem> datasource;
    public static NaturalparksDetailFragment newInstance(Bundle args){
        NaturalparksDetailFragment fr = new NaturalparksDetailFragment();
        fr.setArguments(args);

        return fr;
    }

    public NaturalparksDetailFragment(){
        super();
    }

    @Override
    public Datasource<NaturalDSItem> getDatasource() {
      if (datasource != null) {
        return datasource;
      }
       datasource = NaturalDS.getInstance(new SearchOptions());
        return datasource;
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        // the presenter for this view
        setPresenter(new NaturalparksDetailPresenter(
                (CrudDatasource) getDatasource(),
                this));
        addBehavior(new ShareBehavior(getActivity(), this));

    }

    // Bindings

    @Override
    protected int getLayout() {
        return R.layout.naturalparksdetail_detail;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final NaturalDSItem item, View view) {
        if (item.nATUREPARK != null){
            
            TextView view0 = (TextView) view.findViewById(R.id.view0);
            view0.setText(item.nATUREPARK);
            
        }
        
        ImageView view1 = (ImageView) view.findViewById(R.id.view1);
        URL view1Media = ((AppNowDatasource) getDatasource()).getImageUrl(item.picture);
        if(view1Media != null){
          ImageLoader imageLoader = new PicassoImageLoader(view1.getContext());
          imageLoader.load(imageLoaderRequest()
                                   .withPath(view1Media.toExternalForm())
                                   .withTargetView(view1)
                                   .fit()
                                   .build()
                    );
        	
        } else {
          view1.setImageDrawable(null);
        }
        if (item.address != null){
            
            TextView view2 = (TextView) view.findViewById(R.id.view2);
            view2.setText(item.address);
            bindAction(view2, new MapsAction(
            new ActivityIntentLauncher()
            , "http://maps.google.com/maps?q=" + item.address));
        }
        if (item.openinghours != null){
            
            TextView view3 = (TextView) view.findViewById(R.id.view3);
            view3.setText(item.openinghours);
            
        }
        if (item.detail != null){
            
            TextView view4 = (TextView) view.findViewById(R.id.view4);
            view4.setText(item.detail);
            
        }
    }

    @Override
    protected void onShow(NaturalDSItem item) {
        // set the title for this fragment
        getActivity().setTitle("Natural Parks");
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
            ((DetailCrudPresenter<NaturalDSItem>) getPresenter()).deleteItem(getItem());
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onShare() {
        NaturalDSItem item = getItem();

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("text/plain");

        intent.putExtra(Intent.EXTRA_TEXT, (item.nATUREPARK != null ? item.nATUREPARK : "" ) + "\n" +
                    (item.address != null ? item.address : "" ) + "\n" +
                    (item.openinghours != null ? item.openinghours : "" ) + "\n" +
                    (item.detail != null ? item.detail : "" ));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Natural Parks");
        startActivityForResult(Intent.createChooser(intent, getString(R.string.share)), 1);
    }
}

