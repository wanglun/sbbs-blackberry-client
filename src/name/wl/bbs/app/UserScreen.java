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

    private BbsLabelField id;
    private BbsLabelField username;

    public UserScreen(User user)
    {
        this.user = user;
        this.userJSON = new UserJSON(user);

        alert("查询中", ALERT_WARNING);
        this.userJSON.load(loadListener);

        id = new BbsLabelField(user.getId());
        add(id);

        username = new BbsLabelField(user.getName());
        add(username);


        setStatusbarTitle("查看用户");
    }

    public void update()
    {
        this.username.setText(this.user.getName());
        alert("加载完成");
    }

    public Listener loadListener = new Listener() {
        public void callback(Object o)
        {
            UserJSON obj = (UserJSON)o;
            if (obj.getSuccess()) {
                UserScreen.this.user = obj.getUser();
                bbs.invokeLater(new Runnable() {
                    public void run() {
                        UserScreen.this.update();
                    }
                });
                alert("加载完成");
            } else {
                alert(obj.getError(), ALERT_ERROR);
            }
        }
    };

    protected boolean keyChar(char key, int status, int time)
    {
        switch (key) {
            case '?':
                bbs.pushScreen(new HelpScreen(HelpScreen.TYPE_USER));
                return true;
            case 'q':
                bbs.popScreen(this);
                return true;
            case 'm':
                bbs.pushScreen(new MailSendScreen(user));
                return true;
        }

        return super.keyChar(key, status, time);
    }
}
