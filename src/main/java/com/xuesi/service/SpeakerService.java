package com.xuesi.service;


import com.xuesi.pojo.Result;
import com.xuesi.pojo.Speaker;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface SpeakerService {
    //不能
    Speaker findSpeakerById(String id);

    Result delById(String[] ids);

    Result addSpeaker(Speaker speaker, HttpServletRequest request);




    Result delSpeakerById(String id);

    Result getAll(Integer stu, String timerange, Integer page, Integer limit);

    Speaker selectById(String vid);


    Result selectByLike(String vid, String speakerDesc);

    Result getAllById(Integer stu, String timerange, Integer page, Integer limit, HttpServletRequest request);

    Result edit(String vid, Speaker speaker, HttpServletRequest request);

}
