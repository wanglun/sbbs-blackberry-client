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

    public void load(Listener listener)
    {
        Hashtable posts = new Hashtable();
        posts.put("type", Integer.toString(this.type));
        posts.put("id", Integer.toString(this.id));

        super.load(API, null, posts, listener);
    }

    public void parseContent(final String json)
    {
        if (this.success) {
            try {
                this.result = this.data.getInt("result");
            } catch (Exception e) {
                Logger.debug("token parse error");
                this.success = false;
            }
        }
    }
}
