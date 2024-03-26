package com.example.wsbp.page;

import com.example.wsbp.MySession;
import com.example.wsbp.WsbpApplication;
import com.example.wsbp.page.signed.SignedPage;
import com.example.wsbp.repository.AuthUserRepository;
import com.example.wsbp.service.IUserService;
import com.example.wsbp.service.UserService;
import com.giffing.wicket.spring.boot.starter.configuration.extensions.core.settings.general.GeneralSettingsProperties;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SignPageTest {

    @SpringBean
    IUserService userService;

    AuthUserRepository authUserRepository;

    @BeforeEach
    public void setUp(){

        this.userService = new UserService(authUserRepository);

    }

    @Test
    @DisplayName("SignPageのテスト")
    public void SignTest(){

        var userName = "b2211280";
        var userPass = "b2211280";

        if (userService.existsUser(userName, userPass)) {
            MySession.get().sign(userName);
        }

        var name = MySession.get().getUserName();

    }
}
