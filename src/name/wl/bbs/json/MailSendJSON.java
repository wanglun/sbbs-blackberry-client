package name.wl.bbs.json;

import java.util.Hashtable;
import org.json.me.JSONArray;
import org.json.me.JSONObject;

import name.wl.bbs.http.HTTPRequestThread;
import name.wl.bbs.json.ApiJSON;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;

public class MailSendJSON extends ApiJSON
{
    private static String API = "/mail/send.json";

    private User user;
    private String title;
    private String content;
    private int reid;
    private boolean noquote;

    /* ·µ»Ø */
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

    public void load()
    {
        Hashtable params = new Hashtable();
        params.put("user", this.user.getId());
        params.put("title", this.title);
        params.put("content", this.content);
        params.put("reid", Integer.toString(this.reid));
        params.put("noquote", new Boolean(this.noquote).toString());

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
