package com.example.wsbp.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class IUserServiceTest {

    @Autowired
    IUserService userService;

    @Test
    @DisplayName("名前とパスワードを引数として送ると、記録された行数（1行）が戻り値として返ってくる")
    public void TestBlack(){
        String userName = "b0000000";
        String password = "b0000000";

        int expected = 1;

        int actual = userService.registerUser(userName, password);

        assertEquals(expected, actual);
    }


    @Test
    @DisplayName("userNameとuserPassを入力値として送ると、正常に記録され記録行数（1行）が返ってくる")
    public void TestWhite01(){
        String userName = "b1111111";
        String password = "b1111111";

        int expected = 1;

        int actual = userService.registerUser(userName, password);

        assertEquals(expected, actual);
    }


    @Test
    @DisplayName("userNameの値が空白またはnullのとき、例外がスローされる")
    public void TestWhite02(){
        String userName = null;
        String password = "b2222222";

        assertThrows(DataAccessException.class, () -> userService.registerUser(userName, password));
    }


    @Test
    @DisplayName("userPassの値が空白またはnullのとき、例外がスローされる")
    public void TestWhite03(){
        String userName = "b3333333";
        String password = null;

        assertThrows(DataAccessException.class, () -> userService.registerUser(userName, password));
    }


    @Test
    @DisplayName("既に登録されているuserNameを入力値として送ると、例外がスローされる")
    public void TestWhite04(){
        String userName = "b1111111";
        String password = "b1111111";

        assertThrows(DataAccessException.class, () -> userService.registerUser(userName, password));
    }




}