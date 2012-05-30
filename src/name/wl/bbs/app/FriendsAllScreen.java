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
        FriendsAllJSON friendsJSON = new FriendsAllJSON();
        alert("������", ALERT_WARNING);
        friendsJSON.load(loadListener);

        setStatusbarTitle("ȫ������");
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
                alert("�������");
            } else {
                alert("����:" + obj.getError(), ALERT_ERROR);
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
                alert("ˢ�����");
            } else {
                alert("����:" + obj.getError(), ALERT_ERROR);
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
                    FriendsAllJSON friendsJSON = new FriendsAllJSON();
                    alert("ˢ����", ALERT_WARNING);
                    friendsJSON.refresh(refreshListener);
                }
                return true;
        }

        return super.keyChar(key, status, time);
    }
}
