package name.wl.bbs.app;

import java.util.*;
import net.rim.device.api.ui.*;
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
        items.addElement(new MenuListItem("Sections", sectionsListener));
        items.addElement(new MenuListItem("Hot", hotListener));
        items.addElement(new MenuListItem("Fav", favListener));
        items.addElement(new MenuListItem("Mail", mailListener));
        items.addElement(new MenuListItem("Talk", talkListener));
        items.addElement(new MenuListItem("Notification", notificationListener));
        items.addElement(new MenuListItem("Account", accountListener));

        list = new MenuListField(items);
        add(list);
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
}
