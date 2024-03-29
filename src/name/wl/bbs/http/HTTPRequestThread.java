package name.wl.bbs.http;

import java.util.Hashtable;

import name.wl.bbs.util.*;

/**
 * Threaded HTTPRequest
 */
public class HTTPRequestThread extends Thread 
{
    protected HTTPRequest request;
    Listener listener;

    public HTTPRequestThread(String url)
    {
        this.request = new HTTPRequest(url);
    }

    public HTTPRequestThread(String url, Hashtable postData)
    {
        this.request = new HTTPRequest(url, postData);
    }

    public HTTPRequestThread(String url, Hashtable params, long method)
    {
        this.request = new HTTPRequest(url, params, method);
    }

    public HTTPRequestThread(String url, Hashtable params, byte[] file, String fileField, String fileName, String fileType)
    {
        this.request = new HTTPRequest(url, params, file, fileField, fileName, fileType);
    }

    /* override */
    public void start(Listener l)
    {
        this.listener = l;
        super.start();
    }

    public void run()
    {
        this.request.request();
        this.listener.callback(this);
    }

    public HTTPRequest getHTTPRequest()
    {
        return this.request;
    }

    public String getHTTPReponseText()
    {
        return this.request.getHTTPReponseText();
    }
}
