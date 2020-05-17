package com.xuesi.service.impl;

import com.xuesi.mapper.CourseMapper;
import com.xuesi.mapper.SubjectMapper;
import com.xuesi.mapper.VideoMapper;
import com.xuesi.pojo.Course;
import com.xuesi.pojo.Subject;
import com.xuesi.pojo.SubjectDto;
import com.xuesi.pojo.Video;
import com.xuesi.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    SubjectMapper subjectMapper;
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    VideoMapper videoMapper;

    @Override
    public List<Subject> subjectAll() {

        List<Subject> subjectList = subjectMapper.subjectAll();

        return subjectList;
    }

    @Override
    public Subject subjectById(int subjectId) {

        return subjectMapper.subjectById(subjectId);

    }

    @Override
    public Map findSubjectTypeList() {
        List<Map> list = subjectMapper.findSubjectTypeList();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("data", list);
        return map;
    }

    @Override
    public SubjectDto subjectBYId(int subjectId) {
        SubjectDto subjectDto = new SubjectDto();
       // List<SubjectDto> subjectDtos = new SubjectDto();

        Subject subjects = subjectMapper.subjectBYId(subjectId);

            List<Course> courserList = courseMapper.getCourserBYSubjectId(subjects.getId());
            for (int j = 0; j < courserList.size(); j++) {
                List<Video> video = videoMapper.findVideoBYCid(courserList.get(j).getId());
               // courserList.get[i].setvideolist(video);
                subjectDto.setVideoList(video);

                subjectDto.setVideos(video);

            }
        subjectDto.setCourseList(courserList);


      return subjectDto;


    }

}
