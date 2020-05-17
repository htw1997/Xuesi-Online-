package com.xuesi.utils;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

//专门用来解析视频数据的
public class VideoUtil {

    public static MultimediaInfo getInfo(String path, HttpServletRequest request){

        //path = xx.mp4
        //String realPath = request.getSession().getServletContext().getRealPath("D:\\111\\video_clips\\src\\main\\webapp\\WEB-INF\\video\\");
        String realPath = request.getSession().getServletContext().getRealPath("/WEB-INF/video/");
        System.out.println("VideoUtil中的realPath到底是啥："+realPath);
        File file = new File(realPath + path);
        System.out.println("VideoUtil中的file："+file);
        System.out.println(file);
        Encoder encoder = new Encoder();
        try {
            MultimediaInfo info = encoder.getInfo(file);
            System.out.println("info信息是啥："+info);
            return  info;
        } catch (EncoderException e) {
            e.printStackTrace();
            return null;
        }
    }


    //问题：RealPath为空，如何获取realPath
    //如何将视频和图片上传到本机电脑磁盘上，而非tomcat。
    //如何播放视频并展示图片。


}
