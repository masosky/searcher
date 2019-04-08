package com.schibsted;


import org.apache.commons.io.IOUtils;

import java.io.*;

public class FileUtils {

    private static final File FILE_DIR = new File(System.getProperty("user.home"), "test");

    private File test1File;
    private File test2File;

    public  void deployFile(){
        if (!fileDeployed()) {
            deploy("test1.txt");
            deploy("test2.txt");
        }
        test1File = new File(FileUtils.FILE_DIR.getPath() + "/test1.txt");
        test2File = new File(FileUtils.FILE_DIR.getPath() + "/test2.txt");
    }

    private boolean fileDeployed() {
        return new File(FileUtils.FILE_DIR.getPath() + "/test1.txt").exists() && new File(FileUtils.FILE_DIR.getPath() + "/test2.txt").exists();
    }

    private void deploy(String fileName) {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = FileUtils.class.getResourceAsStream("/" + fileName);

            File file = createFile(fileName);

            if (file.createNewFile()) {
                out = new FileOutputStream(file);
                IOUtils.copy(in, out);
            } else {
                System.out.println("Error while deploying file at server" + fileName);
            }

        } catch (IOException e) {
            System.out.println("Error while deploying file at server");
        } finally {
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(out);
        }
    }

    private File createFile(String name)  {
        createCredentialDir();

        File file = new File(FileUtils.FILE_DIR.getPath() + "/" + name);
        if (file.exists()) {
            if (!file.delete()) {
                System.out.println("Problem deleting previous " + name);
            }
        }
        return file;
    }

    private void createCredentialDir() {
        if (!FileUtils.FILE_DIR.exists()) {
            if (!FileUtils.FILE_DIR.mkdirs()) {
                System.out.println("Error while deploying file at server");
            }
        }
    }
    public File getTest1File() {
        return test1File;
    }

    public File getTest2File() {
        return test2File;
    }

    public static File getFileDir() {
        return FILE_DIR;
    }
}
