package com.smile.entity;

import java.util.List;

public class Course {
    private Integer cid;//课程id
    private String courseName;//课程名称
    private String descs;//课程简介
    private Integer courseType;//课程类别 1：java课程 2：数据库课程 3：前端课程
    private String courseImage;//课程的图片名称
    private String courseVideo;//课程的视频名称
    private Double coursePrice;//课程的价格
    private Integer status;//课程的状态 1：未上架 2：上架 3：下架
    private String createTime;//课程的添加时间

    //储存所有课程的明细
    private List<CourseDetail> cds;

    public List<CourseDetail> getCds() {
        return cds;
    }

    public void setCds(List<CourseDetail> cds) {
        this.cds = cds;
    }

    public Course() {
    }

    public Course(Integer cid, String courseName, String descs, Integer courseType, String courseImage, String courseVideo, Double coursePrice, Integer status, String createTime) {
        this.cid = cid;
        this.courseName = courseName;
        this.descs = descs;
        this.courseType = courseType;
        this.courseImage = courseImage;
        this.courseVideo = courseVideo;
        this.coursePrice = coursePrice;
        this.status = status;
        this.createTime = createTime;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
    }

    public Integer getCourseType() {
        return courseType;
    }

    public void setCourseType(Integer courseType) {
        this.courseType = courseType;
    }

    public String getCourseImage() {
        return courseImage;
    }

    public void setCourseImage(String courseImage) {
        this.courseImage = courseImage;
    }

    public String getCourseVideo() {
        return courseVideo;
    }

    public void setCourseVideo(String courseVideo) {
        this.courseVideo = courseVideo;
    }

    public Double getCoursePrice() {
        return coursePrice;
    }

    public void setCoursePrice(Double coursePrice) {
        this.coursePrice = coursePrice;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Course{" +
                "cid=" + cid +
                ", courseName='" + courseName + '\'' +
                ", descs='" + descs + '\'' +
                ", courseType=" + courseType +
                ", courseImage='" + courseImage + '\'' +
                ", courseVideo='" + courseVideo + '\'' +
                ", coursePrice=" + coursePrice +
                ", status=" + status +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
