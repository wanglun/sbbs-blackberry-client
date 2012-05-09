package name.wl.bbs.app;

import java.util.Vector;
import net.rim.device.api.ui.*;

import name.wl.bbs.hjlp.*;
import name.wl.bbs.ui.*;
import name.wl.bbs.util.*;
import name.wl.bbs.json.*;

public class UserScreen extends BaseScreen
{
    private User user;
    private UserJSON userJSON;

    public UserScreen(User user)
    {
        this.user = user;
        this.userJSON = new UserJSON(user);
        this.userJSON.load(loadListener);
    }

    public Listener loadListener = new Listener() {
        public void callback(Object o)
        {
            UserJSON obj = (UserJSON)o;
            if (obj.getSuccess()) {
                user = obj.getUser();
                alert(user.getId());
            } else {
                alert("load user info failed!");
            }
        }
    };

    protected boolean keyChar(char key, int status, int time)
    {
        switch (key) {
            case 'm':
                bbs.pushScreen(new MailSendScreen(user));
                break;
        }

        return super.keyChar(key, status, time);
    }
}
