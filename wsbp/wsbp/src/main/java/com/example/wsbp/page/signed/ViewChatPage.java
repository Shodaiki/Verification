package com.example.wsbp.page.signed;

import com.example.wsbp.data.AuthUser;
import com.example.wsbp.service.IChatService;
import com.example.wsbp.service.IUserService;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import com.example.wsbp.data.Chat;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import java.util.Objects;

@AuthorizeInstantiation(Roles.USER)
@MountPath("ViewChat")
public class ViewChatPage extends WebPage{

    @SpringBean
    private IChatService chatService;

    @SpringBean
    private IUserService userService;

    public ViewChatPage(){

        var chatModel = Model.ofList(chatService.replyChat());

        var usersLV = new ListView<>("chat",chatModel ) {
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

        var selectionModel = LoadableDetachableModel.of(() -> userService.findAuthUsers());
        // プルダウンメニューから選択したものを入れるためのModelを作成
        var selectedModel = new Model<AuthUser>();

        var form = new Form<>("form"){
            @Override
            protected void onSubmit() {
                // 次ページに渡すModelを用意する。
                // Wicketではさまざまな機能をもったModelを利用できるが、この機能が別ページで想定していない動きをすると動作不良の要因になる場合がある。
                // そのため、次ページにデータを渡す時には新しいModelに設定し直すことを心がけると、バグの防止になる。
                var sendingModel = new Model<>(selectedModel.getObject());
                if (Objects.isNull(sendingModel.getObject())) {
                    // 選択肢が選ばれていない場合の処理
                    // nullをそのまま渡すと、次ページでNullPointerExceptionが発生する要因になるので、ダミーのデータを渡す。
                    var dummyData = new AuthUser("ユーザー名未設定", "パスワード未設定");
                    sendingModel.setObject(dummyData);
                }
                // 次ページに渡すModelを使って、次ページ（ChoiceResultPage）を作成し移動する。
                setResponsePage(new SelectUserViewChatPage(sendingModel));

            }
        };
        add(form);

        // 第1引数の文字列は、AuthUserのuserNameを選択肢の表示用として取り出すことを設定
        var renderer = new ChoiceRenderer<>("userName");

        // プルダウンメニューを作成するためのDropDownChoiceコンポーネント
        // 第1引数は wicket:id, 第2引数は選択したものを入れるためのModel, 第3引数は表示するためのリストのModel
        var userSelection = new DropDownChoice<>("userSelection", selectedModel, selectionModel, renderer){
            @Override
            protected void onInitialize() {
                // このDropDownChoiceの初期化用の処理
                super.onInitialize();
                // 必ず空欄の選択肢を用意するように設定
                setNullValid(true);
                // 空欄の選択肢の送信を許可しないバリデーション
                setRequired(true);
                // エラーメッセージに表示する名前を設定
                setLabel(Model.of("学籍番号の選択肢"));
            }

            @Override
            protected String getNullValidDisplayValue() {

                return getNullKeyDisplayValue();

            }
        };
        form.add(userSelection);

        var feedback = new FeedbackPanel("feedback");
        form.add(feedback);


        var toSignedLink = new BookmarkablePageLink<>("toSigned", SignedPage.class);
        add(toSignedLink);

    }

}
