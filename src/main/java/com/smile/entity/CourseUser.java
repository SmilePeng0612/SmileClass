package com.smile.entity;

public class CourseUser {
    private Integer id;//选课id
    private Integer uid;//关联的用户id
    private Integer cid;//关联的课程id

    private User user;//uid对应的用户
    private Course course;//cid对应的课程

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public CourseUser() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    @Override
    public String toString() {
        return "CourseUser{" +
                "id=" + id +
                ", uid=" + uid +
                ", cid=" + cid +
                '}';
    }
}
