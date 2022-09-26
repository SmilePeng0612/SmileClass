package com.smile.service;

import com.smile.dao.UserDao;
import com.smile.entity.User;
import com.smile.untils.PageTool;

import javax.servlet.http.HttpSession;
import java.util.List;

public class UserService {
    //创建UserDao对象
    private UserDao userDao = new UserDao();

    //后台登录方法
    public boolean adminLogin(User user, HttpSession session) {
        User newUser = userDao.adminLogin(user);
        if (newUser != null) {
            //将用户信息储存到会话对象中
            session.setAttribute("user", newUser);
            return true;
        } else {
            return false;
        }
    }

    //获取用户总量
    public int selectUserCount(String search) {
        long count = (long) userDao.totalCount(search);
        return (int) count;
    }

    //获取所有用户信息
    public List<User> findAllUser(PageTool pageTool, String search) {
        return userDao.findAllUser(pageTool, search);
    }

    public boolean addUser(User user) {
        return userDao.addUser(user) > 0;
    }

    //删除用户的方法
    public boolean deleteUser(String ids) {
        return userDao.deleteUser(ids) > 0;
    }

    //修改用户的方法
    public boolean updateUser(User user) {
        return userDao.updateUser(user) > 0;
    }

    //验证手机号唯一
    public boolean checkPhone(String phone) {
        return userDao.checkPhone(phone)!=null;
    }

    //用户注册的方法
    public boolean userRegist(User user) {
        return userDao.userRegist(user)>0;
    }

    //用户登录的方法
    public boolean userLogin(String phone, String password, HttpSession session) {
        User user = userDao.userLogin(phone,password);
        if (user != null){
            //将用户储存在会话对象中
            session.setAttribute("user",user);
            return true;
        }
        return false;
    }
}
