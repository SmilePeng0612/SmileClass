package com.smile.entity;

public class CourseDetail {
    private Integer id;//明细id
    private String name;//明细名称
    private String type;//所属课程
    private String url;//视频名称
    private String start_data;//开课时间
    private Integer cid;//关联的课程id

    public CourseDetail(Integer id, String name, String type, String url, String start_data, Integer cid) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.url = url;
        this.start_data = start_data;
        this.cid = cid;
    }

    public CourseDetail() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getStart_data() {
        return start_data;
    }

    public void setStart_data(String start_data) {
        this.start_data = start_data;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    @Override
    public String toString() {
        return "CourseDetail{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", start_data='" + start_data + '\'' +
                ", cid=" + cid +
                '}';
    }
}
