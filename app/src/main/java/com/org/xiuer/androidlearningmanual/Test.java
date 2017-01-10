package com.org.xiuer.androidlearningmanual;

import cn.bmob.v3.BmobObject;

/**
 * Created by zhangxiu on 2017/1/10.
 */
public class Test  extends BmobObject {
    private  String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Test(String name) {
        this.name = name;
    }
}

