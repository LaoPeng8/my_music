package org.pjj.music.dao;

import org.apache.ibatis.annotations.Param;
import org.pjj.music.domain.SongList;
import org.pjj.music.domain.SongListStyle;
import org.pjj.music.domain.vo.SongListVo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 歌单 DAO
 * @author PengJiaJun
 * @Date 2021/11/11 23:36
 */
@Repository
public interface SongListMapper {

    /**
     * 增加
     * @param songList
     * @return
     */
    int insert(SongList songList);

    /**
     * 修改
     * @param songList
     * @return
     */
    int update(SongList songList);

    /**
     * 删除
     * @param id
     * @return
     */
    int delete(@Param("id") Integer id);//只有一个参数时不用加 @Param()

    /**
     * 根据主键查询 歌单
     * @param id
     * @return
     */
    SongList selectByPrimaryKey(@Param("id") Integer id);

    /**
     * 查询全部歌单
     * @return
     */
    List<SongListVo> allSongList();

    /**
     * 根据标题 精确 查询歌单列表
     * @param title
     * @return
     */
    List<SongList> songListOfTitle(@Param("title") String title);

    /**
     * 根据标题 模糊 查询歌单列表
     * @param title
     * @return
     */
    List<SongList> likeTitle(@Param("title") String title);

    /**
     * 根据风格 精确 查询歌单列表
     * @param style
     * @return
     */
    List<SongList> songListOfStyle(@Param("style") String style);

    /**
     * 根据风格 模糊 查询歌单列表
     * @param style
     * @return
     */
    List<SongList> likeStyle(@Param("style") String style);

    List<SongListStyle> styleAll();

    int styleInsert(String name);

    int styleDelete(Integer id);

    int styleUpdate(SongListStyle songListStyle);
}
