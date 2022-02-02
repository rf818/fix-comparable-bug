package com.github.hcsp.polymorphism;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;

public class
User implements Comparable<User> {
    /**
     * 用户ID，数据库主键，全局唯一
     */
    private final Integer id;

    /**
     * 用户名
     */
    private final String name;

    public User(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User person = (User) o;

        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    /**
     * 老板说让我按照用户名排序
     */
    @Override
    public int compareTo(User o) {
        int compare1 = name.compareTo(o.name);
        int compare2 = id.compareTo(o.id);

        if (compare1 != 0) return compare1;//先比较name
        else{
            if (compare2 != 0) return compare2;//后比较id。不影响name排序，但是id不同则返回不同
            else return 0;
        }

        }




    public static void main(String[] args) {
        List<User> users =
                Arrays.asList(
                        new User(100, "b"),
                        new User(10, "z"),
                        new User(1, "a"),
                        new User(2000, "a"));
        TreeSet<User> treeSet = new TreeSet<>(users);
        // 为什么这里的输出是3？试着修复其中的bug
        System.out.println(treeSet.size());
    }
}
