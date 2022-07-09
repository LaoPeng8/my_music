package org.pjj.music.service.impl;

import org.pjj.music.dao.ListSongMapper;
import org.pjj.music.domain.ListSong;
import org.pjj.music.domain.Song;
import org.pjj.music.service.ListSongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author PengJiaJun
 * @Date 2021/11/12 23:07
 */
@Service
public class ListSongServiceImpl implements ListSongService {

    @Autowired
    private ListSongMapper listSongMapper;

    /**
     * 增加
     *
     * @param listSong
     * @return
     */
    @Override
    public boolean insert(ListSong listSong) {
        return listSongMapper.insert(listSong) > 0;
    }

    /**
     * 修改
     *
     * @param listSong
     * @return
     */
    @Override
    public boolean update(ListSong listSong) {
        return listSongMapper.update(listSong) > 0;
    }

    /**
     * 删除
     *
     * @param songId
     * @param songListId
     * @return
     */
    @Override
    public boolean delete(Integer songId, Integer songListId) {
        return listSongMapper.delete(songId, songListId) > 0;
    }


    /**
     * 根据主键查询 歌单
     *
     * @param id
     * @return
     */
    @Override
    public ListSong selectByPrimaryKey(Integer id) {
        return listSongMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询全部 歌单
     *
     * @return
     */
    @Override
    public List<ListSong> allListSong() {
        return listSongMapper.allListSong();
    }

    /**
     * 根据 歌单id 查询歌单中的所有歌曲
     *
     * @param songListId
     * @return
     */
    @Override
    public List<Song> songBySongListId(Integer songListId) {
        return listSongMapper.songBySongListId(songListId);
    }

}
