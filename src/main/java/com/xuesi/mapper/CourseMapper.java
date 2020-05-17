package com.xuesi.mapper;

import com.xuesi.pojo.Course;
import com.xuesi.pojo.Speaker;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

public interface CourseMapper {

    List<Course> courseAll();




    Course fingCourseById(int id);


    @Update("update course set course_title = #{courseTitle}, course_desc = #{courseDesc}, subject_id = #{subjectId} where id = #{id}")
    int setCourseById(Course course);


    Course getCourserBySubjectId(int subjectId);

    List<Course> Allcourse();

    List<Course> selectCourse(@Param("stu") Integer stu, @Param("start") Integer start, @Param("limit") Integer limit, @Param("pretime")String pretime, @Param("endtime")String endtime);

    Integer getCount(@Param("stu")Integer stu);

    int deleteByPrimaryKey(String id);

    List<Course> selectByLike(@Param("vid")String vid,@Param("courseDesc") String courseDesc);

    int insert(Course course);

    Course selectByPrimaryKey(String vid);

    int edit(@Param("vid")String vid, @Param("courseDesc")String courseDesc);

    String selectSubjectNameById(int subjectId);


    List<Map> findCourseTypeList();


    List<Course> getCourserBYSubjectId(int id);
}
