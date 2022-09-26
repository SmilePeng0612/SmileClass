package com.smile.service;

import com.smile.dao.CourseDao;
import com.smile.dao.CourseUserDao;
import com.smile.dao.UserDao;
import com.smile.entity.Course;
import com.smile.entity.CourseUser;
import com.smile.entity.Orders;
import com.smile.entity.User;
import com.smile.untils.PageTool;

import java.util.List;

public class CourseUserService {
    //创建CourseUserDao对象
    private CourseUserDao courseUserDao = new CourseUserDao();
    //创建CourseDao对象
    private CourseDao courseDao = new CourseDao();
    //创建UserDao对象
    private UserDao userDao = new UserDao();
    //获取数据总量
    public int selectCourseUserCount(List<Object> uids) {
        long count = (Long)courseUserDao.selectCourseUserCount(uids);
        return (int)count;
    }

    public List<CourseUser> findAllCourseUser(PageTool pageTool, List<Object> uids) {
        List<CourseUser> courseUsers = courseUserDao.findAllCourseUser(pageTool,uids);
        //Dao中查询的数据是uid和cid，返回给Servlet中的数据需要是User和Course，需要遍历得到的集合，通过遍历得到的id获取到对应的数据
        for (CourseUser courseUser : courseUsers) {
            //通过cid得到课程
            Course course = courseDao.findCourseByCid(courseUser.getCid());
            //通过uid得到用户
            User user = userDao.findUserByUid(courseUser.getUid());
            //对属性进行赋值
            courseUser.setCourse(course);
            courseUser.setUser(user);
        }
        return courseUsers;
    }

    //通过模糊查询的姓名，查找对应的用户id
    public List<Object> findUidsByName(String search) {
        return courseUserDao.findUidsByName(search);
    }

    //删除选课的方法
    public boolean deleteCourseUser(String ids) {
        return courseUserDao.deleteCourseUser(ids)>0;
    }

    //修改选课的方法
    public boolean updateCourseUser(String id, String cid) {
        return courseUserDao.updateCourseUser(id,cid)>0;
    }

    public boolean findCurrentCourseUser(String cid, Integer uid) {
        return courseUserDao.findCurrentCourseUser(cid,uid)!=null;
    }

    //添加选课信息的方法
    public boolean addCourseUser(Orders orders) {
        return courseUserDao.addCourseUser(orders)>0;
    }
}
