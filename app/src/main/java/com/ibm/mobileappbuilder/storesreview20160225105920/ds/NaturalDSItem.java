
package com.ibm.mobileappbuilder.storesreview20160225105920.ds;
import android.graphics.Bitmap;
import ibmmobileappbuilder.ds.restds.GeoPoint;
import android.net.Uri;

import ibmmobileappbuilder.mvp.model.IdentifiableBean;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class NaturalDSItem implements Parcelable, IdentifiableBean {

    @SerializedName("nATUREPARK") public String nATUREPARK;
    @SerializedName("picture") public String picture;
    @SerializedName("detail") public String detail;
    @SerializedName("location") public GeoPoint location;
    @SerializedName("address") public String address;
    @SerializedName("openinghours") public String openinghours;
    @SerializedName("id") public String id;
    @SerializedName("pictureUri") public transient Uri pictureUri;

    @Override
    public String getIdentifiableId() {
      return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nATUREPARK);
        dest.writeString(picture);
        dest.writeString(detail);
        dest.writeDoubleArray(location != null  && location.coordinates.length != 0 ? location.coordinates : null);
        dest.writeString(address);
        dest.writeString(openinghours);
        dest.writeString(id);
    }

    public static final Creator<NaturalDSItem> CREATOR = new Creator<NaturalDSItem>() {
        @Override
        public NaturalDSItem createFromParcel(Parcel in) {
            NaturalDSItem item = new NaturalDSItem();

            item.nATUREPARK = in.readString();
            item.picture = in.readString();
            item.detail = in.readString();
            double[] location_coords = in.createDoubleArray();
            if (location_coords != null)
                item.location = new GeoPoint(location_coords);
            item.address = in.readString();
            item.openinghours = in.readString();
            item.id = in.readString();
            return item;
        }

        @Override
        public NaturalDSItem[] newArray(int size) {
            return new NaturalDSItem[size];
        }
    };

}


