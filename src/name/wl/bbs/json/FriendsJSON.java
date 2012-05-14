package name.wl.bbs.json;

import java.util.*;
import org.json.me.*;

import name.wl.bbs.http.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;

public class FriendsJSON extends ApiJSON
{
    private static String API = "/friends.json";

    private static boolean CACHE = true;
    private static String KEY = "sbbs_friends";

    /* --·µ»Ø-- */
    protected static Vector friends;

    public FriendsJSON()
    {
    }

    public void load(Listener listener)
    {
        super.load(API, listener);
    }

    public void refresh(Listener listener)
    {
        this.needRefresh = true;
        load(listener);
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

    public boolean isParsed()
    {
        if (this.friends != null) {
            this.success = true;
            return true;
        }

        return false;
    }

    public String getKey()
    {
        return KEY;
    }

    public static void delCache()
    {
        friends = null;
        Cache.del(KEY);
    }

    public Vector getFriends()
    {
        return this.friends;
    }
}
