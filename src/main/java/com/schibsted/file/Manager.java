package com.schibsted.file;

import java.io.IOException;

public interface Manager {

    void findText() throws IOException;
    int getScore();
    String getFileName();
}
