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
                this.name = this.data.getString("name");
                this.token = this.data.getString("token");
            } catch (Exception e) {
                Logger.debug("token parse error");
                this.success = false;
            }
        }
    }

    public String getToken()
    {
        return this.token;
    }
}
