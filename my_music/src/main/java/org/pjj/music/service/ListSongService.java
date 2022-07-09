package org.pjj.music.service;

import org.apache.ibatis.annotations.Param;
import org.pjj.music.domain.ListSong;
import org.pjj.music.domain.Song;

import java.util.List;

/**
 * 歌单中的歌曲 service
 * @author PengJiaJun
 * @Date 2021/11/12 23:04
 */
public interface ListSongService {

    /**
     * 增加
     * @param listSong
     * @return
     */
    boolean insert(ListSong listSong);

    /**
     * 修改
     * @param listSong
     * @return
     */
    boolean update(ListSong listSong);

    /**
     * 删除
     * @param songId
     * @param songListId
     * @return
     */
    boolean delete(Integer songId, Integer songListId);

    /**
     * 根据主键查询 歌单
     * @param id
     * @return
     */
    ListSong selectByPrimaryKey(@Param("id") Integer id);

    /**
     * 查询全部 歌单
     * @return
     */
    List<ListSong> allListSong();

    /**
     * 根据 歌单id 查询歌单中的所有歌曲
     * @return
     */
    List<Song> songBySongListId(@Param("songListId") Integer songListId);
}
