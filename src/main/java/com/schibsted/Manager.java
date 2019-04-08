package com.schibsted;

import java.io.IOException;

interface Manager {

    void findText() throws IOException;
    int getScore();
    String getFileName();
}
