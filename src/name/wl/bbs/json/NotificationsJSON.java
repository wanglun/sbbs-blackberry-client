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

    public void load(Listener listener)
    {
        super.load(API, listener);
    }

    public void parseContent(final String json)
    {
        if (this.success) {
            try {
                if (this.data.has("mails"))
                    this.mails = Mail.NotificationsJSON(MailboxJSON.INBOX, this.data.getString("mails"));
                if (this.data.has("ats"))
                    this.ats = Topic.NotificationsJSON(this.data.getString("ats"));
                if (this.data.has("replies"))
                    this.replies = Topic.NotificationsJSON(this.data.getString("replies"));
            } catch (Exception e) {
                Logger.debug("notifications parse error");
                this.success = false;
            }
        }
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
