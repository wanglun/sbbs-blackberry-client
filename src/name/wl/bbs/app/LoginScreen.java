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
        editable = true;

        user = new BbsEditField("�û�����");
        add(user);

        passwd = new BbsPasswordEditField("���룺");
        add(passwd);

        login = new BbsButtonField("��¼");
        login.setChangeListener(this);
        add(login);

        setStatusbarTitle("��¼");
    }

    public void fieldChanged(Field field, int context) {
        if (field == login) {
            if (user.getText().length() == 0 ||
                    passwd.getText().length() == 0) {
                alert("�û���/���벻��Ϊ��", ALERT_ERROR);
            } else {
                alert("��¼��", ALERT_WARNING);
                new LoginJSON(user.getText(), passwd.getText()).load(this.loginListener);
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
                alert(obj.getError(), ALERT_ERROR);
            }
        }
    };
}
