package com.smile.service;

import com.smile.dao.CourseDao;
import com.smile.dao.CourseDetailDao;
import com.smile.entity.Course;
import com.smile.entity.CourseDetail;
import com.smile.untils.PageTool;

import java.util.List;

public class CourseService {
    //创建CourseDao对象
    CourseDao courseDao = new CourseDao();

    CourseDetailDao courseDetailDao = new CourseDetailDao();
    //查询课程总数量
    public int selectTotalCourse(String search) {
        long count = (Long)courseDao.selectTotalCourse(search);
        return (int)count;
    }

    //查询所有课程信息
    public List<Course> findAllCourse(PageTool pageTool, String search) {
        return courseDao.findAllCourse(pageTool,search);
    }

    //添加课程的方法
    public boolean addCourse(Course course) {
        return courseDao.addCourse(course)>0;
    }

    //删除课程的方法
    public boolean deleteCourse(String ids) {
        return courseDao.deleteCourse(ids)>0;
    }

    //通过id获取到文件名
    public List<Course> fileNameById(String ids) {
        return courseDao.fileNameById(ids);
    }

    //修改课程的方法
    public boolean updateCourse(Course course) {
        return courseDao.updateCourse(course)>0;
    }

    //不分页获取所有课程
    public List<Course> findCourse(String uid, String cid) {
        return courseDao.findCourse(uid,cid);
    }

    public List<Course> findCourseByType(String courseType, String count) {
        return courseDao.findCourseByType(courseType,count);
    }
    //获取课程总数据量
    public int selectOnlineCourseCount(String courseType, String search) {
        long count = (Long)courseDao.selectOnlineCourseCount(courseType,search);
        return (int)count;
    }

    //获取说有课程的方法
    public List<Course> findOnlineCourse(PageTool pageTool,String courseType, String search) {
        return courseDao.findOnlineCourse(pageTool,courseType,search);
    }

    //通过id查询课程的方法
    public Course findCourseByCid(String cid) {
        Course course = courseDao.findCourseByCid(Integer.valueOf(cid));
        //为当前课程的所有明细列表赋值
        List<CourseDetail> cds = courseDetailDao.findDetailByCid(cid);
        course.setCds(cds);
        return course;
    }
}
