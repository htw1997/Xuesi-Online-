package com.xuesi.mapper;

import com.xuesi.pojo.Admin;
import com.xuesi.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HomeMapper {
    int deleteByPrimaryKey(String id);

    int insert(Admin record);

    Admin selectByPrimaryKey(String id);

    List<Admin> selectAll();

    int updateByPrimaryKey(Admin record);




    Admin selectByUserName(String username);

    int selectTureByOldPassword(@Param("username") String username,@Param("oldPassword")String oldPassword);

    int editPassword(@Param("username")String username,@Param("password") String password);


    Admin selectAdminByUserName(String username);

    int editAdmin(@Param("vid")String vid, @Param("nickname")String nickname, @Param("faceImage")String faceImage);

    Admin selectByVid(String vid);

    Integer getCount();

    List<Admin> selectUser(@Param("stu") Integer stu, @Param("start") Integer start, @Param("limit") Integer limit, @Param("pretime") String pretime, @Param("endtime") String endtime);

    int editRid(@Param("vid")String vid, @Param("rId")String rId);


}
