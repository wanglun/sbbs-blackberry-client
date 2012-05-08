/* ∞Ê√Êƒ⁄»› */
package name.wl.bbs.app;

import java.util.Vector;
import net.rim.device.api.ui.*;

import name.wl.bbs.hjlp.*;
import name.wl.bbs.ui.*;
import name.wl.bbs.util.*;
import name.wl.bbs.json.*;

public class BoardScreen extends BaseScreen
{
    private Board board;
    private BoardJSON boardJSON;
    private Vector topics;
    private TopicListField list;

    public BoardScreen(Board board)
    {
        this.board = board;
        this.boardJSON = new BoardJSON(board);
        this.boardJSON.load(loadListener);
    }

    public Listener loadListener = new Listener() {
        public void callback(Object o)
        {
            BoardJSON obj = (BoardJSON)o;
            if (obj.getSuccess()) {
                topics = obj.getTopics();
                list = new TopicListField(topics);
                bbs.invokeLater(new Runnable() {
                    public void run() {
                        BoardScreen.this.add(list);
                    }
                });
            } else {
                bbs.invokeLater(new Runnable() {
                    public void run() {
                        alert("load board failed!");
                    }
                });
            }
        }
    };

    protected boolean keyChar(char key, int status, int time)
    {
        switch (key) {
            case 'p':
                bbs.pushScreen(new PostScreen(board));
                break;
        }

        return super.keyChar(key, status, time);
    }
}
