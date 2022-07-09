package org.pjj.music.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author PengJiaJun
 * @Date 2021/11/6 15:05
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {// WebMvcConfigurerAdapter 过时了

    /**
     * 解决跨域问题
     *
     * 前后端在一起的项目没有跨域问题
     * 前后端分离的项目会出现跨域问题, 前端项目port:8080 , 后端项目port:8888 不在一个域, 会出现跨域问题
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**") //为指定的路径模式启用跨源请求处理
                //.allowedOrigins("*") //跨域失败(报错), 百度说springboot2.2好像是这个方法, 之后用 下面这个 方法
                .allowedOriginPatterns("*")//设置浏览器允许跨域请求的源 (*表示 所有)
                .allowedMethods("POST", "GET", "PUT", "OPTIONS", "DELETE")  //设置 http方法 为允许的
                .allowCredentials(true)
                .allowedHeaders("*");
    }

    /**
     * 映射静态资源 (不配置的话 前端访问不到, 只能访问到resources/static/..)
     * http://localhost:8888/img/.../...jpg  映射到  项目/img/../..jpg
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //项目根目录/img/
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file:"+System.getProperty("user.dir")
                        +System.getProperty("file.separator")+"img"
                        +System.getProperty("file.separator"));


        //项目根目录/song/
        registry.addResourceHandler("/song/**")
                .addResourceLocations("file:"+System.getProperty("user.dir")
                        +System.getProperty("file.separator")+"song"
                        +System.getProperty("file.separator"));
    }

}
