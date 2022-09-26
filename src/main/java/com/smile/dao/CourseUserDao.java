package com.smile.dao;

import com.smile.entity.Course;
import com.smile.entity.CourseUser;
import com.smile.entity.Orders;
import com.smile.entity.User;
import com.smile.untils.ListUntil;
import com.smile.untils.PageTool;

import java.util.List;

public class CourseUserDao extends BaseDao<CourseUser>{
    //获取数据总量
    public Object selectCourseUserCount(List<Object> uids) {
        String sql = "select count(*) from course_user";
        //判断uids是否为空，也就是是否存在模糊查询
        if (uids != null && uids.size() != 0){
            sql += " where uid in (" + ListUntil.listToString(uids) + ")";
        }
        return oneLineOneRow(sql);
    }

    //获取全部数据
    public List<CourseUser> findAllCourseUser(PageTool pageTool, List<Object> uids) {
        String sql = "select * from course_user";
        //判断uids是否为空，也就是是否存在模糊查询
        if (uids != null && uids.size() != 0){
            sql += " where uid in (" + ListUntil.listToString(uids) + ")";
        }
        sql += " limit ?,?";
        return manyRowManyLine(sql,CourseUser.class,pageTool.getStartIndex(),pageTool.getPageSize());
    }

    //通过模糊查询的姓名，查找对应的用户id
    public List<Object> findUidsByName(String search) {
        return manyLineOnerow("select distinct user.uid from user inner join course_user on " +
                "course_user.uid=user.uid where name like '%" + search + "%'");
    }

    //通过id删除选课信息
    public int deleteCourseUser(String ids) {
        return dml("delete from course_user where id in (" + ids + ")");
    }

    //修改选课的方法
    public int updateCourseUser(String id, String cid) {
        return dml("update course_user set cid = ? where id = ?",cid,id);
    }

    public CourseUser findCurrentCourseUser(String cid, Integer uid) {
        return oneLineManyrow("select * from course_user where cid = ? and uid = ?",CourseUser.class,cid,uid);
    }

    //添加选课信息的方法
    public int addCourseUser(Orders orders) {
        return dml("insert into course_user values (null,?,?)",orders.getCid(),orders.getUid());
    }
}
