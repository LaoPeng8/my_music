package org.pjj.music;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * start 2021-11-06 12:46
 * end   2021-00-00 00:00
 *
 * 配置 热加载
 * 1. Ctrl + Shift + a 输入 Registry 找到 Registry... 点进去,
 * 2. 找到 compiler.automake.allow.when.app.running 然后勾选上, 点击右下角close 关闭
 * 3. Ctrl + F9 就可以热加载了, 不用重启项目 (加方法啥的不能热加载, 不过修改方法内容可以热加载)
 */
@SpringBootApplication
@MapperScan("org.pjj.music.dao")
@ServletComponentScan(basePackages = "org.pjj.music.filter")
public class MyMusicApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyMusicApplication.class, args);
	}

}
