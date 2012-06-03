package name.wl.bbs.app;

import java.util.*;
import net.rim.device.api.ui.*;
import net.rim.device.api.ui.component.Dialog;
import name.wl.bbs.ui.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;

public class MenuScreen extends BaseScreen
{
    private Vector items;
    private MenuListField list;

    public MenuScreen()
    {
        items = new Vector();
        items.addElement(new MenuListItem("����������", sectionsListener));
        items.addElement(new MenuListItem("������Ϣ", hotListener));
        items.addElement(new MenuListItem("�ղؼ�", favListener));
        items.addElement(new MenuListItem("����", mailListener));
        items.addElement(new MenuListItem("̸��˵��", talkListener));
        items.addElement(new MenuListItem("֪ͨ��", notificationListener));
        items.addElement(new MenuListItem(bbs.getId() + "���ʺ�", accountListener));

        list = new MenuListField(items);
        add(list);

        setStatusbarTitle("���˵�");

        // start the tasks timer
        // FIXME add period setting
        bbs.getTasksTimer().schedule(new NotificationsTask(), new Date(), 300000);
    }

    public Listener sectionsListener = new Listener() {
        public void callback(Object o)
        {
            bbs.pushScreen(new SectionsScreen());
        }
    };

    public Listener hotListener = new Listener() {
        public void callback(Object o)
        {
            bbs.pushScreen(new HotMenuScreen());
        }
    };

    public Listener favListener = new Listener() {
        public void callback(Object o)
        {
            bbs.pushScreen(new FavScreen());
        }
    };

    public Listener mailListener = new Listener() {
        public void callback(Object o)
        {
            bbs.pushScreen(new MailMenuScreen());
        }
    };

    public Listener talkListener = new Listener() {
        public void callback(Object o)
        {
            bbs.pushScreen(new TalkMenuScreen());
        }
    };

    public Listener notificationListener = new Listener() {
        public void callback(Object o)
        {
            bbs.pushScreen(new NotificationMenuScreen());
        }
    };

    public Listener accountListener = new Listener() {
        public void callback(Object o)
        {
            bbs.pushScreen(new AccountMenuScreen());
        }
    };

    public boolean onClose()
    {
        int ret = Dialog.ask(Dialog.D_OK_CANCEL, "ȷ��Ҫ�˳��ɣ�");
        if (ret == Dialog.D_OK) {
            close();
            return true;
        }

        return false;
    }
}
