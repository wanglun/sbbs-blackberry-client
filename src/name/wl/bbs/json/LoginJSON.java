package name.wl.bbs.json;

import java.util.Hashtable;
import org.json.me.JSONArray;
import org.json.me.JSONObject;

import name.wl.bbs.http.HTTPRequestThread;
import name.wl.bbs.json.ApiJSON;
import name.wl.bbs.util.*;

public class LoginJSON extends ApiJSON
{
    private static String API = "/token.json";

    /* --²ÎÊý-- */
    private String user = null;
    private String pass = null;

    /* --·µ»Ø-- */
    private String token = null;
    private String name = null;

    public LoginJSON(String user, String pass)
    {
        this.user = user;
        this.pass = pass;
    }

    public void load()
    {
        Hashtable params = new Hashtable();
        params.put("user", this.user);
        params.put("pass", this.pass);

        HTTPRequestThread requestThread = new HTTPRequestThread(getURL(API, params));
        Event.observe(requestThread, "LOADED", this.requestListener);
        requestThread.start();
    }

    public void loadContent(final String json)
    {
        Logger.debug("loadContent");
        super.loadContent(json);

        if (this.success) {
            try {
                this.name = this.data.getString("name");
                this.token = this.data.getString("token");
            } catch (Exception e) {
                Logger.debug("token parse error");
                this.success = false;
            }
        }

        Event.trigger(this, "LOADED");
    }

    public String getToken()
    {
        return this.token;
    }
}
