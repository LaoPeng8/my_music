package org.pjj.music.dao;

import org.apache.ibatis.annotations.Param;
import org.pjj.music.domain.Consumer;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户 DAO
 * @author PengJiaJun
 * @Date 2021/11/13 22:54
 */
@Repository
public interface ConsumerMapper {

    /**
     * 增加
     * @param consumer
     * @return
     */
    int insert(Consumer consumer);

    /**
     * 修改
     * @param consumer
     * @return
     */
    int update(Consumer consumer);

    /**
     * 删除
     * @param id
     * @return
     */
    int delete(@Param("id") Integer id);//只有一个参数时不用加 @Param()

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
    int verifyPassword(@Param("username") String username,@Param("password") String password);

    /**
     *
     * @param username
     * @return
     */
    Consumer getByUsername(String username);

}
