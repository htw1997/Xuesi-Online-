package com.xuesi.controller;


import com.github.pagehelper.PageInfo;
import com.xuesi.pojo.*;
import com.xuesi.service.*;
import com.xuesi.utils.DownUtil;
import com.xuesi.utils.InfoUtils;
import com.xuesi.utils.UUIDUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/video")
@Api(value = "视频管理功能", tags = "视频管理功能")
public class VideoController {
    @Autowired
    VideoService videoService;
    @Autowired
    SpeakerService speakerService;
    @Autowired
    private CourseService courseService;
    @Autowired
    ShoucangService shoucangService;
    @Autowired
    UserService userService;
    @Autowired
    PinglunService pinglunService;
//----------------------------前台--------------------------------

    /**
     * 展示视频详情页
     *
     * @param videoId
     * @param subjectName
     * @param model
     * @return
     */
    private List<Words> lw_list;
    private List<Reply> lr_list;
    @RequestMapping("/showVideo")
    public String showVideo(@RequestParam int videoId, String subjectName, Model model, HttpServletRequest request, HttpSession session) {
        model.addAttribute("subjectName", subjectName);
        System.out.println("videoId的值是：" + videoId);
        Video video = videoService.findVideoById(videoId);

        Speaker speaker = speakerService.findSpeakerById(((Video) video).getSpeakerId());
        video.setSpeaker(speaker);
        model.addAttribute("video", video);
        //将视频信息放入session，便于获取。
        session.setAttribute("video2", video);
        Course course = courseService.fingCourseById(video.getCourseId());
        model.addAttribute("course", course);
        //查询用户是否已收藏
        String email = (String) session.getAttribute("userAccount");
        User user = userService.getUserByEmail(email);
        int userId = user.getId();
        System.out.println("userID的值：" + userId);
        boolean isFavorites = shoucangService.selectShoucang(userId, videoId);
        model.addAttribute("isFavorites", isFavorites);
        //查看收藏的具体内容
        Shoucang shoucang = shoucangService.selectShoucang2(userId,videoId);
        model.addAttribute("shoucang",shoucang);
        //封装Words的评论信息
        lw_list = pinglunService.findByWords(videoId);
        model.addAttribute("lw_list",lw_list);
        //封装回复信息
        lr_list = pinglunService.findByReply();
        model.addAttribute("lr_list",lr_list);

        return "before/section";
    }

