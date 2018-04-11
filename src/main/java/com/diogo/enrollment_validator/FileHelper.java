package com.diogo.enrollment_validator;

import java.io.File;

public class FileHelper {



    public static File getFile(String path) throws Exception {
        return getFile(path,false);
    }

    public static File getFile(String path,Boolean createIfNotExists) throws Exception{
        File f = new File(path);

        if(!f.exists()){
            if(!createIfNotExists)
                throw new Exception("File not exists");
            else
                f.createNewFile();
        }

        return f;
    }
}
