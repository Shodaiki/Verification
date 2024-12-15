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
        // テストで記録されたデータを削除auth_user_fv
        jdbcTemplate.update("DELETE FROM auth_user_fv WHERE user_name = ?", "b0000000");
        jdbcTemplate.update("UPDATE auth_user_fv SET user_name = ?, user_pass = ? WHERE user_name = ?", "b2222222", "b2222222", "b3333333");
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

    @Test
    @RepeatedTest(10)
    @DisplayName("新しい名前と古い名前を引数として送ると、更新された行数（1行）が戻り値として返ってくる")
    public void Test03(){
        String newUserName = "b3333333";
        String currentUserName = "b2222222";

        int expected = 1;

        int actual = userService.changeUser(newUserName, currentUserName);

        assertEquals(expected, actual);
    }

    @Test
    @RepeatedTest(10)
    @DisplayName("ログインしているユーザの現在の名前以外で、すでに登録されている他のユーザの名前を新しい名前とし引数として送ると例外をスローされる")
    public void Test04(){
        String newUserName = "b1111111";
        String currentUserName = "b2222222";

        assertThrows(RuntimeException.class,  () -> userService.changeUser(newUserName, currentUserName));
    }

    @Test
    @RepeatedTest(10)
    @DisplayName("正しいユーザ名とパスワードを引数として送ると、戻り値としてtrueが返ってくる")
    public void Test05(){
        String userName = "b1111111";
        String userPass = "b1111111";

        boolean expected = true;

        boolean actual = userService.existsUser(userName, userPass);

        assertTrue(expected);
    }

    @Test
    @RepeatedTest(10)
    @DisplayName("入力したユーザ名とパスワードが正しくないとき、戻り値としてfalseが返ってくる")
    public void Test06(){
        String userName = "b9999999";
        String userPass = "b9999999";

        boolean expected = false;

        boolean actual = userService.existsUser(userName, userPass);

        assertFalse(expected);
    }

    @Test
    @RepeatedTest(10)
    @DisplayName("入力したユーザ名が正しくないとき、戻り値としてfalseが返ってくる")
    public void Test07(){
        String userName = "b9999999";
        String userPass = "b1111111";

        boolean expected = false;

        boolean actual = userService.existsUser(userName, userPass);

        assertFalse(expected);
    }

    @Test
    @RepeatedTest(10)
    @DisplayName("入力したパスワードが正しくないとき、戻り値としてfalseが返ってくる")
    public void Test08(){
        String userName = "b1111111";
        String userPass = "b9999999";

        boolean expected = false;

        boolean actual = userService.existsUser(userName, userPass);

        assertFalse(expected);
    }

}