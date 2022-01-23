package com.wzz.gulimall.common.config;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        List<String> lis2 = new ArrayList<>();
        lis2.add("c");
        lis2.add("d");
        User user = new User("aa","bbb",list);
        User user2 = new User("aa1","bbb1",lis2);
        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user2);
        StringBuffer sb = new StringBuffer("");
        for (User user1 : userList) {
            sb.append(user1.getFff());
            sb.append("  ");
        }
        String replace = sb.toString().replace("[", "").replace("]", "");
        System.out.println(replace);
    }
}
