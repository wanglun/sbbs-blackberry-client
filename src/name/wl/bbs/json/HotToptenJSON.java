package name.wl.bbs.json;

import java.util.*;
import org.json.me.*;

import name.wl.bbs.http.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;

public class HotToptenJSON extends ApiJSON
{
    private static String API = "/hot/topten.json";

    /* --·µ»Ø-- */
    private Vector topics;

    public HotToptenJSON()
    {
        this.topics = null;
    }

    public void load(Listener listener)
    {
        super.load(API, listener);
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
}
