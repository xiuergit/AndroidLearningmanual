package com.org.xiuer.androidlearningmanual.Adapter.AndroidTheory;

import cn.bmob.v3.BmobObject;

/**
 * Created by zhangxiu on 2017/1/10.
 */

public class Catalog extends BmobObject{
    private  String name;
    private  Boolean select;

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
        return "Catalogs{" +
                "name='" + name + '\'' +
                ", select=" + select +
                '}';
    }
}
