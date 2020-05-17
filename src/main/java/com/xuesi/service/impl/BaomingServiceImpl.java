package com.xuesi.service.impl;

import com.xuesi.mapper.BaomingMapper;
import com.xuesi.pojo.Baoming;
import com.xuesi.pojo.Course;
import com.xuesi.pojo.Result;
import com.xuesi.service.BaomingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class BaomingServiceImpl implements BaomingService {
@Autowired
    BaomingMapper baomingMapper;


    @Override
    public String addBaoming(String name, String tel, String qq,String status, HttpServletRequest request) {
        Baoming baoming = new Baoming();
        baoming.setName(name);
        System.out.println("获得到的name:"+name);
        baoming.setTel(tel);
        baoming.setQq(qq);
        baoming.setStatus(status);
        int i = baomingMapper.insertBaoming(baoming);
        if(i>0){
            return "success";
        }else{
            return "fail";
        }

    }

    @Override
    public Result getAll(Integer stu, String timerange, Integer page, Integer limit) {
        Integer start = (page - 1) * limit;
        System.out.println("起始页："+start);
        List<Baoming> list=null;
        String[] split = null;
        //分页
        if (timerange != null && timerange != "") {
            split = timerange.split("~");
            list = baomingMapper.selectBaoming(stu, start, limit, split[0], split[1]);
        }else{
            list = baomingMapper.selectBaoming(stu, start, limit, null, null);
        }
        Integer total = baomingMapper.getCount(stu);
        Result result = new Result();
        result.setTotal(total);
        result.setItem(list);
        return result;
    }



}
