package com.smile.service;

import com.smile.dao.CourseDetailDao;
import com.smile.entity.CourseDetail;

import java.util.List;
import java.util.Map;

public class CourseDetailService {
    //创建CourseDetailDao对象
    CourseDetailDao courseDetailDao = new CourseDetailDao();
    //添加课程明细方法
    public boolean addCourseDetail(CourseDetail courseDetail) {
        switch (courseDetail.getType()){
            case "1":
                courseDetail.setType("第一章");
                break;
            case "2":
                courseDetail.setType("第二章");
                break;
            case "3":
                courseDetail.setType("第三章");
                break;
            default:
                break;
        }
        return courseDetailDao.addCourseDetail(courseDetail)>0;
    }

    public Map<String, List<CourseDetail>> findCatalogue(String cid) {
        return courseDetailDao.findCatalogue(cid);
    }
}
