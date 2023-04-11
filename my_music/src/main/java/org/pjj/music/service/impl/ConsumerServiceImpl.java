package org.pjj.music.service.impl;

import org.pjj.music.dao.ConsumerMapper;
import org.pjj.music.domain.Consumer;
import org.pjj.music.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户 Service 实现类
 * @author PengJiaJun
 * @Date 2021/11/14 16:54
 */
@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private ConsumerMapper consumerMapper;

    /**
     * 增加
     *
     * @param consumer
     * @return
     */
    @Override
    public boolean insert(Consumer consumer) {
        return consumerMapper.insert(consumer) > 0;
    }

    /**
     * 修改
     *
     * @param consumer
     * @return
     */
    @Override
    public boolean update(Consumer consumer) {
        return consumerMapper.update(consumer) > 0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) {
        return consumerMapper.delete(id) > 0;
    }

    /**
     * 根据主键查询 用户
     *
     * @param id
     * @return
     */
    @Override
    public Consumer selectByPrimaryKey(Integer id) {
        return consumerMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询全部 用户
     *
     * @return
     */
    @Override
    public List<Consumer> allConsumer() {
        return consumerMapper.allConsumer();
    }

    /**
     * 验证用户名密码
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public boolean verifyPassword(String username, String password) {
        return consumerMapper.verifyPassword(username,password) > 0;
    }

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    @Override
    public Consumer getByUsername(String username) {
        return consumerMapper.getByUsername(username);
    }

    @Override
    public Consumer userDetail(Integer id) {
        return consumerMapper.getDetailById(id);
    }

    @Override
    public List<Integer> selectMeLoveSongId(Integer userId) {
        return consumerMapper.selectMeLoveSongId(userId);
    }


}
