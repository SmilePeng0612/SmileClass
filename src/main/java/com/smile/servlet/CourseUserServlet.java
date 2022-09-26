package com.smile.servlet;

import com.alibaba.fastjson.JSON;
import com.smile.dao.UserDao;
import com.smile.entity.*;
import com.smile.service.CourseService;
import com.smile.service.CourseUserService;
import com.smile.untils.PageTool;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/courseUser")
public class CourseUserServlet extends BaseServlet{
    //创建CourseUserService对象
    private CourseUserService courseUserService = new CourseUserService();
    //创建结果集
    private ResultBean resultBean = null;

    //创建CourseService对象
    CourseService courseService = new CourseService();

    //查询所有选课信息的方法
    private void findAllCourseUser (HttpServletRequest request , HttpServletResponse response) throws IOException {
        //先获取模糊查询的姓名
        String search = request.getParameter("search");
        //模糊查询的内容是选课的姓名，但是获取的只有id，所以只能先通过姓名获取对应的id

        List<Object> uids = null;
        //先判断是否为空
        if (search != null){
            //获取到的是一列数据,调用CourseUserService中的findUidsByName方法
            uids = courseUserService.findUidsByName(search);
        }
        //获取数据总量
        int totalCount = courseUserService.selectCourseUserCount(uids);

        //获取当前页数和每页数据量
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");

        //构建分页对象
        PageTool pageTool = new PageTool(totalCount,currentPage,Integer.valueOf(pageSize));

        //调用CourseUserService中的findAllCourseUser方法
        List<CourseUser> courseUsers = courseUserService.findAllCourseUser(pageTool,uids);

        //讲数据储存到分页对象中
        pageTool.setList(courseUsers);

        if (courseUsers != null && courseUsers.size() != 0){
            resultBean = new ResultBean(200,"获取成功",pageTool);
        }else{
            resultBean = new ResultBean(500,"获取失败",null);
        }
        //转换json格式
        String s = JSON.toJSONString(resultBean);
        response.getWriter().write(s);
    }

    //通过id删除选课信息
    private void deleteCourseUser (HttpServletRequest request,HttpServletResponse response) throws IOException {
        //获取id
        String ids = request.getParameter("ids");

        //调用CourseUserService中的deleteCourseUser方法
        boolean isSuccess = courseUserService.deleteCourseUser(ids);
        if (isSuccess){
            resultBean = new ResultBean(200,"删除成功",isSuccess);
        }else{
            resultBean = new ResultBean(500,"删除失败",isSuccess);
        }
        //转换为json数据
        String s = JSON.toJSONString(resultBean);
        response.getWriter().write(s);
    }
    
    //获取所有课程的方法
    private void findCourse (HttpServletRequest request,HttpServletResponse response) throws IOException {
        //获取到uid和cid
        String uid = request.getParameter("uid");
        String cid = request.getParameter("cid");

        //调用CourseService中的findCourse方法
        List<Course> courses = courseService.findCourse(uid,cid);

        //储存数据
        resultBean = new ResultBean(200,"课程获取成功",courses);

        //转换为JSON格式
        String s = JSON.toJSONString(resultBean);
        response.getWriter().write(s);
    }

    //修改选课的方法
    private void updateCourseUser (HttpServletRequest request,HttpServletResponse response) throws IOException {
        //获取id和cid
        String id = request.getParameter("id");
        String cid = request.getParameter("cid");

        //调用CourseUserService中的updateCourseUser方法
        boolean isSuccess = courseUserService.updateCourseUser(id,cid);

        if (isSuccess){
            resultBean = new ResultBean(200,"修改成功",isSuccess);
        }else{
            resultBean = new ResultBean(500,"修改失败",isSuccess);
        }
        //转换为JSON格式
        String s = JSON.toJSONString(resultBean);
        response.getWriter().write(s);
    }

    //获取当前选课记录的方法
    private void findCurrentCourseUser (HttpServletRequest request , HttpServletResponse response) throws IOException {
        //获取cid
        String cid = request.getParameter("cid");
        //从会话中获取User对象
        HttpSession session = request.getSession();
        System.out.println("获取选课信息的" + session);
        User user = (User)session.getAttribute("user");
        //判断user是否为空，也就是是否登录
        if (user != null){
            //不为空，也就是已登录，调用courseUserService中的findCurrentCourseUser方法
            boolean isSuccess = courseUserService.findCurrentCourseUser(cid,user.getUid());
            //判断isSuccess，表示是否已经选课，是否已经购买课程
            if (isSuccess){
                resultBean = new ResultBean(200,"已选课",isSuccess);
            }else{
                resultBean = new ResultBean(500,"未选课",isSuccess);
            }
        }else{
            //表示未登录
            resultBean = new ResultBean(500,"未登录",null);
        }
        //转换为JSON格式
        String s = JSON.toJSONString(resultBean);
        response.getWriter().write(s);
    }

    //添加选课信息的方法
    private void addCourseUser (HttpServletRequest request , HttpServletResponse response) throws IOException {
        //从会话中获取订单信息
        HttpSession session = request.getSession();
        System.out.println("添加选课的" + session);
        Orders orders = (Orders) session.getAttribute("orders");
        //调用CourseUserService中的addCourseUser方法
        boolean isSuccess = courseUserService.addCourseUser(orders);
        if (isSuccess){
            response.sendRedirect("http://192.168.187.128:8080/SmileClassRoom/eduFront/pages/videoDetail.html?cid="+orders.getCid());
        }
    }
}
