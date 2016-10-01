
package com.ibm.mobileappbuilder.storesreview20160225105920.presenters;

import com.ibm.mobileappbuilder.storesreview20160225105920.R;
import com.ibm.mobileappbuilder.storesreview20160225105920.ds.NaturalDSItem;

import java.util.List;

import ibmmobileappbuilder.ds.CrudDatasource;
import ibmmobileappbuilder.ds.Datasource;
import ibmmobileappbuilder.mvp.presenter.BasePresenter;
import ibmmobileappbuilder.mvp.presenter.ListCrudPresenter;
import ibmmobileappbuilder.mvp.view.CrudListView;

public class NaturalparksPresenter extends BasePresenter implements ListCrudPresenter<NaturalDSItem>,
      Datasource.Listener<NaturalDSItem>{

    private final CrudDatasource<NaturalDSItem> crudDatasource;
    private final CrudListView<NaturalDSItem> view;

    public NaturalparksPresenter(CrudDatasource<NaturalDSItem> crudDatasource,
                                         CrudListView<NaturalDSItem> view) {
       this.crudDatasource = crudDatasource;
       this.view = view;
    }

    @Override
    public void deleteItem(NaturalDSItem item) {
        crudDatasource.deleteItem(item, this);
    }

    @Override
    public void deleteItems(List<NaturalDSItem> items) {
        crudDatasource.deleteItems(items, this);
    }

    @Override
    public void addForm() {
        view.showAdd();
    }

    @Override
    public void editForm(NaturalDSItem item, int position) {
        view.showEdit(item, position);
    }

    @Override
    public void detail(NaturalDSItem item, int position) {
        view.showDetail(item, position);
    }

    @Override
    public void onSuccess(NaturalDSItem item) {
                view.showMessage(R.string.items_deleted);
        view.refresh();
    }

    @Override
    public void onFailure(Exception e) {
        view.showMessage(R.string.error_data_generic);
    }

}

