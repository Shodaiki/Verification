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
        jdbcTemplate.update("DELETE FROM auth_user WHERE user_name = ?", "b0000000");
    }

    @AfterEach
    public void tearDown() {
        jdbcTemplate.update(
                "DELETE FROM auth_user WHERE user_name = ?", "b0000000");
        jdbcTemplate.update(
                "UPDATE auth_user SET user_name = ? WHERE user_name = ?", "b2222222", "b2221111");
    }

    @Test
    @DisplayName("名前とパスワードを引数として送ると、記録された行数（1行）が戻り値として帰ってくる")
    public void Test01() {
        String userName = "b0000000";
        String password = "b0000000";

        int expected = 1;

        int actual = userService.registerUser(userName, password);

        assertEquals(expected, actual);

        // fail();

    }

    @Test
    @DisplayName("、例外がスローされる")
    public void Test02() {
        String userName = "b1111111";
        String password = "b1111111";

        assertThrows(RuntimeException.class, () -> userService.registerUser(userName, password));
    }

    @Test
    @DisplayName("今の名前と新しい名前を引数として送ると、変更された行数が返ってくる。")
    public void Test03() {
        String oldUserName = "b2222222";
        String newUserName = "b2221111";

        int expected = 1;

        int actual = userService.changeUser(newUserName, oldUserName);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("登録されている名前を新しい名前として送ると、例外がスローされる")
    public void Test04() {
        String oldUserName = "b1111111";
        String newUserName = "b2222222";

        assertThrows(RuntimeException.class, () -> userService.changeUser(newUserName, oldUserName));
    }

    @Test
    @DisplayName("登録されていない名前を今の名前として送ると、なにも変更されず0が返ってくる")
    public void Test05() {
        String oldUserName = "b2324523";
        String newUserName = "b2353252";

        int expected = 0;

        int actual = userService.changeUser(newUserName, oldUserName);

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("正しいユーザ名とパスワードを送るとtrueが返ってくる")
    public void Test06() {
        String userName = "b2222222";
        String password = "b2222222";

        assertTrue(userService.existsUser(userName, password));
    }

    @Test
    @DisplayName("間違ったパスワードを送るとfalseが返ってくる")
    public void Test07() {
        String userName = "b2222222";
        String password = "b2222221";

        assertFalse(userService.existsUser(userName, password));
    }

    @Test
    @DisplayName("登録されていないユーザ名を送るとfalseが返ってくる")
    public void Test08() {
        String userName = "b2222221";
        String password = "b2222222";

        assertFalse(userService.existsUser(userName, password));
    }
}
