package com.smile.entity;

import java.util.Date;

public class User {
    private Integer uid;//用户主键ID
    private String name;//用户姓名
    private String phone;//用户手机号
    private Integer age;//用户年龄
    private Integer sex;//用户性别 1表示男 0表示女
    private String username;//用户名
    private String password;//用户密码
    private Integer status;//用户启用状态 1表示启用 2表示注销
    private String createtime;//用户注册时间
    private Integer role;//用户权限 1表示管理员 2表示总经理 3表示普通用户
    private String picture;//用户头像

    //空参构造
    public User() {
    }

    //用户名和密码构造函数
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", createtime=" + createtime +
                ", role=" + role +
                ", picture='" + picture + '\'' +
                '}';
    }
}
