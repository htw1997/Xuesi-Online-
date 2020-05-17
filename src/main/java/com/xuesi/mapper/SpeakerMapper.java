package com.xuesi.mapper;

import com.xuesi.pojo.Speaker;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;


import java.util.List;

public interface SpeakerMapper {



    Speaker findSpeakerById(String id);
    /**
     * 全部查询Speaker
     * @param start
     * @param limit
     * @return 返回存放video的列表
     */

    /**
     * 查看表中几条数据
     * @return
     */
    Integer getCount(@Param("stu") Integer stu);

    int deleteByPrimaryKey(String id);

    int insert(Speaker speaker);


    List<Speaker> selectSpeaker(@Param("stu") Integer stu, @Param("start") Integer start, @Param("limit") Integer limit, @Param("pretime")String pretime, @Param("endtime")String endtime);

    Speaker selectByPrimaryKey(String id);

    List<Speaker> selectByLike(@Param("vid") String vid, @Param("speakerDesc") String speakerDesc);

    List<Speaker> selectSpeakerById(@Param("adminid")String adminid,@Param("stu") Integer stu, @Param("start") Integer start, @Param("limit") Integer limit, @Param("pretime")String pretime, @Param("endtime")String endtime);

    Integer getCountById(@Param("adminid") String adminid,@Param("stu")  Integer stu);

    int edit(@Param("vid")String vid, @Param("speakerName")String speakerName, @Param("speakerDesc")String speakerDesc, @Param("speakerJob")String speakerJob, @Param("headImgUrl")String headImgUrl);

    int addSpeakerByRegister(@Param("id")int id, @Param("speakerJob")String speakerJob);

    String selectNickNameById(String speakerId);
}
