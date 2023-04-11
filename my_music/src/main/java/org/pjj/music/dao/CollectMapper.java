package org.pjj.music.dao;

import org.pjj.music.domain.Collect;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectMapper {


    int addMeLove(Collect collect);

    int isMeLove(Collect collect);

    int delMeLove(Collect collect);

    List<Collect> detail(Integer userId);
}
