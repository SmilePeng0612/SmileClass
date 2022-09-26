package com.smile.servlet;

import com.alibaba.fastjson.JSON;
import com.smile.entity.CourseDetail;
import com.smile.entity.ResultBean;
import com.smile.service.CourseDetailService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@WebServlet("/courseDetail")
public class CourseDetailServlet extends BaseServlet{
    //创建CourseDetailService对象
    private CourseDetailService courseDetailService = new CourseDetailService();
    //创建结果集对象
    private ResultBean resultBean = null;

    //添加课程明细的方法
    private void addCourseDetail (HttpServletRequest request , HttpServletResponse response) throws InvocationTargetException, IllegalAccessException, IOException {
        //获取数据
        Map<String, String[]> parameterMap = request.getParameterMap();
        //创建courseDetail空参构造
        CourseDetail courseDetail = new CourseDetail();
        //封装数据
        BeanUtils.populate(courseDetail,parameterMap);

        //调用CourseDetailService对象中的addCourseDetail对象，传入courseDetail对象
        boolean isSuccess = courseDetailService.addCourseDetail(courseDetail);
        if (isSuccess){
            resultBean = new ResultBean(200,"添加成功",isSuccess);
        }else {
            resultBean = new ResultBean(500,"添加失败",isSuccess);
        }
        //转换json格式
        String s = JSON.toJSONString(resultBean);
        response.getWriter().write(s);
    }

    //获取当前课程目录的方法
    private void findCatalogue (HttpServletRequest request , HttpServletResponse response) throws IOException {
        //获取cid
        String cid = request.getParameter("cid");

        //调用courseDetailService中的findCatalogue方法
        Map<String, List<CourseDetail>> map = courseDetailService.findCatalogue(cid);

        //封装数据
        resultBean = new ResultBean(200,"获取成功",map);
        //转换json格式
        String s = JSON.toJSONString(resultBean);
        response.getWriter().write(s);
    }
}
