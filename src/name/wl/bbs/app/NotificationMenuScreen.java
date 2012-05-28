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
        mails = new MenuListItem("���ʼ� - " + NotificationsTask.getMailsCount(), mailsListener);
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

            list.invalidate();
        }
    };

    public Listener clearListener = new Listener() {
        public void callback(Object o)
        {
            ClearNotificationsJSON obj = (ClearNotificationsJSON)o;
            if (obj.getSuccess()) {
                if (obj.getResult() == 0) {
                    alert("֪ͨ�����");
                    new NotificationsTask(loadListener).run();
                } else {
                    alert("��������: " + obj.getResult());
                }
            } else {
                alert("���֪ͨʧ��");
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
            case 'r':
                new NotificationsTask(loadListener).run();
                return true;
            case 'c':
                new ClearNotificationsJSON().load(clearListener);
                return true;
        }

        return super.keyChar(key, status, time);
    }
}
