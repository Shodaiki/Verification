package com.example.wsbp.service;

import com.example.wsbp.data.Chat;

import java.sql.Timestamp;
import java.util.List;

public interface IChatService {
    public void registerUser(String userName, String msgBody, Timestamp chatTime);

    public void removeUser(String msgBody);

    public boolean existsUser(String userName, String msgBody);

    public Timestamp makePostHMS();

    public List<Chat> replyChat();

    public List<Chat> selectreplyChat(String userName);
}
