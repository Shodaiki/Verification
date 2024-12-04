package com.example.wsbp.service;

import com.example.wsbp.data.Chat;

import java.sql.Timestamp;
import java.util.List;

public interface IChatService {
    public void registerChat(String userName, String msgBody, Timestamp chatTime);

    public Timestamp makePostHMS();

    public List<Chat> replyChat();

    public List<Chat> selectreplyChat(String userName);
}
