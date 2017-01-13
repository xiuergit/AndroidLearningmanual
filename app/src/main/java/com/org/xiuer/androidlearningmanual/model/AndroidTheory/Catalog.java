package com.org.xiuer.androidlearningmanual.model.AndroidTheory;

import com.org.xiuer.androidlearningmanual.Test;



import cn.bmob.v3.BmobObject;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.datatype.BmobRelation;

/**
 * Created by zhangxiu on 2017/1/10.
 */

public class Catalog extends BmobObject{
    private  String name;
    private  Boolean select=false;
    private  BmobFile content;

    public BmobFile getContent() {
        return content;
    }

    public void setContent(BmobFile content) {
        this.content = content;
    }

    public Catalog(String name, Boolean select) {
        this.name = name;
        this.select = select;
    }

    public Catalog(String name) {
        this.name = name;
    }

    public Catalog() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getSelect() {
        return select;
    }

    public void setSelect(Boolean select) {
        this.select = select;
    }

    @Override
    public String toString() {
        return "Catalog{" +
                "name='" + name + '\'' +
                ", select=" + select +
                ", content="  +content+
                '}';
    }
}
