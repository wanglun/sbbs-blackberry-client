package name.wl.bbs.util;

import net.rim.device.api.system.*;

public class Cache
{
    public static void set(long key, Object obj)
    {
        PersistentObject persist = PersistentStore.getPersistentObject(key);
        persist.setContents(obj);
        persist.commit();
    }

    public static Object get(long key)
    {
        PersistentObject persist = PersistentStore.getPersistentObject(key);
        return persist.getContents();
    }
}
