package name.wl.bbs.app;

import java.util.Vector;
import net.rim.device.api.ui.*;

import name.wl.bbs.hjlp.*;
import name.wl.bbs.ui.*;
import name.wl.bbs.util.*;
import name.wl.bbs.json.*;

public class MailScreen extends BaseScreen
{
    private Mail mail;
    private MailJSON mailJSON;

    public MailScreen(Mail mail)
    {
        this.mail = mail;
        this.mailJSON = new MailJSON(mail);
        this.mailJSON.load(loadListener);
    }

    public Listener loadListener = new Listener() {
        public void callback(Object o)
        {
            MailJSON obj = (MailJSON)o;
            if (obj.getSuccess()) {
                mail = obj.getMail();
                bbs.invokeLater(new Runnable() {
                    public void run() {
                        alert(mail.getContent());
                    }
                });
            } else {
                bbs.invokeLater(new Runnable() {
                    public void run() {
                        alert("load board failed!");
                    }
                });
            }
        }
    };

    protected boolean keyChar(char key, int status, int time)
    {
        switch (key) {
            case 'r':
                break;
        }

        return super.keyChar(key, status, time);
    }
}
