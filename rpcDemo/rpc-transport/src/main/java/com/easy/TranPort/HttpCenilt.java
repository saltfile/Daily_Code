package com.easy.TranPort;

import com.easy.lcrpc.Peer;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpCenilt implements TransCilent{
    private String http_url;
    @Override
    public void connect(Peer peer) {
        this.http_url = "http://"+ peer.getHost()+peer.getPort();
    }

    @Override
    public InputStream write(InputStream input) {

        try {
            HttpURLConnection connection =(HttpURLConnection) new URL(http_url).openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setRequestMethod("POST");

            connection.connect();
            IOUtils.copy(input,connection.getOutputStream());

            int resultCode = connection.getResponseCode();
            if(resultCode == HttpURLConnection.HTTP_OK){
                return connection.getInputStream();
            }else{
                return connection.getErrorStream();
            }


        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() {

    }
}
