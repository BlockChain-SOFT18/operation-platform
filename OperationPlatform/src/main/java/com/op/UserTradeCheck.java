package com.op;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.*;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserTradeCheck extends HttpServlet{

    private String[] OrderID,OrderTime,UserID,TradeType,TradeMoney;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String userID=request.getParameter("userID");
        //getInfo(userID);
        response.setContentType("text/html;charset=UTF-8");
        String tradeType=request.getParameter("TradeType");
        String startDate=request.getParameter("StartDate");
        String endDate=request.getParameter("EndDate");
        int id=Integer.valueOf(userID).intValue();
        int type=Integer.valueOf(userID).intValue();
        List<Map<String,String>> l=DubboHandler.INSTANCE.accountService.userTradeInformation(id,startDate,endDate,type);

        PrintWriter out = response.getWriter();
        JSONObject json=new JSONObject();
        for(int i=0;i<l.size();i++)
        {
            JSONObject jsonObject=new JSONObject();
            Map p=l.get(i);
            jsonObject.put("OrderID",p.get("OrderID").toString());
            jsonObject.put("OrderTime",p.get("date_time").toString());
            jsonObject.put("UserID",p.get("user_id").toString());
            jsonObject.put("TradeType",p.get("type").toString());
            jsonObject.put("TradeMoney",p.get("sum").toString());
            json.put("Info",jsonObject);
        }
        out.println(json);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doGet(request,response);
    }

}
