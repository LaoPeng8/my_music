package org.pjj.music.controller;

import com.alibaba.fastjson.JSONObject;
import org.pjj.music.domain.Singer;
import org.pjj.music.domain.Song;
import org.pjj.music.service.SongService;
import org.pjj.music.utils.Const;
import org.pjj.music.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 歌曲管理 Controller
 * @author PengJiaJun
 * @Date 2021/11/9 17:53
 */
@RestController
@RequestMapping("/song")
public class SongController {

    @Autowired
    SongService songService;

    @GetMapping
    public Object songCount(){
        return songService.allSong();
    }

    /**
     * 给指定 歌手(singerId) 添加歌曲
     */
    @PostMapping(value = "/add")
    public Object addSong(Song song, @RequestParam("file") MultipartFile musicFile){
        JSONObject jsonObject = new JSONObject();
        song.setPic(Constant.DEFSONGPIC);//默认歌曲图片

        if(musicFile.isEmpty()){
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,"文件上传失败");
            return jsonObject;
        }

        //文件名 = 当前时间毫秒+原来的文件名
        String fileName = System.currentTimeMillis()+musicFile.getOriginalFilename();
        //文件路径  项目根目录/song/
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")+"song"+System.getProperty("file.separator");

        //如果文件路径不存在
        File dirFile = new File(filePath);
        if(!dirFile.exists()){//不存在
            dirFile.mkdir();
        }
        //实际的文件地址(将用户上传的文件, 实际存储的文件)
        File dest = new File(filePath + fileName);
        //存储在数据库中的文件 的相对路径
        String storeMusicFilePath = "/song/"+fileName;

