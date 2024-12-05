package com.example.wsbp.page.signed;

import com.example.wsbp.MySession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.IModel;
import org.wicketstuff.annotation.mount.MountPath;

import java.sql.Timestamp;

@MountPath("ChatComp")
public class ChatCompPage extends WebPage {

    public ChatCompPage(IModel<String> msgBodyModel) {


        var userNameLabel = new Label("userName", MySession.get().getUserName());
        add(userNameLabel);

        var msgBodyLabel = new Label("msgBody", msgBodyModel);
        add(msgBodyLabel);

        var toSignedLink = new BookmarkablePageLink<>("toSigned", SignedPage.class);
        add(toSignedLink);


    }
}
