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
    private Mail mail;

    public MailJSON(Mail mail)
    {
        this(mail.getType(), mail.getId());
    }

    public MailJSON(int type, int id)
    {
        this.type = type;
        this.id = id;
    }

    public void load(Listener listener)
    {
        Hashtable gets = new Hashtable();
        gets.put("type", Integer.toString(this.type));
        gets.put("id", Integer.toString(this.id));

        super.load(API, gets, listener);
    }

    public void parseContent(final String json)
    {
        if (this.success) {
            try {
                this.mail = Mail.TopicJSON(this.type, this.data.getString("mail"));
            } catch (Exception e) {
                Logger.debug("parse error");
                this.success = false;
            }
        }
    }

    public Mail getMail()
    {
        return this.mail;
    }
}
