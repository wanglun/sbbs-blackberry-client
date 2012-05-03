package name.wl.bbs.json;

import java.util.*;
import org.json.me.*;

import name.wl.bbs.http.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;

public class MailDeleteJSON extends ApiJSON
{
    private static String API = "/mail/delete.json";

    /* --²ÎÊý-- */
    /* MailboxJSON.INBOX etc */
    private int type;

    private int id;

    /* --·µ»Ø-- */
    private int result;

    public MailDeleteJSON(int type, int id)
    {
        this.type = type;
        this.id = id;
    }

    public void load()
    {
        Hashtable params = new Hashtable();
        params.put("type", Integer.toString(this.type));
        params.put("id", Integer.toString(this.id));

        HTTPRequestThread requestThread = new HTTPRequestThread(getURL(API, AUTH), params);
        Event.observe(requestThread, "LOADED", this.requestListener);
        requestThread.start();
    }

    public void loadContent(final String json)
    {
        Logger.debug("loadContent");
        super.loadContent(json);

        if (this.success) {
            try {
                this.result = this.data.getInt("result");
            } catch (Exception e) {
                Logger.debug("token parse error");
                this.success = false;
            }
        }

        Event.trigger(this, "LOADED");
    }
}
