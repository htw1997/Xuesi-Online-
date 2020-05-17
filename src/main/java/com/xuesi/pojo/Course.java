package com.xuesi.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Course {
    private int id;
    private String courseTitle;
    private String courseDesc;
    private int subjectId;
    private String subjectName;
    private List<Video> videoList;


}
