package com.xuesi.service.impl;

import com.github.pagehelper.PageHelper;
import com.xuesi.mapper.CourseMapper;
import com.xuesi.mapper.HomeMapper;
import com.xuesi.mapper.SpeakerMapper;
import com.xuesi.mapper.VideoMapper;
import com.xuesi.pojo.Admin;
import com.xuesi.pojo.Result;
import com.xuesi.pojo.Video;
import com.xuesi.pojo.VideoByVo;
import com.xuesi.service.VideoService;
import com.xuesi.utils.UpUtils;
import com.xuesi.utils.VideoUtil;
import it.sauronsoftware.jave.MultimediaInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    VideoMapper videoMapper;
    @Autowired
    HomeMapper homeMapper;
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    SpeakerMapper speakerMapper;

    //-------------------------前台-----------------------
    @Override
    public Video findVideoById(int id) {

        Video video = videoMapper.findVideoById(id);

        return video;
    }



    @Override
    public void addPlayNum(Video video) {
        videoMapper.addPlayNum(video);
    }

    //-------------------------后台------------------------
    @Override
    public Result getAll(Integer stu, String timerange, Integer page, Integer limit) {
        Integer start = (page - 1) * limit;
        List<Video> list = null;
        String[] split = null;
        if (timerange != null && timerange != "") {
            split = timerange.split("~");
            list = videoMapper.selectVideos(stu, start, limit, split[0], split[1]);
        } else {
            list = videoMapper.selectVideos(stu, start, limit, null, null);
        }
        for (int i = 0; i < list.size(); i++) {
            String nickName = speakerMapper.selectNickNameById(list.get(i).getSpeakerId());
            list.get(i).setSpeakerId(nickName);

        }
        Integer total = videoMapper.getCount(stu);
        Result result = new Result();
        result.setTotal(total);
        result.setItem(list);
        return result;
    }
    @Override
    public Result getAllById(Integer stu, String timerange, Integer page, Integer limit, HttpServletRequest request) {
        Admin admin1 = (Admin) request.getSession().getAttribute("user");
        Admin admin =new Admin();
        admin.setId(admin1.getId());
        String adminid = admin.getId();
        //获取用户id
        Integer start = (page - 1) * limit;
        List<Video> list = null;
        String[] split = null;
        if (timerange != null && timerange != "") {
            split = timerange.split("~");
            list = videoMapper.selectVideosById(adminid,stu, start, limit, split[0], split[1]);
        } else {
            list = videoMapper.selectVideosById(adminid,stu, start, limit, null, null);
        }
        for (int i = 0; i < list.size(); i++) {
            String list1 = list.get(i).getSpeakerId();
            String nickName = speakerMapper.selectNickNameById(list.get(i).getSpeakerId());
            System.out.println("进行根据iD获得到的视频讲师名称："+nickName);
            list.get(i).setSpeakerId(nickName);

        }
        Integer total = videoMapper.getCountById(adminid,stu);
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
                int res = videoMapper.deleteByPrimarykey(ids[i]);
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

  /*@Transactional
    @Override
    public Result updateById(String vid, String field, String value) {
        Result result = new Result();
        try {
            int i = videoMapper.updateById(vid, field, value);
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


    }*/

    @Override
    public Result selectByLike(String vid,String videoDesc) {
        //需要做逻辑判定
        Result result = new Result();
        List<Video> list = videoMapper.selectByLike(vid,videoDesc);
        result.setStatus(0);
        result.setMessage("success");
        result.setTotal(100);
        result.setItem(list);
        return result;
    }

    @Override
    public Result uploadVideo(MultipartFile file, HttpServletRequest request) {
        Result result =new Result();
        //此处需要将这个文件上传项目中
        try {
            UpUtils.upfile(file,request);
            result.setMessage(file.getOriginalFilename());
            result.setStatus(200);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setStatus(500);
            result.setMessage("网路异常!");
            return result;

        }

    }
    @Transactional
    @Override
    public Result addVideo(Video video,HttpServletRequest request) {
        // 视频的地址
        // 视频的描述
        // 视频的封面

        Admin user = (Admin)request.getSession().getAttribute("user");
        Date date = new Date();
        video.setSpeakerId(user.getId());
        video.setCreateTime(date);
        video.setStatus(1);

        MultimediaInfo info = VideoUtil.getInfo(video.getVideoPath(), request);
        System.out.println("video的VideoPath是："+video.getVideoPath());
        System.out.println("video实现类的info是啥："+info);
        Float f =  Float.valueOf(info.getDuration()/1000);
        video.setVideoSeconds(f);
        video.setVideoHeight(info.getVideo().getSize().getHeight());
        video.setVideoWidth(info.getVideo().getSize().getWidth());
        //"xx.mp4"
        //原来的video.setVideoPath("/WEB-INF/video/"+video.getVideoPath());
        //video.setCoverPath("/WEB-INF/video/"+video.getCoverPath());
        //video.setVideoPath(video.getVideoPath());
        video.setVideoPath(video.getVideoPath());
        System.out.println("保存到数据库中的视频地址名称:"+video.getVideoPath());
        video.setCoverPath("img/"+video.getCoverPath());
        //因为时添加操作  需要事务  设置手动回滚
        Result result =new Result();
        try {
            int i = videoMapper.insert(video);
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

    @Transactional
    @Override
    public Result delVideoById(int id) {
        //可以通过session  获取到角色信息 判定他是否具有删除数据的权力
        Result result = new Result();
        try {
            int i = videoMapper.deleteByPrimaryKey(id);
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
    public Video selectById(String vid) {
        Video video = videoMapper.selectByPrimaryKey(vid);
        return video;
    }
    @Transactional
    @Override
    public Result edit(String vid, String title, String videoDesc, String status) {
        Result result = new Result();

        System.out.println("videoDesc的值:"+videoDesc);
        System.out.println("edit方法中，vid的值："+vid);
        try {
            int i = videoMapper.edit(vid,title,videoDesc,status);
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
    public void updateLikeCounts(@Param("id1")int id1, @Param("likeCounts")int likeCounts) {
        System.out.println(likeCounts);
        videoMapper.updateLikeCounts(id1,likeCounts);
    }
    @Override
    public void addLikeCounts(@Param("id1")int id1, @Param("likeCounts")int likeCounts) {
        System.out.println(likeCounts);
        videoMapper.addLikeCounts(id1,likeCounts);
    }

    @Transactional
    @Override
    public Result upexcel(MultipartFile file) {
        //需要解析我们的excel表格的数据(POI技术)并且将数据添加到数据库中-->插入操作
        Result result = new Result();
        try {
            //创建一个workbook来解析我们的excel
            Workbook book = null;
            //需要判断上传的excel表格的版本,xlsx,xls
            if(file.getOriginalFilename().endsWith(".xlsx")){
                //新版本的excel
                book = new XSSFWorkbook(file.getInputStream());
            }else{
                //旧版本的excel
                book = new HSSFWorkbook(file.getInputStream());
            }
            //通过book获取我们的sheet
            Sheet sheet = book.getSheetAt(0);
            //先判断sheet是否有数据-->获取最后一行数据的下表(从0开始)
            //只要sheet的最后一行下表>=1 就表示有数据
            if(sheet.getLastRowNum()<1){
                result.setMessage("请在表格中填写数据!");
                result.setStatus(500);
                return result;
            }
            //当判断到有数据的情况-->需要获取它的每一行数据-->从每一行数据中获取到每一列的数据
            for (int i = 1; i <=sheet.getLastRowNum() ; i++) {
                //获取到每一次遍历的excel表格的sheet的行对象
                Row row = sheet.getRow(i);
                //获取每一行的每一列的数据
                String videoDesc = row.getCell(0).getStringCellValue();
                Double likecount = row.getCell(1).getNumericCellValue();
                String path = row.getCell(2).getStringCellValue();
                Double w = row.getCell(3).getNumericCellValue();
                Double h = row.getCell(4).getNumericCellValue();
                //将数据封装并且取数据库执行添加操作
                Video video = new Video();
                //给video添加id  必不可少
                //video.setId(UUID.randomUUID().toString());
                video.setStatus(0);
                //w.intValue() 将我们的Double类型转换成我们的Interger
                video.setVideoWidth(w.intValue());
                video.setVideoHeight(h.intValue());
                video.setVideoPath(path);
                video.setVideoDesc(videoDesc);
                video.setLikeCounts(likecount.intValue());
                videoMapper.insert(video);
            }
            result.setStatus(200);
            return  result;
        }catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result.setStatus(500);
            result.setMessage("上传失败!请核对数据!");
            return result;
        }
    }


}
