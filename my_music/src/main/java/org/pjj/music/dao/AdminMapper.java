package org.pjj.music.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 管理员 DAO
 * @author PengJiaJun
 * @Date 2021/11/7 16:08
 */
@Repository
public interface AdminMapper {

    /**
     * 验证密码是否正确
     * @param username
     * @param password
     * @return
     */
    int verifyPassword(@Param("username") String username, @Param("password") String password);


}
