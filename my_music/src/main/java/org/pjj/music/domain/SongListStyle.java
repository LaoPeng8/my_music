package org.pjj.music.domain;

/**
 * @author PengJiaJun
 * @Date 2023/04/15 22:17
 */
public class SongListStyle {
    private Integer id;
    private String name;

    public SongListStyle() {
    }

    public SongListStyle(String name) {
        this.name = name;
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
}
