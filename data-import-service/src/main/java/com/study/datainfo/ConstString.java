package com.study.datainfo;

import java.util.*;

public class ConstString {
    public static String title = "标题";
    public static String from = "来源";
    public static String type = "分类";
    public static String id = "序号";

    public static Map<String, String> getStrs() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put("id", id);
        map.put("title", title);
        map.put("dataType", type);
        map.put("dataFrom", from);
        return map;
    }
}
