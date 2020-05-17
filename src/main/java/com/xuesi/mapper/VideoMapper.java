package com.xuesi.mapper;

import com.xuesi.pojo.Video;
import com.xuesi.pojo.VideoByVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface VideoMapper {


//--------------------------------前台-----------------------------

    Video findVideoById(int id);

    List<Video> findVideoByCid(int courseId);

    List<Video> videoListAll(VideoByVo videoByVo);

    @Update("update video set play_num = #{playNum} where id = #{id}")
    void addPlayNum(Video video);

    List<Video> findVideoBySid(int spearkerId);


//---------------------------------后台------------------------------

    /**
     * 按条件查询video列表
     *
     * @param stu 发布状态
     * @return 返回存放video的列表
     */
    List<Video> selectVideos(@Param("stu") Integer stu, @Param("start") Integer start, @Param("limit") Integer limit, @Param("pretime") String pretime, @Param("endtime") String endtime);

    /**
     * 获取数据总量
     *
     * @return 返回一个整型的数据总量
     */
    Integer getCount(@Param("stu") Integer stu);


    int deleteByPrimaryKey(int id);


    //int updateById(@Param("vid") String vid,@Param("field") String field, @Param("value") String value);


    /**
     * 通过id 或者视频简介  进行模糊查询
     * @param vid 视频id
     * @param videoDesc 视频简介
     * @return list集合存放着video
     */
    List<Video> selectByLike(@Param("vid") String vid,@Param("videoDesc") String videoDesc);

    int insert(Video video);


    Video selectByPrimaryKey(String vid);





    int deleteByPrimarykey(String id);


    List<Video> selectVideosById(@Param("adminid")String adminid, @Param("stu")Integer stu, @Param("start")Integer start, @Param("limit") Integer limit, @Param("pretime") String pretime, @Param("endtime") String endtime);

    Integer getCountById(@Param("adminid") String adminid,@Param("stu")  Integer stu);
    /**
     * 通过id 修改对应的属性值
     * @param vid   视频id
     * @param videoDesc  视频修改后的描述信息
     * @return 返回一个整型 1 success 0 error
     */

    int edit(@Param("vid")String vid, @Param("title")String title, @Param("videoDesc")String videoDesc, @Param("status")String status);


    List<Video> findVideoBYCid(int id);

    Video selectById(int videoid);

    List<Video> getVideoListByVideoid(int videoid);

    void updateLikeCounts(@Param("id1")int id1, @Param("likeCounts")int likeCounts);


    void addLikeCounts(@Param("id1")int id1, @Param("likeCounts")int likeCounts);

}
