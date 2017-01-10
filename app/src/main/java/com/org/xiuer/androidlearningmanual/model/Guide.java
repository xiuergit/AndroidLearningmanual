package com.org.xiuer.androidlearningmanual.model;

import android.os.Parcel;
import android.os.Parcelable;
import cn.bmob.v3.BmobObject;

/**
 * Created by xiuer on 16/8/18.
 */
public class Guide extends  BmobObject implements Parcelable {
    private  String title;//显示标题
    private  String content;//简单介绍
    private  String fileName;//加载的 assets 下文件的 名字 －－－>内容

    public Guide() {
        this.setTableName("MainGuides");
    }

    public Guide(String title, String content, String fileName) {
        this.setTableName("MainGuides");
        this.title = title;
        this.content = content;
        this.fileName = fileName;
    }

    public Guide(String title, String content) {
        this.setTableName("MainGuides");
        this.title = title;
        this.content = content;
    }

    protected Guide(Parcel in) {
        title = in.readString();
        content = in.readString();
        fileName = in.readString();
    }

    public static final Creator<Guide> CREATOR = new Creator<Guide>() {
        @Override
        public Guide createFromParcel(Parcel in) {
            return new Guide(in);
        }

        @Override
        public Guide[] newArray(int size) {
            return new Guide[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeString(content);
        parcel.writeString(fileName);
    }
}
