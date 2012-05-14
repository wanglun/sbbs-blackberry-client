package name.wl.bbs.json;

import java.util.*;
import org.json.me.*;

import name.wl.bbs.http.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;

public class HotToptenJSON extends ApiJSON
{
    private static String API = "/hot/topten.json";

    private static boolean CACHE = true;
    private static String KEY = "sbbs_topten";

    /* --·µ»Ø-- */
    private static Vector topics;

    public HotToptenJSON()
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
                this.topics = Topic.HotsJSON(this.data.getString("topics"));
            } catch (Exception e) {
                Logger.debug("parse error");
                this.success = false;
            }
        }
    }

    public boolean isParsed()
    {
        if (this.topics != null) {
            this.success = true;
            return true;
        }

        return false;
    }

    public String getKey()
    {
        return KEY;
    }

    public Vector getTopics()
    {
        return this.topics;
    }
}
