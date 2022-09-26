package com.smile.untils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.util.Properties;

/*
     这个类的作用只有一个，就是获取Druid数据库连接池在程序中对应的数据源DataSource对象！
     工具类在设计时：
     1、方法尽量都使用类方法，static修饰，方便外界调用
     2、方法内一旦产生了异常，使用try...catch，不要throws抛出
 */
public class DruidUtils {
    private static DataSource dataSource = null;

    //因为数据源的加载只需要一次(连接池只需要一个就够了！),我们放在静态代码块中加载
    static {
        try {
            //创建输入流读取druid的配置文件
            //获取当前类加载器的输入流，来获取文件！该流获取的就是src下的资源！
            InputStream iStream = DruidUtils.class.getClassLoader().getResourceAsStream("druid.properties");

            //创建属性文件对象
            Properties properties = new Properties();

            //将输入流读取到的文件信息，加载到属性文件对象中
            properties.load(iStream);

            //将属性文件对象传递到创建数据源的方法中，此时就可以使用配置文件中的连接信息了！
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //返回数据源的方法
    public static DataSource getDataSource() {
        return dataSource;
    }
}
