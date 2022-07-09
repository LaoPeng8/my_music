package org.pjj.music.service;

import org.apache.ibatis.annotations.Param;
import org.pjj.music.domain.SongList;

import java.util.List;

/**
 * 歌单 service
 * @author PengJiaJun
 * @Date 2021/11/12 10:53
 */
public interface SongListService {

    /**
     * 增加
     * @param songList
     * @return
     */
    boolean insert(SongList songList);

    /**
     * 修改
     * @param songList
     * @return
     */
    boolean update(SongList songList);

    /**
     * 删除
     * @param id
     * @return
     */
    boolean delete(Integer id);//只有一个参数时不用加 @Param()

    /**
     * 根据主键查询 歌单
     * @param id
     * @return
     */
    SongList selectByPrimaryKey(Integer id);

    /**
     * 查询全部歌单
     * @return
     */
    List<SongList> allSongList();

    /**
     * 根据标题 精确 查询歌单列表
     * @param title
     * @return
     */
    List<SongList> songListOfTitle(String title);

    /**
     * 根据标题 模糊 查询歌单列表
     * @param title
     * @return
     */
    List<SongList> likeTitle(String title);

    /**
     * 根据风格 精确 查询歌单列表
     * @param style
     * @return
     */
    List<SongList> songListOfStyle(String style);

    /**
     * 根据风格 模糊 查询歌单列表
     * @param style
     * @return
     */
    List<SongList> likeStyle(String style);

}
