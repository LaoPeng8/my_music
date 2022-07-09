package org.pjj.music.dao;

import org.apache.ibatis.annotations.Param;
import org.pjj.music.domain.Song;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 歌曲 DAO
 * @author PengJiaJun
 * @Date 2021/11/8 23:24
 */
@Repository
public interface SongMapper {

    /**
     * 增加
     * @param song
     * @return
     */
    int insert(Song song);

    /**
     * 修改
     * @param song
     * @return
     */
    int update(Song song);

    /**
     * 删除
     * @param id
     * @return
     */
    int delete(@Param("id") Integer id);//只有一个参数时不用加 @Param()

    /**
     * 根据主键查询 歌曲
     * @param id
     * @return
     */
    List<Song> selectByPrimaryKey(@Param("id") Integer id);

    /**
     * 查询全部歌曲
     * @return
     */
    List<Song> allSong();

    /**
     * 根据歌名 精确查询列表
     * @param name
     * @return
     */
    List<Song> songOfName(@Param("name") String name);

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
    List<Song> songOfSingerId(@Param("singerId") Integer singerId);

    /**
     * 根据 歌手名 and 歌名 查询 歌手Id
     */
    Integer songOfSingerNameAndSongName(@Param("singerName") String singerName, @Param("songName") String songName);

    /**
     * 返回歌曲总数
     * @return
     */
    Integer songCount();

}
