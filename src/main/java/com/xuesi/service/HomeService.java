package com.xuesi.service;

import com.xuesi.pojo.Admin;
import com.xuesi.pojo.Result;

import javax.servlet.http.HttpServletRequest;

public interface HomeService {
    Result login(String username, String password, String code, HttpServletRequest request);

    Result register(String tel, String password,String code, HttpServletRequest request);

    Result editPassword(String oldPassword, String password,String newPassword, HttpServletRequest request);


    Result editAdmin(String vid,Admin admin, HttpServletRequest request);



    Result getAllUser(Integer stu, String timerange, Integer page, Integer limit, HttpServletRequest request);

    Admin selectById(String vid);

    Result editRid(String vid, String rId);

}

