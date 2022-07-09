package org.pjj.music.service.impl;

import org.pjj.music.dao.SingerMapper;
import org.pjj.music.domain.Singer;
import org.pjj.music.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author PengJiaJun
 * @Date 2021/11/8 0:18
 */
@Service
public class SingerServiceImpl implements SingerService {

    @Autowired
    private SingerMapper singerMapper;

    /**
     * 增加
     *
     * @param singer
     * @return
     */
    @Override
    public boolean insert(Singer singer) {
        return singerMapper.insert(singer) > 0;
    }

    /**
     * 修改
     *
     * @param singer
     * @return
     */
    @Override
    public boolean update(Singer singer) {
        return singerMapper.update(singer) > 0;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public boolean delete(Integer id) {
        return singerMapper.delete(id) > 0;
    }

    /**
     * 根据主键查询 歌手
     *
     * @param id
     * @return
     */
    @Override
    public Singer selectByPrimaryKey(Integer id) {
        return singerMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询全部歌手
     *
     * @return
     */
    @Override
    public List<Singer> allSinger() {
        return singerMapper.allSinger();
    }

    /**
     * 根据歌手姓名 模糊查询列表
     *
     * @param name
     * @return
     */
    @Override
    public List<Singer> singerOfName(String name) {
        return singerMapper.singerOfName(name);
    }

    /**
     * 根据性别查询
     *
     * @param sex 1男 0女    sex原本类型为 byte, 在这里使用 Integer 是没有问题的, 会自动向上转型(Byte 是比 Integer 小一截的)
     * @return
     */
    @Override
    public List<Singer> singerOfSex(Integer sex) {
        return singerMapper.singerOfSex(sex);
    }
}
