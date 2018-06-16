package com.op;

import buaa.jj.accountservice.api.Druid;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DruidWrapper implements Druid {

    private Druid druid;

    public DruidWrapper(Druid druid){
        this.druid=druid;
    }

    public List<Map<String, String>> executeQuery(String s) {
        try {
            return druid.executeQuery(s);
        }catch (Exception e){
            e.printStackTrace();
        }
        List<Map<String, String>>l=new ArrayList<Map<String, String>>();
        return l;
    }

    public boolean execute(String s) {
        return false;
    }

    public int executeUpdate(String s) {
        return 0;
    }

}
