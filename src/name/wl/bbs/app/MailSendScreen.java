package name.wl.bbs.app;

import net.rim.device.api.ui.*;
import name.wl.bbs.ui.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;
import name.wl.bbs.json.*;

public class MailSendScreen extends BaseScreen implements FieldChangeListener
{
    private User user = null;
    private Mail mail = null;
    
    private BbsEditField title;
    private BbsEditField content;

    private BbsCheckboxField noquote;

    private BbsButtonField send;

    public MailSendScreen(Mail mail)
    {
        this(mail, null);
    }

    public MailSendScreen(User user)
    {
        this(null, user);
    }

    public MailSendScreen(Mail mail, User user)
    {
        editable = true;

        this.mail = mail;
        this.user = user;

        title = new BbsEditField("标题");
        if (mail != null) {
            title.setText(mail.getTitle().indexOf("Re: ") == 0 ? mail.getTitle() : "Re: " + mail.getTitle());
        }
        add(title);

        content = new BbsEditField("内容", 3);
        add(content);

        if (mail != null) {
            noquote = new BbsCheckboxField("不引原文", false);
            add(noquote);

            content.setFocus();
        }

        send = new BbsButtonField("发送");
        send.setChangeListener(this);
        add(send);

        setStatusbarTitle(mail == null ? "写邮件" : "回复邮件");
    }

    public void fieldChanged(Field field, int context) {
        if (field == send) {
            String titleStr = title.getText();
            String contentStr = content.getText();
            boolean noquoteBool = false;
            if (mail != null) {
                noquoteBool = noquote.getChecked();
            }
            if (title.getText().length() == 0 ||
                    content.getText().length() == 0) {
                alert("标题/内容不能为空", ALERT_ERROR);
            } else {
                MailSendJSON json;
                if (mail != null) {
                    json = new MailSendJSON(new User(mail.getAuthor()), titleStr, contentStr, mail.getId(), noquoteBool);
                    json.load(this.sendListener);
                    alert("发送中", ALERT_WARNING);
                } else if (user != null) {
                    json = new MailSendJSON(user, titleStr, contentStr, 0, noquoteBool);
                    json.load(this.sendListener);
                    alert("发送中", ALERT_WARNING);
                } else {
                    alert("参数错误", ALERT_ERROR);
                }
            }
        }
    }

    public Listener sendListener = new Listener() {
        public void callback(Object r)
        {
            final MailSendJSON obj = (MailSendJSON) r;
            if (obj.getSuccess()) {
                alert("已发送", true);
            } else {
                alert("错误:" + obj.getError(), ALERT_ERROR);
            }
        }
    };
}
