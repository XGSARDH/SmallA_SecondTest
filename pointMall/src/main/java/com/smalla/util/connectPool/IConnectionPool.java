package com.smalla.util.connectPool;

import java.sql.Connection;

/**
 * @author Sardh
 */
public interface IConnectionPool {

    // 获取连接(重复利用机制)
    public  Connection createConn();

    // 释放连接(可回收机制)
    public  void release(Connection conn);
}
