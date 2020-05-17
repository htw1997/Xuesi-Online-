package com.xuesi.service;

import com.xuesi.pojo.Baoming;
import com.xuesi.pojo.Result;

import javax.servlet.http.HttpServletRequest;

public interface BaomingService {



    Result getAll(Integer stu, String timerange, Integer page, Integer limit);


    String addBaoming(String name, String tel, String qq, String status,HttpServletRequest request);
}
