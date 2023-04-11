package org.pjj.music.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author PengJiaJun
 * @Date 2023/04/05 18:27
 */
public class Collect {
    private Integer id;

    private Integer userId;

    private Integer type;

    private Integer songId;

    private Integer songListId;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;

    public Collect() {
    }

    public Collect(Integer userId, Integer type, Integer songId, Integer songListId) {
        this.userId = userId;
        this.type = type;
        this.songId = songId;
        this.songListId = songListId;
    }

    public Collect(Integer id, Integer userId, Integer type, Integer songId, Integer songListId, Date createTime) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.songId = songId;
        this.songListId = songListId;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getSongId() {
        return songId;
    }

    public void setSongId(Integer songId) {
        this.songId = songId;
    }

    public Integer getSongListId() {
        return songListId;
    }

    public void setSongListId(Integer songListId) {
        this.songListId = songListId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
