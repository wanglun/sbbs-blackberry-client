package name.wl.bbs.app;

import net.rim.device.api.ui.component.KeywordFilterField;
import net.rim.device.api.collection.util.UnsortedReadableList;
import net.rim.device.api.system.Characters;
import net.rim.device.api.ui.MenuItem;

import name.wl.bbs.json.*;
import name.wl.bbs.util.*;
import name.wl.bbs.ui.*;

public class SelectBoardScreen extends BaseScreen
{
    private KeywordFilterField boardFilter;
    private Listener listener;

    public SelectBoardScreen(Listener listener)
    {
        editable = true;

        this.listener = listener;

        boardFilter = new KeywordFilterField();

        if (bbs.getBoards() == null) {
            new BoardListJSON().load(loadBoardsListener);
        } else {
            addBoardFilter();
        }

        addMenuItem(refreshMenuItem);
    }

    private void addBoardFilter()
    {
        boardFilter.setSourceList(bbs.getBoards(), bbs.getBoards());
        boardFilter.setKeywordField(new SelectEditField("Ñ¡Ôñ°æÃæ: "));
        add(boardFilter);
        setTitle(boardFilter.getKeywordField());
    }

    public Listener loadBoardsListener = new Listener() {
        public void callback(Object o)
        {
            BoardListJSON obj = (BoardListJSON)o;
            if (obj.getSuccess()) {
                bbs.setBoards(new SelectList(obj.getBoards()));
                bbs.invokeLater(new Runnable() {
                    public void run() {
                        addBoardFilter();
                    }
                });
            } else {
                alert(obj.getError(), ALERT_CONFIRM);
            }
        }
    };

    public Listener refreshListener = new Listener() {
        public void callback(Object o)
        {
            BoardListJSON obj = (BoardListJSON)o;
            if (obj.getSuccess()) {
                bbs.setBoards(new SelectList(obj.getBoards()));
                bbs.invokeLater(new Runnable() {
                    public void run() {
                        boardFilter.setSourceList(bbs.getBoards(), bbs.getBoards());
                    }
                });
            } else {
                alert(obj.getError(), ALERT_CONFIRM);
            }
        }
    };

    protected boolean keyChar(char key, int status, int time)
    {
        switch (key) {
            case Characters.ENTER:
                if (boardFilter.getResultListSize() > 0) {
                    String board = (String)boardFilter.getSelectedElement();
                    listener.callback(board.substring(0, board.indexOf('-') - 1));
                    bbs.popScreen(this);
                    return true;
                }
                break;
        }

        return super.keyChar(key, status, time);
    }

    final MenuItem refreshMenuItem = new MenuItem("Refresh", 0, 0)
    {       
        public void run() { 
            if (bbs.getBoards() != null) {
                new BoardListJSON().refresh(refreshListener);
            }
        }
    }; 
}
