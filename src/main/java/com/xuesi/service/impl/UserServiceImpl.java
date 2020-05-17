package com.xuesi.service.impl;

import com.xuesi.mapper.UserMapper;
import com.xuesi.pojo.*;
import com.xuesi.service.UserService;
import com.xuesi.utils.UpUtils;
import com.xuesi.utils.VideoUtil;
import it.sauronsoftware.jave.MultimediaInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper mapper;

    @Override
    public int loginUser(User user) {
        return mapper.loginUser(user);
    }

    @Override
    public User getUserByEmail(String eamil) {
        return mapper.getUserByEmail(eamil);
    }

    @Override
    public int upLoadImage(User user) {
        return mapper.updateBySelective(user);
    }

    @Override
    public int updateUser(User user) {
        return mapper.updateBySelective(user);
    }


    @Override
    public int validateEmail(User user) {
        int res = mapper.validateEmail(user);
        return res;
    }

    @Override
    public Result getAll(Integer stu, String timerange, Integer page, Integer limit) {
        Integer start = (page - 1) * limit;
        List<User> list = null;
        String[] split = null;
        System.out.println("下标的start值："+start);
        if (timerange != null && timerange != "") {
            split = timerange.split("~");
            list = mapper.selectUser(stu, start, limit, split[0], split[1]);
        } else {
            list = mapper.selectUser(stu, start, limit, null, null);
        }
        Integer total = mapper.getCount();
        Result result = new Result();
        result.setTotal(total);
        result.setItem(list);
        return result;
    }



    @Override
    public Integer addUser(User user) {
        return mapper.addUser(user);
    }

}
