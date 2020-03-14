package bpod;

import java.util.HashMap;

public class Interpreter {
    public static HashMap<String,Runnable> hashMap=new HashMap<>();

    public static void init()
    {
        
    }

    public static void exec(String string)
    {
        hashMap.get(string).run();
    }

}