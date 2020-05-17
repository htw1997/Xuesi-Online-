package com.xuesi.service.impl;

import com.xuesi.mapper.ShoucangMapper;
import com.xuesi.mapper.VideoMapper;
import com.xuesi.pojo.*;
import com.xuesi.service.ShoucangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShoucangServiceImpl implements ShoucangService {
    @Autowired
    ShoucangMapper shoucangMapper;
    @Autowired
    VideoMapper videoMapper;





    @Override
    public List<Shoucang> showFavourite(int userId) {

        List<Shoucang> shoucang =shoucangMapper.selectShoucang(userId);
        System.out.println("shoucang:"+shoucang);
        return shoucang;
    }

    @Override
    public boolean selectShoucang(int userId, int id) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("videoid", id);
        int count = shoucangMapper.checkFavorites(map);
        if(count>0){
            return true;
        }
        return false;
    }
    @Override
    public Shoucang selectShoucang2(int userId, int videoId) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        map.put("videoid", videoId);
        Shoucang shoucang = shoucangMapper.selectShoucang2(map);
        return shoucang;
    }
    @Override
    public void addShoucang(Shoucang shoucang) {
         shoucangMapper.addShoucang(shoucang);
    }

    @Override
    public void deleteShoucang(int id) {

        shoucangMapper.deleteShoucang(id);
    }




}
