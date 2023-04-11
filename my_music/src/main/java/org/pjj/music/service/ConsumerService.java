package org.pjj.music.service;

import org.apache.ibatis.annotations.Param;
import org.pjj.music.domain.Consumer;

import java.util.List;

/**
 * 用户 Service
 * @author PengJiaJun
 * @Date 2021/11/14 16:53
 */
public interface ConsumerService {

    /**
     * 增加
     * @param consumer
     * @return
     */
    boolean insert(Consumer consumer);

    /**
     * 修改
     * @param consumer
     * @return
     */
    boolean update(Consumer consumer);

    /**
     * 删除
     * @param id
     * @return
     */
    boolean delete(@Param("id") Integer id);//只有一个参数时不用加 @Param()

    /**
     * 根据主键查询 用户
     * @param id
     * @return
     */
    Consumer selectByPrimaryKey(@Param("id") Integer id);

    /**
     * 查询全部 用户
     * @return
     */
    List<Consumer> allConsumer();

    /**
     * 验证用户名密码
     * @param username
     * @param password
     * @return
     */
    boolean verifyPassword(@Param("username") String username,@Param("password") String password);

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    Consumer getByUsername(String username);


    Consumer userDetail(Integer id);

    List<Integer> selectMeLoveSongId(Integer userId);

}
