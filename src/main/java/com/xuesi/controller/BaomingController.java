package com.xuesi.controller;

import com.xuesi.pojo.Baoming;
import com.xuesi.pojo.Course;
import com.xuesi.pojo.Result;
import com.xuesi.service.BaomingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/baoming")
@Api(tags = "该类实现所有关于报名的功能")
public class BaomingController {
    @Autowired
    BaomingService baomingService;

    /**
     * 添加讲师
     *
     * @param
     * @param
     * @return
     */

    @RequestMapping(value = "/baoming", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "添加报名信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "姓名", required = true, dataType = "String"),
            @ApiImplicitParam(name = "tel", value = "电话", required = true, dataType = "String"),
            @ApiImplicitParam(name = "qq", value = "qq", required = true, dataType = "String")
    })
    public String addcourse(String name, String tel, String qq, HttpServletRequest request) {
        String status = "未沟通";
        System.out.println("baoming:" + name);
        String result = baomingService.addBaoming(name, tel, qq,status, request);
        if (result != null) {
            return "success";
        } else {
            return "fail";
        }

    }


    //------------------------------后台------------------------------------

    /**
     * 获取全部讲师
     *
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    @ApiOperation(value = "获取全部的报名信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "stu", value = "状态", required = true, dataType = "String"),
    })
    public Result getAll(Integer stu, String timerange, Integer page, Integer limit) {
        Result result = baomingService.getAll(stu, timerange, page, limit);
        return result;
    }


}
