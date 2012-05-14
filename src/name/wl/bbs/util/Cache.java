package name.wl.bbs.util;

import net.rim.device.api.system.*;

public class Cache
{
    private static long hashKey(String key)
    {
        return (long)key.hashCode();
    }

    public static void set(String key, String json)
    {
        PersistentObject persist = PersistentStore.getPersistentObject(hashKey(key));
        persist.setContents(json);
        persist.commit();
    }

    public static String get(String key)
    {
        PersistentObject persist = PersistentStore.getPersistentObject(hashKey(key));
        return (String)persist.getContents();
    }

    public static void del(String key)
    {
        PersistentStore.destroyPersistentObject(hashKey(key));
    }
}
