package name.wl.bbs.app;

import java.util.*;
import net.rim.device.api.ui.*;
import name.wl.bbs.ui.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;
import name.wl.bbs.json.*;

public class TalkMenuScreen extends BaseScreen
{
    private Vector items;
    private MenuListField list;

    public TalkMenuScreen()
    {
        items = new Vector();
        items.addElement(new MenuListItem("��ѯ�û�", queryListener));
        items.addElement(new MenuListItem("���ߺ���", onlineListener));
        items.addElement(new MenuListItem("���к���", allListener));

        list = new MenuListField(items);
        add(list);

        setStatusbarTitle("̸��˵��");
    }

    public Listener queryListener = new Listener() {
        public void callback(Object o)
        {
            bbs.pushScreen(new UserQueryScreen());
        }
    };

    public Listener onlineListener = new Listener() {
        public void callback(Object o)
        {
            bbs.pushScreen(new FriendsScreen(false));
        }
    };

    public Listener allListener = new Listener() {
        public void callback(Object o)
        {
            bbs.pushScreen(new FriendsScreen(true));
        }
    };
}
