package com.op;

import buaa.jj.accountservice.api.AccountService;
import buaa.jj.accountservice.exceptions.*;

import java.util.*;

public class AccountServiceWrapper implements AccountService{

    private AccountService accountService;

    public AccountServiceWrapper(AccountService accountService){
        this.accountService=accountService;
    }

    public int userLogin(String user_name, String user_passwd){
        if(accountService!=null)
            try {
                return accountService.userLogin(user_name, user_passwd);
            }catch (Exception e){
                if(e instanceof UserNotExistException)
                    return -1;
                else return -2;
            }
        else return -1;
    }

    public int agencyLogin(String agency_name, String agency_passwd) {
        if(accountService!=null)
            return accountService.agencyLogin(agency_name, agency_passwd);
        else return -1;
    }

    public int userRegister(String user_name, String user_passwd, String user_realname, String user_tel, String user_email, String user_identity, int under_agency_id) throws NameDuplicateException, UserAgencyDuplicateException, AgencyNotExistException {
        return accountService.userRegister( user_name,  user_passwd,  user_realname,  user_tel,  user_email,  user_identity,  under_agency_id);
    }

    public int userRegister(String user_name, String user_passwd, String user_realname, String user_tel, String user_email, String user_identity, String under_agency_name) throws NameDuplicateException, UserAgencyDuplicateException, AgencyNotExistException {
        return accountService.userRegister( user_name,  user_passwd,  user_realname,  user_tel,  user_email,  user_identity,  under_agency_name);
    }

    public boolean userPasswdChanging(int user_id, String old_passwd, String new_passwd) throws UserNotExistException, UserFrozenException {
        return accountService.userPasswdChanging(user_id, old_passwd, new_passwd);
    }

    public Map agencyInformation(int agency_id) {
        if(accountService!=null)
            return accountService.agencyInformation(agency_id);
        Map p=new HashMap();
        //测试数据
        p.put("agencyID",1);
        p.put("agencyName","北京航空航天大学");
        p.put("agentName","徐惠彬");
        p.put("agentTel","15652575555");
        return p;
    }

    public List<Integer> agencyAllUser(int agency_id) {
        return accountService.agencyAllUser(agency_id);
    }

    public Map userInformation(int user_id) {
        if(accountService!=null)
            return accountService.userInformation(user_id);
        Map p=new HashMap();
        //测试数据
        p.put("userName","Hestia");
        p.put("userRealName","LXH");
        p.put("ifFrozen",1);
        return p;
    }

    public int freezeUnfreeze(int user_id, boolean if_freeze) throws UserNotExistException {
        return accountService.freezeUnfreeze(user_id, if_freeze);
    }

    public boolean foundPasswd(String user_name, String user_identity, String new_passwd) throws UserNotExistException, UserFrozenException {
        return accountService.foundPasswd(user_name, user_identity, new_passwd);
    }

    public List<Map<String, String>> agencyTradeInformation(int agency_id, String start_date, String end_date, int trade_type) {
        List<Map<String,String>> l=accountService.agencyTradeInformation(agency_id, start_date, end_date, trade_type);
        //测试数据
        if(l==null)
        {
            System.out.println("NULL RESPONSE FROM AS");
            l=new ArrayList<Map<String, String>>();
            return l;
        }
        else return l;
    }

    public List<Map<String, String>> userTradeInformation(int user_id, String start_date, String end_date, int trade_type) {
        List<Map<String, String>> l= accountService.userTradeInformation(user_id, start_date, end_date, trade_type);
        //测试数据
        if(l==null)
        {
            System.out.println("NULL RESPONSE FROM AS");
            l=new ArrayList<Map<String, String>>();
            return l;
        }
        else return l;
    }

    public boolean transferConsume(int pay_user_id, int get_user_id, double amount, boolean trade_type) throws UserNotExistException, UserFrozenException {
        return accountService.transferConsume(pay_user_id, get_user_id, amount, trade_type);
    }

    public boolean reCharge(int user_id, double amount, boolean recharge_platform) throws UserNotExistException {
        return accountService.reCharge(user_id, amount, recharge_platform);
    }

    public boolean drawMoney(int user_id, double amount, boolean draw_platform) throws UserNotExistException {
        return accountService.drawMoney(user_id, amount, draw_platform);
    }

    public int getID(String name, boolean type) {
        return accountService.getID(name, type);
    }

    public void CSSystemReady() {
        accountService.CSSystemReady();
    }

    public void BlockChainServiceReady() {
        accountService.BlockChainServiceReady();
    }

    public void CSSystemClosing() {
        accountService.CSSystemClosing();
    }

    public void BlockChainServiceClosing() {
        accountService.BlockChainServiceClosing();
    }
}
