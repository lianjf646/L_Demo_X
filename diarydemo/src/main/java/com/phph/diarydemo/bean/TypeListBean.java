package com.phph.diarydemo.bean;

/**
 * Created by v on 2019/12/13.
 */
public class TypeListBean {
    public String typeName;
    public int total = 0;

    public TypeListBean() {
    }

    public TypeListBean(String typeName, int total) {
        this.typeName = typeName;
        this.total = total;
    }
}
