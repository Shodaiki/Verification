package com.example.wsbp.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class IUserServiceTest {

    @Autowired
    IUserService userService;


    @Autowired
    JdbcTemplate jdbcTemplate;


    @BeforeEach
    public void setUp() {
        // 必要に応じて、テスト用のデータを準備
        jdbcTemplate.update("DELETE FROM auth_user WHERE user_name = ?", "b0000000");
        jdbcTemplate.update("UPDATE auth_user SET user_name = ? WHERE user_name = ?", "b2222222", "b2222222");
    }

    @AfterEach
    public void tearDown() {
        // テストで挿入されたデータを削除・更新
        jdbcTemplate.update("DELETE FROM auth_user WHERE user_name = ?", "b0000000");
        jdbcTemplate.update("UPDATE auth_user SET user_name = ? WHERE user_name = ?", "b2222222", "b3333333");

    }

    @Test
    @RepeatedTest(10)
    @DisplayName("名前とパスワードを引数として送ると、記録された行数（1行）が戻り値として返ってくる")
    public void Test01(){
        String userName = "b0000000";
        String password = "b0000000";

        int expected = 1;

        int actual = userService.registerUser(userName, password);

        assertEquals(expected, actual);

//        fail();
    }


    @Test
    @RepeatedTest(10)
    @DisplayName("登録されている名前とパスワードを引数として送ると、例外がスローされる")
    public void Test02(){
        String userName = "b1111111";
        String password = "b1111111";

        assertThrows(RuntimeException.class,  () -> userService.registerUser(userName, password));

    }


    @Test
    @DisplayName("名前とログインしているユーザ名を引数として送ると、更新された行数が返ってくる")
    public void Test03() {
        String userName = "b2222222";
        String changeName = "b3333333";

        int expected = 1;

        int actual = userService.changeUser(changeName, userName);

        assertEquals(expected, actual);
    }


    @Test
    @DisplayName("不正な名前とログインしているユーザ名を引数として送ると、例外が発生する")
    public void Test04() {
        String userName = "b2222222";
        String changeName = null;

        assertThrows(RuntimeException.class, () -> userService.changeUser(changeName, userName));
    }


    @Test
    @DisplayName("存在する名前とパスワードを引数として送ると、trueが返ってくる")
    public void Test05() {
        String userName = "b1111111";
        String userpass = "b1111111";

        assertTrue(userService.existsUser(userName, userpass));
    }


    @Test
    @DisplayName("存在しない名前とパスワードを引数として送ると、falseっが返ってくる")
    public void Test06() {
        String userName = "b0000000";
        String userpass = "b0000000";

        assertFalse(userService.existsUser(userName, userpass));
    }

}