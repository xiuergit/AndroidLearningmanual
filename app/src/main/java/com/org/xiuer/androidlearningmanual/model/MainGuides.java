package com.org.xiuer.androidlearningmanual.model;

import android.os.Parcel;
import android.os.Parcelable;

import cn.bmob.v3.BmobObject;

/**
 * Created by zhangxiu on 2017/1/6.
 */

public class MainGuides extends BmobObject implements Parcelable {
    private  String title;//显示标题
    private  String content;//简单介绍
    private  String fileName;//加载的 assets 下文件的 名字 －－－>内容


    public MainGuides() {
    }

    public MainGuides(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public MainGuides(String title, String content, String fileName) {
        this.title = title;
        this.content = content;
        this.fileName = fileName;
    }

    protected MainGuides(Parcel in) {
        title = in.readString();
        content = in.readString();
        fileName = in.readString();
    }

    public static final Creator<MainGuides> CREATOR = new Creator<MainGuides>() {
        @Override
        public MainGuides createFromParcel(Parcel in) {
            return new MainGuides(in);
        }

        @Override
        public MainGuides[] newArray(int size) {
            return new MainGuides[size];
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
    public String toString() {
        return "MainGuides{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", fileName='" + fileName + '\'' +
                '}';
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
