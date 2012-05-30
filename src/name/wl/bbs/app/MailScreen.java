package name.wl.bbs.app;

import java.util.Vector;
import net.rim.device.api.ui.*;

import name.wl.bbs.hjlp.*;
import name.wl.bbs.ui.*;
import name.wl.bbs.util.*;
import name.wl.bbs.json.*;

public class MailScreen extends BaseScreen
{
    private Mail mail;
    private MailJSON mailJSON;

    private BbsLabelField title;
    private BbsLabelField author;
    private BbsRichTextField content;

    public MailScreen(Mail mail)
    {
        this.mail = mail;
        this.mailJSON = new MailJSON(mail);
        this.mailJSON.load(loadListener);

        title = new BbsLabelField(this.mail.getTitle());
        add(title);

        author = new BbsLabelField(this.mail.getAuthor());
        add(author);

        switch (this.mail.getType()) {
            case MailboxJSON.INBOX:
                setStatusbarTitle("�ռ���");
                break;
            case MailboxJSON.SENT:
                setStatusbarTitle("������");
                break;
            case MailboxJSON.DELETED:
                setStatusbarTitle("������");
                break;
        }

        alert("��������", ALERT_WARNING);
    }

    public Listener loadListener = new Listener() {
        public void callback(Object o)
        {
            MailJSON obj = (MailJSON)o;
            if (obj.getSuccess()) {
                mail = obj.getMail();

                bbs.invokeLater(new Runnable() {
                    public void run() {

                        content = new BbsRichTextField(mail.getContent());
                        MailScreen.this.add(content);
                    }
                });
                alert("�������");
            } else {
                alert("����:" + obj.getError(), ALERT_ERROR);
            }
        }
    };

    protected boolean keyChar(char key, int status, int time)
    {
        switch (key) {
            case 'q':
                bbs.popScreen(this);
                return true;
            case 'r':
                bbs.pushScreen(new MailSendScreen(mail));
                break;
        }

        return super.keyChar(key, status, time);
    }
}
