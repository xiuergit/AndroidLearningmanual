package com.org.xiuer.androidlearningmanual.model.AndroidTheory;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * Created by zhangxiu on 2017/1/9.
 */

public class THomeModel extends BmobObject implements Parcelable{


    private String name;
    private String id;
    private List  catalogs;

    protected THomeModel(Parcel in) {
        name = in.readString();
        id = in.readString();
    }

    public static final Creator<THomeModel> CREATOR = new Creator<THomeModel>() {
        @Override
        public THomeModel createFromParcel(Parcel in) {
            return new THomeModel(in);
        }

        @Override
        public THomeModel[] newArray(int size) {
            return new THomeModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List getCatalogs() {
        return catalogs;
    }

    public void setCatalogs(List catalogs) {
        this.catalogs = catalogs;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public THomeModel() {
        super();
    }

    @Override
    public String toString() {
        return "THomeModel{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", catalogs=" + catalogs +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(id);
    }
}
