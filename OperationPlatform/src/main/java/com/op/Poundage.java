package com.op;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
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
        //后端未实现接口，使用假数据进行测试
        getInfo(orgID);
        response.setContentType("text/html;charset=UTF-8");
        String startDate=request.getParameter("StartDate");
        String endDate=request.getParameter("EndDate");
        String channel=request.getParameter("Channel");

        PrintWriter out = response.getWriter();
        JSONObject json=new JSONObject();
        ArrayList<JSONObject> array=new ArrayList<JSONObject>();
        for(int i=0;i<Time.length;i++)
        {
            if(channel.equals(OrgName[i])) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("Time", Time[i]);
                jsonObject.put("Money", Money[i]);
                jsonObject.put("Fee", Fee[i]);
                jsonObject.put("TradeType", TradeType[i]);
                jsonObject.put("OrgName", OrgName[i]);
                array.add(jsonObject);
            }
        }
        json.put("Info",array);
        out.println(json);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doGet(request,response);
    }

    public void getInfo(String orgID){
        Time=new String[8];
        Money=new String[8];
        Fee=new String[8];
        TradeType=new String[8];
        OrgName=new String[8];

        Time[0]="2018/6/1";
        Money[0]="1000000";
        Fee[0]="1000";
        TradeType[0]="充值";
        OrgName[0]="支付宝";

        Time[1]="2018/6/2";
        Money[1]="980000";
        Fee[1]="980";
        TradeType[1]="充值";
        OrgName[1]="微信";

        Time[2]="2018/6/1";
        Money[2]="53000";
        Fee[2]="53";
        TradeType[2]="充值";
        OrgName[2]="京东钱包";

        Time[3]="2018/5/31";
        Money[3]="760000";
        Fee[3]="760";
        TradeType[3]="充值";
        OrgName[3]="蚂蚁花呗";

        Time[4]="2018/6/7";
        Money[4]="340000";
        Fee[4]="340";
        TradeType[4]="提现";
        OrgName[4]="支付宝";

        Time[5]="2018/6/8";
        Money[5]="160000";
        Fee[5]="160";
        TradeType[5]="提现";
        OrgName[5]="微信";

        Time[6]="2018/6/8";
        Money[6]="200";
        Fee[6]="0.2";
        TradeType[6]="提现";
        OrgName[6]="京东钱包";

        Time[7]="2018/6/6";
        Money[7]="740000";
        Fee[7]="740";
        TradeType[7]="提现";
        OrgName[7]="蚂蚁花呗";
    }
}
