package protocol.http;

import rpcFramework.Invocation;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * @program: rpcdemo
 * @description:
 * @author: z.hw
 * @create: 2019-01-06 01:23
 **/
public class HttpClient {


    public String post(String hostname, Integer port, Invocation invocation){

        HttpURLConnection urlConnection=null;
        ObjectOutputStream objectOutputStream=null;
        InputStream inputStream=null;
        try {
             URL url=new URL("http",hostname,port,"/");
             urlConnection = (HttpURLConnection)url.openConnection();

             urlConnection.setRequestMethod("POST");

             urlConnection.setDoOutput(true);

            //输出
            OutputStream outputStream = urlConnection.getOutputStream();
            objectOutputStream=new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(invocation);


        } catch (IOException e) {
            e.printStackTrace();
        }finally {

            if(objectOutputStream!=null){
                try {
                    objectOutputStream.flush();
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

        try {
            //相应结果
             inputStream = urlConnection.getInputStream();
            return IOUtils.toString(inputStream, Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(inputStream!=null)
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }

        return null;
    }


}
