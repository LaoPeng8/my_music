package org.pjj.music.service;

import org.pjj.music.domain.Collect;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CollectService {

    int addMeLove(Collect collect);

    List<Collect> detail(Integer userId);

    int deleteMeLove(Collect collect);

}
