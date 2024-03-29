package name.wl.bbs.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;

import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.io.HttpsConnection;

import java.util.Hashtable;
import net.rim.device.api.compress.GZIPInputStream;

import name.wl.bbs.util.*;

public class HTTPRequest
{
    /**
     * @member Latest HTTP response text
     */
    protected String HTTPResponseText = "";
    /**
     * @member Latest URL requested
     */
    protected String url = null;
    /**
     * @member UserAgent class constant.
     */
    protected String userAgent = "Profile/MIDP-2.0 Configuration/CLDC-1.0";
    /**
     * @member Parameters for get or post.
     */
    protected Hashtable params = null;
    /**
     * @member Method bits.
     */
    protected long method;
    /**
     * @member Post bytes.
     */
    protected byte[] post = null;

    // Constants
    public static long METHOD_GET = 1;
    public static long METHOD_POST = 3;
    public static long METHOD_FORM_MULTIPART = 7;
    public static long METHOD_HTTPS = 8;

    public static boolean GZIP = true;

    protected HttpConnection connection = null;
    protected InputStream inputstream = null;
    protected GZIPInputStream gzipinputstream = null;
    protected OutputStream outputstream = null;

    protected byte[] file;
    protected String fileField;
    protected String fileName;
    protected String fileType;

    public HTTPRequest(String url)
    {
        this.url = url;
        this.method = HTTPRequest.METHOD_GET;
        this.params = new Hashtable();
    }

    public HTTPRequest(String url, Hashtable params)
    {
        this.url = url;
        this.method = HTTPRequest.METHOD_POST;
        this.params = params;
    }

    public HTTPRequest(String url, Hashtable params, long method)
    {
        this.url = url;
        this.method = method;
        this.params = params;
    }

    public HTTPRequest(String url, Hashtable params, byte[] file, String fileField, String fileName, String fileType)
    {
        this.url = url;
        this.method = HTTPRequest.METHOD_FORM_MULTIPART;
        this.params = params;
        this.file = file;
        this.fileField = fileField;
        this.fileName = fileName;
        this.fileType = fileType;
    }

    /**
     * @todo Check for 404 codes
     * @return
     */
    public boolean request()
    {
        boolean success = true;

        try {
            Logger.debug("R0: start request");
            // Make HTTP Connection, HTTP or HTTPS
            if (this.isHTTPS()) {
                this.connection = (HttpsConnection) Connector.open(this.url);
            } else {
                this.connection = (HttpConnection) Connector.open(this.url);
            }

            // Set user agent and connection properties.
            this.connection.setRequestProperty("User-Agent", this.userAgent);
            this.connection.setRequestProperty("Connection", "close");

            if (GZIP) {
                this.connection.setRequestProperty("Accept-Encoding", "gzip");
            }

            // Set HTTP Request Method, GET or POST
            if (this.isPOST()) {
                this.connection.setRequestMethod(HttpConnection.POST);

                // Set URL Encoded or Form Multipart
                if (this.isMULTIPART()) {
                    this.connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + this.getBoundaryString());

                    this.buildFormMultipartPost();
                } else {
                    this.connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");

                    this.buildURLEncodedPost();
                }

                // Post Output
                outputstream = connection.openOutputStream();
                outputstream.write(this.post);
                outputstream.flush();

                //success = this.response();
            } else {
                this.connection.setRequestMethod(HttpConnection.GET);
                this.connection.setRequestProperty("Content-Type", "//text plain");
            }

            success = this.response();

            // Close output stream if opened.
            if (this.isPOST()) {
                outputstream.close();
            }

            this.connection.close();
        } catch (final Exception e) {
            // Error handler
            Logger.debug("R1: " + e.toString());
        }

