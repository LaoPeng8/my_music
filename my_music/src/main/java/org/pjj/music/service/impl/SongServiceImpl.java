package org.pjj.music.service.impl;

import org.pjj.music.dao.SongMapper;
import org.pjj.music.domain.Song;
import org.pjj.music.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author PengJiaJun
 * @Date 2021/11/9 17:32
 */
@Service
public class SongServiceImpl implements SongService {

    @Autowired
    private SongMapper songMapper;

    /**
     * 增加
     *
     * @param song
     * @return
     */
    @Override
    public boolean insert(Song song) {
        return songMapper.insert(song) > 0;
    }

    /**
     * 修改
     *
     * @param song
     * @return
     */
    @Override
    public boolean update(Song song) {
        return songMapper.update(song) > 0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) {
        return songMapper.delete(id) > 0;
    }

    /**
     * 根据主键查询 歌曲
     *
     * @param id
     * @return
     */
    @Override
    public List<Song> selectByPrimaryKey(Integer id) {
        return songMapper.selectByPrimaryKey(id);
    }

    @Override
    public Song selectByPrimaryKeyMe(Integer id) {
        return songMapper.selectByPrimaryKey(id).get(0);
    }

    /**
     * 查询全部歌曲
     *
     * @return
     */
    @Override
    public List<Song> allSong() {
        return songMapper.allSong();
    }

    /**
     * 根据歌名姓名 模糊查询列表
     *
     * @param name
     * @return
     */
    @Override
    public List<Song> songOfName(String name) {
        return songMapper.songOfName(name);
    }

    /**
     * 根据歌名 模糊查询列表
     *
     * @param name
     * @return
     */
    @Override
    public List<Song> likeSongOfName(String name) {
        return songMapper.likeSongOfName('%'+name+'%');
    }

    /**
     * 根据歌手id查询
     *
     * @param singerId
     * @return
     */
    @Override
    public List<Song> songOfSingerId(Integer singerId) {
        return songMapper.songOfSingerId(singerId);
    }

    /**
     * 根据 歌手名 and 歌名 查询 歌手Id
     *
     * @param singerName
     * @param songName
     */
    @Override
    public Integer songOfSingerNameAndSongName(String singerName, String songName) {
        return songMapper.songOfSingerNameAndSongName(singerName,songName);
    }

    /**
     * 返回 歌曲总数
     *
     * @return
     */
    @Override
    public Integer songCount() {
        return songMapper.songCount();
    }
}
