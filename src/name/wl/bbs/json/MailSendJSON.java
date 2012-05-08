package name.wl.bbs.json;

import java.util.*;
import org.json.me.*;

import name.wl.bbs.http.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;

public class MailSendJSON extends ApiJSON
{
    private static String API = "/mail/send.json";

    /* --²ÎÊý-- */
    private User user;
    private String title;
    private String content;
    private int reid;
    private boolean noquote;

    /* --·µ»Ø-- */
    private int result;

    public MailSendJSON(User user, String title, String content)
    {
        this(user, title, content, 0, false);
    }
    public MailSendJSON(User user, String title, String content, int reid)
    {
        this(user, title, content, reid, false);
    }
    public MailSendJSON(User user, String title, String content, int reid, boolean noquote)
    {
        this.user = user;
        this.title = title;
        this.content = content;
        this.reid = reid;
        this.noquote = noquote;
    }

    public void load(Listener listener)
    {
        Hashtable posts = new Hashtable();
        posts.put("user", this.user.getId());
        posts.put("title", this.title);
        posts.put("content", this.content);
        posts.put("reid", Integer.toString(this.reid));

        if (this.noquote) {
            posts.put("noquote", "true");
        }

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

    public int getResult() {
        return this.result;
    }
}
