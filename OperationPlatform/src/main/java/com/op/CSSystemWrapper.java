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
        return "233";
    }

    public String Withdraw(String var1, String var2, double var3, boolean var5, String var6) throws RequestException{
        return "233";
    }

    public String Trade(String var1, String var2, String var3, double var4, String var6) throws RequestException{
        return "233";
    }

    public String QueryRecord(String var1, String var2, int var3) throws TimeOutOfRangeException, OperatorIdOutOfRangeException{
        return "233";
    }

    public String DownloadFile(String var1) throws TimeOutOfRangeException{
        return "23333";//csSystem.DownloadFile(var1);
    }

}
