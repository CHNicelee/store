package com.ice.util;

import java.util.Map;

/**
 * Created by asd on 9/20/2017.
 */
public class ReturnUtil {


    public static void success(Map<String,Object> map){
        map.put("success",true);
    }

    public static void error(Map<String,Object> map,String result){
        map.put("success",false);
        map.put("message",result);
    }

}
