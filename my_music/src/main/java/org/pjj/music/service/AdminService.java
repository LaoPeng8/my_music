package org.pjj.music.service;

/**
 * 管理员 Service
 * @author PengJiaJun
 * @Date 2021/11/7 16:34
 */
public interface AdminService {

    /**
     * 验证密码是否正确
     * @param username
     * @param password
     * @return
     */
    boolean verifyPassword(String username, String password);

}
