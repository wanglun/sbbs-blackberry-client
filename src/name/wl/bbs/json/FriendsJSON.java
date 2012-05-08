package name.wl.bbs.json;

import java.util.*;
import org.json.me.*;

import name.wl.bbs.http.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;

public class FriendsJSON extends ApiJSON
{
    private static String API = "/friends.json";
    private static String APIALL = "/friends/all.json";

    private boolean all = false;

    /* --·µ»Ø-- */
    private Vector friends;

    public FriendsJSON()
    {
        this(false);
    }

    public FriendsJSON(boolean all)
    {
        this.all = all;
        this.friends = null;
    }

    public void load(Listener listener)
    {
        if (this.all)
            super.load(APIALL, listener);
        else
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

    public Vector getFriends()
    {
        return this.friends;
    }
}
