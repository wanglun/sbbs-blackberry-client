package name.wl.bbs.app;

import net.rim.device.api.ui.*;
import name.wl.bbs.ui.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;
import name.wl.bbs.json.*;

public class UserQueryScreen extends BaseScreen implements FieldChangeListener
{
    private BbsEditField user;

    private BbsButtonField query;

    public UserQueryScreen()
    {
        user = new BbsEditField("user");
        add(user);

        query = new BbsButtonField("query");
        query.setChangeListener(this);
        add(query);

        setStatusbarTitle("查询用户");
    }

    public void fieldChanged(Field field, int context) {
        if (field == query) {
            String userStr = user.getText();
            if (userStr.length() == 0) {
                alert("用户名不为空", ALERT_ERROR);
            } else {
                bbs.popScreen(this);
                bbs.pushScreen(new UserScreen(new User(userStr)));
            }
        }
    }
}
