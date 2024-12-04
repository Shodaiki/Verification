package com.example.wsbp.service;

import com.example.wsbp.data.AuthUser;
import com.example.wsbp.data.Chat;
import com.example.wsbp.repository.IChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;

import java.util.List;

@Service
public class ChatService implements IChatService{

    private IChatRepository chatRepos;

    @Autowired
    public ChatService(IChatRepository chatRepos) {
        this.chatRepos = chatRepos;
    }

    @Override
    public void registerChat(String userName, String msgBody, Timestamp chatTime) {
        int n = chatRepos.insert(userName, msgBody, chatTime);
        System.out.println("記録行数：" + n);
    }

    @Override
    public Timestamp makePostHMS(){
        long millis = System.currentTimeMillis();

        Timestamp timestamp = new Timestamp(millis);
        return timestamp;
    }


    @Override
    public List<Chat> replyChat() {
        var users = chatRepos.reply();
        System.out.println("データ件数：" + users.size());
        return users;
    }

    @Override
    public List<Chat> selectreplyChat(String userName) {
        var selectusers = chatRepos.selectreply(userName);
        System.out.println("データ件数：" + selectusers.size());
        return selectusers;
    }
}
