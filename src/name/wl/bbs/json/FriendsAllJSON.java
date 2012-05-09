package name.wl.bbs.json;

import java.util.*;
import org.json.me.*;

import name.wl.bbs.http.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;

public class FriendsAllJSON extends FriendsJSON
{
    private static String API = "/friends/all.json";

    private static boolean CACHE = true;
    private static String KEY = "sbbs_friends_all";

    public String getKey()
    {
        return KEY;
    }
}
