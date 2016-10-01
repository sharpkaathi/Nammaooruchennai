
package com.ibm.mobileappbuilder.storesreview20160225105920.ds;
import java.net.URL;
import com.ibm.mobileappbuilder.storesreview20160225105920.R;
import ibmmobileappbuilder.ds.RestService;
import ibmmobileappbuilder.util.StringUtils;

/**
 * "NaturalDSService" REST Service implementation
 */
public class NaturalDSService extends RestService<NaturalDSServiceRest>{

    public static NaturalDSService getInstance(){
          return new NaturalDSService();
    }

    private NaturalDSService() {
        super(NaturalDSServiceRest.class);

    }

    @Override
    public String getServerUrl() {
        return "https://ibm-pods.buildup.io";
    }

    @Override
    protected String getApiKey() {
        return "5oVWZJMy";
    }

    @Override
    public URL getImageUrl(String path){
        return StringUtils.parseUrl("https://ibm-pods.buildup.io/app/57ef5bd59d17e00300d4d46e",
                path,
                "apikey=5oVWZJMy");
    }

}

