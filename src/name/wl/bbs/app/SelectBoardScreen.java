package name.wl.bbs.app;

import net.rim.device.api.ui.component.KeywordFilterField;
import net.rim.device.api.collection.util.UnsortedReadableList;
import net.rim.device.api.system.Characters;

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
        boardFilter.setSourceList(bbs.getBoards(), bbs.getBoards());
        boardFilter.setKeywordField(new SelectEditField("—°‘Ò∞Ê√Ê: "));

        add(boardFilter);

        setTitle(boardFilter.getKeywordField());
    }

    protected boolean keyChar(char key, int status, int time)
    {
        switch (key) {
            case Characters.ENTER:
                String board = (String)boardFilter.getSelectedElement();
                listener.callback(board.substring(0, board.indexOf('-') - 1));
                bbs.popScreen(this);
                return true;
        }

        return super.keyChar(key, status, time);
    }
}
