package com.sdt.fsc.hik;


import jodd.util.URLDecoder;

public class DynamicLoadSDK {

    public static String DLL_PATH;

    static {
        String path = (DynamicLoadSDK.class.getResource("/").getPath())
                .replaceAll("%20","").substring(1).replace("/","\\");
        try {
            DLL_PATH = URLDecoder.decode(path, "utf-8");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(DynamicLoadSDK.DLL_PATH);
    }
}
