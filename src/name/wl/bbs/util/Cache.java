package name.wl.bbs.util;

import net.rim.device.api.system.*;

public class Cache
{
    private static long hashKey(String key)
    {
        return (long)key.hashCode();
    }

    public static void set(String key, Object obj)
    {
        PersistentObject persist = PersistentStore.getPersistentObject(hashKey(key));
        persist.setContents(obj);
        persist.commit();
    }

    public static Object get(String key)
    {
        PersistentObject persist = PersistentStore.getPersistentObject(hashKey(key));
        return persist.getContents();
    }

    public static void del(String key)
    {
        PersistentStore.destroyPersistentObject(hashKey(key));
    }
}
