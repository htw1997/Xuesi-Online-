package com.xuesi.pojo;

import com.xuesi.utils.StrTimeUtils;
import lombok.Data;

import java.util.Date;


@Data
public class Video {


    private int id;

    private String title;

    private String speakerId;

    private int courseId;

    private String videoPath;

    private String speakerName;

    private int playNum;

    private String coverPath;

    private Speaker speaker;//前端的

    private String audioId;

    private String videoDesc;

    private Float videoSeconds;

    private Integer videoWidth;

    private Integer videoHeight;

    private Integer status;

    private Date createTime;

    private String courseTitle;

    private int likeCounts;

    public String getShowTime() {
        return StrTimeUtils.intToStrTime(videoSeconds);
    }

    private static final long serialVersionUID = 1L;

}
