package org.pjj.music.dao;

import org.apache.ibatis.annotations.Param;
import org.pjj.music.domain.Rank;
import org.springframework.stereotype.Repository;

@Repository
public interface RankMapper {

    int insert(Rank record);

    int insertSelective(Rank record);

    /**
     * 查总分
     * @param songListId
     * @return
     */
    int selectScoreSum(Long songListId);

    /**
     * 查总评分人数
     * @param songListId
     * @return
     */
    int selectRankNum(Long songListId);

    /**
     * 查询 自己对该歌单的评分
     * @param songListId
     * @param consumerId
     * @return
     */
    Integer selectRankScoreOfMe(@Param("songListId") Long songListId, @Param("consumerId") Long consumerId);

}
