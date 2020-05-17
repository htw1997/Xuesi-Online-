package com.xuesi.pojo;

import lombok.Data;

import java.util.List;

@Data
public class SubjectDto {
    private int id;
    private List<Course> courseList;
    private List<Video> videos;
    private List<Video> videoList;

}
