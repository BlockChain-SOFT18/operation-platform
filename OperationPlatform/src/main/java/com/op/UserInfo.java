package com.op;

import org.apache.catalina.User;

import java.io.*;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserInfo extends HttpServlet{

    private String State;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/xml;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String userID=request.getParameter("userID");
        String msg=request.getParameter("msg");
        int id=Integer.valueOf(userID).intValue();

        out.println("<Information> \n ");
        if(msg.equals("Freeze")) {
            int result=DubboHandler.INSTANCE.accountService.freezeUnfreeze(id, true);
            out.println("<Info>" + result + "</Info> \n");
        }
        else if(msg.equals("Active")){
            int result=DubboHandler.INSTANCE.accountService.freezeUnfreeze(id, false);
            out.println("<Info>" + result + "</Info> \n");
        }
        else if(msg.equals("ChangePwd"))
        {
            String pwd1=request.getParameter("pwd1");
            String pwd2=request.getParameter("pwd2");
            try {
                boolean result = DubboHandler.INSTANCE.accountService.userPasswdChanging(id, Encrypt.SHA256(pwd1), Encrypt.SHA256(pwd2));
                System.out.println(result);
                out.println("<Info>" + result + "</Info> \n");
            }catch (Exception e){
                out.println("<Info>" + false + "</Info> \n");
                e.printStackTrace();
            }
        }
        else if(msg.equals("Load"))
        {
            Map p=DubboHandler.INSTANCE.accountService.userInformation(id);
            if(p.get("ifFrozen").toString().equals("true"))
                State="冻结";
            else State="激活";
            out.println("<Info> \n "+ userID +" \n </Info> \n");
            out.println("<Info> \n "+ p.get("userName").toString() +" \n </Info> \n");
            out.println("<Info> \n "+ p.get("userRealName").toString() +" \n </Info> \n");
            out.println("<Info> \n "+ State +" \n </Info> \n");
        }
        out.println("</Information>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }

}
