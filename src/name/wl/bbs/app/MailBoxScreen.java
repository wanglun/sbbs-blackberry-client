package name.wl.bbs.app;

import java.util.Vector;
import net.rim.device.api.ui.*;

import name.wl.bbs.hjlp.*;
import name.wl.bbs.ui.*;
import name.wl.bbs.util.*;
import name.wl.bbs.json.*;

public class MailBoxScreen extends BaseScreen
{
    private static final int LIMIT = 10;

    private MailListField list = null;

    private int type;

    public MailBoxScreen(int type)
    {
        this.type = type;

        new MailboxJSON(type).load(loadListener);

        switch (this.type) {
            case MailboxJSON.INBOX:
                setStatusbarTitle("收件箱");
                break;
            case MailboxJSON.SENT:
                setStatusbarTitle("发件箱");
                break;
            case MailboxJSON.DELETED:
                setStatusbarTitle("垃圾箱");
                break;
        }
    }

    public Listener loadListener = new Listener() {
        public void callback(Object o)
        {
            MailboxJSON obj = (MailboxJSON)o;
            if (obj.getSuccess()) {
                final Vector mails = obj.getMails();
                if (list == null) {
                    list = new MailListField(mails, mailListener, moreListener);
                    bbs.invokeLater(new Runnable() {
                        public void run() {
                            MailBoxScreen.this.add(list);
                        }
                    });
                } else {
                    bbs.invokeLater(new Runnable() {
                        public void run() {
                            list.appendMails(mails);
                            moreListener.setLoaded();
                        }
                    });
                }
            } else {
                alert("加载信件失败");
            }
        }
    };

    public Listener moreListener = new Listener() {
        public void callback(Object obj)
        {
            if (!this.isLoading()) {
                new MailboxJSON(type, list.getSize(), LIMIT).load(loadListener);
                this.setLoading();
            }
        }
    };

    public Listener mailListener = new Listener() {
        public void callback(Object o)
        {
            bbs.pushScreen(new MailScreen((Mail)o));
        }
    };

    protected boolean keyChar(char key, int status, int time)
    {
        switch (key) {
            case 'p':
                break;
        }

        return super.keyChar(key, status, time);
    }
}
