package org.pjj.music.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 用户 实体类
 * @author PengJiaJun
 * @Date 2021/11/13 22:28
 */
public class Consumer {

    /* 主键 */
    private Integer id;
    /* 密码 */
    private String username;
    /* 密码 */
    private String password;
    /* 性别 */
    private Integer sex;
    /* 手机号 */
    private String phoneNum;
    /* 电子邮箱 */
    private String email;
    /* 生日 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;
    /* 签名 */
    private String introduction;
    /* 地区 */
    private String location;
    /* 头像 */
    private String avator;
    /* 创建时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;
    /* 更新时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date updateTime;

    public Consumer() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAvator() {
        return avator;
    }

    public void setAvator(String avator) {
        this.avator = avator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
