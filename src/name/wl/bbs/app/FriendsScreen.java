package name.wl.bbs.app;

import java.util.Vector;
import net.rim.device.api.ui.*;

import name.wl.bbs.hjlp.*;
import name.wl.bbs.ui.*;
import name.wl.bbs.util.*;
import name.wl.bbs.json.*;

public class FriendsScreen extends BaseScreen
{
    private Vector friends;
    private FriendListField list;

    public FriendsScreen()
    {
        FriendsJSON friendsJSON = new FriendsJSON();
        friendsJSON.load(loadListener);
        alert("加载中", ALERT_WARNING);

        setStatusbarTitle("在线好友");
    }

    public Listener loadListener = new Listener() {
        public void callback(Object o)
        {
            FriendsJSON obj = (FriendsJSON)o;
            if (obj.getSuccess()) {
                friends = obj.getFriends();
                list = new FriendListField(friends, friendListener);
                bbs.invokeLater(new Runnable() {
                    public void run() {
                        FriendsScreen.this.add(list);
                    }
                });
                alert("加载完成");
            } else {
                alert("错误:" + obj.getError(), ALERT_ERROR);
            }
        }
    };

    public Listener refreshListener = new Listener() {
        public void callback(Object o)
        {
            FriendsJSON obj = (FriendsJSON)o;
            if (obj.getSuccess()) {
                friends = obj.getFriends();
                bbs.invokeLater(new Runnable() {
                    public void run() {
                        list.setUsers(friends);
                    }
                });
                alert("刷新完成");
            } else {
                alert("错误:" + obj.getError(), ALERT_ERROR);
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
            case 'm':
                break;
            case 'r':
                if (list != null) {
                    FriendsJSON friendsJSON = new FriendsJSON();
                    alert("刷新中", ALERT_WARNING);
                    friendsJSON.refresh(refreshListener);
                }
                return true;
        }

        return super.keyChar(key, status, time);
    }
}
