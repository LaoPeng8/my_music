package org.pjj.music.service.impl;

import org.pjj.music.dao.SongListMapper;
import org.pjj.music.domain.SongList;
import org.pjj.music.domain.SongListStyle;
import org.pjj.music.domain.vo.SongListVo;
import org.pjj.music.service.SongListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.HashMap;
import java.util.List;

/**
 * 歌单实现类
 * @author PengJiaJun
 * @Date 2021/11/12 10:55
 */
@Service
public class SongListServiceImpl implements SongListService {

    @Autowired
    private SongListMapper songListMapper;

    /**
     * 增加
     *
     * @param songList
     * @return
     */
    @Override
    public boolean insert(SongList songList) {
        return songListMapper.insert(songList) > 0;
    }

    /**
     * 修改
     *
     * @param songList
     * @return
     */
    @Override
    public boolean update(SongList songList) {

        if(!songList.getPic().equals("") && songList.getPic() == null){//如果songList带有 pic 属性, 则说明需要修改歌单图片, 才会删除老的歌单图片
            //先查询 后修改
            SongList oleSongList = selectByPrimaryKey(songList.getId());
            if(!oleSongList.getPic().equals("/img/songListPic/123.jpg")){ // 不是默认头像才会删除 (默认头像不删除)
                File songListPic = new File(System.getProperty("user.dir") + oleSongList.getPic());
                songListPic.delete();//删除旧的歌单头像, 不管删除成功与否. 真正决定删除成功与否的是 下面的数据库中的删除
            }
        }

        return songListMapper.update(songList) > 0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) {
        //先查询 后删除
        SongList songList = selectByPrimaryKey(id);
        if(!songList.getPic().equals("/img/songListPic/123.jpg")){ // 不是默认歌单头像才会删除 (默认歌单头像不删除)
            File singerAvatarFile = new File(System.getProperty("user.dir") + songList.getPic());
            singerAvatarFile.delete();//删除歌单头像, 不管删除成功与否. 真正决定删除成功与否的是 下面的数据库中的删除
        }
        return songListMapper.delete(id) > 0;
    }

    /**
     * 根据主键查询 歌单
     *
     * @param id
     * @return
     */
    @Override
    public SongList selectByPrimaryKey(Integer id) {
        return songListMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询全部歌单
     *
     * @return
     */
    @Override
    public List<SongListVo> allSongList() {
        List<SongListVo> songListVos = songListMapper.allSongList();
        for (SongListVo item : songListVos) {
            if(item.getName() == null || item.getName().equals("") || item.getName().equals("null")){
                item.setName("无");
            }
        }
        return songListVos;
    }

    /**
     * 根据标题 精确 查询歌单列表
     *
     * @param title
     * @return
     */
    @Override
    public List<SongList> songListOfTitle(String title) {
        return songListMapper.songListOfTitle(title);
    }

    /**
     * 根据标题 模糊 查询歌单列表
     *
     * @param title
     * @return
     */
    @Override
    public List<SongList> likeTitle(String title) {
        return songListMapper.likeTitle(title);
    }

    /**
     * 根据风格 精确 查询歌单列表
     *
     * @param style
     * @return
     */
    @Override
    public List<SongList> songListOfStyle(String style) {
        return songListMapper.songListOfStyle(style);
    }

    /**
     * 根据风格 模糊 查询歌单列表
     *
     * @param style
     * @return
     */
    @Override
    public List<SongList> likeStyle(String style) {
        return songListMapper.likeStyle(style);
    }

    @Override
    public List<SongListStyle> styleAll() {
        return songListMapper.styleAll();
    }

    @Override
    public int styleInsert(String name) {
        return songListMapper.styleInsert(name);
    }

    @Override
    public int styleDelete(Integer id) {
        return songListMapper.styleDelete(id);
    }

    @Override
    public int styleUpdate(SongListStyle songListStyle) {
        return songListMapper.styleUpdate(songListStyle);
    }

    @Override
    public List<HashMap> indexSongListStyle() {
        return songListMapper.indexSongListStyle();
    }
}
