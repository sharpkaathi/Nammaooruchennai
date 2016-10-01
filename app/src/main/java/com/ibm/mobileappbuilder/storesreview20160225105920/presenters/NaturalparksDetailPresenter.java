
package com.ibm.mobileappbuilder.storesreview20160225105920.presenters;

import com.ibm.mobileappbuilder.storesreview20160225105920.R;
import com.ibm.mobileappbuilder.storesreview20160225105920.ds.NaturalDSItem;

import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.mvp.presenter.BasePresenter;
import ibmmobileappbuilder.mvp.presenter.DetailCrudPresenter;
import ibmmobileappbuilder.mvp.view.DetailView;

public class NaturalparksDetailPresenter extends BasePresenter implements DetailCrudPresenter<NaturalDSItem>,
      Datasource.Listener<NaturalDSItem> {

    private final CrudDatasource<NaturalDSItem> datasource;
    private final DetailView view;

    public NaturalparksDetailPresenter(CrudDatasource<NaturalDSItem> datasource, DetailView view){
        this.datasource = datasource;
        this.view = view;
    }

    @Override
    public void deleteItem(NaturalDSItem item) {
        datasource.deleteItem(item, this);
    }

    @Override
    public void editForm(NaturalDSItem item) {
        view.navigateToEditForm();
    }

    @Override
    public void onSuccess(NaturalDSItem item) {
                view.showMessage(R.string.item_deleted, true);
        view.close(true);
    }

    @Override
    public void onFailure(Exception e) {
        view.showMessage(R.string.error_data_generic, true);
    }
}

