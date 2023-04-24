package org.pjj.music.service.impl;

import org.pjj.music.dao.CollectMapper;
import org.pjj.music.domain.Collect;
import org.pjj.music.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author PengJiaJun
 * @Date 2023/04/05 18:38
 */
@Service
public class CollectServiceImpl implements CollectService {

    @Autowired
    private CollectMapper collectMapper;


    @Override
    public int addMeLove(Collect collect) {

        int meLove = collectMapper.isMeLove(collect);
        if(meLove > 0) {// 已经喜欢, 则本次为 取消喜欢
            collectMapper.delMeLove(collect);
            return 2;
        }

        return collectMapper.addMeLove(collect);
    }

    @Override
    public List<Collect> detail(Integer userId) {
        return collectMapper.detail(userId);
    }

    @Override
    public int deleteMeLove(Collect collect) {
        return collectMapper.delMeLove(collect);
    }
}
