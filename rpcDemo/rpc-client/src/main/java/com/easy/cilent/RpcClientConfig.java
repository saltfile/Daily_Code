package com.easy.cilent;

import com.easy.TranPort.HttpCenilt;
import com.easy.TranPort.TransCilent;
import com.easy.lcrpc.Peer;
import com.easy.rpc.Decoder;
import com.easy.rpc.Encoder;
import com.easy.rpc.JSONDecoder;
import com.easy.rpc.JSONEncoder;
import lombok.Data;
import org.eclipse.jetty.server.HttpTransport;

import java.util.Arrays;
import java.util.List;
@Data
public class RpcClientConfig {
    private Class<? extends TransCilent> tranClients = HttpCenilt.class;

    private Class<? extends Encoder> ecoder = JSONEncoder.class;

    private Class<? extends Decoder> decoder = JSONDecoder.class;

    private Class<? extends TranportsSelector> selectClass = RandomTransportSelector.class;

    private int connectCount = 1;

    List<Peer> servers = Arrays.asList(new Peer("127.0.0.1",8000));


}
