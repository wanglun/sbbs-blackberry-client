package name.wl.bbs.app;

import java.util.*;
import name.wl.bbs.util.*;

public class Settings
{
    private Hashtable settings;

    private static final int updateDelayDefault = 10;
    private static final int loadTopicsDefault = 10;

    private static final String updateDelay = "updateDelay";
    private static final String loadTopics = "loadTopics";

    private static final String KEY = "bbs_settings";

    public Settings()
    {
        Hashtable tmp = (Hashtable)Cache.get(KEY);
        if (tmp == null) {
            settings = new Hashtable();
            settings.put(updateDelay, new Integer(updateDelayDefault));
            settings.put(loadTopics, new Integer(loadTopicsDefault));
        } else {
            settings = tmp;
        }
    }

    public void setUpdateDelay(int i)
    {
        this.settings.put(updateDelay, new Integer(i));
        store();
    }

    public void setLoadTopics(int i)
    {
        this.settings.put(loadTopics, new Integer(i));
        store();
    }

    public int getUpdateDelay()
    {
        if (this.settings.containsKey(updateDelay)) {
            Integer n = (Integer)this.settings.get(updateDelay);
            return n.intValue();
        }

        return updateDelayDefault;
    }

    public int getLoadTopics()
    {
        if (this.settings.containsKey(loadTopics)) {
            Integer n = (Integer)this.settings.get(loadTopics);
            return n.intValue();
        }

        return loadTopicsDefault;
    }

    public void store()
    {
        Cache.set(KEY, this.settings);
    }
}
