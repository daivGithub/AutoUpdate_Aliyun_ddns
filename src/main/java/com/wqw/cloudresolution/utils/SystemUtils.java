package com.wqw.cloudresolution.utils;


import com.wqw.cloudresolution.constant.Constants;
import com.wqw.cloudresolution.ymlEntity.ParaMeterEntity;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

@Component
public class SystemUtils {

    @Autowired
    ParaMeterEntity paraMeterEntity;

    public String getLocalAddress(){
        String ip="";
        String url=paraMeterEntity.getGetipurl();
        HttpClient client=wrapClient(url);
        HttpGet get=new HttpGet(url);
        Response rsp=new Response();

        try {
            rsp=convert(client.execute(get));
        } catch (IOException e) {
            return "";
        }
        String str=rsp.getBody();
        if("error".equals(str)){
            return null;
        }else{
            int start=str.indexOf("[")+1;
            int end=str.indexOf("]");
            ip=str.substring(start,end);
        }
        return ip;
    }

    private static HttpClient wrapClient(String host){
        HttpClient client=new DefaultHttpClient();
        if(host.startsWith("https://")){
           sslClient(client);
        }
        return client;
    }
    private static void sslClient(HttpClient client){
        try {
            SSLContext ctx = SSLContext.getInstance("TLS");
            X509TrustManager tm=new X509TrustManager() {
                @Override
                public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                }

                @Override
                public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            ctx.init(null,new TrustManager[]{tm},null);
            SSLSocketFactory ssf=new SSLSocketFactory(ctx);
            ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            ClientConnectionManager ccm=client.getConnectionManager();
            SchemeRegistry registry=ccm.getSchemeRegistry();
            registry.register(new Scheme("https",443,ssf));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private static Response convert(HttpResponse response){
        Response res=new Response();
        if(null!=response){
            res.setStatusCode(response.getStatusLine().getStatusCode());
            for (Header header:response.getAllHeaders())
            {

                res.setHeader(header.getName(),MessageDigestUtil.iso88591ToUtf8(header.getValue()));
            }
            res.setContentType(res.getHeader("Content-Type"));
            res.setContentType(res.getHeader("Content-Type"));
            res.setContentType(res.getHeader("X-Ca-Request-Id"));
            res.setContentType(res.getHeader("X-Ca-Error-Message"));
           // res.setBody(readStreamAsStr(response.getEntity().getContent()));
            try {
                res.setBody(readStreamAsStr(response.getEntity().getContent()));
            } catch (IOException e) {
                res.setBody("error");
            }
        }else{
            //服务器无反映
            res.setStatusCode(500);
            res.setErrorMessage("No Response");
        }
        return res;
    }

    /**
     * 将流转换为字符串
     * @param is
     * @return
     * @throws IOException
     */
    public static String readStreamAsStr(InputStream is){
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        WritableByteChannel dest= Channels.newChannel(bos);
        ReadableByteChannel src=Channels.newChannel(is);
        ByteBuffer bb=ByteBuffer.allocate(4096);
        try {
            while(src.read(bb)!=-1){
                bb.flip();
                dest.write(bb);
                bb.clear();
            }
            return new String(bos.toByteArray(), Constants.ENCODING);
        }catch (IOException e){
            throw new RuntimeException(e);
        }finally {
            try {
                src.close();
                dest.close();
            }catch (Exception e){
                throw new RuntimeException(e);
            }
        }
    }


    /**public static void main(String[] args) {
        System.out.println(getLocalAddress());
    }**/
}