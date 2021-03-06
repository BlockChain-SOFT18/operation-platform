package com.op;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManualCharge extends HttpServlet{

    private String[] OrderID,OrderTime,UserID,TradeType,TradeMoney,TradeState;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String orgID=request.getParameter("orgID");
        String att=request.getParameter("att");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        JSONObject json=new JSONObject();
        ArrayList<JSONObject> array=new ArrayList<JSONObject>();
        if(att.equals("Search"))
        {
            String tradeType=request.getParameter("TradeType");
            int type=0;
            if(tradeType.equals("充值"))
                type=0;
            else if(tradeType.equals("提现"))
                type=1;
            else if(tradeType.equals("消费"))
                type=2;
            String time=request.getParameter("Time");
            int days=0;
            if(time.equals("最近14天"))
                days=14;
            else if(time.equals("最近7天"))
                days=7;
            else if(time.equals("最近3天"))
                days=3;

            DateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String s=DubboHandler.INSTANCE.csSystem.QueryRecord(df.format(new Date().getTime()-days*24*60*60*1000),df.format(new Date()),type);
            System.out.println(s);
            JSONArray jsonArray=JSONArray.fromString(s);
            for(int i=0;i<jsonArray.length();i++)
            {
                JSONObject jo=jsonArray.getJSONObject(i);
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("OrderID",jo.get("requestID"));
                jsonObject.put("OrderTime",jo.get("requestTime"));
                jsonObject.put("UserID",jo.get("userID"));
                if(jo.get("method").equals(0))
                    jsonObject.put("TradeType","充值");
                else if(jo.get("method").equals(1))
                    jsonObject.put("TradeType","提现");
                else if(jo.get("method").equals(2))
                    jsonObject.put("TradeType","消费");
                jsonObject.put("TradeMoney",jo.get("amount"));
                jsonObject.put("TradeState","已清分");
                array.add(jsonObject);
            }
            json.put("Info",array);
        }
        else if(att.equals("Adjust"))
        {
            String money=request.getParameter("Money");
            String changeID=request.getParameter("ChangeID");
            adjust(orgID,changeID,money);
            json.put("Info",changeID+" adjust "+money);
        }
        out.println(json);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }

    public void adjust(String orgID,String changeID,String money){
        int id=Integer.valueOf(changeID);
        double sum=Double.valueOf(money);
        DubboHandler.INSTANCE.accountService.reCharge(id,sum,true);
    }
}
