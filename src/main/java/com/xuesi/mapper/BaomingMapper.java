package com.xuesi.mapper;

import com.xuesi.pojo.Baoming;
import com.xuesi.pojo.Course;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BaomingMapper {
    int insertBaoming(Baoming baoming);

    Integer getCount(Integer stu);

    List<Baoming> selectBaoming(@Param("stu") Integer stu, @Param("start") Integer start, @Param("limit") Integer limit, @Param("pretime")String pretime, @Param("endtime")String endtime);



}
