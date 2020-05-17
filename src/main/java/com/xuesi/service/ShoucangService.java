package com.xuesi.service;

import com.xuesi.pojo.Result;
import com.xuesi.pojo.Shoucang;
import com.xuesi.pojo.Subject;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ShoucangService {







    List<Shoucang> showFavourite(int userId);


    boolean selectShoucang(int userId, int id);



    void addShoucang(Shoucang shoucang);



    Shoucang selectShoucang2(int userId, int videoId);

    void deleteShoucang(int id);
}
