package com.example.wsbp.service;

import com.example.wsbp.data.Chat;

import java.util.List;

public interface IChatService {
    public void registerUser(String userName, String msgBody);

    public void removeUser(String msgBody);

    public boolean existsUser(String userName, String msgBody);

    public List<Chat> replyChat();
}
