package name.wl.bbs.app;

import java.util.*;
import net.rim.device.api.ui.*;
import name.wl.bbs.ui.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;
import name.wl.bbs.json.*;

public class AccountMenuScreen extends BaseScreen
{
    private Vector items;
    private MenuListField list;

    public AccountMenuScreen()
    {
        items = new Vector();
        items.addElement(new MenuListItem("ÍË³öµÇÂ¼", logoutListener));

        list = new MenuListField(items);
        add(list);
    }

    public Listener logoutListener = new Listener() {
        public void callback(Object o)
        {
            bbs.setLoggedIn(false);
            bbs.setToken(null);

            (new LoginJSON()).delCache();
            (new FavJSON()).delCache();
            (new FriendsJSON()).delCache();
            (new FriendsAllJSON()).delCache();

            System.exit(0);
        }
    };
}
