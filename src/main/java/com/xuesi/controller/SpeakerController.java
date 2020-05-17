package com.xuesi.controller;


import com.xuesi.pojo.Admin;
import com.xuesi.pojo.Result;
import com.xuesi.pojo.Speaker;
import com.xuesi.pojo.Video;
import com.xuesi.service.SpeakerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/speakers")
@Api(value = "讲师的管理功能", tags = "讲师的管理功能")
public class SpeakerController {

    @Autowired
    SpeakerService speakerService;

//------------------------------后台---------------------------------------

    /**
     * 获取全部讲师
     *
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/getAll")
    @ResponseBody
    public Result getAll(Integer stu, String timerange, Integer page, Integer limit, HttpServletRequest request) {
        Admin admin1 = (Admin) request.getSession().getAttribute("user");
        Admin admin2 = new Admin();
        admin2.setrId(admin1.getrId());
        String admin = "admin";
        //赋值，管理员的字段
        System.out.println("测试从前端获得的rId：" + admin1.getrId());
        admin2.setUsername(admin1.getUsername());
        System.out.println("测试从前端获得的Username：" + admin1.getUsername());
        admin2.setId(admin1.getId());
        //判断获得的rid是否等于管理员
        if (admin.equals(admin2.getrId())) {
            Result result = speakerService.getAll(stu, timerange, page, limit);
            return result;
        } else {
            Result result = speakerService.getAllById(stu, timerange, page, limit,request);
            return result;
        }
    }

    /**
     * 删除讲师
     *
     * @param ids
     * @return
     */
    @RequestMapping("/del")
    @ResponseBody
    public Result delById(String[] ids) {
        Result result = speakerService.delById(ids);
        return result;
    }

    /**
     * 添加讲师
     *
     * @param speaker
     * @param request
     * @return
     */
    @RequestMapping("/addspeaker")
    @ResponseBody
    public Result addspeaker(Speaker speaker, HttpServletRequest request) {
        Result result = speakerService.addSpeaker(speaker, request);
        System.out.println("controller:" + result);
        System.out.println("难受的地址" + speaker.getHeadImgUrl());
        return result;
    }

    /**
     * 修改讲师描述
     *
     * @param
     * @param
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Result edit( String vid,Speaker speaker,HttpServletRequest request) {
        Result result = speakerService.edit(vid, speaker,request);
        return result;
    }
/**
 * @RequestMapping("/edit")
 *     @ResponseBody
 *     public Result edit(String vid, Speaker speaker) {
 *         Result result = speakerService.edit(vid, speaker);
 *         return result;
 *     }
 */
    /**
     * 根据id进行删除
     *
     * @param id
     * @return
     */
    @RequestMapping("/delById")
    @ResponseBody
    public Result delSpeakerById(String id) {
        Result result = speakerService.delSpeakerById(id);
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
     * 根据用户id查询,并带着数据跳转页面
     *
     * @param vid
     * @param model
     * @return
     */
    @RequestMapping("/getEdit")
    public String getEdit(String vid, Model model) {
        //通过id取找当前行的数据,并且返回给前端  session域     request域
        Speaker speaker = speakerService.selectById(vid);
        model.addAttribute("speaker", speaker);
        return "behind/editSpeaker";
    }

    /**
     * 按照需求进行模糊查询（根据id和简介）
     *
     * @param vid
     * @param speakerDesc
     * @return
     */
    @RequestMapping("/selectByLike")
    @ResponseBody
    public Result selectByLike(String vid, String speakerDesc) {

        return speakerService.selectByLike(vid, speakerDesc);
    }

//-------------------------------后台页面跳转--------------------------


    /**
     * 跳转到speaker页面
     *
     * @return
     */
    @RequestMapping("/speaker")
    public String speaker() {
        return "behind/speaker";
    }

    /**
     * 跳转到后台添加讲师页面
     *
     * @return
     */
    @RequestMapping("/addSpeaker")
    public String addSpeaker() {
        return "/behind/addSpeaker";
    }
}
