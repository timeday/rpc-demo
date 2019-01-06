package protocol.http;

import org.apache.catalina.*;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;

/**
 * @program: rpcdemo
 * @description:
 * @author: z.hw
 * @create: 2019-01-06 00:33
 **/
public class HttpServer {

    public void start(String hostname,Integer port){

        System.out.println("定制tomcat启动服务");
        Tomcat tomcat=new Tomcat();

        Server server = tomcat.getServer();

        Service service = server.findService("Tomcat");

        Connector connector=new Connector();

        connector.setPort(port);

        Engine engine=new StandardEngine();

        engine.setDefaultHost(hostname);


        Host host=new StandardHost();

        host.setName(hostname);

        String contextPath="";

        Context context=new StandardContext();
        context.setPath(contextPath);
        context.addLifecycleListener(new Tomcat.FixContextListener());


        host.addChild(context);

        engine.addChild(host);

        service.setContainer(engine);
        service.addConnector(connector);

        tomcat.addServlet(contextPath,"dispatcher",new DispartceServlet()); //怎加处理的服务器类

        context.addServletMappingDecoded("/*","dispatcher");//增加容器上下文处理路径

        try {
            tomcat.start();//初始化
            tomcat.getServer().await();//等待
        } catch (LifecycleException e) {
            e.printStackTrace();
        } finally {
        }


    }



}
