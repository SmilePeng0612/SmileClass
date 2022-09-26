package com.smile.servlet;

import com.alibaba.fastjson.JSON;
import com.smile.dao.CourseDao;
import com.smile.entity.Course;
import com.smile.entity.Orders;
import com.smile.entity.ResultBean;
import com.smile.entity.User;
import com.smile.service.CourseService;
import com.smile.untils.PageTool;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@WebServlet("/course")
@MultipartConfig
public class CourseServlet extends BaseServlet {
    //创建CourseService对象
    private CourseService courseService = new CourseService();
    //创建结果对象
    private ResultBean resultBean = null;

    //创建查询所有课程的方法
    private void findAllCourse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取模糊查询的数据
        String search = request.getParameter("search");
        //查询所有课程的总数量
        int totalCourse = courseService.selectTotalCourse(search);

        //获取到当前页码数和每页数据量
        String currentPage = request.getParameter("currentPage");
        String pageSize = request.getParameter("pageSize");

        //构建分页对象,传入三个参数
        PageTool pageTool = new PageTool(totalCourse, currentPage, Integer.valueOf(pageSize));

        //调用CourseService中的findAllCourse方法,传入pageTool参数
        List<Course> courses = courseService.findAllCourse(pageTool, search);

        //将数据储存到分页对象中
        pageTool.setList(courses);
        if (courses != null && courses.size() != 0) {
            resultBean = new ResultBean(200, "获取成功", pageTool);
        } else {
            resultBean = new ResultBean(500, "获取失败", null);
        }
        //转换为json，返回数据
        String s = JSON.toJSONString(resultBean);
        response.getWriter().write(s);
    }

    //上传文件的方法
    private void uploadFile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        /*
        上传文件的目的：
        1、获取上传文件的文件名
        2.将文件保存在本地中
        part对象中保存的就是上传的文件内容
        */

        //获取文件内容
        Part part = request.getPart("file");

        //获取文件名称
        String fileName = part.getSubmittedFileName();
        //解决文件同名问题
        fileName = UUID.randomUUID() + fileName;

        //构建储存上传文件的文件夹
        String dirPath = "/usr/local/softs/apache-tomcat-8.5.56/webapps/saveUploadFile";
        File file = new File(dirPath);
        //判断文件夹是否存在
        if (!file.exists()) {
            //如果不存在，就新建文件夹
            file.mkdirs();
        }

        //将part中的文件写入文件夹中生成文件
        part.write(dirPath + "/" + fileName);

        //判断文件的类型,通过截取文件名的后缀判断
        String type = fileName.substring(fileName.lastIndexOf(".") + 1);

        if (type.equals("jpg") || type.equals("png") || type.equals("jpeg") || type.equals("gif")) {
            //如果是图片，返回1和文件名
            resultBean = new ResultBean(1, null, fileName);
        } else {
            //否则返回2和文件名
            resultBean = new ResultBean(2, null, fileName);
        }
        //转换为json，返回数据
        String s = JSON.toJSONString(resultBean);
        response.getWriter().write(s);
    }

    //删除上传文件的方法
    private void deleteFile(HttpServletRequest request, HttpServletResponse response) {
        //获取需要删除的文件名
        String filename = request.getParameter("filename");

        //构建文件对象
        File file = new File("/usr/local/softs/apache-tomcat-8.5.56/webapps/saveUploadFile/" + filename);

        //删除文件
        file.delete();
    }

    //添加课程方法
    private void addCourse(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException, IOException {
        //获取课程信息
        Map<String, String[]> parameterMap = request.getParameterMap();
        //创建Course空参构造
        Course course = new Course();
        //封装信息
        BeanUtils.populate(course, parameterMap);
        //调用courseService中的addCourse方法,传递course参数
        boolean isSuccess = courseService.addCourse(course);
        if (isSuccess) {
            resultBean = new ResultBean(200, "添加课程成功", isSuccess);
        } else {
            resultBean = new ResultBean(500, "添加课程失败", isSuccess);
        }
        //转换为json，返回数据
        String s = JSON.toJSONString(resultBean);
        response.getWriter().write(s);
    }

    //删除课程的方法
    private void deleteCourse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取课程上传的文件名称和id
        String ids = request.getParameter("ids");//课程的id

        //通过id获取要删除的文件名
        List<Course> list = courseService.fileNameById(ids);

        //调用courseService中的deleteCourse方法,传递ids参数
        boolean isSuccess = courseService.deleteCourse(ids);

        if (isSuccess) {
            //判断list集合是否存在元素
            if (list.size() != 0) {
                //遍历list集合
                for (Course l : list) {
                    //判断哪个元素由内容
                    if (l.getCourseImage() != null) {
                        //获取需要删除的文件名
                        String imageFileName = l.getCourseImage();
                        //构建文件对象
                        File imageFile = new File("/usr/local/softs/apache-tomcat-8.5.56/webapps/saveUploadFile/" + imageFileName);
                        //删除文件
                        imageFile.delete();
                    }
                    if (l.getCourseVideo() != null) {
                        //获取需要删除的文件名
                        String videoFileName = l.getCourseVideo();
                        //构建文件对象
                        File videoFile = new File("/usr/local/softs/apache-tomcat-8.5.56/webapps/saveUploadFile/" + videoFileName);
                        //删除文件
                        videoFile.delete();
                    }
                }
            }
            resultBean = new ResultBean(200, "删除课程成功", isSuccess);
        } else {
            resultBean = new ResultBean(500, "删除课程失败", isSuccess);
        }
        //转换为json，返回数据
        String s = JSON.toJSONString(resultBean);
        response.getWriter().write(s);
    }

    //修改课程信息的方法
    private void updateCourse(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException, IOException {
        //获取请求的信息
        Map<String, String[]> parameterMap = request.getParameterMap();

        //获取老图片文件名
        String oldCourseImage = request.getParameter("oldCourseImage");
        //获取老视频文件名
        String oldCourseVideo = request.getParameter("oldCourseVideo");

        //获取新图片文件名
        String courseImage = request.getParameter("courseImage");
        //获取新视频文件名
        String courseVideo = request.getParameter("courseVideo");

        //判断哪个元素由内容
        if (!courseImage.equals(oldCourseImage)) {
            //获取需要删除的文件名
            String imageFileName = oldCourseImage;
            //构建文件对象
            File imageFile = new File("F:/saveUploadFile/" + imageFileName);
            //删除文件
            imageFile.delete();
        }
        if (!courseVideo.equals(oldCourseVideo)) {
            //获取需要删除的文件名
            String videoFileName = oldCourseVideo;
            //构建文件对象
            File videoFile = new File("F:/saveUploadFile/" + videoFileName);
            //删除文件
            videoFile.delete();
        }

        //构建Course空参构造
        Course course = new Course();

        BeanUtils.populate(course, parameterMap);

        boolean isSuccess = courseService.updateCourse(course);
        if (isSuccess) {
            resultBean = new ResultBean(200, "修改成功", isSuccess);
        } else {
            resultBean = new ResultBean(500, "修改失败", isSuccess);
        }
        //转换为json数据
        String s = JSON.toJSONString(resultBean);
        response.getWriter().write(s);
    }

    //通过课程类型查找课程
    private void findCourseByType(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取课程类型和数量
        String courseType = request.getParameter("courseType");
        String count = request.getParameter("count");

        //调用CourseService中的findCourseByType方法
        List<Course> courses = courseService.findCourseByType(courseType, count);

        //封装数据
        resultBean = new ResultBean(200, null, courses);

        //转换为json数据
        String s = JSON.toJSONString(resultBean);
        response.getWriter().write(s);
    }

    //获取全部课程页面的数据
    private void findOnlineCourse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取到课程类型
        String courseType = request.getParameter("courseType");

        //获取到模糊查询内容
        String search = request.getParameter("search");

        //获取总数据量
        int totalCount = courseService.selectOnlineCourseCount(courseType, search);
        //从页面请求中获取当前页码
        String currentPage = request.getParameter("currentPage");

        //创建PageTool对象
        PageTool pageTool = new PageTool(totalCount, currentPage, 6);

        //调用courseService中的findOnlineCourse方法
        List<Course> courses = courseService.findOnlineCourse(pageTool, courseType, search);

        //把courses封装于pageTool中
        pageTool.setList(courses);

        //封装数据
        resultBean = new ResultBean(200, null, pageTool);
        //转换为json数据
        String s = JSON.toJSONString(resultBean);
        response.getWriter().write(s);
    }

    //通过id查询课程
    private void findCourseByCid(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //先获取cid
        String cid = request.getParameter("cid");

        //调用courseService中的findCourseByCid方法
        Course course = courseService.findCourseByCid(cid);

        //存储数据
        resultBean = new ResultBean(200, null, course);
        //转换为json数据
        String s = JSON.toJSONString(resultBean);
        response.getWriter().write(s);
    }

    //生成订单的方法
    private void createOrders(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取到请求中的cid和price
        String price = request.getParameter("price");
        String cid = request.getParameter("cid");
        //获取会话中的user对象
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        System.out.println("订单中的" + session);
        //使用UUID生成随机订单编号
        String oid = UUID.randomUUID().toString();
        //构建订单对象
        Orders orders = new Orders(oid, user.getName() + "的订单",
                Double.valueOf(price), Integer.valueOf(cid), user.getUid());
        session.setAttribute("orders",orders);
        response.sendRedirect("pay/index.jsp");
    }
}
