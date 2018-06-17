package com.op;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.*;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Poundage extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String orgID=request.getParameter("orgID");
        //后端未实现接口，使用假数据进行测试
        response.setContentType("text/html;charset=UTF-8");
        String startDate=request.getParameter("StartDate");
        startDate+=" 00:00:00";
        String endDate=request.getParameter("EndDate");
        endDate+=" 23:59:59";
        String channel=request.getParameter("Channel");
        int id=Integer.valueOf(orgID).intValue();
        List<Map<String, String>> l=new ArrayList<Map<String, String>>();
        try {
            l = DubboHandler.INSTANCE.druid.executeQuery("select * from platforminformation");
        }catch (Exception e){
            e.printStackTrace();
        }

        PrintWriter out = response.getWriter();
        JSONObject json=new JSONObject();
        ArrayList<JSONObject> array=new ArrayList<JSONObject>();
        for(int i=0;i<l.size();i++)
        {
            if(channel.equals("支付宝")) {
                Map p=l.get(i);
                System.out.println(p);
                JSONObject jsonObject = new JSONObject();
                Date date=new Date();
                jsonObject.put("Time",(date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+date.getDate());
                jsonObject.put("Money", p.get("platformBalance"));
                jsonObject.put("Fee", p.get("liquidationBalance"));
                jsonObject.put("OrgName", "支付宝");
                array.add(jsonObject);
            }
        }
        json.put("Info",array);
        out.println(json);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }

}
