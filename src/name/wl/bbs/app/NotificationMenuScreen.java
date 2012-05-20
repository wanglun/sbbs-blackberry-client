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
    private MenuListItem mails;
    private MenuListItem ats;
    private MenuListItem replies;
    private MenuListField list;

    public NotificationMenuScreen()
    {
        items = new Vector();
        mails = new MenuListItem("新邮件 - " + NotificationsTask.getMailsCount(), mailsListener);
        ats = new MenuListItem("爱特我的 - " + NotificationsTask.getAtsCount(), atsListener);
        replies = new MenuListItem("回复我的 - " + NotificationsTask.getRepliesCount(), repliesListener);

        items.addElement(mails);
        items.addElement(ats);
        items.addElement(replies);

        list = new MenuListField(items);
        add(list);

        setStatusbarTitle("通知箱");

        new NotificationsTask(loadListener).run();
    }

    public Listener loadListener = new Listener() {
        public void callback(Object o)
        {
            mails.setLabel("新邮件 - " + NotificationsTask.getMailsCount());
            ats.setLabel("爱特我的 - " + NotificationsTask.getAtsCount());
            replies.setLabel("回复我的 - " + NotificationsTask.getRepliesCount());

            list.invalidate();
        }
    };

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
