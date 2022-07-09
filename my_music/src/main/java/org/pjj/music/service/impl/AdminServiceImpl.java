package org.pjj.music.service.impl;

import org.pjj.music.dao.AdminMapper;
import org.pjj.music.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 管理员 Service 实现类
 * @author PengJiaJun
 * @Date 2021/11/7 16:35
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    /**
     * 验证密码是否正确
     * @param username
     * @param password
     * @return
     */
    @Override
    public boolean verifyPassword(String username, String password){
        return adminMapper.verifyPassword(username,password) > 0;
    }
}
