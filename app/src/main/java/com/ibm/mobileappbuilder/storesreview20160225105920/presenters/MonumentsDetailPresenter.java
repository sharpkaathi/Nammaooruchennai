
package com.ibm.mobileappbuilder.storesreview20160225105920.presenters;

import com.ibm.mobileappbuilder.storesreview20160225105920.R;
import com.ibm.mobileappbuilder.storesreview20160225105920.ds.StoresDSItem;

import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.mvp.presenter.BasePresenter;
import ibmmobileappbuilder.mvp.presenter.DetailCrudPresenter;
import ibmmobileappbuilder.mvp.view.DetailView;

public class MonumentsDetailPresenter extends BasePresenter implements DetailCrudPresenter<StoresDSItem>,
      Datasource.Listener<StoresDSItem> {

    private final CrudDatasource<StoresDSItem> datasource;
    private final DetailView view;

    public MonumentsDetailPresenter(CrudDatasource<StoresDSItem> datasource, DetailView view){
        this.datasource = datasource;
        this.view = view;
    }

    @Override
    public void deleteItem(StoresDSItem item) {
        datasource.deleteItem(item, this);
    }

    @Override
    public void editForm(StoresDSItem item) {
        view.navigateToEditForm();
    }

    @Override
    public void onSuccess(StoresDSItem item) {
                view.showMessage(R.string.item_deleted, true);
        view.close(true);
    }

    @Override
    public void onFailure(Exception e) {
        view.showMessage(R.string.error_data_generic, true);
    }
}

