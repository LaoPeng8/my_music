package org.pjj.music.domain.vo;

/**
 * @author PengJiaJun
 * @Date 2023/04/18 11:40
 */
public class SongListVo {
    /* 主键 */
    private Integer id;
    /* 标题 */
    private String title;
    /* 歌单图片 */
    private String pic;
    /* 简介 */
    private String introduction;
    /* 歌单风格 id */
    private Integer style;

    /* 歌单风格名称 (比如: 华语, 古风, 摇滚, 爵士) */
    private String name;

    public SongListVo() {
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

    public Integer getStyle() {
        return style;
    }

    public void setStyle(Integer style) {
        this.style = style;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
