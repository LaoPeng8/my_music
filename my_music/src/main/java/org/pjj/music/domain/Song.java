package org.pjj.music.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 歌曲
 * @author PengJiaJun
 * @Date 2021/11/8 23:09
 */
public class Song {

    /* 主键 */
    private Integer id;
    /* 歌手 id */
    private Integer singerId;
    /* 歌名 */
    private String name;
    /* 歌曲简介 */
    private String introduction;
    /* 创建时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;
    /* 更新时间 */
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date updateTime;
    /* 歌曲图片 */
    private String pic;
    /* 歌词 */
    private String lyric;
    /* 歌曲地址 */
    private String url;

    public Song() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSingerId() {
        return singerId;
    }

    public void setSingerId(Integer singerId) {
        this.singerId = singerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
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

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Song{" +
                "id=" + id +
                ", singerId=" + singerId +
                ", name='" + name + '\'' +
                ", introduction='" + introduction + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", pic='" + pic + '\'' +
                ", lyric='" + lyric + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
