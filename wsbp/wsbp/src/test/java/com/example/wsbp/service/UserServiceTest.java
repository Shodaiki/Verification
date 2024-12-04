package com.example.wsbp.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class UserServiceTest {

    @Autowired
    IUserService userService;

    @Test
    @DisplayName("名前とパスワードを引数として送ると、記録された行数（1行）が戻り値として返ってくる")
    public void Test01(){
        String userName = "b0000000";
        String password = "b0000000";

        int expected = 1;

        int actual = userService.registerUser(userName, password);

        assertEquals(expected, actual);
    }


    @Test
    @DisplayName("名前とパスワードを引数として送り、存在すればtrueが返ってくる")
    public void Test02(){
        String userName = "b0000000";
        String password = "b0000000";

        var actual = userService.existsUser(userName, password);

        assertTrue(actual);
    }

}
