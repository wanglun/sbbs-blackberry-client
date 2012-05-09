package name.wl.bbs.json;

import java.util.*;
import org.json.me.*;

import name.wl.bbs.http.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;

public class SectionsJSON extends ApiJSON
{
    private static String API = "/sections.json";

    private static boolean CACHE = true;
    private static String KEY = "sbbs_sections";

    /* --����-- */
    /* ��ѡ �Զ�����..��Ϊ�ϲ�Ŀ¼ */
    private boolean up = false;

    /* --����-- */
    private static Vector boards;

    public SectionsJSON()
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
                this.boards = Board.BoardsJSON(this.data.getString("boards"));
            } catch (Exception e) {
                Logger.debug("parse error");
                this.success = false;
            }
        }
    }

    public boolean isParsed()
    {
        if (this.boards != null) {
            this.success = true;
            return true;
        }

        return false;
    }

    public void setCache(String json)
    {
        Cache.set(KEY, json);
    }

    public String getCache()
    {
        return Cache.get(KEY);
    }

    public Vector getBoards()
    {
        return this.boards;
    }
}
