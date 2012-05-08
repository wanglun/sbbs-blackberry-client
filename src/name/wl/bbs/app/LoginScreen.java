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
    }

    public void fieldChanged(Field field, int context) {
        if (field == login) {
            if (user.getText().length() == 0 ||
                    passwd.getText().length() == 0) {
                alert("user OR pawwd is empty");
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
                bbs.setToken(obj.getToken());
                bbs.setLoggedIn(true);

                bbs.invokeLater(new Runnable() {
                    public void run() {
                        bbs.popScreen(LoginScreen.this);
                        bbs.pushScreen(new FavScreen());
                    }
                });
            } else {
                bbs.invokeLater(new Runnable() {
                    public void run() {
                        alert("user OR passwd is invalid");
                    }
                });
            }
        }
    };
}
