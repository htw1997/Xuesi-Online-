package com.xuesi.pojo;

import lombok.Data;

import java.util.List;
@Data
public class Subject {
    private int id;
    private String subjectName;
    private List<Course> courseList;

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", subjectName='" + subjectName + '\'' +
                ", courseList=" + courseList +
                '}';
    }


}
