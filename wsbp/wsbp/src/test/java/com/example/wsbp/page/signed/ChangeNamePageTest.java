package com.example.wsbp.page.signed;

import com.example.wsbp.MySession;
import com.example.wsbp.repository.AuthUserRepository;
import com.example.wsbp.service.UserService;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Url;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.nio.charset.Charset;
import java.util.Locale;


public class ChangeNamePageTest {

    @Mock
    AuthUserRepository authUserRepository;

    @InjectMocks
    UserService userService;

    @BeforeEach
    public void setUp(){

        MockitoAnnotations.openMocks(this);

    }

    @Test
    @DisplayName("新しくユーザ名を入力すると、変更される")
    public void changeFormTest() {
        var changeName = "b2221280";
        var userName = "b2211280";
        userService.changeUser(changeName, userName);

    }

}
