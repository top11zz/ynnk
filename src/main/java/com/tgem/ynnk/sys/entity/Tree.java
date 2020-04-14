package com.tgem.ynnk.sys.entity;

import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;
public interface Tree<E> {
    public String getId();

    public String getPid();

    public void setChildList(List<E> childList);

    public List<E> getChildList();

}
