package com.example.wsbp.repository;

import com.example.wsbp.data.AuthUser;
import com.example.wsbp.data.Chat;

import java.util.List;

public interface IChatRepository {
    int insert(String userName, String msgBody);

    int delete(String userName);

    boolean exists(String userName, String msgBody);

    List<Chat> reply();
}
