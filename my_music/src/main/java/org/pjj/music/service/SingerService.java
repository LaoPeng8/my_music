package org.pjj.music.service;

import org.pjj.music.domain.Singer;

import java.util.List;

/**
 * 歌手 Service
 * @author PengJiaJun
 * @Date 2021/11/8 00:16
 */
public interface SingerService {

    /**
     * 增加
     * @param singer
     * @return
     */
    boolean insert(Singer singer);

    /**
     * 修改
     * @param singer
     * @return
     */
    boolean update(Singer singer);

    /**
     * 删除
     * @param id
     * @return
     */
    boolean delete(Integer id);

    /**
     * 根据主键查询 歌手
     * @param id
     * @return
     */
    Singer selectByPrimaryKey(Integer id);

    /**
     * 查询全部歌手
     * @return
     */
    List<Singer> allSinger();

    /**
     * 根据歌手姓名 模糊查询列表
     * @param name
     * @return
     */
    List<Singer> singerOfName(String name);

    /**
     * 根据性别查询
     * @param sex 1男 0女    sex原本类型为 byte, 在这里使用 Integer 是没有问题的, 会自动向上转型(Byte 是比 Integer 小一截的)
     * @return
     */
    List<Singer> singerOfSex(Integer sex);

}
