package com.xuesi.service;

import com.xuesi.pojo.Reply;
import com.xuesi.pojo.Words;

import java.util.List;

public interface PinglunService {
    void saveWords(Words words);


    List<Words> findByWords(int videoId);

    void saveReply(Reply reply);

    List<Reply> findByReply();

}
