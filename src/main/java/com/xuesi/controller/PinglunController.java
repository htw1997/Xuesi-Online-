package com.xuesi.controller;

import com.xuesi.pojo.Reply;
import com.xuesi.pojo.Video;
import com.xuesi.pojo.Words;
import com.xuesi.service.PinglunService;
import com.xuesi.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/pinglun")
public class PinglunController {
    @Autowired
    PinglunService pinglunService;
    @Autowired
    VideoService videoService;

    /**
     * 前台保存评论信息
     *
     * @param words
     * @return
     */
    @RequestMapping("/saveWords")
    public String saveWords(Words words) {
        if (words != null) {
            String videoId = words.getLw_for_video_id();
            System.out.println("videoID:" + videoId);
            pinglunService.saveWords(words);
            return "redirect:/video/showVideo?videoId=" + videoId;
            //跳到展示视频的方法里面了  这个没问题
        } else {
            return null;
        }
    }

    /**
     * 前台保存回复信息
     */
    @RequestMapping(value = "/saveReply")
    public String saveReply(Reply reply) {
        if (reply != null) {
            pinglunService.saveReply(reply);
            String videoId = reply.getLr_for_video_id();
            System.out.println("videoID:" + videoId);
            return "redirect:/video/showVideo?videoId=" + videoId;
        } else {
            return null;
        }
    }

    /**
     * 跳转到查看文章内容页面
     */
    //声明用于存放留言回复信息的List集合
    private List<Words> lw_list1;
    private List<Reply> lr_list1;

    @RequestMapping(value = "/toPinglunView")
    public String toArticleView(@RequestParam int vid, Model model) {
        //封装留言信息
        lw_list1 = pinglunService.findByWords(vid);
        model.addAttribute("lw_list1", lw_list1);

        //封装回复信息
        lr_list1 = pinglunService.findByReply();
        model.addAttribute("lr_list1", lr_list1);
        //查询视频信息
        Video video = videoService.findVideoById(vid);
        System.out.println("查询到当前视频的ID值：" + video.getId());
        if (video != null) {
            model.addAttribute("video", video);
            return "behind/pinglun";
        } else {
            return null;
        }
    }
    /**
     * 后台保存评论信息
     *
     * @param words
     * @return
     */
    @RequestMapping("/saveWords1")
    public String saveWords1(Words words) {
        if (words != null) {
            String vid = words.getLw_for_video_id();
            System.out.println("videoID:" + vid);
            pinglunService.saveWords(words);
            return "forward:/pinglun/toPinglunView?vid=" + vid;
            //跳到展示视频的方法里面了  这个没问题
        } else {
            return null;
        }
    }
    /**
     * 前台保存回复信息
     */
    @RequestMapping(value = "/saveReply1")
    public String saveReply2(Reply reply) {
        if (reply != null) {
            pinglunService.saveReply(reply);
            String vid = reply.getLr_for_video_id();
            System.out.println("videoID:" + vid);
            return "forward:/pinglun/toPinglunView?vid=" + vid;
        } else {
            return null;
        }
    }
}
