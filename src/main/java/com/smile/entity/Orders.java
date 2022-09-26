package com.smile.entity;

public class Orders {
    private String oid;//订单编号
    private String oname;//订单名称
    private Double price;//订单金额
    private Integer cid;//订单中购买的课程
    private Integer uid;//用户id

    public Orders() {
    }

    public Orders(String oid, String oname, Double price, Integer cid, Integer uid) {
        this.oid = oid;
        this.oname = oname;
        this.price = price;
        this.cid = cid;
        this.uid = uid;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getOname() {
        return oname;
    }

    public void setOname(String oname) {
        this.oname = oname;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "oid='" + oid + '\'' +
                ", oname='" + oname + '\'' +
                ", price=" + price +
                ", cid=" + cid +
                ", uid=" + uid +
                '}';
    }
}
