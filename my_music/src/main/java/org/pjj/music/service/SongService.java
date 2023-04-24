package org.pjj.music.service;

import org.apache.ibatis.annotations.Param;
import org.pjj.music.domain.Song;

import java.util.List;

/**
 * 歌曲 service
 * @author PengJiaJun
 * @Date 2021/11/9 17:30
 */
public interface SongService {

    /**
     * 增加
     * @param song
     * @return
     */
    boolean insert(Song song);

    /**
     * 修改
     * @param song
     * @return
     */
    boolean update(Song song);

    /**
     * 删除
     * @param id
     * @return
     */
    boolean delete(Integer id);

    /**
     * 根据主键查询 歌曲
     * @param id
     * @return 返回 List
     */
    List<Song> selectByPrimaryKey(Integer id);

    /**
     * 根据主键查询 歌曲
     * @param id
     * @return 返回 Song
     */
    Song selectByPrimaryKeyMe(Integer id);

    /**
     * 查询全部歌曲
     * @return
     */
    List<Song> allSong();

    /**
     * 根据歌名 模糊查询列表
     * @param name
     * @return
     */
    List<Song> songOfName(String name);

    /**
     * 根据歌名 模糊查询列表
     * @param name
     * @return
     */
    List<Song> likeSongOfName(@Param("name") String name);

    /**
     * 根据歌手id查询
     * @return
     */
    List<Song> songOfSingerId(Integer singerId);

    /**
     * 根据 歌手名 and 歌名 查询 歌手Id
     */
    Integer songOfSingerNameAndSongName(String singerName, String songName);

    /**
     * 返回 歌曲总数
     * @return
     */
    Integer songCount();

    Integer songOfSongName(String name);

}
