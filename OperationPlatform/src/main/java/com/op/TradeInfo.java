package com.op;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TradeInfo extends HttpServlet{

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String orgID=request.getParameter("orgID");
        response.setContentType("text/html;charset=UTF-8");
        String tradeType=request.getParameter("TradeType");
        String Date1=request.getParameter("Date1");
        String Date2=request.getParameter("Date2");
        int id=Integer.valueOf(orgID).intValue();

        PrintWriter out = response.getWriter();
        JSONObject json=new JSONObject();
        ArrayList<JSONObject> array=new ArrayList<JSONObject>();
        Date date=new Date();
        for(int i=0;i<15;i++)
        {
            double Money=0;
            int TotalCount=0,RealCount=0;
            String startDate=(date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+(date.getDate()-i)+" 00:00:00";
            String endDate=(date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+(date.getDate()-i)+" 23:59:59";
            System.out.println(startDate+" "+endDate);
            List<Map<String,String>> l1=DubboHandler.INSTANCE.accountService.agencyTradeInformation(id,startDate,endDate,0);
            List<Map<String,String>> l2=DubboHandler.INSTANCE.accountService.agencyTradeInformation(id,startDate,endDate,1);
            List<Map<String,String>> l3=DubboHandler.INSTANCE.accountService.agencyTradeInformation(id,startDate,endDate,2);
            for(int j=0;j<l1.size();j++)
            {
                Map p=l1.get(j);
                Iterator it = p.entrySet().iterator();
                Map.Entry m=(Map.Entry)it.next();
                try {
                    JSONObject js = JSONObject.fromObject(m.getValue());
                    if (js.get("type").equals("true")) {
                        TotalCount++;
                        RealCount++;
                        Money += Double.valueOf(js.get("sum").toString());
                        System.out.println(Money);
                    } else TotalCount++;
                }catch (Exception e){
                    System.out.println("Make IDE happy");
                }
            }
            for(int j=0;j<l2.size();j++)
            {
                Map p=l2.get(j);
                Iterator it = p.entrySet().iterator();
                Map.Entry m=(Map.Entry)it.next();
                try {
                    JSONObject js = JSONObject.fromObject(m.getValue());
                    if (js.get("type").equals("true")) {
                        TotalCount++;
                        RealCount++;
                        Money += Double.valueOf(js.get("sum").toString());
                        System.out.println(Money);
                    } else TotalCount++;
                }catch (Exception e){
                    System.out.println("Make IDE happy");
                }
            }
            for(int j=0;j<l3.size();j++)
            {
                Map p=l3.get(j);
                Iterator it = p.entrySet().iterator();
                Map.Entry m=(Map.Entry)it.next();
                try {
                    JSONObject js = JSONObject.fromObject(m.getValue());
                    if (js.get("type").equals("true")) {
                        TotalCount++;
                        RealCount++;
                        Money += Double.valueOf(js.get("sum").toString());
                        System.out.println(Money);
                    } else TotalCount++;
                }catch (Exception e){
                    System.out.println("Make IDE happy");
                }
            }
            if(TotalCount>0)
            {
                String TransferRate = String.valueOf((double) Math.round(Double.valueOf(RealCount) / Double.valueOf(TotalCount) * 10000) / 100) + "%";
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("Date", (date.getYear()+1900)+"-"+(date.getMonth()+1)+"-"+date.getDate());
                jsonObject.put("Money", Money);
                jsonObject.put("RealCount", RealCount);
                jsonObject.put("TotalCount", TotalCount);
                jsonObject.put("TransferRate", TransferRate);
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
