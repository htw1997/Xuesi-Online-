package com.xuesi.service;

import com.xuesi.pojo.Subject;
import com.xuesi.pojo.SubjectDto;

import java.util.List;
import java.util.Map;

public interface SubjectService {
    List<Subject> subjectAll();


    Subject subjectById(int subjectId);

    Map findSubjectTypeList();
    SubjectDto subjectBYId(int subjectId);


}
