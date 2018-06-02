package com.op;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileDownload extends HttpServlet{

    private String[] FileName,Address;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String orgID = request.getParameter("orgID");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        JSONObject json = new JSONObject();
        String fileType = request.getParameter("FileType");
        String accountType = request.getParameter("AccountType");
        String accountTime = request.getParameter("AccountTime");
        searchInfo(orgID, fileType, accountType, accountTime);
        for (int i = 0; i < FileName.length; i++)
        {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("No", i);
            jsonObject.put("FileName", FileName[i]);
            jsonObject.put("Address", Address[i]);
            json.put("Info", jsonObject);
        }
        out.println(json);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doGet(request,response);
    }

    public void searchInfo(String orgID,String fileType,String accountType,String accountTime){
        FileName=new String[3];
        Address=new String [3];

        FileName[0]="支付宝";
        Address[0]="www.baidu.com";

        FileName[1]="微信";
        Address[1]="www.google.com";

        FileName[2]="QQ钱包";
        Address[2]="www.taobao.com";
    }
}
