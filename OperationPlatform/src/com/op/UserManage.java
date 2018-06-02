package com.op;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserManage extends HttpServlet{

    private String[] UserID,UserName,State;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        String orgID=request.getParameter("orgID");
        String att=request.getParameter("att");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        JSONObject json=new JSONObject();
        if(att.equals("List"))
        {
            getInfo(orgID);
            for(int i=0;i<UserID.length;i++)
            {
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("UserID",UserID[i]);
                jsonObject.put("UserName",UserName[i]);
                jsonObject.put("State",State[i]);
                json.put("Info",jsonObject);
            }
        }
        else if(att.equals("Search"))
        {
            String userID=request.getParameter("UserID");
            String userName=request.getParameter("UserName");
            String state=request.getParameter("State");
            searchInfo(orgID,userID,userName,state);
            for(int i=0;i<UserID.length;i++)
            {
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("UserID",UserID[i]);
                jsonObject.put("UserName",UserName[i]);
                jsonObject.put("State",State[i]);
                json.put("Info",jsonObject);
            }
        }
        else if(att.equals("Freeze"))
        {
            String changeID=request.getParameter("ChangeID");
            freeze(orgID,changeID);
            json.put("Info",changeID);
        }
        else if(att.equals("Active"))
        {
            String changeID=request.getParameter("ChangeID");
            active(orgID,changeID);
            json.put("Info",changeID);
        }
        out.println(json);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        doGet(request,response);
    }

    public void getInfo(String orgID){
        UserID=new String[3];
        UserName=new String[3];
        State=new String[3];

        UserID[0]="233333";
        UserName[0]="DeadPool";
        State[0]="激活";

        UserID[1]="123131";
        UserName[1]="Wed";
        State[1]="冻结";

        UserID[2]="654321";
        UserName[2]="HHH";
        State[2]="冻结";
    }

    public void searchInfo(String orgID,String userID,String userName,String state){
        UserID=new String[2];
        UserName=new String[2];
        State=new String[2];

        UserID[0]="233333";
        UserName[0]="DeadPool";
        State[0]="激活";

        UserID[1]="123131";
        UserName[1]="Wed";
        State[1]="冻结";
    }

    public void freeze(String orgID,String changeID){

    }

    public void active(String orgID,String changeID){

    }
}
