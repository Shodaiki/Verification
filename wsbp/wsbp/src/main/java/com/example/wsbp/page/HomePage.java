package com.example.wsbp.page;

import com.giffing.wicket.spring.boot.context.scan.WicketHomePage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.wicketstuff.annotation.mount.MountPath;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.Model;
import com.example.wsbp.service.ISampleService;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.markup.html.link.BookmarkablePageLink;


@WicketHomePage
@MountPath("Home")
public class HomePage extends WebPage {

    @SpringBean
    private ISampleService service;

    public HomePage() {
        var dateModel = Model.of("");

        var youModel = Model.of("Wicket-Spring-Boot");
        var youLabel = new Label("you", youModel);
        add(youLabel);
        var gakusekiModel = Model.of("b2211280");
        var gakusekiLabel = new Label("gakuseki", gakusekiModel);
        add(gakusekiLabel);
        var shimeiModel = Model.of("小路大貴");
        var shimeiLabel = new Label("shimei", shimeiModel);
        add(shimeiLabel);
        var timeModel = Model.of(service.makeCurrentHMS());
        var timeLabel = new Label("time", timeModel);
        add(timeLabel);
        var randModel = Model.of(service.makeRandInt());
        var randLabel = new Label("rand", randModel);
        add(randLabel);

        var dateform = new Form<>("date"){
          @Override
          protected void onSubmit(){
              var Date = dateModel.getObject();
              System.out.println(Date);
              setResponsePage(SignPage.class);
              //log.debug("日付" + Date);
          }
        };
        add(dateform);

        var toUserMakerLink = new BookmarkablePageLink<>("toUserMaker", UserMakerPage.class);
        add(toUserMakerLink);
        var toUserDeleteLink = new BookmarkablePageLink<>("toUserDelete", UserDeletePage.class);
        add(toUserDeleteLink);
    }

}
