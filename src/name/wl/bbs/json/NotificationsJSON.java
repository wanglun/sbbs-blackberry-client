package name.wl.bbs.json;

import java.util.*;
import org.json.me.*;

import name.wl.bbs.http.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;

public class NotificationsJSON extends ApiJSON
{
    private static String API = "/notifications.json";

    /* --·µ»Ø-- */
    private Vector mails;
    private Vector ats;
    private Vector replies;

    public NotificationsJSON()
    {
        this.mails = null;
        this.ats = null;
        this.replies = null;
    }

    public void load()
    {
        HTTPRequestThread requestThread = new HTTPRequestThread(getURL(API, AUTH));
        Event.observe(requestThread, "LOADED", this.requestListener);
        requestThread.start();
    }

    public void loadContent(final String json)
    {
        Logger.debug("loadContent");
        super.loadContent(json);

        if (this.success) {
            try {
                this.mails = Topic.MailsJSON(this.data.getString("mails"));
                this.ats = Topic.NotificationsJSON(this.data.getString("ats"));
                this.replies = Topic.NotificationsJSON(this.data.getString("replies"));
            } catch (Exception e) {
                Logger.debug("notifications parse error");
                this.success = false;
            }
        }

        Event.trigger(this, "LOADED");
    }

    public Vector getMails()
    {
        return mails;
    }
    public Vector getAts()
    {
        return ats;
    }
    public Vector getReplies()
    {
        return replies;
    }
}
