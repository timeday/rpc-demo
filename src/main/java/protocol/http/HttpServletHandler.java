package protocol.http;

import rpcFramework.Invocation;
import rpcFramework.Url;
import org.apache.commons.io.IOUtils;
import register.Register;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.ObjectInputStream;
import java.lang.reflect.Method;
import java.nio.charset.Charset;

/**
 * @program: rpcdemo
 * @description:
 * @author: z.hw
 * @create: 2019-01-06 00:46
 **/
public class HttpServletHandler {


    public void Handler(ServletRequest req, ServletResponse res){


        try {
            ServletInputStream inputStream = req.getInputStream();

            ObjectInputStream objectInputStream=new ObjectInputStream(inputStream);

            Invocation invocation=(Invocation)objectInputStream.readObject();

            //找实现类

            String interfaceName = invocation.getInterfaceName();

            Url url= Register.getRandomUrl(interfaceName);

            Class aClass = Register.get(interfaceName, url);

            Method method = aClass.getMethod(invocation.getMethodName(), invocation.getParamTypes());

            String result = (String)method.invoke(aClass.newInstance(), invocation.getParams());


            ServletOutputStream outputStream = res.getOutputStream();

            IOUtils.write("tomcat:"+result,outputStream, Charset.defaultCharset());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }

    }

}
