package name.wl.bbs.json;

import java.util.*;
import org.json.me.*;

import name.wl.bbs.http.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;

public class TopicJSON extends ApiJSON
{
    /* "/topic/:board/:id.json?limit=2" */
    private static String API = "/topic/";

    /* --����-- */
    private Board board;

    private int id;

    /* ��ѡ �Ƿ�������� */
    private boolean raw = false;

    /* ��ѡ ��ʼ�±� */
    private int start;

    private static int LIMIT = 10;
    /* ��ѡ ��໰����� */
    private int limit;

    /* --����-- */
    private Vector topics;

    public TopicJSON(Board board)
    {
        this(board, false, 0, LIMIT);
    }
    public TopicJSON(Board board, boolean raw)
    {
        this(board, raw, 0, LIMIT);
    }
    public TopicJSON(Board board, boolean raw, int start)
    {
        this(board, raw, start, LIMIT);
    }
    public TopicJSON(Board board, boolean raw, int start, int limit)
    {
        this.board = board;
        this.raw = raw;
        this.start = start;
        this.limit = limit;
    }

    public void load()
    {
        Hashtable params = new Hashtable();
        params.put("raw", new Boolean(raw).toString());
        params.put("start", Integer.toString(this.start));
        params.put("limit", Integer.toString(this.limit));

        HTTPRequestThread requestThread = new HTTPRequestThread(
                getURL(API + this.board.getName() + "/" + this.id + ".json", params, AUTH));
        Event.observe(requestThread, "LOADED", this.requestListener);
        requestThread.start();
    }

    public void loadContent(final String json)
    {
        Logger.debug("loadContent");
        super.loadContent(json);

        if (this.success) {
            try {
                /* parse the json */
            } catch (Exception e) {
                Logger.debug("parse error");
                this.success = false;
            }
        }

        Event.trigger(this, "LOADED");
    }
}
