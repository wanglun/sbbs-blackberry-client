package name.wl.bbs.json;

import java.util.*;
import org.json.me.*;

import name.wl.bbs.http.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;

public class TopicPostJSON extends ApiJSON
{
    private static String API = "/topic/post.json";

    /* --参数-- */
    private Board board;
    private String title;
    private String content;
    private int reid;
    private boolean notopten;
    private boolean noquote;
    private boolean anony;

    /* --返回-- */
    /* 发文成功后 返回的话题 */
    private Topic topic;

    public TopicPostJSON(Board board, String title, String content)
    {
        this(board, title, content, 0, false, false, false);
    }
    public TopicPostJSON(Board board, String title, String content, int reid)
    {
        this(board, title, content, reid, false, false, false);
    }
    public TopicPostJSON(Board board, String title, String content,
            int reid, boolean notopten, boolean noquote, boolean anony)
    {
        this.board = board;
        this.title = title;
        this.content = content;
        this.reid = reid;
        this.notopten = notopten;
        this.noquote = noquote;
        this.anony = anony;
    }

    public void load()
    {
        Hashtable params = new Hashtable();
        params.put("board", this.board.getName());
        params.put("title", this.title);
        params.put("content", this.content);
        params.put("reid", Integer.toString(this.reid));
        params.put("notopten", new Boolean(this.notopten).toString());
        params.put("noquote", new Boolean(this.noquote).toString());
        params.put("anony", new Boolean(this.anony).toString());

        HTTPRequestThread requestThread = new HTTPRequestThread(getURL(API, AUTH), params);
        Event.observe(requestThread, "LOADED", this.requestListener);
        requestThread.start();
    }

    public void loadContent(final String json)
    {
        Logger.debug("loadContent");
        super.loadContent(json);

        if (this.success) {
            try {
                // this.topic
            } catch (Exception e) {
                Logger.debug("token parse error");
                this.success = false;
            }
        }

        Event.trigger(this, "LOADED");
    }
}
