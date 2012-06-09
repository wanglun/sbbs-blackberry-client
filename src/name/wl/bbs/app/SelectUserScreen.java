package name.wl.bbs.app;

import net.rim.device.api.ui.component.KeywordFilterField;
import net.rim.device.api.collection.util.UnsortedReadableList;
import net.rim.device.api.system.Characters;

import name.wl.bbs.json.*;
import name.wl.bbs.util.*;
import name.wl.bbs.ui.*;
import name.wl.bbs.hjlp.*;

public class SelectUserScreen extends BaseScreen
{
    private KeywordFilterField userFilter;
    private Listener listener;

    public SelectUserScreen(Listener listener)
    {
        editable = true;

        this.listener = listener;

        userFilter = new KeywordFilterField();

        if (bbs.getFriends() == null) {
            alert("加载中", ALERT_WARNING);
            new FriendsJSON().load(loadListener);
        } else {
            addUserFilter();
        }

    }

    private void addUserFilter()
    {
        userFilter.setSourceList(bbs.getFriends(), bbs.getFriends());
        userFilter.setKeywordField(new SelectEditField("查找用户: "));
        add(userFilter);
        setTitle(userFilter.getKeywordField());
    }

    public Listener loadListener = new Listener() {
        public void callback(Object o)
        {
            FriendsJSON obj = (FriendsJSON)o;
            if (obj.getSuccess()) {
                bbs.setFriends(new SelectList(obj.getFriends()));
                bbs.invokeLater(new Runnable() {
                    public void run() {
                        addUserFilter();
                    }
                });
            } else {
                alert(obj.getError(), ALERT_ERROR);
            }
        }
    };

    protected boolean keyChar(char key, int status, int time)
    {
        switch (key) {
            case Characters.ENTER:
                User user = null;
                if (userFilter.getResultListSize() > 0) {
                    user = (User)userFilter.getSelectedElement();
                } else {
                    user = new User(userFilter.getKeyword());
                }
                listener.callback(user);
                bbs.popScreen(this);
                return true;
        }

        return super.keyChar(key, status, time);
    }
}
