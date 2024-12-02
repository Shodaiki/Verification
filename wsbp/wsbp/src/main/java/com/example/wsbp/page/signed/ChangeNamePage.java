package com.example.wsbp.page.signed;

import com.example.wsbp.MySession;
import com.example.wsbp.service.IUserService;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

@AuthorizeInstantiation(Roles.USER)
@MountPath("ChangeName")
public class ChangeNamePage extends WebPage {

    @SpringBean
    IUserService userService;

    public ChangeNamePage(){
        var changeNameModel = Model.of("");


        var changeForm = new Form<>("change") {
            @Override
            protected void onSubmit() {
                var userName = MySession.get().getUserName();
                var changeName = changeNameModel.getObject();
                var msg = "送信データ："
                        + changeName;

                System.out.println(msg);

                userService.changeUser(changeName, userName);
                MySession.get().sign(changeName);
                setResponsePage(new ChangeNameCompPage(changeNameModel));
            }
        };
        add(changeForm);

        var changeNameField = new TextField<>("change_user_Name", changeNameModel);
        changeForm.add(changeNameField);
    }
}
