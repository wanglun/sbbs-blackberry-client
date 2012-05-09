package name.wl.bbs.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.microedition.rms.*;

public class Cache
{
    public static void set(String key, String json)
    {
        try {
            RecordStore store = RecordStore.openRecordStore(key, true);
            int count = store.getNumRecords();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream os = new DataOutputStream(baos);

            os.writeUTF(json);

            os.close();

            byte[] data = baos.toByteArray();
            if (count == 0) {
                store.addRecord(data, 0, data.length);
            } else {
                store.setRecord(1, data, 0, data.length);
            }
            store.closeRecordStore();
        } catch (Exception e) {
            Logger.debug(e.toString());
        }
    }

    public static String get(String key)
    {
        String json = null;

        try {
            RecordStore store = RecordStore.openRecordStore(key, true);
            int count = store.getNumRecords();

            if (count == 0)
                return null;

            byte[] data = store.getRecord(1);
            DataInputStream is = new DataInputStream(new ByteArrayInputStream(data));

            json = is.readUTF();

            store.closeRecordStore();
        } catch (Exception e) {
            Logger.debug(e.toString());
        }

        return json;
    }

    public void del(String key)
    {
        try {
            RecordStore store = RecordStore.openRecordStore(key, true);
            int count = store.getNumRecords();

            if (count == 0)
                return;
            else {
                store.deleteRecord(1);
            }

            store.closeRecordStore();
        } catch (Exception e) {
            Logger.debug(e.toString());
        }
    }
}
