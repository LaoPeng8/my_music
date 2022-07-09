package org.pjj.music.dao;

import org.apache.ibatis.annotations.Param;
import org.pjj.music.domain.ListSong;
import org.pjj.music.domain.Song;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 歌单中的歌曲 DAO
 * @author PengJiaJun
 * @Date 2021/11/12 16:57
 */
@Repository
public interface ListSongMapper {

    /**
     * 增加
     * @param listSong
     * @return
     */
    int insert(ListSong listSong);

    /**
     * 修改
     * @param listSong
     * @return
     */
    int update(ListSong listSong);

    /**
     * 删除 根据 歌曲Id 与 歌单Id
     * @param songId
     * @param songListId
     * @return
     */
    int delete(@Param("songId") Integer songId, @Param("songListId") Integer songListId);

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
