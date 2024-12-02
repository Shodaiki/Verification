package com.example.wsbp.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class UserServiceTest {

    @Autowired
    IUserService userService;

    @Test
    @DisplayName("自分の学籍番号とパスワードを入力値として送ると、記録された行数（1行）が戻り値として返ってくる")
    public void Test(){
        String userName = "b0000000";
        String password = "b0000000";

        int expected = 1;

        int actual = userService.registerUser(userName, password);

        assertEquals(expected, actual);
    }

}
