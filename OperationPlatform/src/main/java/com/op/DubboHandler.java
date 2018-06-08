package com.op;

import buaa.jj.accountservice.api.AccountService;
import com.altale.service.CSSystem;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class DubboHandler {
    public static DubboHandler INSTANCE;
    public static void init(){
        INSTANCE=new DubboHandler();
    }
    public AccountService accountService;
    public CSSystem csSystem;
    private DubboHandler(){
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
            accountService = (AccountService) context.getBean(AccountService.class);
            accountService=new AccountServiceWrapper(accountService);
            csSystem = (CSSystem) context.getBean(CSSystem.class);
            csSystem=new CSSystemWrapper(csSystem);
        }catch (Exception e){
            e.printStackTrace();
        }
        //context.start();

    }
}
