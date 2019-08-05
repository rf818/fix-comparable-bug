package com.github.hcsp.polymorphism;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.TreeSet;

public class User implements Comparable<User> {
    /** 用户ID，数据库主键，全局唯一 */
    private final Integer id;

    /** 用户名 */
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

    // 方便查看集合中的对象打印结果
    @Override
    public String toString() {
        return "{ id: " + this.id + ", name: " + this.name + " }";
    }

    /** 老板说让我按照用户名排序 */
    @Override
    public int compareTo(User o) {
        // 下面被注释了的就是有 bug 的实现
        // return name.compareTo(o.name);

        // 下面尝试修复bug，核心思想：全等时才可以放心被集合去重，而只是 name 相等，则不做排序调整
        if (this.equals(o)) {
            return 0;
        } else if (name.equals(o.name)) {
            return -1;
        } else {
            return name.compareTo(o.name); // 只会处理大于或小于
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
        System.out.println(treeSet);
    }
}
