package com.example.wsbp.page.signed;

import com.example.wsbp.page.SignPage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.model.IModel;
import org.wicketstuff.annotation.mount.MountPath;

@MountPath("ChangeNameComp")
public class ChangeNameCompPage extends WebPage {

    public ChangeNameCompPage(IModel<String> userNameModel){
        var userNameLabel = new Label("userName", userNameModel);
        add(userNameLabel);

        var toSignedLink = new BookmarkablePageLink<>("toSigned", SignedPage.class);
        add(toSignedLink);
    }
}
