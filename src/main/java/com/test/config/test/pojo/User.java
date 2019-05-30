package com.test.config.test.pojo;

public class User {
    private String name = "2";
    private String age = "1";



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return name +"_"+ age;
    }
}
