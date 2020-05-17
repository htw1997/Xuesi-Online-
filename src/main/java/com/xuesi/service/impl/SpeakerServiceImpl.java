package com.xuesi.service.impl;

import com.github.pagehelper.PageHelper;
import com.xuesi.mapper.SpeakerMapper;
import com.xuesi.pojo.Admin;
import com.xuesi.pojo.Result;
import com.xuesi.pojo.Speaker;
import com.xuesi.pojo.Video;
import com.xuesi.service.SpeakerService;
import lombok.experimental.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class SpeakerServiceImpl implements SpeakerService {
    @Autowired
    SpeakerMapper speakerMapper;

    @Override
    public Speaker findSpeakerById(String id) {

        Speaker speaker = speakerMapper.findSpeakerById(id);

        return speaker;
    }

    @Override
    public Result getAll(Integer stu, String timerange, Integer page, Integer limit) {
        Integer start = (page - 1) * limit;
        System.out.println("起始页：" + start);
        List<Speaker> list = null;
        String[] split = null;
        //分页
        if (timerange != null && timerange != "") {
            split = timerange.split("~");
            list = speakerMapper.selectSpeaker(stu, start, limit, split[0], split[1]);
        } else {
            list = speakerMapper.selectSpeaker(stu, start, limit, null, null);
        }
        Integer total = speakerMapper.getCount(stu);
        Result result = new Result();
        result.setTotal(total);
        result.setItem(list);
        return result;
    }

    @Override
    public Result getAllById(Integer stu, String timerange, Integer page, Integer limit, HttpServletRequest request) {
        Admin admin1 = (Admin) request.getSession().getAttribute("user");
        Admin admin = new Admin();
        admin.setId(admin1.getId());
        String adminid = admin.getId();
        //获取用户id
        Integer start = (page - 1) * limit;
        List<Speaker> list = null;
        String[] split = null;
        if (timerange != null && timerange != "") {
            split = timerange.split("~");
            list = speakerMapper.selectSpeakerById(adminid, stu, start, limit, split[0], split[1]);
        } else {
            list = speakerMapper.selectSpeakerById(adminid, stu, start, limit, null, null);
        }
        Integer total = speakerMapper.getCountById(adminid, stu);
        Result result = new Result();
        result.setTotal(total);
        result.setItem(list);
        return result;
    }

    @Override
    public Speaker selectById(String vid) {
        Speaker speaker = speakerMapper.selectByPrimaryKey(vid);
        return speaker;

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
                int res = speakerMapper.deleteByPrimaryKey(ids[i]);
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

    @Transactional
    @Override
    public Result addSpeaker(Speaker speaker, HttpServletRequest request) {
        // 视频的地址
        // 视频的描述
        // 视频的封面
        System.out.println("speaker从前端获取到的地址：" + speaker.getHeadImgUrl());
        speaker.setHeadImgUrl("img/" + speaker.getHeadImgUrl());
        //因为时添加操作  需要事务  设置手动回滚
        Result result = new Result();
        try {
            int i = speakerMapper.insert(speaker);
            if (i > 0) {
                result.setStatus(200);
            } else {
                result.setStatus(500);
                result.setMessage("插入失败!");
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result.setMessage("未知异常!");
            result.setStatus(500);
            return result;
        }
    }

    @Override
    public Result edit(String vid, Speaker speaker, HttpServletRequest request) {

        System.out.println("从前面获取的speakerId:" + speaker.getId());
        System.out.println("从前面获取的vid:" + vid);
        System.out.println("speaker从前端获取到的地址：" + speaker.getHeadImgUrl());
        Result result = new Result();
        String speakerName = speaker.getSpeakerName();
        String speakerDesc = speaker.getSpeakerDesc();
        String speakerJob = speaker.getSpeakerJob();

        String headImgUrl = "img/" + speaker.getHeadImgUrl();
        System.out.println("自己拼接的图片地址：" + headImgUrl);
        System.out.println("speakerName的值：" + speakerName);
        try {
            int i = speakerMapper.edit(vid, speakerName, speakerDesc, speakerJob, headImgUrl);
            if (i > 0) {
                result.setStatus(200);
            } else {
                result.setStatus(500);
                result.setMessage("修改失败!");
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result.setMessage("未知异常!");
            result.setStatus(500);
            return result;
        }

    }

    @Override
    public Result delSpeakerById(String id) {
        //可以通过session  获取到角色信息 判定他是否具有删除数据的权力
        Result result = new Result();
        try {
            int i = speakerMapper.deleteByPrimaryKey(id);
            if (i > 0) {
                result.setStatus(200);
            } else {
                result.setStatus(500);
                result.setMessage("删除失败!");
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result.setMessage("未知异常!");
            result.setStatus(500);
            return result;
        }

    }

    @Override
    public Result selectByLike(String vid, String speakerDesc) {
        //需要做逻辑判定
        Result result = new Result();
        List<Speaker> list = speakerMapper.selectByLike(vid, speakerDesc);
        result.setStatus(0);
        result.setMessage("success");
        result.setTotal(100);
        result.setItem(list);
        return result;
    }


}
