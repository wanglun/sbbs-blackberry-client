package name.wl.bbs.json;

import java.util.*;
import org.json.me.*;

import name.wl.bbs.http.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;

public class FriendsJSON extends ApiJSON
{
    private static String API = "/friends.json";

    /* --·µ»Ø-- */
    private Vector friends;

    public FriendsJSON()
    {
        this.friends = null;
    }

    public void load(Listener listener)
    {
        super.load(API, listener);
    }

    public void parseContent(final String json)
    {
        if (this.success) {
            try {
                this.friends = Friend.UsersJSON(this.data.getString("friends"));
            } catch (Exception e) {
                Logger.debug("parse error");
                this.success = false;
            }
        }
    }
}
