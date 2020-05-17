package com.xuesi.controller;

import com.xuesi.service.SubjectService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/subject")
@Api(value = "课程管理功能", tags = "课程管理功能")
public class SubjectController {
    @Autowired
    SubjectService subjectService;


    @RequestMapping("/findSubjectTypeList")
    @ResponseBody
    public Map findSubjectTypeList() {

        return  subjectService.findSubjectTypeList();
    }
}
