package com.example.wsbp.page.signed;

import com.example.wsbp.data.AuthUser;
import com.example.wsbp.data.Chat;
import com.example.wsbp.service.IChatService;
import com.example.wsbp.service.IUserService;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

@AuthorizeInstantiation(Roles.USER)
@MountPath("SelectUserViewChat")
public class SelectUserViewChatPage extends WebPage {

    @SpringBean
    private IChatService chatService;

    @SpringBean
    private IUserService userService;

    public SelectUserViewChatPage(IModel<AuthUser> model){

        var choicedAuthUser = model.getObject();

        var chatModel = Model.ofList(chatService.selectreplyChat(choicedAuthUser.getUserName()));

        //System.out.println(choicedAuthUser.getUserName());

        var usersLV = new ListView<>("SelectUserChat",chatModel ) {
            @Override
            protected void populateItem(ListItem<Chat> listItem) {
                // List型のモデルから、 <li>...</li> ひとつ分に分けられたモデルを取り出す
                var itemModel = listItem.getModel();
                var chat = itemModel.getObject(); // 元々のListの n 番目の要素


                // インスタンスに入れ込まれたデータベースの検索結果を、列（＝フィールド変数）ごとにとりだして表示する
                // add する先が listItem になることに注意。
                var userNameModel = Model.of(chat.getUserName());
                var userNameLabel = new Label("userName", userNameModel);
                listItem.add(userNameLabel);

                var msgBodyModel = Model.of(chat.getMsgBody());
                var msgBodyLabel = new Label("msgBody", msgBodyModel);
                listItem.add(msgBodyLabel);

                var chatTimeModel = Model.of(chat.getChatTime());
                var chatTimeLabel = new Label("chatTime", chatTimeModel);
                listItem.add(chatTimeLabel);

            }
        };
        add(usersLV);


        var toSignedLink = new BookmarkablePageLink<>("toSigned", SignedPage.class);
        add(toSignedLink);

    }

}
