
package com.ibm.mobileappbuilder.storesreview20160225105920.ds;
import android.graphics.Bitmap;
import ibmmobileappbuilder.ds.restds.GeoPoint;
import android.net.Uri;

import ibmmobileappbuilder.mvp.model.IdentifiableBean;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

public class StoresDSItem implements Parcelable, IdentifiableBean {

    @SerializedName("monuments") public String monuments;
    @SerializedName("picture") public String picture;
    @SerializedName("detail") public String detail;
    @SerializedName("location") public GeoPoint location;
    @SerializedName("address") public String address;
    @SerializedName("openinghours") public String openinghours;
    @SerializedName("ranking") public String ranking;
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
        dest.writeString(monuments);
        dest.writeString(picture);
        dest.writeString(detail);
        dest.writeDoubleArray(location != null  && location.coordinates.length != 0 ? location.coordinates : null);
        dest.writeString(address);
        dest.writeString(openinghours);
        dest.writeString(ranking);
        dest.writeString(id);
    }

    public static final Creator<StoresDSItem> CREATOR = new Creator<StoresDSItem>() {
        @Override
        public StoresDSItem createFromParcel(Parcel in) {
            StoresDSItem item = new StoresDSItem();

            item.monuments = in.readString();
            item.picture = in.readString();
            item.detail = in.readString();
            double[] location_coords = in.createDoubleArray();
            if (location_coords != null)
                item.location = new GeoPoint(location_coords);
            item.address = in.readString();
            item.openinghours = in.readString();
            item.ranking = in.readString();
            item.id = in.readString();
            return item;
        }

        @Override
        public StoresDSItem[] newArray(int size) {
            return new StoresDSItem[size];
        }
    };

}


