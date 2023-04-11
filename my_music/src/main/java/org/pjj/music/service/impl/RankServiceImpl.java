package org.pjj.music.service.impl;

import org.pjj.music.dao.RankMapper;
import org.pjj.music.domain.Rank;
import org.pjj.music.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author PengJiaJun
 * @Date 2023/04/06 16:42
 */
@Service
public class RankServiceImpl implements RankService {

    @Autowired
    private RankMapper rankMapper;

    @Override
    public int rankOfSongListId(Long songListId) {
        return rankMapper.selectScoreSum(songListId) / rankMapper.selectRankNum(songListId);
    }

    @Override
    public boolean addRank(Rank rank) {

        return rankMapper.insertSelective(rank) > 0 ? true:false;
    }

    @Override
    public Integer selectRankScoreOfMe(Long songListId, Long consumerId) {
        return rankMapper.selectRankScoreOfMe(songListId, consumerId);
    }
}
