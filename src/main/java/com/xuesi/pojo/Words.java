package com.xuesi.pojo;

import lombok.Data;

@Data
public class Words {

    //编号
    private int lw_id;
    //留言人
    private String lw_name;
    //留言时间
    private String lw_date;
    //留言内容
    private String lw_content;
    //给谁留言
    private String lw_for_name;
    //在哪个视频留言(id)
    private String lw_for_video_id;

}