    /**
     * 上传图片文件
     *
     * @param imgfile
     * @return
     */
    @RequestMapping("/fileUpload")
    @ResponseBody
    public String fileUpload(MultipartFile imgfile) {
        System.out.println(imgfile.getOriginalFilename());
        String filename = imgfile.getOriginalFilename();
        String suffix = filename.substring(filename.lastIndexOf("."));
        String newFilename = UUIDUtils.getUUID() + suffix;
        try {
            imgfile.transferTo(new File(InfoUtils.getProperties("UPLOAD_LOC"), newFilename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String imgUrl = InfoUtils.getProperties("IMG_PATH") + newFilename;
        System.out.println(imgUrl);
        return imgUrl;
    }


    /**
     * 修改播放次数
     *
     * @param videoId
     * @param playNum
     */
    @RequestMapping("/updatePalyNum")
    @ResponseBody
    public void updatePalyNum(Integer videoId, Integer playNum) {
        Video video = new Video();
        video.setId(videoId);
        video.setPlayNum(playNum + 1);
        System.out.println(video);
        videoService.addPlayNum(video);
    }

    //-----------------------------后台----------------------------


    /**
     * 获取全部视频
     *
     * @param stu
     * @param timerange
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/getAll")
    @ResponseBody
    public Result getAll(Integer stu, String timerange, Integer page, Integer limit, HttpServletRequest request) {
        Admin admin1 = (Admin) request.getSession().getAttribute("user");
        Admin admin2 = new Admin();
        admin2.setrId(admin1.getrId());
        String admin = "admin";
        //赋值，管理员的字段
        System.out.println("测试从前端获得的rId：" + admin1.getrId());
        admin2.setUsername(admin1.getUsername());
        System.out.println("测试从前端获得的Username：" + admin1.getUsername());
        admin2.setId(admin1.getId());
        //判断获得的rid是否等于管理员
        if (admin.equals(admin2.getrId())) {
            Result result = videoService.getAll(stu, timerange, page, limit);
            return result;
        } else {
            Result result = videoService.getAllById(stu, timerange, page, limit, request);
            return result;
        }


    }


    /**
     * 删除视频
     *
     * @param ids
     * @return
     */
    @RequestMapping("/del")
    @ResponseBody
    public Result delById(String[] ids) {
        Result result = videoService.delById(ids);
        return result;
    }

    /**
     * 修改视频的描述信息
     *
     * @param vid
     * @param field
     * @param value
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public Result updateById(String vid, String field, String value) {
        //  Result result = videoService.updateById(vid, field, value);
        return null;
    }

    /**
     * 按照需求进行模糊查询（根据id和简介）
     *
     * @param vid
     * @param videoDesc
     * @return
     */
    @RequestMapping("/selectByLike")
    @ResponseBody
    public Result selectByLike(String vid, String videoDesc) {

        return videoService.selectByLike(vid, videoDesc);
    }

    /**
     * 上传视频
     *
     * @param file
     * @param request
     * @return
     */
    @RequestMapping("/uploadvideo")
    @ResponseBody
    public Result upload(MultipartFile file, HttpServletRequest request) {
        Result result = videoService.uploadVideo(file, request);
        return result;
    }

    /**
     * 上传视频封面
     *
     * @param file
     * @param request
     * @return
     */
    @RequestMapping("/uploadimg")
    @ResponseBody
    public Result uploadimg(MultipartFile file, HttpServletRequest request) {
        Result result = videoService.uploadVideo(file, request);
        return result;
    }

    /**
     * 添加视频
     *
     * @param video
     * @param request
     * @return
     */
    @RequestMapping("/addvideo")
    @ResponseBody
    public Result addvideo(Video video, HttpServletRequest request) {
        Result result = videoService.addVideo(video, request);
        System.out.println("controller:" + result);
        System.out.println("难受的地址" + video.getVideoPath());
        return result;
    }

    /**
     * 根据id进行删除
     *
     * @param id
     * @return
     */
    @RequestMapping("/delById")
    @ResponseBody
    public Result delVideoById(int id) {
        Result result = videoService.delVideoById(id);
        return result;
    }

    /**
     * 根据用户id查询,并带着数据跳转到页面
     *
     * @param vid
     * @param model
     * @return
     */
    @RequestMapping("/getEdit")
    public String getEdit(String vid, Model model) {

        //通过id取找当前行的数据,并且返回给前端  session域     request域
        Video video = videoService.selectById(vid);
        model.addAttribute("video", video);
        return "behind/editVideo";
    }

    /**
     * 修改视频描述（可以后续修改视频标题）
     *
     * @param vid
     * @param videoDesc
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Result edit(String vid, String title, String videoDesc, String status) {
        Result result = videoService.edit(vid, title, videoDesc, status);
        return result;
    }

    @RequestMapping("/down")
    public void down(HttpServletRequest request, HttpServletResponse response, String filename) {
        DownUtil.down(request, response, filename);
    }

    /**
     * 下载excel表格
     *
     * @param file
     * @return
     */
    @RequestMapping("/upexcel")
    @ResponseBody
    public Result upexcel(MultipartFile file) {
        Result result = videoService.upexcel(file);
        return result;
    }

//-----------------------后台页面跳转--------------------------

    /**
     * 跳转到后台video页面
     *
     * @return
     */
    @RequestMapping("/video")
    public String video() {
        return "/behind/video";
    }

    /**
     * 跳转到后台添加视频页面
     *
     * @return
     */
    @RequestMapping("/addVideo")
    public String addVideo() {
        return "/behind/addvideo";
    }

    /**
     * 跳转到后台添加视频页面
     *
     * @return
     */
    @RequestMapping("/editVideo")
    public String editVideo() {
        return "/behind/editVideo";
    }


}
