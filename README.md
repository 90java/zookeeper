# zookeeper
zookeeper 学习

    参考文档：
    https://zookeeper.apache.org/doc/current/index.html
    http://zookeeper.apache.org/doc/r3.5.6/zookeeperStarted.html#sc_RunningReplicatedZooKeeper
    https://zookeeper.apache.org/doc/r3.5.6/javaExample.html
    
    
    分为单机 伪集群(同一台机器)  集群(不同机器)
    
    管理ZooKeeper存储
        外部管理zookeeper存储（data和日志）
    ZooKeeper数据保留在内存中，这意味着ZooKeeper可以实现高吞吐量和低延迟数。
        
    高性能    
        
    高可用性
        不会成为单点故障  因为可以布置为集群
        
    启动：
        windows
        服务端：zkServer.cmd
        客户端：zkCli.cmd
            默认连接：127.0.0.1 2181
            其他情况： zkCli.cmd -server ip:port
    znode 
        ZooKeeper数据节点
    Znodes
            
    zookeeper 常用操作
        create : 在树中的某个位置创建一个节点  create [-s] [-e] path(节点znode) data(与节点关联的数据) acl
        delete : 删除节点
        exists : 测试某个位置是否存在节点
        get data : 从节点读取数据
        set data : 将数据写入节点       set /zk_test junk
        get children : 获取节点子节点的列表
        sync : 等待数据传播
        ls 查看目录
        
        ----
       
        
        
        
        
    集群模式下。有一个leader， 所有客户端写请求都转发到leader的那个单个服务器，其余的zookeeper服务器（跟随者）从leader接收消息建议
    并同一消息传递，并将从服务器跟leader同步。
    
    有一种情况：当leader挂了，会进行推选leader. 这段时间服务就不可访问， 所以这一块导致了这段时间没了可用性（CAP中的A没了）
    
    
    

单机模式
    
      启动过程中读取conf/zoo.cfg  
      dataDir
  集群(不同机器)   生产环境 
  
     配置多个server.X组成ZooKeeper服务的服务器 建议奇数个服务器。至少三个服务器。底下的配置每台服务器都一样。
     server.1=host:port1:port2
     server.2=host:port1:port2
     server.3=host:port1:port2
     server.X=host:port1:port2
     怎么知道启动的是哪那一台服务器 **
        它会查找配置的data文件下myid文件 内配置那个X 就是那台服务器
     配置服务器后面有两个端口 
        第一个端口 port1 用于连接其他服务器，进行通信。更具体的是使用zookeeper服务器使用此端口连接到leader服务器。
        第二个端口 port2  用于进行leader选举  
          
 伪集群(同一台机器)  本地开发环境  （不建议生产使用，每个服务器要有自己的计算机，）
    
    所有的host 配置为localhost port1配置为唯一的。port2领导者选择端口
    配置单独dataDir 和不同clientPort
    
