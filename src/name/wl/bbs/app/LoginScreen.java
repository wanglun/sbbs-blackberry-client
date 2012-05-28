package name.wl.bbs.app;

import net.rim.device.api.ui.*;
import name.wl.bbs.ui.*;
import name.wl.bbs.util.*;
import name.wl.bbs.json.*;

public class LoginScreen extends BaseScreen implements FieldChangeListener
{
    private BbsEditField user;
    private BbsPasswordEditField passwd;
    private BbsButtonField login;

    public LoginScreen()
    {
        user = new BbsEditField("user");
        add(user);

        passwd = new BbsPasswordEditField("pass");
        add(passwd);

        login = new BbsButtonField("login");
        login.setChangeListener(this);
        add(login);

        setStatusbarTitle("登录");
    }

    public void fieldChanged(Field field, int context) {
        if (field == login) {
            if (user.getText().length() == 0 ||
                    passwd.getText().length() == 0) {
                alert("用户名/密码不能为空");
            } else {
                LoginJSON login = new LoginJSON(user.getText(), passwd.getText());
                login.load(this.loginListener);
            }
        }
    }

    public Listener loginListener = new Listener() {
        public void callback(Object r)
        {
            LoginJSON obj = (LoginJSON) r;
            if (obj.getSuccess()) {
                bbs.setId(obj.getId());
                bbs.setName(obj.getName());
                bbs.setToken(obj.getToken());
                bbs.setLoggedIn(true);

                bbs.invokeLater(new Runnable() {
                    public void run() {
                        bbs.popScreen(LoginScreen.this);
                        bbs.pushScreen(new MenuScreen());
                    }
                });
            } else {
                alert("用户名/密码错误");
            }
        }
    };
}
