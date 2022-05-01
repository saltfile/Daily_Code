package com.easy.cilent;

import com.easy.TranPort.TransCilent;
import com.easy.common.utils.ReflectionUtils;
import com.easy.lcrpc.Peer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomTransportSelector implements TranportsSelector{
    private List<TransCilent> cilents;
    public RandomTransportSelector(){
        cilents = new ArrayList<>();
    }


    @Override
    public synchronized void  init(List<Peer> peers, int count, Class<? extends TransCilent> clazz) {
        count = Math.max(count,1);

        for(Peer peer : peers){
            for(int i = 0;i < count;i++){
                TransCilent cilent = ReflectionUtils.newInstance(clazz);
                cilent.connect(peer);
                cilents.add(cilent);
            }
            System.out.println("connect service:"+peer);
        }
    }

    @Override
    public synchronized TransCilent select() {
        int i = new Random().nextInt(cilents.size());

        return cilents.remove(i);
    }

    @Override
    public synchronized void release(TransCilent cilent) {
        cilents.add(cilent);
    }

    @Override
    public synchronized void close() {
        for(TransCilent cilent:cilents){
            cilent.close();
        }
        cilents.clear();
    }
}
