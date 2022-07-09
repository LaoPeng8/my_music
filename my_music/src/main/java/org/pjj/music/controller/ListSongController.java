package org.pjj.music.controller;

import com.alibaba.fastjson.JSONObject;
import org.pjj.music.domain.ListSong;
import org.pjj.music.domain.Song;
import org.pjj.music.service.ListSongService;
import org.pjj.music.service.SongService;
import org.pjj.music.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;

/**
 * @author PengJiaJun
 * @Date 2021/11/13 0:01
 */
@RestController
public class ListSongController {

    @Autowired
    private ListSongService listSongService;

    @Autowired
    private SongService songService;

    /**
     * 给 指定歌单中 添加歌曲   (只能添加 数据库中已有的歌曲 到歌单)
     */
    @GetMapping(value = "/song/singerName/detail")
    public Object addSong(HttpServletRequest request,  HttpSession session){
        JSONObject jsonObject = new JSONObject();
        String name = request.getParameter("name");//歌手-歌名

        //根据 - 拆分出 歌手 与 歌名
        int split = name.indexOf('-');
        String singerName = name.substring(0,split);//歌手名
        String songName = name.substring(split + 1);//歌名

        int songId;
        int songListId;
        try {
            songId = songService.songOfSingerNameAndSongName(singerName, songName);//歌曲Id
            songListId = (int) session.getAttribute("currentSongListId");//歌单Id
        } catch (NullPointerException e){
            e.printStackTrace();
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,"添加失败");
            return jsonObject;
        }

        boolean flag = false;
        if(songId != 0 && songListId != 0){// 如果 songId 为空, 说明没有查到 歌曲Id, 那么这首歌就不能加入该歌单
            ListSong listSong = new ListSong();
            listSong.setSongId(songId);
            listSong.setSongListId(songListId);
            flag = listSongService.insert(listSong);
        }

        if(flag){
            jsonObject.put(Const.CODE,1);
            jsonObject.put(Const.MSG,"添加成功");
        }else{
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,"添加失败");
        }

        return jsonObject;
    }

    /**
     * 删除 某歌单 中的某首歌
     */
    @GetMapping(value = "/ListSong/delete")
    public Object delete(Integer songId, HttpSession session){
        JSONObject jsonObject = new JSONObject();

        Integer songListId = (Integer) session.getAttribute("currentSongListId");//歌单Id

        boolean flag = listSongService.delete(songId, songListId);

        if(flag){
            jsonObject.put(Const.CODE,1);
            jsonObject.put(Const.MSG,"删除成功");
        }else{
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,"删除失败");
        }

        return jsonObject;
    }

    @GetMapping(value = "/listSong/detail")
    public Object detail(Integer songListId, HttpSession session){
        JSONObject jsonObject = new JSONObject();

        //由于 在往歌单中添加歌曲时, 没有传入歌单Id, 于是后台就插入不了记录
        //由于 只有在打开一个歌单后, 才可以添加歌曲,  而该方法就打开歌单的请求(请求该方法(携带歌单Id)返回该歌单中的所有歌曲)
        //然后 在此处记录 歌单Id, 之后就可以在后台插入了 (在另外的歌单添加歌曲, 必然会再次请求该方法, 那再次记录新的歌单Id, 如此往复)
        session.setAttribute("currentSongListId",songListId);
        //删除时 也是一样, 同时需要 歌单Id 与 歌曲Id

        return listSongService.songBySongListId(songListId);
    }


}
