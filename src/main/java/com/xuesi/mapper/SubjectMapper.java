package com.xuesi.mapper;


import com.xuesi.pojo.Subject;

import java.util.List;
import java.util.Map;

public interface SubjectMapper {
    List<Subject> subjectAll();
   //不能
    Subject subjectById(int subjectId);

    List<Map> findSubjectTypeList();


    Subject subjectBYId(int subjectId);
}
