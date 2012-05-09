package name.wl.bbs.app;

import java.util.Vector;
import net.rim.device.api.ui.*;

import name.wl.bbs.hjlp.*;
import name.wl.bbs.ui.*;
import name.wl.bbs.util.*;
import name.wl.bbs.json.*;

public class NotificationScreen extends BaseScreen
{
    private NotificationsJSON notificationsJSON;
    private Vector topics;
    private TopicListField list;

    public static final int MAILS = 0; 
    public static final int ATS = 1;
    public static final int REPLIES = 2;

    private int type;

    public NotificationScreen(int type)
    {
        this.type = type;

        this.notificationsJSON = new NotificationsJSON();
        this.notificationsJSON.load(loadListener);
    }

    public Listener loadListener = new Listener() {
        public void callback(Object o)
        {
            NotificationsJSON obj = (NotificationsJSON)o;
            if (obj.getSuccess()) {
                switch (type) {
                    case MAILS:
                        topics = obj.getMails();
                        break;
                    case ATS:
                        topics = obj.getAts();
                        break;
                    case REPLIES:
                        topics = obj.getReplies();
                        break;
                }
                list = new TopicListField(topics, topicListener);
                bbs.invokeLater(new Runnable() {
                    public void run() {
                        NotificationScreen.this.add(list);
                    }
                });
            } else {
                alert("load notifications failed!");
            }
        }
    };

    public Listener topicListener = new Listener() {
        public void callback(Object o)
        {
            switch (type) {
                case MAILS:
                    bbs.pushScreen(new MailScreen((Mail)o));
                    break;
                case ATS:
                    bbs.pushScreen(new ThreadScreen((Topic)o));
                    break;
                case REPLIES:
                    bbs.pushScreen(new ThreadScreen((Topic)o));
                    break;
            }
        }
    };

    protected boolean keyChar(char key, int status, int time)
    {
        switch (key) {
            case 'p':
                break;
        }

        return super.keyChar(key, status, time);
    }
}
