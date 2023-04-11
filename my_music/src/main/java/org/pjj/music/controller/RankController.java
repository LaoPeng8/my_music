package org.pjj.music.controller;

import com.alibaba.fastjson.JSONObject;
import org.pjj.music.domain.Rank;
import org.pjj.music.service.impl.RankServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author PengJiaJun
 * @Date 2023/04/06 16:43
 */
@RestController
@Controller
public class RankController {

    @Autowired
    private RankServiceImpl rankService;

    //    提交评分
    @ResponseBody
    @RequestMapping(value = "/rank/add", method = RequestMethod.POST)
    public Object addRank(HttpServletRequest req){
        JSONObject jsonObject = new JSONObject();
        String songListId = req.getParameter("songListId").trim();
        String consumerId = req.getParameter("consumerId").trim();
        String score = req.getParameter("score").trim();

        Rank rank = new Rank();
        rank.setSongListId(Long.parseLong(songListId));
        rank.setConsumerId(Long.parseLong(consumerId));
        rank.setScore(Integer.parseInt(score));

        Integer scoreMe = rankService.selectRankScoreOfMe(rank.getSongListId(), rank.getConsumerId());
        if(scoreMe != null) {
            jsonObject.put("code", 2);
            jsonObject.put("msg", "请不要重复评价");
            return jsonObject;
        }


        boolean res = rankService.addRank(rank);
        if (res){
            jsonObject.put("code", 1);
            jsonObject.put("msg", "评价成功");
            return jsonObject;
        }else {
            jsonObject.put("code", 0);
            jsonObject.put("msg", "评价失败");
            return jsonObject;
        }
    }

    //    获取指定歌单的评分
    @RequestMapping(value = "/rank", method = RequestMethod.GET)
    public Object rankOfSongListId(HttpServletRequest req){
        String songListId = req.getParameter("songListId");
        return rankService.rankOfSongListId(Long.parseLong(songListId));
    }

    // 查询自己的评价
    @RequestMapping(value = "/rank/me", method = RequestMethod.GET)
    public Object rankOfMe(HttpServletRequest req) {
        String songListId = req.getParameter("songListId");
        String consumerId = req.getParameter("consumerId");
        return rankService.selectRankScoreOfMe(Long.parseLong(songListId), Long.parseLong(consumerId));
    }
}
