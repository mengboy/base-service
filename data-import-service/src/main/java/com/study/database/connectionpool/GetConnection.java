package com.study.database.connectionpool;

public class GetConnection {
    private volatile static ConnectionPool connectionPool = null;

    /**
     * 单例模式获取数据库连接池对象
     * @param url
     * @param user
     * @param password
     * @return
     */
    public static ConnectionPool getConnectionPool(String url, String user, String password){
        if (connectionPool == null) {               //Single Checked
            synchronized (GetConnection.class) {
                if (connectionPool == null) {       //Double Checked
                    try {
                        connectionPool = new ConnectionPool(url, user, password);
                    }catch (Exception e){
                        throw new RuntimeException("数据库连接失败", e);
                    }
                }
            }
        }
        return connectionPool ;
    }
}
