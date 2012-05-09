package name.wl.bbs.app;

import net.rim.device.api.ui.*;
import name.wl.bbs.ui.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;
import name.wl.bbs.json.*;

public class PostScreen extends BaseScreen implements FieldChangeListener
{
    private Topic topic = null;
    private Board board = null;
    
    private BbsEditField title;
    private BbsEditField content;

    private BbsCheckboxField notopten;
    private BbsCheckboxField noquote;
    private BbsCheckboxField anony;

    private BbsButtonField post;

    public PostScreen(Board board)
    {
        this(board, null);
    }

    public PostScreen(Topic topic)
    {
        this(null, topic);
    }

    public PostScreen(Board board, Topic topic)
    {
        this.board = board;
        this.topic = topic;

        title = new BbsEditField("title");
        add(title);

        content = new BbsEditField("content");
        add(content);

        notopten = new BbsCheckboxField("notopten", false);
        add(notopten);

        noquote = new BbsCheckboxField("noquote", false);
        add(noquote);

        anony = new BbsCheckboxField("anony", false);
        add(anony);

        post = new BbsButtonField("post");
        post.setChangeListener(this);
        add(post);
    }

    public void fieldChanged(Field field, int context) {
        if (field == post) {
            String titleStr = title.getText();
            String contentStr = content.getText();
            boolean notoptenBool = notopten.getChecked();
            boolean noquoteBool = noquote.getChecked();
            boolean anonyBool = anony.getChecked();
            if (titleStr.length() == 0 ||
                    contentStr.length() == 0) {
                alert("title OR content is empty");
            } else {
                TopicPostJSON json;
                if (board != null) {
                    if (topic != null)
                        json = new TopicPostJSON(board, titleStr, contentStr, topic.getId(),
                                notoptenBool, noquoteBool, anonyBool);
                    else
                        json = new TopicPostJSON(board, titleStr, contentStr, 0,
                                notoptenBool, noquoteBool, anonyBool);
                } else {
                    json = new TopicPostJSON(new Board(topic.getBoard()), titleStr, contentStr, topic.getId(),
                            notoptenBool, noquoteBool, anonyBool);
                }
                json.load(this.postListener);
            }
        }
    }

    public Listener postListener = new Listener() {
        public void callback(Object r)
        {
            TopicPostJSON obj = (TopicPostJSON) r;
            if (obj.getSuccess()) {
                alert("post success");
            } else {
                alert("post failed");
            }
        }
    };
}
