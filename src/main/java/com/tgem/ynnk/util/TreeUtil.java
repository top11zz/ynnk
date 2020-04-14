package com.tgem.ynnk.util;

import com.sun.org.apache.xpath.internal.operations.Equals;
import com.tgem.ynnk.sys.entity.Tree;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TreeUtil {


    public static<T extends Tree> List<T> makeTree(List<T> list, String pid) {

        //子类
        List<T> children = list.stream().filter(x -> x.getPid() .equals(pid)).collect(Collectors.toList());

        //后辈中的非子类
        List<T> successor = list.stream().filter(x -> !x.getPid().equals(pid)).collect(Collectors.toList());

        children.forEach(x ->
                {
                    x.setChildList(makeTree((List<Tree>) successor, x.getId()));
                }
        );

        return children;

    }


    public static <T extends Tree> List<T> tree2List(List<T> tree, List<T> companies){
        tree.forEach(x->{
            companies.add(x);
            tree2List(x.getChildList(),companies);
            x.setChildList(null);
        });
        return companies;
    }
}
