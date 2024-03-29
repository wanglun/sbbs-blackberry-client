package name.wl.bbs.json;

import java.util.*;
import org.json.me.*;

import name.wl.bbs.http.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;

public class MailboxJSON extends ApiJSON
{
    private static String API = "/mailbox/get.json";

    private static String KEY = "sbbs_mailbox";

    /* --参数-- */
    public static final int INBOX = 0;
    public static final int SENT = 1;
    public static final int DELETED = 2;
    /* 类型 */
    private int type;

    /* 可选 起始下标 */
    private int start;

    /* 可选 最多话题个数 */
    private int limit;

    /* --返回-- */
    /* 信件 */
    private Vector mails;

    public MailboxJSON(int type)
    {
        this(type, 0, 0);
    }
    public MailboxJSON(int type, int start)
    {
        this(type, start, 0);
    }
    public MailboxJSON(int type, int start, int limit)
    {
        this.type = type;
        this.start = start;

        if (limit > 0)
            this.limit = limit;
        else
            this.limit = bbs.getSettings().getLoadTopics();

        this.mails = null;
    }

    static public String getTypeString(int type)
    {
        switch (type) {
            case INBOX:
                return "收件箱";
            case SENT:
                return "发件箱";
            case DELETED:
                return "垃圾箱";
        }
        return "";
    }

    public void load(Listener listener)
    {
        Hashtable gets = new Hashtable();
        gets.put("type", Integer.toString(this.type));
        gets.put("start", Integer.toString(this.start));
        gets.put("limit", Integer.toString(this.limit));

        super.load(API, gets, listener);
    }

    public void refresh(Listener listener)
    {
        this.needRefresh = true;
        load(listener);
    }

    public void parseContent(final String json)
    {
        if (this.success) {
            try {
                this.mails = Mail.TopicsJSON(type, this.data.getString("mails"));
            } catch (Exception e) {
                Logger.debug("sections parse error");
                this.success = false;
            }
        }
    }

    public String getKey()
    {
        if (start == 0) {
            return KEY + this.type;
        } else {
            return null;
        }
    }

    public void delCache()
    {
        Cache.del(getKey());
    }

    public Vector getMails()
    {
        return this.mails;
    }
}
