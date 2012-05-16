package name.wl.bbs.app;

import java.util.*;
import net.rim.device.api.ui.*;
import name.wl.bbs.ui.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;
import name.wl.bbs.json.*;

public class NotificationMenuScreen extends BaseScreen
{
    private Vector items;
    private MenuListField list;

    public NotificationMenuScreen()
    {
        items = new Vector();
        items.addElement(new MenuListItem("新邮件", mailsListener));
        items.addElement(new MenuListItem("爱特我的", atsListener));
        items.addElement(new MenuListItem("回复我的", repliesListener));

        list = new MenuListField(items);
        add(list);

        setStatusbarTitle("通知箱");
    }

    public Listener mailsListener = new Listener() {
        public void callback(Object o)
        {
            bbs.pushScreen(new NotificationScreen(NotificationScreen.MAILS));
        }
    };

    public Listener atsListener = new Listener() {
        public void callback(Object o)
        {
            bbs.pushScreen(new NotificationScreen(NotificationScreen.ATS));
        }
    };

    public Listener repliesListener = new Listener() {
        public void callback(Object o)
        {
            bbs.pushScreen(new NotificationScreen(NotificationScreen.REPLIES));
        }
    };
}
