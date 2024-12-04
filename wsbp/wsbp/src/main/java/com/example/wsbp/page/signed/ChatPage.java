package com.example.wsbp.page.signed;

import com.example.wsbp.service.IChatService;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.StringValidator;
import org.wicketstuff.annotation.mount.MountPath;

import java.sql.Timestamp;

@AuthorizeInstantiation(Roles.USER)
@MountPath("Chat")
public class ChatPage extends WebPage {

    @SpringBean
    private IChatService chatService;

    public ChatPage() {

        var userNameModel = Model.of("");
        var msgBodyModel = Model.of("");


        //var toHomeLink = new BookmarkablePageLink<>("toHome", HomePage.class);
        //add(toHomeLink);

        var chatInfoForm = new Form<>("chatInfo") {
            @Override
            protected void onSubmit() {
                var userName = userNameModel.getObject();
                var msgBody = msgBodyModel.getObject();
                var chatTime = chatService.makePostHMS();
                var msg = "送信データ："
                        + userName
                        + ","
                        + msgBody
                        + ","
                        + chatTime;
                System.out.println(msg);

                chatService.registerChat(userName, msgBody, chatTime);
                setResponsePage(new ChatCompPage(userNameModel,msgBodyModel));
            }
        };
        add(chatInfoForm);


        var userNameField = new TextField<>("user_Name", userNameModel) {
            // onInitialize() は全てのコンポーネントに備わっている、初期化時の処理。
            // オーバーライドするときは super.onInitialize() を忘れずに残しておく。
            @Override
            protected void onInitialize() {
                super.onInitialize();
                // 文字列の長さを8〜32文字に制限するバリデータ
                var validator = StringValidator.lengthBetween(8, 32);
                add(validator);
            }
        };
        chatInfoForm.add(userNameField);

        var msgBodyField = new TextField<>("msg_body", msgBodyModel);
        chatInfoForm.add(msgBodyField);
    }
}
