package com.easy.TranPort;

import java.io.InputStream;
import java.io.OutputStream;

public interface RequsetHandler {
    void onRequest(InputStream recive, OutputStream ToRespone);
}
