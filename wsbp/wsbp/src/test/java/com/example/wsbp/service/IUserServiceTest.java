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
        jdbcTemplate.update("DELETE FROM auth_user_fv WHERE user_name = ?", "b0000000");
    }

    @AfterEach
    public void tearDown() {
        // テストで記録されたデータを削除
        jdbcTemplate.update("DELETE FROM auth_user_fv WHERE user_name = ?", "b0000000");
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

        //コメントアウトする
        //fail();

    }

    @Test
    @RepeatedTest(10)
    @DisplayName("、例外がスローされる")
    public void Test02(){
        String userName = "b1111111";
        String password = "b1111111";

        assertThrows(RuntimeException.class,  () -> userService.registerUser(userName, password));

    }

}