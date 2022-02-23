package xyz.carjoy.question.utils;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyNameCoder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.InputStream;
import java.net.SocketTimeoutException;


public class HttpsRequest {

    //接口地址
    private String apiURL = "";
    private Log logger = LogFactory.getLog(this.getClass());
    private static final String pattern = "yyyy-MM-dd HH:mm:ss:SSS";
    private CloseableHttpClient httpClient = null;
    private HttpPost method = null;
    private long startTime = 0L;
    private long endTime = 0L;
    private int status = 0;
    //连接超时时间，默认10秒
    private int socketTimeout = 10000;

    //传输超时时间，默认30秒
    private int connectTimeout = 30000;
    /**
     * 接口地址
     * @param url
     */
    public HttpsRequest(String url){

        if(url != null)
        {
            this.apiURL = url;
        }
        if(apiURL != null)
        {
            httpClient = HttpClients.createDefault();
            method = new HttpPost(apiURL);
        }
    }

    /**
     * 调用 API
     * @param parameters
     * @return
     */
    public String post(Object xmlObj)
    {
        String result = null;

        HttpPost httpPost = new HttpPost(apiURL);

        //解决XStream对出现双下划线的bug
        XStream xStreamForRequestPostData = new XStream(new DomDriver("UTF-8", new XmlFriendlyNameCoder("-_", "_")));

        //将要提交给API的数据对象转换成XML格式数据Post给API
        String postDataXML = xStreamForRequestPostData.toXML(xmlObj);
        logger.info("API，url是："+apiURL);
        logger.info("API，POST过去的数据是：");
        logger.info(postDataXML);

        //得指明使用UTF-8编码，否则到API服务器XML的中文不能被成功识别
        StringEntity postEntity = new StringEntity(postDataXML, "UTF-8");
        httpPost.addHeader("Content-Type", "text/xml");
        httpPost.setEntity(postEntity);

        //设置请求器的配置
        httpPost.setConfig(RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build());

        logger.info("executing request" + httpPost.getRequestLine());

        try {
            HttpResponse response = httpClient.execute(httpPost);

            HttpEntity entity = response.getEntity();

            result = EntityUtils.toString(entity, "UTF-8");

        } catch (ConnectionPoolTimeoutException e) {
            logger.error("http get throw ConnectionPoolTimeoutException(wait time out)");

        } catch (ConnectTimeoutException e) {
            logger.error("http get throw ConnectTimeoutException");

        } catch (SocketTimeoutException e) {
            logger.error("http get throw SocketTimeoutException");

        } catch (Exception e) {
            logger.error("http get throw Exception");

        } finally {
            httpPost.abort();
        }
        return result;
    }


    /**
     * 调用 API
     * @param parameters
     * @return
     */
    public String postJson(String json)
    {
        String result = null;
        HttpPost httpPost = new HttpPost(apiURL);
        logger.info("API，POST过去的数据是：");
        logger.info(json);

        //得指明使用UTF-8编码，否则到API服务器XML的中文不能被成功识别
        StringEntity postEntity = new StringEntity(json, "UTF-8");
        httpPost.addHeader("Content-Type", "text/xml");
        httpPost.setEntity(postEntity);

        //设置请求器的配置
        httpPost.setConfig(RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build());

        logger.info("executing request" + httpPost.getRequestLine());

        try {
            CloseableHttpResponse response = httpClient.execute(httpPost);

            HttpEntity entity = response.getEntity();

            result = EntityUtils.toString(entity, "UTF-8");

        } catch (ConnectionPoolTimeoutException e) {
            logger.error("http get throw ConnectionPoolTimeoutException(wait time out)");

        } catch (ConnectTimeoutException e) {
            logger.error("http get throw ConnectTimeoutException");

        } catch (SocketTimeoutException e) {
            logger.error("http get throw SocketTimeoutException");

        } catch (Exception e) {
            logger.error("http get throw Exception");

        } finally {
            httpPost.abort();
        }
        return result;
    }

    /**
     * 调用 API
     * @param parameters
     * @return
     */
    public InputStream postJsonInputStream(String json)
    {
        InputStream result = null;
        HttpPost httpPost = new HttpPost(apiURL);
        logger.info("API，POST过去的数据是：");
        logger.info(json);

        //得指明使用UTF-8编码，否则到API服务器XML的中文不能被成功识别
        StringEntity postEntity = new StringEntity(json, "UTF-8");
        httpPost.addHeader("Content-Type", "text/xml");
        httpPost.setEntity(postEntity);

        //设置请求器的配置
        httpPost.setConfig(RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build());

        logger.info("executing request" + httpPost.getRequestLine());

        try {
            CloseableHttpResponse response = httpClient.execute(httpPost);

            HttpEntity entity = response.getEntity();

            result = entity.getContent();

        } catch (ConnectionPoolTimeoutException e) {
            logger.error("http get throw ConnectionPoolTimeoutException(wait time out)");

        } catch (ConnectTimeoutException e) {
            logger.error("http get throw ConnectTimeoutException");

        } catch (SocketTimeoutException e) {
            logger.error("http get throw SocketTimeoutException");

        } catch (Exception e) {
            logger.error("http get throw Exception");

        } finally {
            httpPost.abort();
        }
        return result;
    }

    public String get() {
        String result = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httpget.
        HttpGet httpget = new HttpGet(apiURL);

        //设置请求器的配置
        httpget.setConfig(RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build());

        try {
            // 执行get请求.
            CloseableHttpResponse response = httpclient.execute(httpget);
            // 获取响应实体
            HttpEntity entity = response.getEntity();

            result = EntityUtils.toString(entity, "UTF-8");
        } catch (ConnectionPoolTimeoutException e) {
            logger.error("http get throw ConnectionPoolTimeoutException(wait time out)");

        } catch (ConnectTimeoutException e) {
            logger.error("http get throw ConnectTimeoutException");

        } catch (SocketTimeoutException e) {
            logger.error("http get throw SocketTimeoutException");

        } catch (Exception e) {
            logger.error("http get throw Exception");

        } finally {
            httpget.abort();
        }
        return result;
    }

    public InputStream  getInputStream() {
        InputStream result = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httpget.
        HttpGet httpget = new HttpGet(apiURL);

        //设置请求器的配置
        httpget.setConfig(RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build());

        try {
            // 执行get请求.
            CloseableHttpResponse response = httpclient.execute(httpget);
            // 获取响应实体
            HttpEntity entity = response.getEntity();

            result = entity.getContent();
        } catch (ConnectionPoolTimeoutException e) {
            logger.error("http get throw ConnectionPoolTimeoutException(wait time out)");

        } catch (ConnectTimeoutException e) {
            logger.error("http get throw ConnectTimeoutException");

        } catch (SocketTimeoutException e) {
            logger.error("http get throw SocketTimeoutException");

        } catch (Exception e) {
            logger.error("http get throw Exception");

        } finally {
            httpget.abort();
        }
        return result;
    }

    /**
     * 0.成功 1.执行方法失败 2.协议错误 3.网络错误
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }
    /**
     * @return the startTime
     */
    public long getStartTime() {
        return startTime;
    }
    /**
     * @return the endTime
     */
    public long getEndTime() {
        return endTime;
    }
}
