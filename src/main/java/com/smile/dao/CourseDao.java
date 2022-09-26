package com.smile.dao;

import com.smile.entity.Course;
import com.smile.untils.PageTool;

import java.util.List;

public class CourseDao extends BaseDao<Course>{
    //查询课程的总数据量
    public Object selectTotalCourse(String search) {
        String sql = "select count(*) from course";
        //判断是否有模糊查询
        if (search != null){
            sql += " where courseName like '%" + search + "%'";
        }
        return oneLineOneRow(sql);
    }
//查询所有课程信息
    public List<Course> findAllCourse(PageTool pageTool, String search) {
        String sql = "select * from course";
        //判断是否有模糊查询
        if (search != null){
            sql += " where courseName like '%" + search + "%'";
        }
        sql += " limit ? , ?";
        return manyRowManyLine(sql,
                Course.class,pageTool.getStartIndex(),pageTool.getPageSize());
    }

    //添加课程
    public int addCourse(Course course) {
        return dml("insert into course values (null,?,?,?,?,?,?,?,now())",
                course.getCourseName(),
                course.getDescs(),
                course.getCourseType(),
                course.getCourseImage(),
                course.getCourseVideo(),
                course.getCoursePrice(),
                course.getStatus());
    }

    //删除课程
    public int deleteCourse(String ids) {
        return dml("delete from course where cid in (" + ids + ")");
    }

    //通过id获取文件名
    public List<Course> fileNameById(String ids) {
        return manyRowManyLine("select courseImage,courseVideo from course WHERE cid IN (" + ids + ")",Course.class);
    }

    public int updateCourse(Course course) {
        return dml("update course set courseName = ? , descs = ? , courseType = ? ," +
                " courseImage = ? , courseVideo = ? , coursePrice = ? , status = ? where cid = ?",
                course.getCourseName(),
                course.getDescs(),
                course.getCourseType(),
                course.getCourseImage(),
                course.getCourseVideo(),
                course.getCoursePrice(),
                course.getStatus(),
                course.getCid());
    }

    //通过uid查找数据的方法
    public Course findCourseByCid(Integer cid) {
        return oneLineManyrow("select * from course where cid = ?",Course.class,cid);
    }

    //不分页获取所有课程的方法
    public List<Course> findCourse(String uid, String cid) {
        return manyRowManyLine("select * from course where status = 1 " +
                "and cid not in (select cid from course_user where uid = ? and cid != ?)",
                Course.class,uid,cid);
    }

    //通过课程类型查找课程
    public List<Course> findCourseByType(String courseType, String count) {
        return manyRowManyLine("select * from course where courseType = ? and status = 1 limit ?",
                Course.class,courseType,Integer.valueOf(count));
    }
    //获取课程总数据量
    public Object selectOnlineCourseCount(String courseType, String search) {
        String sql = "select count(*) from course where status = 1";
        //判断是否有类型选择
        if (Integer.valueOf(courseType) != 0){
            sql += " and courseType = " + courseType;
        }
        //判断是否有模糊查询
        if (search != null){
            sql += " and courseName like '%" + search +"%'";
        }
        return oneLineOneRow(sql);
    }

    //全部课程页面获取所有课程的方法
    public List<Course> findOnlineCourse(PageTool pageTool,String courseType, String search) {
        String sql = "select * from course where status = 1";
        //判断是否有类型选择
        if (Integer.valueOf(courseType) != 0){
            sql += " and courseType = " + courseType;
        }
        //判断是否有模糊查询
        if (search != null){
            sql += " and courseName like '%" + search +"%'";
        }
        sql += " limit ?,?";
        return manyRowManyLine(sql,Course.class,pageTool.getStartIndex(),pageTool.getPageSize());
    }
}
