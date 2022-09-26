package com.smile.dao;

import com.smile.entity.CourseDetail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseDetailDao extends BaseDao<CourseDetail>{
    public int addCourseDetail(CourseDetail courseDetail) {
        return dml("insert into coursedetail values (null,?,?,?,?,?)",
                courseDetail.getName(),courseDetail.getType(),
                courseDetail.getUrl(),courseDetail.getStart_data(),courseDetail.getCid());
    }

    public List<CourseDetail> findDetailByCid(String cid) {
        return manyRowManyLine("select * from coursedetail where cid = ? order by start_data asc",
                CourseDetail.class,cid);
    }

    public Map<String, List<CourseDetail>> findCatalogue(String cid) {
        //先创建一个Map集合
        Map<String, List<CourseDetail>> map = new HashMap<String, List<CourseDetail>>();
        //通过cid获取当前课程有多少章节
        List<Object> types = manyLineOnerow("select distinct type from coursedetail where cid = ?",cid);
        //遍历章节列表，通过章节获取到对应的明细列表
        if (types != null && types.size() != 0){
            for (Object type : types) {
                //转换章节类型
                String t = (String)type;
                List<CourseDetail> courseDetails = manyRowManyLine("select * from coursedetail where type = ? and cid = ?",
                        CourseDetail.class,t,cid);
                //存入Map集合中
                map.put(t,courseDetails);
            }
        }
        return map;
    }
}
