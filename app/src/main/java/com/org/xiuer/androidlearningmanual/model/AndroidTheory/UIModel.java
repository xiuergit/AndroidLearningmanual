package com.org.xiuer.androidlearningmanual.model.AndroidTheory;

import java.util.List;

/**
 * Created by zhangxiu on 2017/1/10.
 */

public class UIModel {


    /**
     * name : TextView
     * attributes : [{"name":"android:textColor","method":"setTextColor(int)","description":"设置文本颜色"}]
     */

    private String name;
    private List<AttributesBean> attributes;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<AttributesBean> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<AttributesBean> attributes) {
        this.attributes = attributes;
    }

    public static class AttributesBean {
        /**
         * name : android:textColor
         * method : setTextColor(int)
         * description : 设置文本颜色
         */

        private String name;
        private String method;
        private String description;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return "AttributesBean{" +
                    "name='" + name + '\'' +
                    ", method='" + method + '\'' +
                    ", description='" + description + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "UIModel{" +
                "name='" + name + '\'' +
                ", attributes=" + attributes +
                '}';
    }
}
