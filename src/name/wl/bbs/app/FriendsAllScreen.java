package name.wl.bbs.app;

import java.util.Vector;
import net.rim.device.api.ui.*;

import name.wl.bbs.hjlp.*;
import name.wl.bbs.ui.*;
import name.wl.bbs.util.*;
import name.wl.bbs.json.*;

public class FriendsAllScreen extends BaseScreen
{
    private Vector friends;
    private FriendListField list;

    public FriendsAllScreen()
    {
        alert("加载中", ALERT_WARNING);
        new FriendsAllJSON().load(loadListener);

        setStatusbarTitle("全部好友");
    }

    public Listener loadListener = new Listener() {
        public void callback(Object o)
        {
            FriendsAllJSON obj = (FriendsAllJSON)o;
            if (obj.getSuccess()) {
                friends = obj.getFriends();
                list = new FriendListField(friends, friendListener);
                bbs.invokeLater(new Runnable() {
                    public void run() {
                        FriendsAllScreen.this.add(list);
                    }
                });
                alert("加载完成");
            } else {
                alert(obj.getError(), ALERT_ERROR);
            }
        }
    };

    public Listener refreshListener = new Listener() {
        public void callback(Object o)
        {
            FriendsAllJSON obj = (FriendsAllJSON)o;
            if (obj.getSuccess()) {
                friends = obj.getFriends();
                bbs.invokeLater(new Runnable() {
                    public void run() {
                        list.setUsers(friends);
                    }
                });
                alert("刷新完成");
            } else {
                alert(obj.getError(), ALERT_ERROR);
            }
        }
    };

    public Listener friendListener = new Listener() {
        public void callback(Object o)
        {
            bbs.pushScreen(new UserScreen((User)o));
        }
    };

    protected boolean keyChar(char key, int status, int time)
    {
        switch (key) {
            case 'r':
                if (list != null) {
                    alert("刷新中", ALERT_WARNING);
                    new FriendsAllJSON().refresh(refreshListener);
                }
                return true;
            case 'O':
                bbs.popScreen(this);
                bbs.pushScreen(new FriendsScreen());
                return true;
        }

        return super.keyChar(key, status, time);
    }
}
