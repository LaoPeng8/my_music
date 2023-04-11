package org.pjj.music.service;

import org.apache.ibatis.annotations.Param;
import org.pjj.music.domain.Rank;

public interface RankService {

    int rankOfSongListId(Long songListId);

    boolean addRank(Rank rank);

    /**
     * 查询 自己对该歌单的评分
     * @param songListId
     * @param consumerId
     * @return
     */
    Integer selectRankScoreOfMe(Long songListId, Long consumerId);

}
