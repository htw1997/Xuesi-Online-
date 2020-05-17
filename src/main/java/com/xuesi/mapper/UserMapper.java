package com.xuesi.mapper;

import com.xuesi.pojo.User;
import com.xuesi.pojo.Video;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {


    Integer addUser(User user);

    int loginUser(User user);

    User getUserByEmail(String phone);

    int updateBySelective(User user);

    int insert(User user);



    int validateEmail(User user);



    Integer getCount();


    List<User> selectUser(@Param("stu") Integer stu, @Param("start") Integer start, @Param("limit") Integer limit, @Param("pretime") String pretime, @Param("endtime") String endtime);
}
