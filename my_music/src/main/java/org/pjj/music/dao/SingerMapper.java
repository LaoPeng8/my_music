package org.pjj.music.dao;

import org.apache.ibatis.annotations.Param;
import org.pjj.music.domain.Singer;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 歌手 DAO
 * @author PengJiaJun
 * @Date 2021/11/7 23:09
 */
@Repository
public interface SingerMapper {

    /**
     * 增加
     * @param singer
     * @return
     */
    int insert(Singer singer);

    /**
     * 修改
     * @param singer
     * @return
     */
    int update(Singer singer);

    /**
     * 删除
     * @param id
     * @return
     */
    int delete(@Param("id") Integer id);//只有一个参数时不用加 @Param()

    /**
     * 根据主键查询 歌手
     * @param id
     * @return
     */
    Singer selectByPrimaryKey(@Param("id") Integer id);

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
    List<Singer> singerOfName(@Param("name") String name);

    /**
     * 根据性别查询
     * @param sex 1男 0女    sex原本类型为 byte, 在这里使用 Integer 是没有问题的, 会自动向上转型(Byte 是比 Integer 小一截的)
     * @return
     */
    List<Singer> singerOfSex(@Param("sex") Integer sex);


}
