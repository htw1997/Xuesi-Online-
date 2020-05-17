package com.xuesi.service.impl;

import com.xuesi.mapper.PinglunMapper;
import com.xuesi.pojo.Reply;
import com.xuesi.pojo.Words;
import com.xuesi.service.PinglunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PinglunServiceImpl implements PinglunService {
    @Autowired
    PinglunMapper pinglunMapper;

    @Override
    public void saveWords(Words words) {
        pinglunMapper.saveWords(words);
    }

    @Override
    public List<Words> findByWords(int videoId) {
       return pinglunMapper.findByWords(videoId);
    }

    @Override
    public void saveReply(Reply reply) {
        pinglunMapper.saveReply(reply);
    }

    @Override
    public List<Reply> findByReply() {
        return pinglunMapper.findByReply();
    }
}
