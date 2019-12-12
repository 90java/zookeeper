package com.nojava;

import static org.junit.Assert.assertTrue;

import com.nojava.one.MyWatcher;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.Test;

import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void test01() throws IOException, KeeperException, InterruptedException {
//        new ZooKeeper("127.0.0.1:2181",30000,new MyWatcher());
        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181",30000,(watchedEvent)->{
            System.out.println("----------------");
            System.out.println(watchedEvent.getPath());
            System.out.println(watchedEvent.getState());
            System.out.println(watchedEvent.getType());
            System.out.println("----------------");
        });

        String node="/node1";

        System.out.println("==================");
        Stat stat = zooKeeper.exists(node, false);
        System.out.println("stat:"+stat);
        if(null == stat){
            String s = zooKeeper.create(node, "test".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println("："+s);
        }
        byte[] bytes = zooKeeper.getData(node, false, stat);
        System.out.println("节点数据："+new String(bytes));
        zooKeeper.close();

    }

}
