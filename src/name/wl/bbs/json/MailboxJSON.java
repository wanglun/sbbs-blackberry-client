package name.wl.bbs.json;

import java.util.*;
import org.json.me.*;

import name.wl.bbs.http.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;

public class MailboxJSON extends ApiJSON
{
    private static String API = "/mailbox/get.json";

    /* --参数-- */
    private static int INBOX = 0;
    private static int SENT = 1;
    private static int DELETED = 2;
    /* 类型 */
    private int type;

    /* 可选 起始下标 */
    private int start;

    private static int LIMIT = 10;
    /* 可选 最多话题个数 */
    private int limit;

    /* --返回-- */
    /* 信件 */
    private Vector mails;

    public MailboxJSON(int type)
    {
        this(type, 0, LIMIT);
    }
    public MailboxJSON(int type, int start)
    {
        this(type, start, LIMIT);
    }
    public MailboxJSON(int type, int start, int limit)
    {
        this.type = type;
        this.start = start;
        this.limit = limit;

        this.mails = null;
    }

    public void load()
    {
        Hashtable params = new Hashtable();
        params.put("type", Integer.toString(this.type));
        params.put("start", Integer.toString(this.start));
        params.put("limit", Integer.toString(this.limit));

        HTTPRequestThread requestThread = new HTTPRequestThread(getURL(API, AUTH));
        Event.observe(requestThread, "LOADED", this.requestListener);
        requestThread.start();
    }

    public void loadContent(final String json)
    {
        super.loadContent(json);

        if (this.success) {
            try {
                this.mails = Topic.TopicsJSON(this.data.getString("mails"));
            } catch (Exception e) {
                Logger.debug("sections parse error");
                this.success = false;
            }
        }

        Event.trigger(this, "LOADED");
    }
}
