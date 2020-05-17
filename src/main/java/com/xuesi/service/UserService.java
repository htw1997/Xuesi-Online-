package com.xuesi.service;

import com.xuesi.pojo.Result;
import com.xuesi.pojo.Subject;
import com.xuesi.pojo.User;
import com.xuesi.resultVo.R;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface UserService {


    int loginUser(User user);

    User getUserByEmail(String eamil);

    int upLoadImage(User user);

    int updateUser(User user);





    Integer addUser(User user);

    int validateEmail(User user);


    Result getAll(Integer stu, String timerange, Integer page, Integer limit);


}
