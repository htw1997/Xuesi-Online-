package com.xuesi.pojo;



import lombok.Data;

import java.util.List;
@Data
public class VideoByVo {
    private int page = 1;
    private int rows = 5;
    private int begin = 0;
    private int speakerId;
    private int courseId;
    private String title;
    private List<Integer> ids;

    public List<Integer> getIds() {
        return ids;
    }

    public void setIds(List<Integer> ids) {
        this.ids = ids;
    }

    public int getSpearkerId() {
        return speakerId;
    }

    public void setSpearkerId(int spearkerId) {
        this.speakerId = spearkerId;
    }
    @Override
    public String toString() {
        return "VideoByVo{" +
                "page=" + page +
                ", rows=" + rows +
                ", begin=" + begin +
                ", speakerId=" + speakerId +
                ", courseId=" + courseId +
                ", title='" + title + '\'' +
                ", ids=" + ids +
                '}';
    }


}
