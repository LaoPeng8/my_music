package org.pjj.music.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 歌手
 * @author PengJiaJun
 * @Date 2021/11/7 23:01
 */
public class Singer {
    /* 主键 */
    private Integer id;
    /* 名称 */
    private String name;
    /* 性别 */
    private Byte sex;
    /* 头像 */
    private String pic;
    /* 生日 */
    @DateTimeFormat(pattern = "yyyy-MM-dd")//前端传字符串类型的生日到后端, 就可以用Date类型接收, 否则报错
    private Date birth;
    /* 地区 */
    private String location;
    /* 简介 */
    private String introduction;

    public Singer() {
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

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    public String toString() {
        return "Singer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", pic='" + pic + '\'' +
                ", birth=" + birth +
                ", location='" + location + '\'' +
                ", introduction='" + introduction + '\'' +
                '}';
    }
}
