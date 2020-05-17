package com.xuesi.controller;

import com.github.pagehelper.PageInfo;
import com.xuesi.pojo.*;
import com.xuesi.service.CourseService;
import com.xuesi.service.SubjectService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/course")
@Api(value = "前台和后台的课程管理功能", tags = "前台和后台的课程管理功能")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @Autowired
    private SubjectService subjectService;

//-----------------------------------前台----------------------------------------

    /**
     * 查找所有课程
     *
     * @param subjectId
     * @param model
     * @return
     */
    @RequestMapping("/list")
    public String list(int subjectId,Model model) {
        Subject subject = subjectService.subjectById(subjectId);

       //SubjectDto subject = subjectService.subjectBYId(subjectId);
        model.addAttribute("subject", subject);
        return "before/course";
    }

//-------------------------------后台-------------------------------

    /**
     * 获取全部讲师
     *
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping( "/getAll")
    @ResponseBody
    public Result getAll(Integer stu, String timerange, Integer page, Integer limit) {
        Result result = courseService.getAll(stu, timerange, page, limit);
        return result;
    }

    /**
     * 删除课程
     *
     * @param ids
     * @return
     */

    @RequestMapping("/del")
    @ResponseBody
    public Result delById(String[] ids) {
        Result result = courseService.delById(ids);
        return result;
    }

    /**
     * 修改视频的描述信息
     *
     * @param vid
     * @param field
     * @param value
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public Result updateById(String vid, String field, String value) {
        //  Result result = videoService.updateById(vid, field, value);
        return null;
    }

    /**
     * 按照需求进行模糊查询（根据id和简介）
     *
     * @param vid
     * @param courseDesc
     * @return
     */
    @RequestMapping("/selectByLike")
    @ResponseBody
    public Result selectByLike(String vid, String courseDesc) {

        return courseService.selectByLike(vid, courseDesc);
    }

    /**
     * 添加讲师
     *
     * @param course
     * @param request
     * @return
     */
    @RequestMapping("/addcourse")
    @ResponseBody
    public Result addcourse(Course course, HttpServletRequest request) {
        Result result = courseService.addCourse(course, request);
        System.out.println("controller:" + result);
        return result;
    }

    /**
     * 根据id进行删除
     *
     * @param id
     * @return
     */
    @RequestMapping("/delById")
    @ResponseBody
    public Result delCourseById(String id) {
        Result result = courseService.delCourseById(id);
        return result;
    }

    /**
     * 修改讲师描述
     *
     * @param vid
     * @param courseDesc
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Result edit(String vid, String courseDesc) {
        Result result = courseService.edit(vid, courseDesc);
        return result;
    }

    /**
     * 根据用户id查询,并带着数据跳转页面
     *
     * @param vid
     * @param model
     * @return
     */
    @RequestMapping("/getEdit")
    public String getEdit(String vid, Model model) {
        //通过id取找当前行的数据,并且返回给前端  session域     request域
        Course course = courseService.selectById(vid);
        model.addAttribute("course", course);
        return "behind/editCourse";
    }

 @RequestMapping("/findCourseTypeList")
 @ResponseBody
 public Map findCourseTypeList() {

        return  courseService.findCourseTypeList();
 }

//----------------------------后台页面跳转-------------------------

    /**
     * 跳转到后台添加课程页面
     *
     * @return
     */
    @RequestMapping("/addCourse")
    public String addCourse() {

        return "/behind/addCourse";
    }

    /**
     * 跳转到course页面
     *
     * @return
     */
    @RequestMapping("/course")
    public String course() {
        return "behind/course";
    }


}
