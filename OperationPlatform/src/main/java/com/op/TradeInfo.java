package com.op;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TradeInfo extends HttpServlet{

    private String[] Date,Money,RealCount,TotalCount,TransferRate;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String orgID=request.getParameter("orgID");
        getInfo(orgID);
        response.setContentType("text/html;charset=UTF-8");
        String tradeType=request.getParameter("TradeType");
        String Date1=request.getParameter("Date1");
        String Date2=request.getParameter("Date2");

        PrintWriter out = response.getWriter();
        JSONObject json=new JSONObject();
        ArrayList<JSONObject> array=new ArrayList<JSONObject>();
        for(int i=0;i<Date.length;i++)
        {
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("Date",Date[i]);
            jsonObject.put("Money",Money[i]);
            jsonObject.put("RealCount",RealCount[i]);
            jsonObject.put("TotalCount",TotalCount[i]);
            jsonObject.put("TransferRate",TransferRate[i]);
            array.add(jsonObject);
        }
        json.put("Info",array);
        out.println(json);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }

    public void getInfo(String orgID){
        Date=new String[2];
        Money=new String[2];
        RealCount=new String[2];
        TotalCount=new String[2];
        TransferRate=new String[2];

        Date[0]="2018/4/1";
        Money[0]="10000";
        RealCount[0]="15";
        TotalCount[0]="17";
        TransferRate[0]=String.valueOf((double)Math.round(Double.valueOf(RealCount[0])/Double.valueOf(TotalCount[0])*10000)/100)+"%";

        Date[1]="2018/4/2";
        Money[1]="13000";
        RealCount[1]="13";
        TotalCount[1]="19";
        TransferRate[1]=String.valueOf((double)Math.round(Double.valueOf(RealCount[1])/Double.valueOf(TotalCount[1])*10000)/100)+"%";

    }
}
