package com.xuesi.pojo;
import lombok.Data;

import java.util.List;
@Data
public class Speaker {
    private int id;
    private String speakerName;
    private String speakerDesc;
    private String speakerJob;
    private String headImgUrl;
    private List<Video> videos;

    public List<Video> getVideos() {
        return videos;
    }

    @Override
    public String toString() {
        return "Speaker{" +
                "id=" + id +
                ", speakerName='" + speakerName + '\'' +
                ", speakerDesc='" + speakerDesc + '\'' +
                ", speakerJob='" + speakerJob + '\'' +
                ", headImgUrl='" + headImgUrl + '\'' +
                ", videos=" + videos +
                '}';
    }


}
