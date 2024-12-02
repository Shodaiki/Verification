package com.example.wsbp.page.signed;

import com.example.wsbp.MySession;
import com.example.wsbp.page.HomePage;
import com.example.wsbp.page.SignPage;
import com.example.wsbp.repository.AuthUserRepository;
import com.example.wsbp.service.IUserService;
import com.example.wsbp.service.UserService;
import org.apache.wicket.Session;
import org.apache.wicket.mock.MockApplication;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SignedPageTest {

    private WicketTester tester;

//    @Mock
//    AuthUserRepository authUserRepository;
//
//    @InjectMocks
//    IUserService userService;

    @BeforeEach
    public void setUp(){
        WebApplication application = new MockApplication(){
            public Session newSession(Request request, Response response){
                return new MySession(request);
            }
        };
        tester = new WicketTester(application);

        //MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("サインインしていればSignPageに戻らない")
    public void GetTest(){
        var userName = "b2211280";
        tester.startPage(new HomePage());
        MySession session = MySession.get();
        session.sign(userName);

        if(session.isSignedIn()){
            tester.assertRenderedPage(SignedPage.class);
        } else {
            tester.assertRenderedPage(SignPage.class);
        }
    }

}
