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
        items.addElement(new MenuListItem("查询用户", queryListener));
        items.addElement(new MenuListItem("在线好友", onlineListener));
        items.addElement(new MenuListItem("所有好友", allListener));

        list = new MenuListField(items);
        add(list);

        setStatusbarTitle("谈天说地");
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
