package com.example.wsbp.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
        jdbcTemplate.update("delete from auth_user where user_name = ?", "b0000000");
        jdbcTemplate.update("update auth_user set user_name = ? where user_pass = ?", "b2222222", "b2222222");

    }

    @AfterEach
    public void tearDown() {
        jdbcTemplate.update("delete from auth_user where user_name = ?", "b0000000");
        jdbcTemplate.update("update auth_user set user_name = ? where user_pass = ?", "b2222222", "b2222222");
    }

    @Test
    @DisplayName("名前とパスワードを引数として送ると、記録された行数（1行）が戻り値として返ってくる")
    public void Test1() {
        String userName = "b0000000";
        String userPass = "b0000000";

        int expected = 1;
        int actual = userService.registerUser(userName, userPass);

        assertEquals(expected, actual);

//        fail();
    }

    @Test
    @DisplayName("、例外がスローされる")
    public void Test2() {
        String userName = "b1111111";
        String userPass = "b1111111";

        assertThrows(RuntimeException.class, () -> userService.registerUser(userName, userPass));
    }

    @Test
    @DisplayName("変更後の名前と変更前の名前を引数として送ると、記録された行数（1行）が戻り値として返ってくる")
    public void Test3() {
        String changeName = "b3333333";
        String userName = "b2222222";

        int expected = 1;
        int actual = userService.changeUser(changeName, userName);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("存在している名前と同じ変更後の名前を引数として送ると、例外がスローされる")
    public void Test4() {
        String changeName = "b1111111";
        String userName = "b2222222";

        assertThrows(RuntimeException.class, () -> userService.changeUser(changeName, userName));
    }

    @Test
    @DisplayName("存在しない名前を変更前の名前の引数として送ると、記録された行数（0行）が戻り値として返ってくる")
    public void Test5() {
        String changeName = "b3333333";
        String userName = "b3333333";

        int expected = 0;
        int actual = userService.changeUser(changeName, userName);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("存在している名前と正しいパスワードを引数として送ると、登録済み（true）が返ってくる")
    public void Test6() {
        String userName = "b1111111";
        String userPass = "b1111111";

        assertTrue(userService.existsUser(userName, userPass));
    }

    @Test
    @DisplayName("存在している名前と誤ったパスワードを引数として送ると、認証不可（false）が返ってくる")
    public void Test7() {
        String userName = "b1111111";
        String userPass = "b0000000";

        assertFalse(userService.existsUser(userName, userPass));
    }

    @Test
    @DisplayName("存在していない名前とパスワードを引数として送ると、未登録（false）が返ってくる")
    public void Test8() {
        String userName = "b0000000";
        String userPass = "b1111111";

        assertFalse(userService.existsUser(userName, userPass));
    }
}
