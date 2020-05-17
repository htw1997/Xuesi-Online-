package com.xuesi.pojo;

import com.xuesi.utils.StrTimeUtils;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Shoucang {
    private int id;
    private int userId;
    private int videoid;
    private String coverPath;
    private int playNum;
    private String title;
    //视频标题
    private Float videoSeconds;



    private static final long serialVersionUID = 1L;
}
