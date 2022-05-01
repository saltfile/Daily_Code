package com.easy.cilent;

import com.easy.TranPort.HttpCenilt;
import com.easy.TranPort.TransCilent;
import com.easy.lcrpc.Peer;

import java.util.List;

/**
 * 表示选择哪个server去链接
 */

public interface TranportsSelector {
    /**
     * 初始化selector
     * @param peers 可以连接server端点信息
     * @param count client 与 server建立多少个连接
     * @param clazz client实现class
     */
    void init(List<Peer> peers,int count,Class<? extends TransCilent> clazz);

    /**
     * 选择一个tranport与server做交互
     * @return 网络client
     */
    TransCilent select();

    /**
     * 释放用完的client
     * @param cilent
     */
    void release(TransCilent cilent);

    void close();
}
