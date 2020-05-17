package com.xuesi.service;

import com.xuesi.pojo.Result;
import com.xuesi.pojo.Video;
import com.xuesi.pojo.VideoByVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface VideoService {


    //----------------前台-----------------



    void addPlayNum(Video video);

    //----------------后台----------------

    /**
     * 按条件获取video列表信息
     *
     * @param stu
     * @return
     */
    Result getAll(Integer stu, String timerange, Integer page, Integer limit);

    Result delById(String[] ids);

   //Result updateById(String vid, String field, String value);

    Result selectByLike(String vid,String videoDesc);


    Result uploadVideo(MultipartFile file, HttpServletRequest request);

    Result addVideo(Video video, HttpServletRequest request);


    Result delVideoById(int id);


    Video selectById(String vid);




    Result upexcel(MultipartFile file);

    Video findVideoById(int videoId);


    Result getAllById(Integer stu, String timerange, Integer page, Integer limit, HttpServletRequest request);

    Result edit(String vid, String title, String videoDesc, String status);


    void updateLikeCounts(@Param("id1")int id1,@Param("likeCounts") int likeCounts);

    void addLikeCounts(@Param("id1")int id1, @Param("likeCounts")int likeCounts);
}