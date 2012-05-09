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
        add(title);

        content = new BbsEditField("content");
        add(content);

        noquote = new BbsCheckboxField("noquote", false);
        add(noquote);

        send = new BbsButtonField("send");
        send.setChangeListener(this);
        add(send);
    }

    public void fieldChanged(Field field, int context) {
        if (field == send) {
            String titleStr = title.getText();
            String contentStr = content.getText();
            boolean noquoteBool = noquote.getChecked();
            if (title.getText().length() == 0 ||
                    content.getText().length() == 0) {
                alert("title OR content is empty");
            } else {
                MailSendJSON json;
                if (mail != null) {
                    json = new MailSendJSON(new User(mail.getAuthor()), titleStr, contentStr, mail.getId(), noquoteBool);
                    json.load(this.sendListener);
                } else if (user != null) {
                    json = new MailSendJSON(user, titleStr, contentStr, 0, noquoteBool);
                    json.load(this.sendListener);
                } else {
                    alert("user and mail are null");
                }
            }
        }
    }

    public Listener sendListener = new Listener() {
        public void callback(Object r)
        {
            final MailSendJSON obj = (MailSendJSON) r;
            if (obj.getSuccess()) {
                alert("send success: result code " + obj.getResult());
            } else {
                alert("send failed");
            }
        }
    };
}
