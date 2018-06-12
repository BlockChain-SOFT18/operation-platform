package com.op;

import com.altale.service.CSException.OperatorIdOutOfRangeException;
import com.altale.service.CSException.RequestException;
import com.altale.service.CSException.TimeOutOfRangeException;
import com.altale.service.CSSystem;

public class CSSystemWrapper implements CSSystem{

    private CSSystem csSystem;

    public CSSystemWrapper(CSSystem csSystem){
        this.csSystem=csSystem;
    }

    public String Recharge(String var1, String var2, double var3, boolean var5, String var6) throws RequestException{
        return "2333";
    }

    public String Withdraw(String var1, String var2, double var3, boolean var5, String var6) throws RequestException{
        return "233";
    }

    public String Trade(String var1, String var2, String var3, double var4, String var6) throws RequestException{
        return "233";
    }

    public String QueryRecord(String StartDate, String EndDate, int Type) throws TimeOutOfRangeException, OperatorIdOutOfRangeException{
        if(csSystem!=null)
            return csSystem.QueryRecord(StartDate,EndDate,Type);
        //测试数据
        return "[" +
                "{Request_id:1,Request_time:'2018-6-10 10:10:10.0',Merchant_id:19,Amt:10.0,Operate_status:'已清分'}," +
                "{Request_id:3,Request_time:'2018-6-10 12:36:23.0',Merchant_id:15,Amt:17.3,Operate_status:'已清分'}," +
                "{Request_id:6,Request_time:'2018-6-10 16:57:45.0',Merchant_id:16,Amt:200.0,Operate_status:'已清分'}," +
                "]";
    }

    public String DownloadFile(String Date) throws TimeOutOfRangeException{
        if(csSystem!=null)
            return csSystem.DownloadFile(Date);
        //测试数据
        return "[" +
                "{FileName:'支付宝',Address:'http://www.alibaba.com'}," +
                "{FileName:'微信',Address:'http://www.tencent.com'}," +
                "{FileName:'京东钱包',Address:'http://www.JD.com'}," +
                "]";
    }

}
