package name.wl.bbs.json;

import java.util.*;
import org.json.me.*;

import name.wl.bbs.http.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;

public class HotSectionJSON extends ApiJSON
{
    private static String API = "/hot/section.json";

    /* --²ÎÊý-- */
    private int section;

    /* --·µ»Ø-- */
    private Vector topics;

    public HotSectionJSON(int section)
    {
        this.section = section;
        this.topics = null;
    }

    public void load(Listener listener)
    {
        Hashtable gets = new Hashtable();
        gets.put("section", Integer.toString(this.section));

        super.load(API, gets, listener);
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
