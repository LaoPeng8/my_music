package org.pjj.music;

import org.junit.jupiter.api.Test;
import org.pjj.music.dao.SingerMapper;
import org.pjj.music.domain.Singer;
import org.pjj.music.domain.Song;
import org.pjj.music.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest
class MyMusicApplicationTests {

	@Autowired
	SingerMapper singerMapper;

	@Autowired
	SongService songService;

	@Test
	void contextLoads() {
		Song song = new Song();
		song.setSingerId(530);
		song.setName("爱你一万年");
		song.setUrl("/song/爱你一万年.mp3");
		song.setIntroduction("描述音乐...");

		boolean insert = songService.insert(song);
		System.out.println(insert);
		System.out.println(song.toString());
	}

}
