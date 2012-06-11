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
        mails = new MenuListItem("���ż� - " + NotificationsTask.getMailsCount(), mailsListener);
        ats = new MenuListItem("�����ҵ� - " + NotificationsTask.getAtsCount(), atsListener);
        replies = new MenuListItem("�ظ��ҵ� - " + NotificationsTask.getRepliesCount(), repliesListener);

        items.addElement(mails);
        items.addElement(ats);
        items.addElement(replies);

        list = new MenuListField(items);
        add(list);

        setStatusbarTitle("֪ͨ��");
    }

    public Listener loadListener = new Listener() {
        public void callback(Object o)
        {
            mails.setLabel("���ʼ� - " + NotificationsTask.getMailsCount());
            ats.setLabel("�����ҵ� - " + NotificationsTask.getAtsCount());
            replies.setLabel("�ظ��ҵ� - " + NotificationsTask.getRepliesCount());

            alert("��ˢ��");

            list.invalidate();
        }
    };

    public Listener clearListener = new Listener() {
        public void callback(Object o)
        {
            ClearNotificationsJSON obj = (ClearNotificationsJSON)o;
            if (obj.getSuccess()) {
                mails.setLabel("���ʼ� - 0");
                ats.setLabel("�����ҵ� - 0");
                replies.setLabel("�ظ��ҵ� - 0");

                alert("�����δ��");

                list.invalidate();
            } else {
                alert(obj.getError(), ALERT_ERROR);
            }
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

    protected boolean keyChar(char key, int status, int time)
    {
        switch (key) {
            case '?':
                bbs.pushScreen(new HelpScreen(HelpScreen.TYPE_NOTIFICATIONMENU));
                return true;
            case 'r':
                alert("ˢ����", ALERT_WARNING);
                new NotificationsTask(loadListener).run();
                return true;
            case 'c':
                new ClearNotificationsJSON().load(clearListener);
                return true;
            case 'q':
                refreshNotifications();
                break;
        }

        return super.keyChar(key, status, time);
    }

    private void refreshNotifications()
    {
        if (NotificationsTask.getCount() > 0)
            new NotificationsTask().run();
    }

    public boolean onClose()
    {
        refreshNotifications();
        return super.onClose();
    }
}
