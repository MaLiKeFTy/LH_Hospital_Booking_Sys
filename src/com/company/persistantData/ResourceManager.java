package com.company.persistantData;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ResourceManager {

    public static void Save(Serializable data, String fileName) throws Exception {
        try (ObjectOutputStream ois = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)))) {
            System.out.println("done");
            ois.writeObject(data);
        }
    }


    public static Object[] Load(String fileName) throws Exception {

        ArrayList<Object> objects = new ArrayList<Object>();
        FileInputStream fis = new FileInputStream(".save");
        ObjectInputStream ois = new ObjectInputStream(fis);

        Object obj = null;

        boolean isExist = true;

        while (isExist) {
            if (fis.available() != 0) {
                obj = ois.readObject();
                objects.add(obj);
            } else {
                isExist = false;
            }
        }
        return objects.toArray();

        // return objectsList.toArray();

        /*try(ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)))){
            return ois.readObject();
        }*/
    }

}
