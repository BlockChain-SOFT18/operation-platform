package com.op;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserManage extends HttpServlet{

    private String State;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        String orgID=request.getParameter("orgID");
        String att=request.getParameter("att");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        int id=Integer.valueOf(orgID).intValue();

        JSONObject json=new JSONObject();
        ArrayList<JSONObject> array=new ArrayList<JSONObject>();
        if(att.equals("List"))
        {
            List<Integer> l=DubboHandler.INSTANCE.accountService.agencyAllUser(id);
            for(int i=0;i<l.size();i++)
            {
                Map p=DubboHandler.INSTANCE.accountService.userInformation(l.get(i));
                if(p.get("ifFrozen").toString().equals("true"))
                    State="冻结";
                else State="激活";
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("UserID",l.get(i));
                jsonObject.put("UserName",p.get("userName").toString());
                jsonObject.put("State",State);
                array.add(jsonObject);
            }
            json.put("Info",array);
        }
        else if(att.equals("Search"))
        {
            String userID=request.getParameter("UserID");
            String userName=request.getParameter("UserName");
            String state=request.getParameter("State");
            List<Integer> l=DubboHandler.INSTANCE.accountService.agencyAllUser(id);
            for(int i=0;i<l.size();i++)
            {
                Map p=DubboHandler.INSTANCE.accountService.userInformation(l.get(i));
                if(p.get("ifFrozen").toString().equals("true"))
                    State="冻结";
                else State="激活";
                boolean f1=false,f2=false,f3=false;
                if(userID==""||userID!=""&&Integer.valueOf(userID).intValue()==l.get(i))
                    f1=true;
                if(userName==""||userName!=""&&userName.equals(p.get("userName")))
                    f2=true;
                if(state==""||state!=""&&state.equals(State))
                    f3=true;
                if(f1&&f2&&f3) {
                    JSONObject jsonObject=new JSONObject();
                    jsonObject.put("UserID",l.get(i));
                    jsonObject.put("UserName",p.get("userName").toString());
                    jsonObject.put("State",State);
                    array.add(jsonObject);
                }
            }
            json.put("Info",array);
        }
        else if(att.equals("Freeze"))
        {
            int changeID=Integer.valueOf(request.getParameter("ChangeID")).intValue();
            DubboHandler.INSTANCE.accountService.freezeUnfreeze(changeID,true);
            json.put("Info",changeID);
        }
        else if(att.equals("Active"))
        {
            int changeID=Integer.valueOf(request.getParameter("ChangeID")).intValue();
            DubboHandler.INSTANCE.accountService.freezeUnfreeze(changeID,false);
            json.put("Info",changeID);
        }
        out.println(json);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }

}
