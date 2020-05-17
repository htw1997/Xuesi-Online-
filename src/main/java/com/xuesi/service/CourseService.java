package com.xuesi.service;

import com.xuesi.pojo.Course;
import com.xuesi.pojo.Result;
import com.xuesi.pojo.Speaker;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface CourseService {



    //不能
    Course fingCourseById(int id);

    int setCourseById(Course course);



    Result getAll(Integer stu, String timerange, Integer page, Integer limit);

    Result delById(String[] ids);

    Result selectByLike(String vid, String courseDesc);

    Result addCourse(Course course, HttpServletRequest request);

    Course selectById(String vid);

    Result delCourseById(String id);

    Result edit(String vid, String courseDesc);


    Map findCourseTypeList();

}
