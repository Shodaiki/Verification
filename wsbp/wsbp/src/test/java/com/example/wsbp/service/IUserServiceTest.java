package com.example.wsbp.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Signed;

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
        jdbcTemplate.update("UPDATE auth_user SET user_name = ? WHERE user_name = ? ","b2222222","b2222220" );
    }

    @AfterEach
    public void tearDown() {
        // テストで記録されたデータを削除
        jdbcTemplate.update("DELETE FROM auth_user WHERE user_name = ?", "b0000000");
    }

    @Test
    @DisplayName("名前とパスワードを引数として送ると、記録された行数（1行）が戻り値として返ってくる")
    public void Test01() {
        String userName = "b0000000";
        String password = "b0000000";

        int expected = 1;

        int actual = userService.registerUser(userName, password);

        assertEquals(expected, actual);

        //fail();

    }

    @Test
    @DisplayName("、例外がスローされる")
    public void Test02() {
        String userName = "b1111111";
        String password = "b1111111";

        assertThrows(RuntimeException.class, () -> userService.registerUser(userName, password));
    }

    @Test
    @DisplayName("変更したい名前を送ると、記録された行数（1行）が戻り値として返ってくる")
    public void Test03() {
        String changeName = "b2222220";
        String userName = "b2222222";

        int expected = 1;

        int actual = userService.changeUser(changeName, userName);

        assertEquals(expected, actual);

    }

    @Test
    @DisplayName("、例外がスローされる")
    public void Test04() {
        String changeName = "b1111111";
        String userName = "b2222222";

        assertThrows(RuntimeException.class, () -> userService.changeUser(changeName, userName));
    }

    @Test
    @DisplayName("ログインする")
    public void Test05() {
        String userName = "b2222222";
        String userPass = "b2222222";

        boolean actual = userService.existsUser(userName, userPass);

        assertTrue(actual);
    }
}