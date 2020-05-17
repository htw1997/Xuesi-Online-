package com.xuesi.pojo;

import lombok.Data;

import java.io.Serializable;

@Data
public class Reply implements Serializable {

    //回复信息编号
    private int lr_id;
    //回复人
    private String lr_name;
    //回复时间
    private String lr_date;
    //回复内容
    private String lr_content;
    //给谁回复
    private String lr_for_name;
    //哪条留下的回复言
    private String lr_for_words;
    //给哪条回复信息回复的
    private String lr_for_reply;
    //在哪个视频下的回复
    private String lr_for_video_id;


}
