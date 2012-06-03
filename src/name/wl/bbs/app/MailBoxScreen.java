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
    private boolean needRefresh = false;

    private int type;

    public MailBoxScreen(int type)
    {
        this.type = type;

        alert("加载中", ALERT_WARNING);
        new MailboxJSON(type).load(loadListener);

        setStatusbarTitle(MailboxJSON.getTypeString(this.type));
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
                    alert("加载完成");
                } else if (needRefresh) {
                    needRefresh = false;
                    bbs.invokeLater(new Runnable() {
                        public void run() {
                            list.setMails(mails);
                        }
                    });
                    alert("加载完成");
                } else {
                    bbs.invokeLater(new Runnable() {
                        public void run() {
                            if (mails.size() == 0) {
                                alert("到底了:)");
                            } else {
                                list.appendMails(mails);
                                alert("加载完成");
                            }
                            moreListener.setLoaded();
                        }
                    });
                }
            } else {
                alert(obj.getError(), ALERT_ERROR);
            }
        }
    };

    public Listener moreListener = new Listener() {
        public void callback(Object obj)
        {
            if (!this.isLoading()) {
                alert("加载更多", ALERT_WARNING);
                new MailboxJSON(type, list.getSize(), LIMIT).load(loadListener);
                this.setLoading();
            }
        }
    };

    public Listener mailListener = new Listener() {
        public void callback(Object o)
        {
            bbs.pushScreen(new MailScreen(list));
        }
    };

    private void changeBox(int type)
    {
        if (this.type != type) {
            this.type = type;
            needRefresh = true;
            alert("刷新中", ALERT_WARNING);
            setStatusbarTitle(MailboxJSON.getTypeString(this.type));
            new MailboxJSON(this.type).refresh(loadListener);
        }
    }

    protected boolean keyChar(char key, int status, int time)
    {
        switch (key) {
            case 'r':
                needRefresh = true;
                alert("刷新中", ALERT_WARNING);
                new MailboxJSON(type).refresh(loadListener);
                return true;
            case 'I':
                changeBox(MailboxJSON.INBOX);
                return true;
            case 'S':
                changeBox(MailboxJSON.SENT);
                return true;
            case 'D':
                changeBox(MailboxJSON.DELETED);
                return true;
        }

        return super.keyChar(key, status, time);
    }
}
