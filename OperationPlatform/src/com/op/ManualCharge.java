package com.op;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManualCharge extends HttpServlet{

    private String[] OrderID,OrderTime,UserID,TradeType,TradeMoney,TradeState;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String orgID=request.getParameter("orgID");
        String att=request.getParameter("att");
        if(att.equals("Search"))
        {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            JSONObject json=new JSONObject();
            String tradeType=request.getParameter("TradeType");
            String userID=request.getParameter("UserID");
            String startDate=request.getParameter("StartDate");
            String endDate=request.getParameter("EndDate");
            searchInfo(orgID,tradeType,userID,startDate,endDate);
            for(int i=0;i<UserID.length;i++)
            {
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("OrderID",OrderID[i]);
                jsonObject.put("OrderTime",OrderTime[i]);
                jsonObject.put("UserID",UserID[i]);
                jsonObject.put("TradeType",TradeType[i]);
                jsonObject.put("TradeMoney",TradeMoney[i]);
                jsonObject.put("TradeState",TradeState[i]);
                json.put("Info",jsonObject);
            }
            out.println(json);
        }
        else if(att.equals("Adjust"))
        {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            JSONObject json=new JSONObject();
            String money=request.getParameter("Money");
            String changeID=request.getParameter("ChangeID");
            adjust(orgID,changeID,money);
            json.put("Info",changeID+" adjust "+money);
            out.println(json);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doGet(request,response);
    }

    public void searchInfo(String orgID,String tradeType,String userID,String startDate,String endDate){
        OrderID=new String[2];
        OrderTime=new String[2];
        UserID=new String[2];
        TradeType=new String[2];
        TradeMoney=new String[2];
        TradeState=new String[2];

        OrderID[0]="654321";
        OrderTime[0]="2018/4/1 23:59:59";
        UserID[0]="233333";
        TradeType[0]="转账";
        TradeMoney[0]="1000";
        TradeState[0]="完成";

        OrderID[1]="521314";
        OrderTime[1]="2018/4/2 00:00:00";
        UserID[1]="123131";
        TradeType[1]="充值";
        TradeMoney[1]="10000";
        TradeState[1]="处理中";
    }

    public void adjust(String orgID,String changeID,String money){

    }
}
