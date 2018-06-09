package com.op;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TradeCheck extends HttpServlet{

    private String[] OrderID,OrderTime,UserID,TradeType,TradeMoney;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String orgID=request.getParameter("orgID");
        getInfo(orgID);
        response.setContentType("text/html;charset=UTF-8");
        String tradeType=request.getParameter("TradeType");
        String startDate=request.getParameter("StartDate");
        String endDate=request.getParameter("EndDate");

        PrintWriter out = response.getWriter();
        JSONObject json=new JSONObject();
        for(int i=0;i<OrderID.length;i++)
        {
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("OrderID",OrderID[i]);
            jsonObject.put("OrderTime",OrderTime[i]);
            jsonObject.put("UserID",UserID[i]);
            jsonObject.put("TradeType",TradeType[i]);
            jsonObject.put("TradeMoney",TradeMoney[i]);
            json.put("Info",jsonObject);
        }
        out.println(json);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doGet(request,response);
    }

    public void getInfo(String orgID){
        OrderID=new String[2];
        OrderTime=new String[2];
        UserID=new String[2];
        TradeType=new String[2];
        TradeMoney=new String[2];

        OrderID[0]="654321";
        OrderTime[0]="2018/4/1 23:59:59";
        UserID[0]="233333";
        TradeType[0]="转账";
        TradeMoney[0]="1000";

        OrderID[1]="521314";
        OrderTime[1]="2018/4/2 00:00:00";
        UserID[1]="123131";
        TradeType[1]="充值";
        TradeMoney[1]="10000";
    }
}