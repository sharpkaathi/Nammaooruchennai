
package com.ibm.mobileappbuilder.storesreview20160225105920.ui;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.view.View;
import com.ibm.mobileappbuilder.storesreview20160225105920.ds.NaturalDSItem;
import com.ibm.mobileappbuilder.storesreview20160225105920.ds.NaturalDSService;
import com.ibm.mobileappbuilder.storesreview20160225105920.presenters.NaturalparksFormPresenter;
import com.ibm.mobileappbuilder.storesreview20160225105920.R;
import ibmmobileappbuilder.ds.restds.GeoPoint;
import ibmmobileappbuilder.ui.FormFragment;
import ibmmobileappbuilder.util.StringUtils;
import ibmmobileappbuilder.views.GeoPicker;
import ibmmobileappbuilder.views.ImagePicker;
import ibmmobileappbuilder.views.TextWatcherAdapter;
import java.io.IOException;
import java.io.File;

import static android.net.Uri.fromFile;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.filter.Filter;
import java.util.Arrays;
import com.ibm.mobileappbuilder.storesreview20160225105920.ds.NaturalDSItem;
import com.ibm.mobileappbuilder.storesreview20160225105920.ds.NaturalDS;

public class NaturalDSItemFormFragment extends FormFragment<NaturalDSItem> {

    private CrudDatasource<NaturalDSItem> datasource;

    public static NaturalDSItemFormFragment newInstance(Bundle args){
        NaturalDSItemFormFragment fr = new NaturalDSItemFormFragment();
        fr.setArguments(args);

        return fr;
    }

    public NaturalDSItemFormFragment(){
        super();
    }

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);

        // the presenter for this view
        setPresenter(new NaturalparksFormPresenter(
                (CrudDatasource) getDatasource(),
                this));

            }

    @Override
    protected NaturalDSItem newItem() {
        return new NaturalDSItem();
    }

    private NaturalDSService getRestService(){
        return NaturalDSService.getInstance();
    }

    @Override
    protected int getLayout() {
        return R.layout.naturalparks_form;
    }

    @Override
    @SuppressLint("WrongViewCast")
    public void bindView(final NaturalDSItem item, View view) {
        
        bindString(R.id.naturalds_naturepark, item.nATUREPARK, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.nATUREPARK = s.toString();
            }
        });
        
        
        bindImage(R.id.naturalds_picture,
            item.picture != null ?
                getRestService().getImageUrl(item.picture) : null,
            0,
            new ImagePicker.Callback(){
                @Override
                public void imageRemoved(){
                    item.picture = null;
                    item.pictureUri = null;
                    ((ImagePicker) getView().findViewById(R.id.naturalds_picture)).clear();
                }
            }
        );
        
        
        bindString(R.id.naturalds_detail, item.detail, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.detail = s.toString();
            }
        });
        
        
        bindLocation(R.id.naturalds_location, item.location,
            new GeoPicker.PointChangedListener() {
                @Override
                public void onPointChanged(GeoPoint point) {
                    item.location = point;
                }
            }
        );
        
        
        bindString(R.id.naturalds_address, item.address, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.address = s.toString();
            }
        });
        
        
        bindString(R.id.naturalds_openinghours, item.openinghours, new TextWatcherAdapter() {
            @Override
            public void afterTextChanged(Editable s) {
                item.openinghours = s.toString();
            }
        });
        
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK) {
            ImagePicker picker = null;
            Uri imageUri = null;
            NaturalDSItem item = getItem();

            if((requestCode & ImagePicker.GALLERY_REQUEST_CODE) == ImagePicker.GALLERY_REQUEST_CODE) {
              imageUri = data.getData();
              switch (requestCode - ImagePicker.GALLERY_REQUEST_CODE) {
                        
                        case 0:   // picture field
                            item.pictureUri = imageUri;
                            item.picture = "cid:picture";
                            picker = (ImagePicker) getView().findViewById(R.id.naturalds_picture);
                            break;
                        
                default:
                  return;
              }

              picker.setImageUri(imageUri);
            } else if((requestCode & ImagePicker.CAPTURE_REQUEST_CODE) == ImagePicker.CAPTURE_REQUEST_CODE) {
				      switch (requestCode - ImagePicker.CAPTURE_REQUEST_CODE) {
                        
                        case 0:   // picture field
                            picker = (ImagePicker) getView().findViewById(R.id.naturalds_picture);
                            imageUri = fromFile(picker.getImageFile());
                        		item.pictureUri = imageUri;
                            item.picture = "cid:picture";
                            break;
                        
                default:
                  return;
              }
              picker.setImageUri(imageUri);
            }
        }
    }
}

