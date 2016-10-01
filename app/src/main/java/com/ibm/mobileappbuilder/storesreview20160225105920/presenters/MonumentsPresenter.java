
package com.ibm.mobileappbuilder.storesreview20160225105920.presenters;

import com.ibm.mobileappbuilder.storesreview20160225105920.R;
import com.ibm.mobileappbuilder.storesreview20160225105920.ds.StoresDSItem;

import java.util.List;

import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.mvp.presenter.BasePresenter;
import ibmmobileappbuilder.mvp.presenter.ListCrudPresenter;
import ibmmobileappbuilder.mvp.view.CrudListView;

public class MonumentsPresenter extends BasePresenter implements ListCrudPresenter<StoresDSItem>,
      Datasource.Listener<StoresDSItem>{

    private final CrudDatasource<StoresDSItem> crudDatasource;
    private final CrudListView<StoresDSItem> view;

    public MonumentsPresenter(CrudDatasource<StoresDSItem> crudDatasource,
                                         CrudListView<StoresDSItem> view) {
       this.crudDatasource = crudDatasource;
       this.view = view;
    }

    @Override
    public void deleteItem(StoresDSItem item) {
        crudDatasource.deleteItem(item, this);
    }

    @Override
    public void deleteItems(List<StoresDSItem> items) {
        crudDatasource.deleteItems(items, this);
    }

    @Override
    public void addForm() {
        view.showAdd();
    }

    @Override
    public void editForm(StoresDSItem item, int position) {
        view.showEdit(item, position);
    }

    @Override
    public void detail(StoresDSItem item, int position) {
        view.showDetail(item, position);
    }

    @Override
    public void onSuccess(StoresDSItem item) {
                view.showMessage(R.string.items_deleted);
        view.refresh();
    }

    @Override
    public void onFailure(Exception e) {
        view.showMessage(R.string.error_data_generic);
    }

}

