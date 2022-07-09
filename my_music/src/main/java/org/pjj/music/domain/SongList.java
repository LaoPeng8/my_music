package org.pjj.music.domain;

/**
 * 歌单
 * @author PengJiaJun
 * @Date 2021/11/11 23:29
 */
public class SongList {
    /* 主键 */
    private Integer id;
    /* 标题 */
    private String title;
    /* 歌单图片 */
    private String pic;
    /* 简介 */
    private String introduction;
    /* 歌单风格 (比如: 华语, 古风, 摇滚, 爵士) */
    private String style;

    public SongList() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }
}
