package com.op;

import buaa.jj.accountservice.api.AccountService;
import com.altale.service.CSSystem;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.CSS;

public class FileDownload extends HttpServlet{

    private String[] FileName,Address;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String orgID = request.getParameter("orgID");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String fileType = request.getParameter("FileType");
        String accountType = request.getParameter("AccountType");
        String accountTime = request.getParameter("AccountTime");
        int type=0;
        if(accountType.equals("充值"))
            type=1;
        else if(accountType.equals("提现"))
            type=2;
        else if(accountType.equals("消费"))
            type=3;
        int days=0;
        if(accountTime.equals("最近15天"))
            days=15;
        else if(accountTime.equals("最近7天"))
            days=7;
        else if(accountTime.equals("最近3天"))
            days=3;

        String s=DubboHandler.INSTANCE.csSystem.DownloadFile(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date().getTime()-days*24*60*60*1000));
        System.out.println(s);
        JSONArray jsonArray=JSONArray.fromString(s);
        JSONObject json = new JSONObject();
        ArrayList<JSONObject> array=new ArrayList<JSONObject>();
        for (int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject jo=jsonArray.getJSONObject(i);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("No", i+1);
            jsonObject.put("FileName", jo.get("FileName"));
            jsonObject.put("Address", jo.get("Address"));
            array.add(jsonObject);
        }
        json.put("Info",array);
        out.println(json);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }

}
