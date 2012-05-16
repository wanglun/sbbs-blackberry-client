package name.wl.bbs.app;

import java.util.*;
import net.rim.device.api.ui.*;
import name.wl.bbs.ui.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;
import name.wl.bbs.json.*;

public class MailMenuScreen extends BaseScreen
{
    private Vector items;
    private MenuListField list;

    public MailMenuScreen()
    {
        items = new Vector();
        items.addElement(new MenuListItem("收件箱", inboxListener));
        items.addElement(new MenuListItem("发件箱", sentListener));
        items.addElement(new MenuListItem("垃圾箱", deletedListener));

        list = new MenuListField(items);
        add(list);
    }

    public Listener inboxListener = new Listener() {
        public void callback(Object o)
        {
            bbs.pushScreen(new MailBoxScreen(MailboxJSON.INBOX));
        }
    };

    public Listener sentListener = new Listener() {
        public void callback(Object o)
        {
            bbs.pushScreen(new MailBoxScreen(MailboxJSON.SENT));
        }
    };

    public Listener deletedListener = new Listener() {
        public void callback(Object o)
        {
            bbs.pushScreen(new MailBoxScreen(MailboxJSON.DELETED));
        }
    };
}
