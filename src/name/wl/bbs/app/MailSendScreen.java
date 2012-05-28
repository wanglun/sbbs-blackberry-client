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
        this.mail = mail;
        this.user = user;

        title = new BbsEditField("title");
        if (mail != null) {
            title.setText(mail.getTitle().indexOf("Re: ") == 0 ? mail.getTitle() : "Re: " + mail.getTitle());
        }
        add(title);

        content = new BbsEditField("content");
        add(content);

        noquote = new BbsCheckboxField("noquote", false);
        add(noquote);

        send = new BbsButtonField("send");
        send.setChangeListener(this);
        add(send);

        setStatusbarTitle("写邮件");
    }

    public void fieldChanged(Field field, int context) {
        if (field == send) {
            String titleStr = title.getText();
            String contentStr = content.getText();
            boolean noquoteBool = noquote.getChecked();
            if (title.getText().length() == 0 ||
                    content.getText().length() == 0) {
                alert("标题/内容不能为空");
            } else {
                MailSendJSON json;
                if (mail != null) {
                    json = new MailSendJSON(new User(mail.getAuthor()), titleStr, contentStr, mail.getId(), noquoteBool);
                    json.load(this.sendListener);
                } else if (user != null) {
                    json = new MailSendJSON(user, titleStr, contentStr, 0, noquoteBool);
                    json.load(this.sendListener);
                } else {
                    alert("参数错误");
                }
            }
        }
    }

    public Listener sendListener = new Listener() {
        public void callback(Object r)
        {
            final MailSendJSON obj = (MailSendJSON) r;
            if (obj.getSuccess()) {
                if (obj.getResult() == 0)
                    alert("已发送");
                else
                    alert("发送失败: " + obj.getResult());
            } else {
                alert("网络错误");
            }
        }
    };
}
