package name.wl.bbs.json;

import java.util.*;
import org.json.me.*;
import net.rim.device.api.ui.UiApplication;

import name.wl.bbs.http.*;
import name.wl.bbs.util.*;

import name.wl.bbs.app.Bbs;

public class ApiJSON
{
    protected static final String BASEAPI = "http://bbs.seu.edu.cn/api";

    protected static final boolean AUTH = true;
    protected static final boolean NOAUTH = false;

    protected JSONObject data = null;

    protected Listener listener = null;

    protected static Bbs bbs = null;

    /* --·µ»Ø-- */
    protected int time;
    protected int cost;
    protected boolean success;
    protected String error;

    public ApiJSON()
    {
        bbs = (Bbs)UiApplication.getUiApplication();
    }

    public void setCache()
    {
    }

    public String getCache()
    {
        return null;
    }

    protected static String getURL(String method)
    {
        Hashtable params = null;
        boolean auth = true;

        return getURL(method, params, auth);
    }

    protected static String getURL(String method, boolean auth)
    {
        Hashtable params = null;

        return getURL(method, params, auth);
    }

    protected static String getURL(String method, Hashtable params)
    {
        boolean auth = true;

        return getURL(method, params, auth);
    }

    protected static String getURL(String method, Hashtable params, boolean auth)
    {
        String s = "";
        if (params != null) {
            s = "?";
            s += URLUTF8Encoder.encodeParams(params);
        }
        if (auth) {
            if (s.equals(""))
                s += "?token=" + bbs.getToken();
            else
                s += "&token=" + bbs.getToken();
        }

        return BASEAPI + method + s;
    }

    protected void load(String api, Listener listener)
    {
        load(api, null, listener, true);
    }

    protected void load(String api, Hashtable gets, Listener listener)
    {
        load(api, gets, listener, true);
    }

    protected void load(String api, Hashtable gets, Listener listener, boolean auth)
    {
        load(api, gets, null, listener, auth);
    }

    protected void load(String api, Hashtable gets, Hashtable posts, Listener listener)
    {
        load(api, gets, posts, listener, true);
    }

    protected void load(String api, Hashtable gets, Hashtable posts, Listener listener, boolean auth)
    {
        this.listener = listener;

        String json = getCache();
        if (json != null) {
            loadContent(json);
            return;
        }

        HTTPRequestThread requestThread;

        if (posts != null)
            requestThread = new HTTPRequestThread(getURL(api, gets, auth), posts);
        else
            requestThread = new HTTPRequestThread(getURL(api, gets, auth));
        requestThread.start(this.requestListener);
    }

    protected Listener requestListener = new Listener()
    {
        public void callback(Object r)
        {
            loadContent(((HTTPRequestThread) r).getHTTPReponseText());
        }
    };

    protected void loadContent(final String jsonString)
    {
        Logger.debug(jsonString);
        try {
            data = new JSONObject(jsonString);
        } catch (Exception e) {
            Logger.debug("EXCEPTION");
            e.printStackTrace();

            this.success = false;

            return;
        }

        if (data == null) {
            this.success = false;

            return;
        }

        try {
            this.success = data.getBoolean("success");
            this.time = data.getInt("time");
            this.cost = data.getInt("cost");

            if (!this.success) {
                this.error = data.getString("error");
            }

        } catch (Exception e) {
            this.success = false;
        }

        parseContent(jsonString);

        if (this.success) {
            setCache();
        }

        callback();
    }

    void callback()
    {
        if (this.listener != null)
            this.listener.callback(this);
    }

    void parseContent(String jsonString)
    {
    }

    public boolean getSuccess()
    {
        return this.success;
    }
}
