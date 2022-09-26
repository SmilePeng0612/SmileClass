package com.smile.untils;

import java.util.List;

public class ListUntil {
    public static String listToString (List<Object> list){
        //先定义一个字符串
        String str = "";
        //遍历list集合
        for (Object id : list) {
            str += "," + id;
        }
        //截取掉第一个，
        str = str.substring(1);
        return str;
    }

}