        return success;
    }

    /**
     * Handle an HTTP request response.
     * 
     * @return boolean  Success or failure.
     * @throws IOException 
     */
    protected boolean response() throws IOException
    {
        boolean success;
        boolean isGzip = false;
        // Check response code for success
        if (this.connection.getResponseCode() == HttpConnection.HTTP_OK) {
            String encoding = this.connection.getEncoding();
            if (encoding != null && encoding.equals("gzip")) {
                isGzip = true;
            }

            if (isGzip) {
                this.gzipinputstream = new GZIPInputStream(connection.openInputStream());

                ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
                byte incomingData[] = new byte[1024];
                int bytes_read = 0;
                while ((bytes_read = gzipinputstream.read(incomingData)) != -1) {
                    bytestream.write(incomingData, 0, bytes_read);
                }
                this.HTTPResponseText = new String(bytestream.toByteArray());
                bytestream.close();

                this.gzipinputstream.close();
            } else {
                this.inputstream = connection.openInputStream();

                int length = (int) connection.getLength();
                if (length > 0) {
                    byte incomingData[] = new byte[length];
                    this.inputstream.read(incomingData);
                    this.HTTPResponseText = new String(incomingData);
                } else {
                    ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
                    byte incomingData[] = new byte[1024];
                    int bytes_read = 0;
                    while ((bytes_read = inputstream.read(incomingData)) != -1) {
                        bytestream.write(incomingData, 0, bytes_read);
                    }
                    this.HTTPResponseText = new String(bytestream.toByteArray());
                    bytestream.close();
                }

                this.inputstream.close();
            }

            success = true;
        } else {
            success = false;
        }

        return success;
    }

    protected void buildURLEncodedPost()
    {
        this.post = URLUTF8Encoder.encodeParams(this.params).getBytes();
    }

    protected void buildFormMultipartPost() throws IOException
    {
        String postData = "--" + this.getBoundaryString() + "\r\n";

        Enumeration keys = this.params.keys();

        while (keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            String value = (String) this.params.get(key);

            postData += "Content-Disposition: form-data; name=\"" + key + "\"\r\n";
            postData += "\r\n" + value + "\r\n";
            postData += "--" + this.getBoundaryString() + "\r\n";
        }

        postData += "Content-Disposition: form-data; name=\"" + fileField + "\"; filename=\"" + fileName + "\"\r\n";
        postData += "Content-Type: " + fileType + "\r\n\r\n";

        String endBoundary = "\r\n--" + this.getBoundaryString() + "--\r\n";

        ByteArrayOutputStream postBytes = new ByteArrayOutputStream();

        postBytes.write(postData.getBytes());
        postBytes.write(this.file);
        postBytes.write(endBoundary.getBytes());

        this.post = postBytes.toByteArray();
        postBytes.close();
    }

    protected String getBoundaryMessage(String boundary, Hashtable params, String fileField, String fileName, String fileType)
    {
        StringBuffer res = new StringBuffer("--").append(boundary).append("\r\n");

        Enumeration keys = params.keys();

        while(keys.hasMoreElements()) {
            String key = (String)keys.nextElement();
            String value = (String)params.get(key);

            res.append("Content-Disposition: form-data; name=\"").append(key).append("\"\r\n")    
                .append("\r\n").append(value).append("\r\n")
                .append("--").append(boundary).append("\r\n");
        }
        res.append("Content-Disposition: form-data; name=\"").append(fileField).append("\"; filename=\"").append(fileName).append("\"\r\n") 
            .append("Content-Type: ").append(fileType).append("\r\n\r\n");

        return res.toString();
    }

    /**
     * Gets response text of the HTTP request.
     * 
     * @return HTTPResponseText
     */
    public String getHTTPReponseText()
    {
        return this.HTTPResponseText;
    }
    /**
     * Gets parameters submitted with request.
     * 
     * @return
     */
    public Hashtable getParams()
    {
        return this.params;
    }
    /**
     * Gets the URL requested.
     * 
     * @return
     */
    public String getURL()
    {
        return this.url;
    }

    /**
     * Checks if the HTTPS bit is set in the method. 
     * 
     * @return  True if the method should use HTTPS
     */
    protected boolean isHTTPS()
    {
        if ((this.method & HTTPRequest.METHOD_HTTPS) == HTTPRequest.METHOD_HTTPS) {
            return true;
        }

        return false;
    }
    /**
     * Checks if the POST bit is set in the method.
     * 
     * @return True if the method should use POST
     */
    protected boolean isPOST()
    {
        if ((this.method & HTTPRequest.METHOD_POST) == HTTPRequest.METHOD_POST) {
            return true;
        }

        return false;
    }
    /**
     * Checks if the Form/MultiPart bit is set in the method.
     * 
     * @return True if the method should use MULTIPART
     */
    protected boolean isMULTIPART()
    {
        if ((this.method & HTTPRequest.METHOD_FORM_MULTIPART) == HTTPRequest.METHOD_FORM_MULTIPART) {
            return true;
        }

        return false;
    }

    protected String getBoundaryString()
    {
        return "----------v3UpaFg13e3bPJ9Zfh7aj6";
    }
}
