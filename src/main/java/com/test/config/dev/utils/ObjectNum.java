package com.test.config.dev.utils;

import java.util.List;
import java.util.Map;

/**
 * 根据对象的类型返回对应定义的值
 * List 0
 * Map 1
 * String 2
 * @author
 */
public class ObjectNum {

    public static Integer getObjectTypeNum(Object o){
        if(o instanceof List){
            return 1;
        }else if(o instanceof Map){
            return 2;
        }else if(o instanceof String){
            return 3;
        }else {
            return null;
        }
    }

}
