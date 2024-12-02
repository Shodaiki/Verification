package com.example.wsbp.page.signed;

import com.example.wsbp.MySession;
import com.example.wsbp.page.HomePage;
import com.example.wsbp.repository.AuthUserRepository;
import com.example.wsbp.service.UserService;
import org.apache.wicket.Session;
import org.apache.wicket.mock.MockApplication;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.Url;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.Charset;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class ChangeNamePageTest {

    private WicketTester tester;

    @Mock
    AuthUserRepository authUserRepository;

    @InjectMocks
    UserService userService;

    @BeforeEach
    public void setUp(){
        WebApplication application = new MockApplication(){
            public Session newSession(Request request, Response response){
                return new MySession(request);
            }
        };
        tester = new WicketTester(application);

        MockitoAnnotations.openMocks(this);

    }

    @Test
    @DisplayName("新しくユーザ名を入力すると、変更される")
    public void changeFormTest() {

        var changeName = "b2211280";
        var userName = "b2221280";
        userService.changeUser(changeName, userName);

    }

    @Test
    @DisplayName("変更後のセッション名が変更名になっている")
    public void SessionTest(){

        //tester.startPage(HomePage.class);
        MySession session = MySession.get();
        var userName = "b2211280";
        var changeName = "b2221280";

        session.sign(userName);
        var name = session.getUserName();

        session.sign(changeName);
        var newName = session.getUserName();

        assertNotEquals(name, newName);

    }

}
