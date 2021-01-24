package bll;

import dal.Read;

public class Receiver {
    public static String read (String name) {
        String filepath = null;
        try {
            filepath = Read.search(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(filepath);
        return filepath;
    }
}
