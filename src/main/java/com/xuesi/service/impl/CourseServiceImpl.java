package com.xuesi.service.impl;

import com.github.pagehelper.PageHelper;
import com.xuesi.mapper.CourseMapper;
import com.xuesi.pojo.Course;
import com.xuesi.pojo.Result;
import com.xuesi.pojo.Speaker;
import com.xuesi.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CourseServiceImpl implements CourseService {

@Autowired
    CourseMapper courseMapper;



    @Override
    public Course fingCourseById(int id) {

        return courseMapper.fingCourseById(id);
    }



    @Override
    public int setCourseById(Course course) {

        return courseMapper.setCourseById(course);
    }

    //------------------------------------------后台------------------------------------------
    @Override
    public Result getAll(Integer stu, String timerange, Integer page, Integer limit) {
        Integer start = (page - 1) * limit;
        System.out.println("起始页："+start);
        List<Course> list=null;
        String[] split = null;
        //分页
        if (timerange != null && timerange != "") {
            split = timerange.split("~");
            list = courseMapper.selectCourse(stu, start, limit, split[0], split[1]);
        }else{
            list = courseMapper.selectCourse(stu, start, limit, null, null);
        }
        for (int i = 0; i < list.size(); i++) {
            String subjectName = courseMapper.selectSubjectNameById(list.get(i).getSubjectId());
            list.get(i).setSubjectName(subjectName);
        }
        Integer total = courseMapper.getCount(stu);
        Result result = new Result();
        result.setTotal(total);
        result.setItem(list);
        return result;
    }

    @Transactional
    @Override
    public Result delById(String[] ids) {
        Result result = new Result();
        //需要对用户的权限进行判定
        //循环遍历我们的ids
        try {
            for (int i = 0; i < ids.length; i++) {
                //进行删除操作
                int res = courseMapper.deleteByPrimaryKey(ids[i]);
            }
            result.setMessage("操作成功!");
            result.setStatus(200);
            return result;
        } catch (Exception e) {
            //在控制台打印错误信息
            e.printStackTrace();
            //手动设置事务回滚
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result.setMessage("系统繁忙,请刷新页面在进行尝试");
            result.setStatus(500);
            return result;

        }
    }

    @Override
    public Result selectByLike(String vid, String courseDesc) {
        //需要做逻辑判定
        Result result = new Result();
        List<Course> list = courseMapper.selectByLike(vid,courseDesc);
        result.setStatus(0);
        result.setMessage("success");
        result.setTotal(100);
        result.setItem(list);
        return result;
    }


    @Transactional
    @Override
    public Result addCourse(Course course, HttpServletRequest request) {


        //因为时添加操作  需要事务  设置手动回滚
        Result result =new Result();
        try {
            int i = courseMapper.insert(course);
            if(i>0){
                result.setStatus(200);
            }else{
                result.setStatus(500);
                result.setMessage("插入失败!");
            }
            return  result;
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result.setMessage("未知异常!");
            result.setStatus(500);
            return  result;
        }
    }

    @Override
    public Course selectById(String vid) {
        Course course = courseMapper.selectByPrimaryKey(vid);
        return course;
    }

    @Override
    public Result delCourseById(String id) {
        //可以通过session  获取到角色信息 判定他是否具有删除数据的权力
        Result result = new Result();
        try {
            int i = courseMapper.deleteByPrimaryKey(id);
            if(i>0){
                result.setStatus(200);
            }else{
                result.setStatus(500);
                result.setMessage("删除失败!");
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result.setMessage("未知异常!");
            result.setStatus(500);
            return  result;
        }

    }

    @Override
    public Result edit(String vid, String courseDesc) {
        Result result = new Result();
        try {
            int i = courseMapper.edit(vid,courseDesc);
            if(i>0){
                result.setStatus(200);
            }else{
                result.setStatus(500);
                result.setMessage("删除失败!");
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result.setMessage("未知异常!");
            result.setStatus(500);
            return  result;
        }
    }

    @Override
    public Map findCourseTypeList() {
    List<Map> list = courseMapper.findCourseTypeList();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code",0);
        map.put("data",list);
        return map;
    }


}
