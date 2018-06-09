package com.op;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Poundage extends HttpServlet{

    private String[] Time,Money,Fee,TradeType,OrgName;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String orgID=request.getParameter("orgID");
        getInfo(orgID);
        response.setContentType("text/html;charset=UTF-8");
        String startDate=request.getParameter("StartDate");
        String endDate=request.getParameter("EndDate");
        String channel=request.getParameter("Channel");

        PrintWriter out = response.getWriter();
        JSONObject json=new JSONObject();
        for(int i=0;i<Time.length;i++)
        {
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("Time",Time[i]);
            jsonObject.put("Money",Money[i]);
            jsonObject.put("Fee",Fee[i]);
            jsonObject.put("TradeType",TradeType[i]);
            jsonObject.put("OrgName",OrgName[i]);
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
        Time=new String[2];
        Money=new String[2];
        Fee=new String[2];
        TradeType=new String[2];
        OrgName=new String[2];

        Time[0]="2018/4/1";
        Money[0]="1000000";
        Fee[0]="1000";
        TradeType[0]="充值";
        OrgName[0]="支付宝";

        Time[1]="2018/4/2";
        Money[1]="980000";
        Fee[1]="980";
        TradeType[1]="提现";
        OrgName[1]="微信";
    }
}