package org.pjj.music.controller;

import com.alibaba.fastjson.JSONObject;
import org.pjj.music.dao.CollectMapper;
import org.pjj.music.domain.Collect;
import org.pjj.music.domain.Consumer;
import org.pjj.music.domain.Singer;
import org.pjj.music.service.ConsumerService;
import org.pjj.music.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 用户 Controller
 * @author PengJiaJun
 * @Date 2021/11/14 17:00
 */
@RestController
@RequestMapping("/user")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

    @Autowired
    private CollectMapper collectMapper;

    /**
     * 查询全部歌手
     */
    @GetMapping
    public Object allSinger(){
        return consumerService.allConsumer();
    }

    /**
     * 用户登录
     */
    @PostMapping("/login/status")
    public Object updateSingerPic(String username, String password, HttpSession session){
        JSONObject jsonObject = new JSONObject();

        if(username == null || "".equals(username)){
            jsonObject.put(Const.CODE, 0);
            jsonObject.put(Const.MSG, "用户名不能为空");
            return jsonObject;
        }
        if(password == null || "".equals(password)){
            jsonObject.put(Const.CODE, 0);
            jsonObject.put(Const.MSG, "密码不能为空");
            return jsonObject;
        }

        boolean flag = consumerService.verifyPassword(username,password);
        if(flag){   //登录成功
            jsonObject.put(Const.CODE,1);
            jsonObject.put(Const.MSG,"登录成功");
            jsonObject.put("userMsg", consumerService.getByUsername(username));
            session.setAttribute(Const.NAME,username);
        }else{
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,"登录失败");
        }
        return jsonObject;

    }

    /**
     * 增加
     */
    @PostMapping("/add")
    public Object addSinger(Consumer consumer){
        JSONObject jsonObject = new JSONObject();

        if(consumer.getUsername() == null || "".equals(consumer.getUsername())){
            jsonObject.put(Const.CODE, 0);
            jsonObject.put(Const.MSG, "用户名不能为空");
            return jsonObject;
        }
        Consumer consumerT = consumerService.getByUsername(consumer.getUsername().trim());
        if(consumerT != null){
            jsonObject.put(Const.CODE, 0);
            jsonObject.put(Const.MSG, "用户名已存在");
            return jsonObject;
        }
        if(consumer.getPassword() == null || "".equals(consumer.getPassword())){
            jsonObject.put(Const.CODE, 0);
            jsonObject.put(Const.MSG, "密码不能为空");
            return jsonObject;
        }

        boolean flag = consumerService.insert(consumer);
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
    public Object updateSinger(Consumer consumer){
        JSONObject jsonObject = new JSONObject();

        boolean flag = consumerService.update(consumer);
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

        //查询文件路径, 删除文件(用户头像)后, 再删除数据库中的记录
        Consumer consumer = consumerService.selectByPrimaryKey(id);
        if(!"/img/user.jpg".equals(consumer.getAvator())){//不删除默认头像
            File file = new File(System.getProperty("user.dir") + System.getProperty("file.separator") + consumer.getAvator());
            file.delete();
        }

        return consumerService.delete(id);
    }

    /**
     * 根据主键查询 歌手
     */
    @GetMapping("/selectByPrimaryKey")
    public Object selectByPrimaryKey(Integer id){
        return consumerService.selectByPrimaryKey(id);
    }

    /**
     * 验证用户名密码
     */
    @GetMapping("/verifyPassword")
    public Object verifyPassword(String username, String password){
        return consumerService.verifyPassword(username, password);
    }

    /**
     * 根据用户名查询用户
     */
    @GetMapping("/getByUsername")
    public Object getByUsername(String username){
        return consumerService.getByUsername(username);
    }

    /**
     * 修改用户头像
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
                    +System.getProperty("file.separator")+"avatorImages"
                    +System.getProperty("file.separator"); // 项目根目录/img/avatorImages/;

            //如果文件路径(目录)不存在, 新增该路径
            File file1 = new File(filePath);
            if(!file1.exists()){//不存在
                file1.mkdir();
            }
            //实际的文件地址
            File dest = new File(filePath+System.getProperty("file.separator")+fileName);
            //存储到数据库里的相对文件地址
            String storeAvatarPath = "/img/avatorImages/"+fileName;
            Consumer consumer = new Consumer();
            consumer.setId(id);
            consumer.setAvator(storeAvatarPath);
            try {
                avatarFile.transferTo(dest);//文件上传

                //先查询 后修改
                Consumer oldConsumer = consumerService.selectByPrimaryKey(id);
                if(!oldConsumer.getAvator().equals("/img/avatorImages/user.jpg")){ // 不是默认头像才会删除 (默认头像不删除)
                    File singerAvatarFile = new File(System.getProperty("user.dir") + oldConsumer.getAvator());
                    singerAvatarFile.delete();//删除头像, 不管删除成功与否. 真正决定删除成功与否的是 下面的数据库中的删除
                }

                boolean flag = consumerService.update(consumer);//修改数据库中 用户头像字段avator 为新上传的文件的相对文件地址
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


    /**
     * 根据主键查询 用户信息
     */
    @GetMapping("/detail")
    public Object userDetail(Integer id){
        return consumerService.selectByPrimaryKey(id);
    }


    /**
     * 根据主键查询 用户我喜欢歌曲id 集合
     */
    @GetMapping("/meLoveSongId")
    public List<Integer> MeLoveSongId(Integer userId){
        return consumerService.selectMeLoveSongId(userId);
    }

    @GetMapping("/isMeLove")
    public Object isMeLove(Integer userId, Integer songId) {
        Collect collect = new Collect();
        collect.setUserId(userId);
        collect.setSongId(songId);

        int meLove = collectMapper.isMeLove(collect);

        JSONObject jsonObject = new JSONObject();
        if(meLove >= 1) {
            jsonObject.put(Const.CODE,1);
            jsonObject.put(Const.MSG,"love");
        }else {
            jsonObject.put(Const.CODE,0);
            jsonObject.put(Const.MSG,"notLove");
        }

        return jsonObject;
    }

}
