

package com.ibm.mobileappbuilder.storesreview20160225105920.ds;

import android.content.Context;

import java.net.URL;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import ibmmobileappbuilder.ds.SearchOptions;
import ibmmobileappbuilder.ds.restds.AppNowDatasource;
import ibmmobileappbuilder.util.StringUtils;
import ibmmobileappbuilder.ds.restds.TypedByteArrayUtils;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * "NaturalDS" data source. (e37eb8dc-6eb2-4635-8592-5eb9696050e3)
 */
public class NaturalDS extends AppNowDatasource<NaturalDSItem>{

    // default page size
    private static final int PAGE_SIZE = 20;

    private NaturalDSService service;

    public static NaturalDS getInstance(SearchOptions searchOptions){
        return new NaturalDS(searchOptions);
    }

    private NaturalDS(SearchOptions searchOptions) {
        super(searchOptions);
        this.service = NaturalDSService.getInstance();
    }

    @Override
    public void getItem(String id, final Listener<NaturalDSItem> listener) {
        if ("0".equals(id)) {
                        getItems(new Listener<List<NaturalDSItem>>() {
                @Override
                public void onSuccess(List<NaturalDSItem> items) {
                    if(items != null && items.size() > 0) {
                        listener.onSuccess(items.get(0));
                    } else {
                        listener.onSuccess(new NaturalDSItem());
                    }
                }

                @Override
                public void onFailure(Exception e) {
                    listener.onFailure(e);
                }
            });
        } else {
                      service.getServiceProxy().getNaturalDSItemById(id, new Callback<NaturalDSItem>() {
                @Override
                public void success(NaturalDSItem result, Response response) {
                                        listener.onSuccess(result);
                }

                @Override
                public void failure(RetrofitError error) {
                                        listener.onFailure(error);
                }
            });
        }
    }

    @Override
    public void getItems(final Listener<List<NaturalDSItem>> listener) {
        getItems(0, listener);
    }

    @Override
    public void getItems(int pagenum, final Listener<List<NaturalDSItem>> listener) {
        String conditions = getConditions(searchOptions, getSearchableFields());
        int skipNum = pagenum * PAGE_SIZE;
        String skip = skipNum == 0 ? null : String.valueOf(skipNum);
        String limit = PAGE_SIZE == 0 ? null: String.valueOf(PAGE_SIZE);
        String sort = getSort(searchOptions);
                service.getServiceProxy().queryNaturalDSItem(
                skip,
                limit,
                conditions,
                sort,
                null,
                null,
                new Callback<List<NaturalDSItem>>() {
            @Override
            public void success(List<NaturalDSItem> result, Response response) {
                                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    private String[] getSearchableFields() {
        return new String[]{"nATUREPARK", "detail", "address", "openinghours"};
    }

    // Pagination

    @Override
    public int getPageSize(){
        return PAGE_SIZE;
    }

    @Override
    public void getUniqueValuesFor(String searchStr, final Listener<List<String>> listener) {
        String conditions = getConditions(searchOptions, getSearchableFields());
                service.getServiceProxy().distinct(searchStr, conditions, new Callback<List<String>>() {
             @Override
             public void success(List<String> result, Response response) {
                                  result.removeAll(Collections.<String>singleton(null));
                 listener.onSuccess(result);
             }

             @Override
             public void failure(RetrofitError error) {
                                  listener.onFailure(error);
             }
        });
    }

    @Override
    public URL getImageUrl(String path) {
        return service.getImageUrl(path);
    }

    @Override
    public void create(NaturalDSItem item, Listener<NaturalDSItem> listener) {
                    
        if(item.pictureUri != null){
            service.getServiceProxy().createNaturalDSItem(item,
                TypedByteArrayUtils.fromUri(item.pictureUri),
                callbackFor(listener));
        }
        else
            service.getServiceProxy().createNaturalDSItem(item, callbackFor(listener));
        
    }

    private Callback<NaturalDSItem> callbackFor(final Listener<NaturalDSItem> listener) {
      return new Callback<NaturalDSItem>() {
          @Override
          public void success(NaturalDSItem item, Response response) {
                            listener.onSuccess(item);
          }

          @Override
          public void failure(RetrofitError error) {
                            listener.onFailure(error);
          }
      };
    }

    @Override
    public void updateItem(NaturalDSItem item, Listener<NaturalDSItem> listener) {
                    
        if(item.pictureUri != null){
            service.getServiceProxy().updateNaturalDSItem(item.getIdentifiableId(),
                item,
                TypedByteArrayUtils.fromUri(item.pictureUri),
                callbackFor(listener));
        }
        else
            service.getServiceProxy().updateNaturalDSItem(item.getIdentifiableId(), item, callbackFor(listener));
        
    }

    @Override
    public void deleteItem(NaturalDSItem item, final Listener<NaturalDSItem> listener) {
                service.getServiceProxy().deleteNaturalDSItemById(item.getIdentifiableId(), new Callback<NaturalDSItem>() {
            @Override
            public void success(NaturalDSItem result, Response response) {
                                listener.onSuccess(result);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    @Override
    public void deleteItems(List<NaturalDSItem> items, final Listener<NaturalDSItem> listener) {
                service.getServiceProxy().deleteByIds(collectIds(items), new Callback<List<NaturalDSItem>>() {
            @Override
            public void success(List<NaturalDSItem> item, Response response) {
                                listener.onSuccess(null);
            }

            @Override
            public void failure(RetrofitError error) {
                                listener.onFailure(error);
            }
        });
    }

    protected List<String> collectIds(List<NaturalDSItem> items){
        List<String> ids = new ArrayList<>();
        for(NaturalDSItem item: items){
            ids.add(item.getIdentifiableId());
        }
        return ids;
    }

}

