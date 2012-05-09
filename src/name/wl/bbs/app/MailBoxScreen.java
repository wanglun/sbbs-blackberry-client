package name.wl.bbs.app;

import java.util.Vector;
import net.rim.device.api.ui.*;

import name.wl.bbs.hjlp.*;
import name.wl.bbs.ui.*;
import name.wl.bbs.util.*;
import name.wl.bbs.json.*;

public class MailBoxScreen extends BaseScreen
{
    private MailboxJSON mailboxJSON;
    private Vector mails;
    private MailListField list;

    private int type;

    public MailBoxScreen(int type)
    {
        this.type = type;
        this.mailboxJSON = new MailboxJSON(type);
        this.mailboxJSON.load(loadListener);
    }

    public Listener loadListener = new Listener() {
        public void callback(Object o)
        {
            MailboxJSON obj = (MailboxJSON)o;
            if (obj.getSuccess()) {
                mails = obj.getMails();
                list = new MailListField(mails, mailListener);
                bbs.invokeLater(new Runnable() {
                    public void run() {
                        MailBoxScreen.this.add(list);
                    }
                });
            } else {
                alert("load mails failed!");
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
