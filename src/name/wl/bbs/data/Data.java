package name.wl.bbs.data;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.microedition.rms.*;

import name.wl.bbs.util.*;

class Data
{
    protected static final String NAME = "";

    public Data()
    {
    }

    public void store()
    {
        try {
            RecordStore store = RecordStore.openRecordStore(NAME, true);
            int count = store.getNumRecords();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream os = new DataOutputStream(baos);

            // write timestamp
            storeData(os);

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

    public boolean load()
    {
        try {
            RecordStore store = RecordStore.openRecordStore(NAME, true);
            int count = store.getNumRecords();

            if (count == 0)
                return false;

            byte[] data = store.getRecord(1);
            DataInputStream is = new DataInputStream(new ByteArrayInputStream(data));

            loadData(is);

            store.closeRecordStore();
        } catch (Exception e) {
            Logger.debug(e.toString());
        }

        return true;
    }

    private void storeData(DataOutputStream os)
    {
    }
    private void loadData(DataInputStream is)
    {
    }

    public void storeJSON(String json)
    {
        try {
            RecordStore store = RecordStore.openRecordStore(NAME, true);
            int count = store.getNumRecords();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            DataOutputStream os = new DataOutputStream(baos);

            // write timestamp
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

    public String loadJSON()
    {
        String json = "";

        try {
            RecordStore store = RecordStore.openRecordStore(NAME, true);
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

    public void destroy()
    {
        try {
            RecordStore store = RecordStore.openRecordStore(NAME, true);
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