        try {
            musicFile.transferTo(dest); //上传文件 至 dest文件

            song.setUrl(storeMusicFilePath);
            boolean flag = songService.insert(song);//修改数据库中 歌曲地址字段url 为新上传的文件的相对文件地址

            if(flag){
                jsonObject.put(Const.CODE,1);
                jsonObject.put(Const.MSG,"上传成功");
                jsonObject.put("url",storeMusicFilePath);
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

    /**
     * 根据歌手Id 查询歌曲
     */
    @GetMapping("/singer/detail")
    public List<Song> songOfSingerId(Integer singerId) {
        return songService.songOfSingerId(singerId);
    }

    /**
     * 更新指定 歌手(singerId) 的歌曲
     */
    @PostMapping("/update")
    public Object updateSong(Song song){
        JSONObject jsonObject = new JSONObject();

        boolean flag = songService.update(song);
        if(flag){
            jsonObject.put(Const.CODE,1);
            jsonObject.put(Const.MSG,"修改成功");
        }else{
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,"修改失败");
        }

        return jsonObject;
    }

    /**
     * 删除歌曲
     */
    @GetMapping("/delete")
    public Object deleteSong(Integer id){

        //先在数据库中查出对应的文件地址, 然后再在文件中将其删除, 然后再进行下面的代码(删除数据库中的数据)
        Song song = songService.selectByPrimaryKeyMe(id);
        File file = new File(System.getProperty("user.dir")+System.getProperty("file.separator")+song.getUrl());
        boolean delete = file.delete();//删除本地的歌曲文件
        boolean flag;

        if(delete){//本地歌曲文件删除成功
            flag = songService.delete(id);//删除数据库中歌曲的记录
        }else{//删除失败
            flag = false;
        }

        return flag;
    }

    /**
     * 根据 歌曲id 更新歌曲图片
     */
    @PostMapping("/img/update")
    public Object updateSongPic(@RequestParam("file") MultipartFile updateFile, Song song) {
        JSONObject jsonObject = new JSONObject();

        if(updateFile.isEmpty()){//更新图片失败
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,"更新失败");
            return jsonObject;
        }

        //文件名 = 当前时间毫秒+原来的文件名
        String fileName = System.currentTimeMillis()+updateFile.getOriginalFilename();

        //文件路径  项目根目录/song/
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")
                +"img"+System.getProperty("file.separator")
                +"songPic"+System.getProperty("file.separator");

        //如果文件路径不存在
        File dirFile = new File(filePath);
        if(!dirFile.exists()){
            dirFile.mkdir();
        }

        //实际的文件地址(将用户上传的文件, 实际存储的文件)
        File dest = new File(filePath + fileName);
        //存储在数据库中的文件 的相对路径
        String songPic = "/img/songPic/"+fileName;
        song.setPic(songPic);

        try {
            //上传文件 至 dest文件
            updateFile.transferTo(dest);

            //上传成功之后, 将旧的歌曲图片删除
            //先查询 再修改 (其实这部分内容应该写在 service层中 songService.update(song) 直接写在该方法中)
            Song oldSongPic = songService.selectByPrimaryKeyMe(song.getId());
            if(!Constant.DEFSONGPIC.equals(oldSongPic.getPic())){ //不删除默认头像
                File oldSongPicFile = new File(System.getProperty("user.dir") + oldSongPic.getPic());
                oldSongPicFile.delete();//不管旧的歌曲图片删除成功还是失败都可以新上传歌曲图片 (歌曲图片一般不会删除失败, 就没必要再套一层 if )
            }

            //修改数据库中 歌曲图片字段pic 为新上传的文件的相对文件地址
            boolean flag = songService.update(song);
            if(flag){
                jsonObject.put(Const.CODE,1);
                jsonObject.put(Const.MSG,"更新成功");
                jsonObject.put("url",songPic);
            }else{
                jsonObject.put(Const.CODE,0);
                jsonObject.put(Const.MSG,"更新失败");
            }

        } catch (IOException e) {
            e.printStackTrace();
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,"更新失败");
        } finally{
            return jsonObject;
        }

    }

    /**
     * 根据 歌曲id 更新歌曲
     */
    @PostMapping("/url/update")
    public Object updateSong(@RequestParam("file") MultipartFile updateFile, Song song) {

        JSONObject jsonObject = new JSONObject();

        if(updateFile.isEmpty()){//更新歌曲失败
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,"更新失败");
            return jsonObject;
        }

        //文件名 = 当前时间毫秒+原来的文件名
        String fileName = System.currentTimeMillis()+updateFile.getOriginalFilename();

        //文件路径  项目根目录/song/
        String filePath = System.getProperty("user.dir")+System.getProperty("file.separator")
                +"song"+System.getProperty("file.separator");

        //如果文件路径不存在
        File dirFile = new File(filePath);
        if(!dirFile.exists()){
            dirFile.mkdir();
        }

        //实际的文件地址(将用户上传的文件, 实际存储的文件)
        File dest = new File(filePath + fileName);
        //存储在数据库中的文件 的相对路径
        String songUrl = "/song/"+fileName;
        song.setUrl(songUrl);

        try {
            //上传文件 至 dest文件
            updateFile.transferTo(dest);

            //上传成功之后, 将旧的歌曲删除
            //先查询 再修改
            Song oldSong = songService.selectByPrimaryKeyMe(song.getId());
            File oldSongFile = new File(System.getProperty("user.dir") + oldSong.getUrl());
            oldSongFile.delete();//不管旧的歌曲删除成功还是失败都可以新上传歌曲 (歌曲一般不会删除失败, 就没必要再套一层 if )

            //修改数据库中 歌曲Url字段 为新上传的文件的相对文件地址
            boolean flag = songService.update(song);
            if(flag){
                jsonObject.put(Const.CODE,1);
                jsonObject.put(Const.MSG,"更新成功");
                jsonObject.put("url",songUrl);
            }else{
                jsonObject.put(Const.CODE,0);
                jsonObject.put(Const.MSG,"更新失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,"更新失败");
        } finally{
            return jsonObject;
        }

    }

    /**
     * 根据 歌曲Id 查询歌曲
     */
    @GetMapping("/detail")
    public List<Song> songOfSongId(Integer id) {
        return songService.selectByPrimaryKey(id);
    }

    /**
     *
     * @param songName
     * @return
     */
    @GetMapping("/likeSongOfName/detail")
    public Object likeSongOfName(String name){
        return songService.likeSongOfName(name);
    }

}
