package org.pjj.music.controller;

import com.alibaba.fastjson.JSONObject;
import org.pjj.music.domain.Collect;
import org.pjj.music.service.CollectService;
import org.pjj.music.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author PengJiaJun
 * @Date 2023/04/05 18:26
 */
@RestController
@RequestMapping("/collection")
public class CollectController {

    @Autowired
    private CollectService collectService;

    @PostMapping("/add")
    public Object add(@RequestParam("userId") Integer userId, @RequestParam("type") Integer type, @RequestParam("songId") Integer songId) {
        JSONObject jsonObject = new JSONObject();

        Collect collect = new Collect(userId, type, songId, null);

        int flag = collectService.addMeLove(collect);
        if(flag == 2) {
            jsonObject.put(Const.CODE,2);
            jsonObject.put(Const.MSG,"取消喜欢");
        }else if(flag >= 1) {
            jsonObject.put(Const.CODE,1);
            jsonObject.put(Const.MSG,"添加成功");
        }else {
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,"添加失败-未知原因");
        }

        return jsonObject;
    }

    @GetMapping("/detail")
    public Object detail(@RequestParam("userId") Integer userId) {
        return collectService.detail(userId);
    }


}
