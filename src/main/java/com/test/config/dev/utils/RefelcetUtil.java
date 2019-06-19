package com.test.config.dev.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class RefelcetUtil {

    public static List<String> getFieldNames(Class aclass){
        Field[] declaredFields = aclass.getDeclaredFields();
        List<String> nameList = new ArrayList<>();
        for (Field declaredField : declaredFields) {
            nameList.add(declaredField.getName());
        }

        return nameList;
    }

    /**
     * 获取对象中属性名对应的属性值
     * @param a
     * @param fieldName
     * @param <T>
     * @return
     */
    public static <T> Object getFieldValues(T a, String fieldName){
        Class<?> aClass = a.getClass();
        Object o = null;
        try {
            Field declaredField = aClass.getDeclaredField(fieldName);
            declaredField.setAccessible(true);
            o = declaredField.get(a);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return o == null ? "默认值": o;
    }

}
