package name.wl.bbs.json;

import java.util.*;
import org.json.me.*;

import name.wl.bbs.http.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;

public class MailJSON extends ApiJSON
{
    private static String API = "/mail/get.json";

    /* --²ÎÊý-- */
    /* MailboxJSON.INBOX etc */
    private int type;

    private int id;

    /* --·µ»Ø-- */
    private Topic mail;

    public MailJSON(int type, int id)
    {
        this.type = type;
        this.id = id;
    }

    public void load()
    {
        Hashtable params = new Hashtable();
        params.put("type", Integer.toString(this.type));
        params.put("id", Integer.toString(this.id));

        HTTPRequestThread requestThread = new HTTPRequestThread(getURL(API, params, AUTH));
        Event.observe(requestThread, "LOADED", this.requestListener);
        requestThread.start();
    }

    public void loadContent(final String json)
    {
        Logger.debug("loadContent");
        super.loadContent(json);

        if (this.success) {
            try {
                this.mail = Topic.TopicJSON(this.data.getString("mail"));
            } catch (Exception e) {
                Logger.debug("parse error");
                this.success = false;
            }
        }

        Event.trigger(this, "LOADED");
    }
}
