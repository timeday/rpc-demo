package register;

import rpcFramework.Url;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: rpcdemo
 * @description:
 * @author: z.hw
 * @create: 2019-01-06 00:27
 **/
public class Register {


    private static volatile Map<String,Map<Url,Class>> REGISTER=new HashMap<String, Map<Url, Class>>();

    public static void register(Url url,String interfacterName,Class interfacterImpl){
        System.out.println("服务注册");
        Map<Url,Class> map=new HashMap<Url, Class>();
        map.put(url,interfacterImpl);
        REGISTER.put(interfacterName,map);
        /**
         * 两个进程 使用的通过一个map，只有保存到一个公共的地方才能获取到数据 可以保存到redis/数据库等
         */
        saveFile();
    }

    private static void saveFile() {

        try {
            File file = new File("d:/register.txt");
            if(!file.exists()){
                file.createNewFile();
            }
            FileOutputStream fileOutputStream=new FileOutputStream(file);
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(REGISTER);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static Url getRandomUrl(String interfacterName){
        Map<String, Map<Url, Class>> mapMap = getFile();
        return mapMap.get(interfacterName).keySet().iterator().next();
    }

    private static Map<String,Map<Url,Class>> getFile() {

        try {
            File file = new File("d:/register.txt");
            if(!file.exists()){
                file.createNewFile();
            }

            FileInputStream fileInputStream=new FileInputStream(file) ;
            ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);

            return (Map<String,Map<Url,Class>>)objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Class get(String interfacterName,Url url){
        Map<String, Map<Url, Class>> mapMap = getFile();
        return mapMap.get(interfacterName).get(url);
    }




}
