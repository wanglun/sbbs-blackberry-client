package name.wl.bbs.data;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.microedition.rms.*;

import name.wl.bbs.util.*;

class AuthData extends Data {
    private static final String NAME = "auth";

    private String user;
    private String token;

    public AuthData()
    {
    }

    public AuthData(String user, String token)
    {
        this.user = user;
        this.token = token;
    }

    public void storeData(DataOutputStream os)
    {
        try {
            os.writeUTF(this.user);
            os.writeUTF(this.token);
        } catch (Exception e) {
            Logger.debug(e.toString());
        }
    }

    public void loadData(DataInputStream is)
    {
        try {
            user = is.readUTF();
            token = is.readUTF();
        } catch (Exception e) {
            Logger.debug(e.toString());
        }
    }

    public String getUser()
    {
        return user;
    }

    public String getToken()
    {
        return token;
    }
}
