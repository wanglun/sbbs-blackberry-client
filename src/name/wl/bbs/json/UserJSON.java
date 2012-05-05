package name.wl.bbs.json;

import java.util.*;
import org.json.me.*;

import name.wl.bbs.http.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;

public class UserJSON extends ApiJSON
{
    /* "/user/:id.json" */
    private static String API = "/user/";

    /* --²ÎÊý-- */
    private String id;

    /* --·µ»Ø-- */
    private User user;

    public UserJSON(String id)
    {
        this.id = id;
    }

    public void load(Listener listener)
    {
        super.load(API + this.id + ".json", listener);
    }

    public void parseContent(final String json)
    {
        if (this.success) {
            try {
                this.user = User.UserJSON(this.data.getString("user"));
            } catch (Exception e) {
                Logger.debug("parse error");
                this.success = false;
            }
        }
    }
}
