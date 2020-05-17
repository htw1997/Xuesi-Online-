package com.xuesi.controller;

import com.xuesi.pojo.*;
import com.xuesi.service.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shoucang")
@Api(tags = "该类实现所有关于收藏的功能")
public class ShoucangController {

    @Autowired
    UserService userService;
    @Autowired
    ShoucangService shoucangService;
    @Autowired
    VideoService videoService;
    @Autowired
    SpeakerService speakerService;
    @Autowired
    CourseService courseService;

    /**
     * 添加课程收藏
     */
    @RequestMapping("/addShoucang/{id}")
    @ResponseBody
    public Map<String, Object> addShoucang(HttpSession session, HttpServletRequest request, @ModelAttribute("Shoucang") Shoucang shoucang, @PathVariable("id") int id) {

        Map<String, Object> json = new HashMap<String, Object>();
        Video video = (Video) session.getAttribute("video2");
        String email = (String) session.getAttribute("userAccount");
        System.out.println("我到这里了" + email);
        User user = userService.getUserByEmail(email);
        System.out.println("我到这里了2");
        int userId = user.getId();
        System.out.println("userID的值：" + userId);
        System.out.println("获取到的视频ID：" + id);

        boolean is = shoucangService.selectShoucang(userId, id);

        if (is) {
            json.put("success", false);
            json.put("message", "该课程你已经收藏过了");
            return json;
        }
        shoucang.setTitle(video.getTitle());
        shoucang.setVideoid(video.getId());
        shoucang.setUserId(userId);
        shoucang.setCoverPath(video.getCoverPath());
        shoucang.setVideoSeconds(video.getVideoSeconds());
        shoucang.setPlayNum(video.getPlayNum());
        shoucangService.addShoucang(shoucang);
        //添加收藏人数
        int id1 =video.getId();
        System.out.println("video的东西"+video);
        System.out.println("获取到的id："+id1);
        int likeCounts = video.getLikeCounts()+1;
        System.out.println("获得到的likeCounts:"+likeCounts);
        videoService.addLikeCounts(id1,likeCounts);
        json.put("success", true);
        json.put("message", "收藏成功");

        return json;

    }


    /**
     * 删除课程收藏
     */
    @RequestMapping("/deleteShoucang/{id}")
    @ResponseBody
    /**
     * 根据前端传回来的收藏视频ID
     */
    public Map<String, Object> deleteShoucang(HttpSession session,HttpServletRequest request,  @PathVariable("id") int id) {
        Map<String, Object> json = new HashMap<String, Object>();
        System.out.println("获取到的收藏id:"+id);
        shoucangService.deleteShoucang(id);
       //删除收藏人数
        Video video = (Video) session.getAttribute("video2");
        int id1 =video.getId();
        int likeCounts = video.getLikeCounts()-1;
        videoService.updateLikeCounts(id1,likeCounts);

        json.put("success",true);
        json.put("message","删除收藏成功");
        return json;
    }


    /**
     * 展示收藏的视频
     *
     * @return
     */
    @RequestMapping("/showFavourite")
    public String showFavourite(HttpSession session, Model model) {

        String email = (String) session.getAttribute("userAccount");
        User user = userService.getUserByEmail(email);
        int userId = user.getId();
        List<Shoucang> shoucangList = shoucangService.showFavourite(userId);
        session.setAttribute("shoucangList", shoucangList);
        return "before/shoucang";

    }
    /**
     * 修改收藏人数
     *
     * @param videoId
     * @param playNum
     */
    @RequestMapping("/updateLikeCounts")
    @ResponseBody
    public void updateLikeCounts(Integer videoId, Integer playNum) {
        Video video = new Video();
        video.setId(videoId);
        video.setPlayNum(playNum + 1);
        System.out.println(video);
        videoService.addPlayNum(video);
    }
}
