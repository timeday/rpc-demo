package rpcFramework;

import java.io.Serializable;
import java.util.Objects;

/**
 * @program: rpcdemo
 * @description:
 * @author: z.hw
 * @create: 2019-01-06 00:28
 **/
public class Url implements Serializable {

    private String hostname;

    private Integer port;

    public Url(String hostname, Integer port) {
        this.hostname = hostname;
        this.port = port;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    /**
     * 根据hostname和port 判断两个对象是否相同 这个多个线程的每一个url只要hostname和port一样，就可以将其当成同一个对象
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(hostname,port);
    }

    @Override
    public boolean equals(Object obj) {

        if (this==obj) return true;

        if (obj==null || getClass() !=obj.getClass()) return false;

        Url url=(Url)obj;

        return Objects.equals(hostname,url.hostname) && Objects.equals(port,url.port);
    }
}
