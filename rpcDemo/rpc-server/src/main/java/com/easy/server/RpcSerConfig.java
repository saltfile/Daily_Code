package com.easy.server;

import com.easy.TranPort.HttpServer;
import com.easy.TranPort.TransServer;
import com.easy.rpc.Decoder;
import com.easy.rpc.Encoder;
import com.easy.rpc.JSONDecoder;
import com.easy.rpc.JSONEncoder;
import lombok.Data;

@Data
public class RpcSerConfig {
    private Class <? extends TransServer> tranClass = HttpServer.class;
    private Class <? extends Encoder> ecode = JSONEncoder.class;
    private Class <? extends Decoder> dcode = JSONDecoder.class;
    private int port = 8000;



}
