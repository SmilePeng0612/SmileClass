package com.smile.dao;

import com.smile.entity.User;
import com.smile.untils.PageTool;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

public class UserDao extends BaseDao<User>{
    //后台登录方法
    //需要验证用户名、密码、启用状态、用户权限
    public User adminLogin(User user) {
        return oneLineManyrow("select * from user where username=? and password=? and status=1 and role=1",
                User.class,user.getUsername(),user.getPassword());
    }

    //获取用户总量
    public Object totalCount(String search) {
        //定义查询sql语句
        String sql = "select count(*) from user";
        //当存在模糊查询时，改变sql语句
        if (search != null){
            sql += " where name like '%" + search + "%'";
        }
        return oneLineOneRow(sql);
    }

    //获取所有用户信息
    public List<User> findAllUser(PageTool pageTool, String search) {
        //定义查询sql语句
        String sql = "select * from user";
        //当存在模糊查询时，改变sql语句
        if (search != null){
            sql += " where name like '%" + search + "%'";
        }
        sql += " limit ?,?";
        return manyRowManyLine(sql,User.class,
                pageTool.getStartIndex(),pageTool.getPageSize());
    }

    public int addUser(User user) {
        return dml("insert into user values (null,?,?,?,?,?,?,?,now(),?,null)",
                user.getName(),user.getPhone(),user.getAge(),user.getSex(),
                user.getUsername(),user.getPassword(),user.getStatus(),user.getRole());
    }
    //删除用户
    public int deleteUser(String ids) {
        return dml("delete from user where uid in (" + ids + ")");
    }

    //修改用户的方法
    public int updateUser(User user) {
        return dml("update user set name = ?,phone = ?,age = ?,sex = ?,username = ?,password = ?," +
                "status = ?,role = ? where uid = ?",user.getName(),user.getPhone(),user.getAge(),user.getSex(),
                user.getUsername(),user.getPassword(),user.getStatus(),user.getRole(),user.getUid());
    }

    //通过Id查找用户
    public User findUserByUid(Integer uid) {
        return oneLineManyrow("select * from user where uid = ?",User.class,uid);
    }

    //验证手机号唯一
    public User checkPhone(String phone) {
        return oneLineManyrow("select * from user where phone = ?",User.class,phone);
    }

    //用户注册的方法
    public int userRegist(User user) {
        return dml("insert into user (uid,name,phone,password,status,createtime,role) values (null,?,?,?,1,now(),3)",
                user.getName(),user.getPhone(),user.getPassword());
    }

    public User userLogin(String phone, String password) {
        return oneLineManyrow("select * from user where phone = ? and password = ?",User.class,phone,password);
    }
}
