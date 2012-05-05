package name.wl.bbs.json;

import java.util.*;
import org.json.me.*;

import name.wl.bbs.http.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;

public class ClearNotificationsJSON extends ApiJSON
{
    private static String API = "/clear_notifications.json";

    public ClearNotificationsJSON()
    {
    }

    public void load(Listener listener)
    {
        super.load(API, listener);
    }

    public void parseContent(final String json)
    {
    }
}
