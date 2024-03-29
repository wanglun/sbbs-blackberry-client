package name.wl.bbs.json;

import java.util.*;
import org.json.me.*;

import name.wl.bbs.http.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;

public class LoginJSON extends ApiJSON
{
    private static final String API = "/token.json";

    private static boolean CACHE = true;
    private static String KEY = "sbbs_token";

    /* --����-- */
    private String user = null;
    private String pass = null;

    /* --����-- */
    private String id = null;
    private String name = null;
    private String token = null;

    public LoginJSON()
    {
    }

    public LoginJSON(String user, String pass)
    {
        this.user = user;
        this.pass = pass;
    }

    public void load(Listener listener)
    {
        Hashtable gets = new Hashtable();
        gets.put("user", this.user);
        gets.put("pass", this.pass);

        super.load(API, gets, listener);
    }

    public void parseContent(final String json)
    {
        if (this.success) {
            try {
                this.id = this.data.getString("id");
                this.name = this.data.getString("name");
                this.token = this.data.getString("token");
            } catch (Exception e) {
                Logger.debug("token parse error");
                this.success = false;
            }
        }
    }

    public String getKey()
    {
        return KEY;
    }

    public void delCache()
    {
        Cache.del(getKey());
    }

    public String getToken()
    {
        return this.token;
    }

    public String getId()
    {
        return this.id;
    }

    public String getName()
    {
        return this.name;
    }
}
