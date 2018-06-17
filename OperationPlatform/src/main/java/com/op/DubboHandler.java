package com.op;

import buaa.jj.accountservice.api.AccountService;
import buaa.jj.accountservice.api.Druid;
import com.altale.service.CSSystem;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class DubboHandler {
    public static DubboHandler INSTANCE;
    public static void init(){
        INSTANCE=new DubboHandler();
    }
    public AccountService accountService;
    public Druid druid;
    public CSSystem csSystem;
    private DubboHandler(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        accountService = (AccountService) context.getBean(AccountService.class);
        accountService=new AccountServiceWrapper(accountService);
        druid = (Druid) context.getBean(Druid.class);
        druid=new DruidWrapper(druid);
        csSystem = (CSSystem) context.getBean(CSSystem.class);
        csSystem=new CSSystemWrapper(csSystem);
        context.start();
    }
}
