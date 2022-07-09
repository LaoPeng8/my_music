package org.pjj.music.domain;

/**
 * 歌单中的歌曲
 * @author PengJiaJun
 * @Date 2021/11/12 16:53
 */
public class ListSong {

    /* 主键 */
    private Integer id;
    /* 歌曲 id */
    private Integer songId;
    /* 歌单 id */
    private Integer songListId;

    public ListSong() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
