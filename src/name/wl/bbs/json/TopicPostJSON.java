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

    public void load(Listener listener)
    {
        Hashtable posts = new Hashtable();
        posts.put("board", this.board.getName());
        posts.put("title", this.title);
        posts.put("content", this.content);
        posts.put("reid", Integer.toString(this.reid));

        if (this.notopten)
            posts.put("notopten", "true");
        if (this.noquote)
            posts.put("noquote", "true");
        if (this.anony)
            posts.put("anony", "true");

        super.load(API, null, posts, listener);
    }

    public void parseContent(final String json)
    {
        if (this.success) {
            try {
                this.topic = Topic.TopicJSON(this.data.getString("topic"));
            } catch (Exception e) {
                Logger.debug("token parse error");
                this.success = false;
            }
        }
    }
}
