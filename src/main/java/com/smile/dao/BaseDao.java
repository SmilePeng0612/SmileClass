package com.smile.dao;

import com.smile.untils.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class BaseDao<T> {
    //封装使用DbUtils + druid 完成的数据库操作
    //将QueryRunner创建在方法外面，以便在多个方法中都可以使用
    private QueryRunner qRunner = new QueryRunner(DruidUtils.getDataSource());

    //增删改的方法
    public int dml(String sql , Object...obj){
        int row = 0;
        try {
            row = qRunner.update(sql, obj);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return row;
    }

    //单行多列，一条记录
    public T oneLineManyrow (String sql,Class<T> clazz , Object...obj){
        T t = null;
        try {
            t = qRunner.query(sql, new BeanHandler<T>(clazz), obj);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return t;
    }

    //多行多列，多条记录
    public List<T> manyRowManyLine(String sql , Class<T> clazz , Object...obj){
        List<T> list = null;
        try {
            list = qRunner.query(sql, new BeanListHandler<T>(clazz), obj);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    //单行单列，单一数据
    public Object oneLineOneRow(String sql,Object...obj){
        Object object = null;
        try {
            object = qRunner.query(sql, new ScalarHandler(), obj);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return object;
    }

    //单列多行，一列数据
    public List<Object> manyLineOnerow(String sql,Object...obj){
        List<Object> list = null;
        try {
            list = qRunner.query(sql, new ColumnListHandler(), obj);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}
