package org.pjj.music.controller;

import com.alibaba.fastjson.JSONObject;
import org.pjj.music.domain.Singer;
import org.pjj.music.domain.SongList;
import org.pjj.music.service.SongListService;
import org.pjj.music.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 歌单 Controller
 * @author PengJiaJun
 * @Date 2021/11/12 11:04
 */
@RestController
@RequestMapping("/songList")
public class SongListController {

    @Autowired
    private SongListService songListService;

    /**
     * 查询全部歌单
     */
    @GetMapping
    public Object allSinger(){
        return songListService.allSongList();
    }

    /**
     * 增加
     */
    @PostMapping("/add")
    public Object addSinger(SongList songList){
        JSONObject jsonObject = new JSONObject();

        boolean flag = songListService.insert(songList);

        if(flag){   //保存成功
            jsonObject.put(Const.CODE,1);
            jsonObject.put(Const.MSG,"添加成功");
        }else{
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,"添加失败");
        }
        return jsonObject;
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public Object updateSinger(SongList songList){
        JSONObject jsonObject = new JSONObject();

        boolean flag = songListService.update(songList);
        if(flag){   //修改成功
            jsonObject.put(Const.CODE,1);
            jsonObject.put(Const.MSG,"修改成功");
        }else{
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,"修改失败");
        }
        return jsonObject;
    }

    /**
     * 删除
     */
    @GetMapping("/delete")
    public Object deleteSinger(Integer id){
        return songListService.delete(id);
    }

    /**
     * 根据主键查询 歌单
     */
    @GetMapping("/selectByPrimaryKey")
    public Object selectByPrimaryKey(Integer id){
        return songListService.selectByPrimaryKey(id);
    }

    /**
     * 根据标题 精确 查询歌单列表
     * @param title
     * @return
     */
    @GetMapping("/songListOfTitle")
    public Object songListOfTitle(String title){
        return songListService.songListOfTitle(title);
    }


    /**
     * 根据标题 模糊 查询歌单列表
     * @param title
     * @return
     */
    @GetMapping("/likeTitle")
    public Object likeTitle(String title){
        return songListService.likeTitle("%"+title+"%");
    }

    /**
     * 根据风格 精确 查询歌单列表
     * @param style
     * @return
     */
    @GetMapping("/style/detail")
    public Object songListOfStyle(String style){
        return songListService.songListOfStyle(style);
    }

    /**
     * 根据风格 模糊 查询歌单列表
     * @param style
     * @return
     */
    @GetMapping("/style/likeStyle")
    public Object likeStyle(String style){
        return songListService.likeStyle("%"+style+"%");
    }

    /**
     * 修改歌单头像
     */
    @PostMapping("/img/update")
    public Object updateSingerPic(@RequestParam("file") MultipartFile avatarFile, @RequestParam("id") Integer id){
        JSONObject jsonObject = new JSONObject();
        if(avatarFile.isEmpty()){//上传的文件为空, 则直接上传失败
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,"文件上传失败");
            return jsonObject;
        }else{
            //文件名 = 当前时间的毫秒 + 原来的文件名
            String fileName = System.currentTimeMillis()+avatarFile.getOriginalFilename();
            //文件路径
            String filePath = System.getProperty("user.dir")
                    +System.getProperty("file.separator")+"img"
                    +System.getProperty("file.separator")+"songListPic"
                    +System.getProperty("file.separator"); // 项目根目录/img/songListPic/;

            //如果文件路径(目录)不存在, 新增该路径
            File file1 = new File(filePath);
            if(!file1.exists()){//不存在
                file1.mkdir();
            }

            //实际的文件地址
            File dest = new File(filePath + System.getProperty("file.separator")+fileName);
            //存储到数据库里的相对文件地址
            String storeAvatarPath = "/img/songListPic/"+fileName;
            SongList songList = new SongList();
            songList.setId(id);
            songList.setPic(storeAvatarPath);

            try {
                avatarFile.transferTo(dest);//文件上传

                boolean flag = songListService.update(songList);//修改数据库中 歌单头像字段pic 为新上传的文件的相对文件地址
                if(flag){
                    jsonObject.put(Const.CODE,1);
                    jsonObject.put(Const.MSG,"上传成功");
                    jsonObject.put("pic",storeAvatarPath);
                }else{
                    jsonObject.put(Const.CODE,0);
                    jsonObject.put(Const.MSG,"上传失败");
                }
            } catch (IOException e) {
                e.printStackTrace();
                jsonObject.put(Const.CODE,0);
                jsonObject.put(Const.MSG,"上传失败:" + e.getMessage());
            } finally {
                return jsonObject;
            }

        }
    }


}
