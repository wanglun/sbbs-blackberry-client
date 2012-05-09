package name.wl.bbs.app;

import java.util.Vector;
import net.rim.device.api.ui.*;

import name.wl.bbs.hjlp.*;
import name.wl.bbs.ui.*;
import name.wl.bbs.util.*;
import name.wl.bbs.json.*;

public class FriendsScreen extends BaseScreen
{
    private boolean all = false;

    private Vector friends;
    private FriendListField list;

    public FriendsScreen(boolean all)
    {
        this.all = all;

        if (all) {
            FriendsAllJSON friendsJSON = new FriendsAllJSON();
            friendsJSON.load(loadListener);
        } else {
            FriendsJSON friendsJSON = new FriendsJSON();
            friendsJSON.load(loadListener);
        }
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
            } else {
                alert("load friends failed!");
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
            } else {
                alert("load friends failed!");
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
                    if (all) {
                        FriendsAllJSON friendsJSON = new FriendsAllJSON();
                        friendsJSON.refresh(refreshListener);
                    } else {
                        FriendsJSON friendsJSON = new FriendsJSON();
                        friendsJSON.refresh(refreshListener);
                    }
                }
                return true;
        }

        return super.keyChar(key, status, time);
    }
}
