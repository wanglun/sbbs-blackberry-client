package name.wl.bbs.app;

import java.util.*;

import name.wl.bbs.json.*;
import name.wl.bbs.util.*;
import net.rim.device.api.system.Alert;
import net.rim.device.api.system.LED;

public class NotificationsTask extends TimerTask
{
    private static Vector mails = null;
    private static Vector ats = null;
    private static Vector replies = null;
    private Listener successListener = null;

    public NotificationsTask()
    {
    }

    public NotificationsTask(Listener listener)
    {
        successListener = listener;
    }

    public void run()
    {
        new NotificationsJSON().load(loadListener);
    }

    public Listener loadListener = new Listener() {
        public void callback(Object o)
        {
            NotificationsJSON obj = (NotificationsJSON)o;
            if (obj.getSuccess()) {
                mails = obj.getMails();
                ats = obj.getAts();
                replies = obj.getReplies();

                if (getCount() > 0) {
                    Alert.startVibrate(300);

                    LED.setConfiguration(LED.LED_TYPE_STATUS, 500, 500, LED.BRIGHTNESS_50);
                    LED.setState(LED.STATE_BLINKING);
                } else {
                    LED.setState(LED.STATE_OFF);
                }

                if (successListener != null)
                    successListener.callback(this);
            } else {
                LED.setState(LED.STATE_OFF);
            }
        }
    };

    public static void setMails(Vector topics)
    {
        mails = topics;
    }
    public static Vector getMails()
    {
        return mails;
    }

    public static void setAts(Vector topics)
    {
        ats = topics;
    }
    public static Vector getAts()
    {
        return ats;
    }

    public static void setReplies(Vector topics)
    {
        replies = topics;
    }
    public static Vector getReplies()
    {
        return replies;
    }

    public static int getMailsCount()
    {
        return (mails == null) ? 0 : mails.size();
    }

    public static int getAtsCount()
    {
        return (ats == null) ? 0 : ats.size();
    }

    public static int getRepliesCount()
    {
        return (replies == null) ? 0 : replies.size();
    }

    public static int getCount()
    {
        return getMailsCount() + getAtsCount() + getRepliesCount();
    }
}
