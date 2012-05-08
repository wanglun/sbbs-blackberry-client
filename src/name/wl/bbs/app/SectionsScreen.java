package name.wl.bbs.app;

import java.util.*;
import net.rim.device.api.ui.*;
import name.wl.bbs.ui.*;
import name.wl.bbs.util.*;
import name.wl.bbs.hjlp.*;
import name.wl.bbs.json.SectionsJSON;

public class SectionsScreen extends BaseScreen
{
    private SectionsJSON sections;
    private Vector boards;
    private SectionListField list;

    public SectionsScreen()
    {
        sections = new SectionsJSON();

        sections.load(loadListener);
    }

    public void close()
    {
        if (this.list.setPrevious()) {
        } else {
            super.close();
        }
    }

    public Listener loadListener = new Listener() {
        public void callback(Object o)
        {
            SectionsJSON obj = (SectionsJSON)o;
            if (obj.getSuccess()) {
                boards = obj.getBoards();
                list = new SectionListField(boards);
                bbs.invokeLater(new Runnable() {
                    public void run() {
                        SectionsScreen.this.add(list);
                    }
                });
            } else {
                bbs.invokeLater(new Runnable() {
                    public void run() {
                        alert("load sections failed!");
                    }
                });
            }
        }
    };
}
