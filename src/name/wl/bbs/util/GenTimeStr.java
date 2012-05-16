package name.wl.bbs.util;

import net.rim.device.api.i18n.SimpleDateFormat;

public class GenTimeStr
{
    public static String standard(long time)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // require milliseconds
        return formatter.formatLocal(time * 1000);
    }

    public static String pretty(long time)
    {
        // FIXME
        return standard(time);
    }
}
