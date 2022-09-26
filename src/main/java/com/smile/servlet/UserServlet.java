package com.smile.servlet;

import com.alibaba.fastjson.JSON;
import com.smile.entity.ResultBean;
import com.smile.entity.User;
import com.smile.service.UserService;
import com.smile.untils.PageTool;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@WebServlet("/user")
public class UserServlet extends BaseServlet {
    //创建UserService对象
    private UserService userService = new UserService();
    //创建结果集对象
    private ResultBean resultBean = null;

    //后台登录验证方法
    private void adminLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取用户信息
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String imageCode = request.getParameter("imageCode");

        //取出之前已经储存到会话中生成的验证码
        HttpSession session = request.getSession();
        //取出的会话对象为Object类型，需要强转为String类型
        String verCode = (String) session.getAttribute("verCode");

        //与现在请求传入的验证码imageCode进行比较
        if (imageCode.equalsIgnoreCase(verCode)) {
            //验证成功
            //创建User对象，传两个参数username和password，储存数据
            User user = new User(username, password);
            //调用UserService中的adminLogin方法，返回验证账号密码是否正确
            //获取用户信息，先储存用户信息
            boolean isSuccess = userService.adminLogin(user, session);
            if (isSuccess) {
                //账号密码正确
                resultBean = new ResultBean(200, "登录成功", isSuccess);
            } else {//账号密码错误
                resultBean = new ResultBean(500, "用户名或密码错误！", isSuccess);
            }
        } else {//验证失败返回错误信息
            resultBean = new ResultBean(500, "验证码输入错误！", null);
        }
        //转换为json数据
        String s = JSON.toJSONString(resultBean);
        response.getWriter().write(s);
    }

    //获取当前登录的用户信息
    private void findCurrentUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取储存在会话中的用户信息
        HttpSession session = request.getSession();
        //取出的会话对象为Object类型，需要强转为User类型
        User user = (User) session.getAttribute("user");
        //判断用户是否存在
        if (user != null) {
            resultBean = new ResultBean(200, "获取用户成功", user);
        } else {
            resultBean = new ResultBean(500, "您还未登录，请先登录！", user);
        }
        //转换为json数据
        String s = JSON.toJSONString(resultBean);
        response.getWriter().write(s);
    }

    //退出登录界面，删除会话数据
    private void exitLogin(HttpServletRequest request, HttpServletResponse response) {
        //获取会话，清空数据
        HttpSession session = request.getSession();
        session.invalidate();//销毁当前会话
    }

    //获取所有用户的方法
    private void findAllUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //先获取模糊查询的内容
        String search = request.getParameter("search");
        //查询数据库得到数据总量
        int totalCount = userService.selectUserCount(search);
        //从请求中获取到当前页数，每页显示的记录数
        String currentPage = request.getParameter("currentPage");//当前页数
        String pageSize = request.getParameter("pageSize");//每页显示的记录数
        //创建分页对象
        PageTool pageTool = new PageTool(totalCount, currentPage, Integer.valueOf(pageSize));

        //调用UserService中的findAllUser方法,传入pageTool
        List<User> users = userService.findAllUser(pageTool, search);

        //将得到的用户信息储存到分页对象中
        pageTool.setList(users);

        if (users != null && users.size() != 0) {
            resultBean = new ResultBean(200, "用户获取成功", pageTool);
        } else {
            resultBean = new ResultBean(500, "用户获取失败", pageTool);
        }
        //转换为json数据
        String s = JSON.toJSONString(resultBean);
        response.getWriter().write(s);
    }

    //添加用户方法
    private void addUser(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException, IOException {
        //获取数据，封装对象
        Map<String, String[]> parameterMap = request.getParameterMap();
        String[] names = parameterMap.get("name");
        System.out.println(names);
        //使用User空参构造创建对象
        User user = new User();
        BeanUtils.populate(user, parameterMap);

        //调用UserService中的addUser方法
        boolean isSuccess = userService.addUser(user);
        if (isSuccess) {
            resultBean = new ResultBean(200, "添加成功", isSuccess);
        } else {
            resultBean = new ResultBean(500, "添加失败", isSuccess);
        }
        //转换为json数据
        String s = JSON.toJSONString(resultBean);
        response.getWriter().write(s);
    }

    //删除用户的方法
    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取请求的数据：ids
        String ids = request.getParameter("ids");
        //调用userService中的deleteUser方法，传入ids参数
        boolean isSuccess = userService.deleteUser(ids);

        if (isSuccess) {
            resultBean = new ResultBean(200, "删除成功", isSuccess);
        } else {
            resultBean = new ResultBean(500, "删除失败", isSuccess);
        }
        //转换为json数据
        String s = JSON.toJSONString(resultBean);
        response.getWriter().write(s);
    }

    //修改用户的方法
    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws InvocationTargetException, IllegalAccessException, IOException {
        //获取用户信息封装对象
        Map<String, String[]> parameterMap = request.getParameterMap();

        //构建User空参构造
        User user = new User();

        BeanUtils.populate(user, parameterMap);
        //调用userService中的updateUser方法，传入user参数
        boolean isSuccess = userService.updateUser(user);
        if (isSuccess) {
            resultBean = new ResultBean(200, "修改成功", isSuccess);
        } else {
            resultBean = new ResultBean(500, "修改失败", isSuccess);
        }
        //转换为json数据
        String s = JSON.toJSONString(resultBean);
        response.getWriter().write(s);
    }

    //手机号唯一的校验方法
    private void checkPhone(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取输入的手机号
        String phone = request.getParameter("phone");

        //调用UserService中的checkPhone方法
        boolean isSuccess = userService.checkPhone(phone);

        //封装数据
        resultBean = new ResultBean(200,null,isSuccess);

        //转换为JSON格式
        String s = JSON.toJSONString(resultBean);
        response.getWriter().write(s);
    }

    //验证码校验的方法
    private void checkCode (HttpServletRequest request , HttpServletResponse response) throws IOException {
        //获取页面输入的验证码
        String code = request.getParameter("code");

        //从会话中获取生成时保存的验证码
        HttpSession session = request.getSession();
        String verCode = (String)session.getAttribute("verCode");
        boolean b = code.equalsIgnoreCase(verCode);
        //封装数据
        resultBean = new ResultBean(200,null,b);

        //转换为JSON格式
        String s = JSON.toJSONString(resultBean);
        response.getWriter().write(s);
    }

    //用户注册的方法
    private void userRegist (HttpServletRequest request , HttpServletResponse response) throws InvocationTargetException, IllegalAccessException, IOException {
        //获取请求的数据
        Map<String, String[]> parameterMap = request.getParameterMap();
        //创建User对象
        User user = new User();
        BeanUtils.populate(user,parameterMap);
        //调用UserService中的userRegist方法
        boolean isSuccess = userService.userRegist(user);
        if (isSuccess){
            resultBean = new ResultBean(200,"注册成功",isSuccess);
        }else{
            resultBean = new ResultBean(500,"注册失败，请检查信息",isSuccess);
        }
        //转换为JSON格式
        String s = JSON.toJSONString(resultBean);
        response.getWriter().write(s);
    }

    //用户登录的方法
    private void userLogin (HttpServletRequest request,HttpServletResponse response) throws IOException {
        //获取手机号和密码
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");

        //获取会话对象
        HttpSession session = request.getSession();
        System.out.println("用户登录的" + session);
        //调用UserService中的userLogin方法
        boolean isSuccess = userService.userLogin(phone,password,session);

        resultBean = new ResultBean(200,null,isSuccess);
        //转换为JSON格式
        String s = JSON.toJSONString(resultBean);
        response.getWriter().write(s);
    }

}
