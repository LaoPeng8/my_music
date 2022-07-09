package org.pjj.music.controller;

import com.alibaba.fastjson.JSONObject;
import org.pjj.music.domain.Singer;
import org.pjj.music.service.SingerService;
import org.pjj.music.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 歌手 Controller
 * @author PengJiaJun
 * @Date 2021/11/8 10:30
 */
@RestController
@RequestMapping("/singer")
public class SingerController {

    @Autowired
    private SingerService singerService;

    /**
     * 查询全部歌手
     */
    @GetMapping
    public Object allSinger(){
        return singerService.allSinger();
    }

    /**
     * 增加
     */
    @PostMapping("/add")
    public Object addSinger(Singer singer){
        JSONObject jsonObject = new JSONObject();

        boolean flag = singerService.insert(singer);
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
    public Object updateSinger(Singer singer){
        JSONObject jsonObject = new JSONObject();

        boolean flag = singerService.update(singer);
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
     * 发现一个问题: 当删除一个歌手后, 该歌手在数据库中的数据被删除了
     *              但是 存在本地的 用户头像 却还在本地
     * 解决: 删除时, 把歌手头像的本地地址 先记录下来, 待删除成功后, 将头像删除  (默认头像不删除)
     */
    @GetMapping("/delete")
    public Object deleteSinger(Integer id){

        //先查询 后删除
        Singer singer = singerService.selectByPrimaryKey(id);
        if(!singer.getPic().equals("/img/singerPic/hhh.jpg")){ // 不是默认头像才会删除 (默认头像不删除)
            File singerAvatarFile = new File(System.getProperty("user.dir") + singer.getPic());
            singerAvatarFile.delete();//删除头像, 不管删除成功与否. 真正决定删除成功与否的是 下面的数据库中的删除
        }

        boolean flag = singerService.delete(id);

        return flag;
    }

    /**
     * 根据主键查询 歌手
     */
    @GetMapping("/selectByPrimaryKey")
    public Object selectByPrimaryKey(Integer id){
        return singerService.selectByPrimaryKey(id);
    }

    /**
     * 根据歌手姓名 模糊查询列表
     */
    @GetMapping("/singerOfName")
    public Object singerOfName(String name){
        return singerService.singerOfName("%"+name+"%");//模糊查询
    }

    /**
     * 根据性别查询
     */
    @GetMapping("/sex/detail")
    public Object singerOfSex(String sex){
        return singerService.singerOfSex(Integer.parseInt(sex));
    }

    /**
     * 修改歌手头像
     * 发现一个问题: 当修改一个用户的头像后, 该用户在数据库中的头像地址被修改了
     *              但是 之前 存在本地的 用户头像 却还在本地, 更新后的文件也在本地, 这样头像岂不越存越多?
     * 解决: 修改时, 把用户旧头像的本地地址 先记录下来, 更新头像成功后, 将旧头像删除
     */
    @PostMapping("/avatar/update")
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
//            String filePath = "classpath:/img/singerPic/";//不能用classpath, 项目在E盘, 给我定位到C盘去了
            String filePath = System.getProperty("user.dir")
                    +System.getProperty("file.separator")+"img"
                    +System.getProperty("file.separator")+"singerPic"
                    +System.getProperty("file.separator"); // 项目根目录/img/singerPic/;

            //如果文件路径(目录)不存在, 新增该路径
            File file1 = new File(filePath);
            if(!file1.exists()){//不存在
                file1.mkdir();
            }
            //实际的文件地址
            File dest = new File(filePath+System.getProperty("file.separator")+fileName);
            //存储到数据库里的相对文件地址
            String storeAvatarPath = "/img/singerPic/"+fileName;
            Singer singer = new Singer();
            singer.setId(id);
            singer.setPic(storeAvatarPath);
            try {
                avatarFile.transferTo(dest);//文件上传

                //先查询 后修改
                Singer oldSinger = singerService.selectByPrimaryKey(id);
                if(!oldSinger.getPic().equals("/img/singerPic/hhh.jpg")){ // 不是默认头像才会删除 (默认头像不删除)
                    File singerAvatarFile = new File(System.getProperty("user.dir") + oldSinger.getPic());
                    singerAvatarFile.delete();//删除头像, 不管删除成功与否. 真正决定删除成功与否的是 下面的数据库中的删除
                }

                boolean flag = singerService.update(singer);//修改数据库中 歌手头像字段pic 为新上传的文件的相对文件地址
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
