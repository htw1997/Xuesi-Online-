package com.xuesi.mapper;

import com.xuesi.pojo.Result;
import com.xuesi.pojo.Shoucang;
import com.xuesi.pojo.Subject;
import com.xuesi.pojo.Video;

import java.util.List;
import java.util.Map;

public interface ShoucangMapper {






    List<Shoucang> selectShoucang(int userId);


    int checkFavorites(Map<String, Object> map);


    void addShoucang(Shoucang shoucang);


    void deleteShoucang(int id);

    Shoucang selectShoucang2(Map<String, Object> map);

}
