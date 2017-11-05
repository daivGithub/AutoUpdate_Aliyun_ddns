package com.wqw.cloudresolution.utils;

import com.wqw.cloudresolution.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Jobs {

    @Autowired
    private JobService jobService;

    public final static long ONE_Minute =  60 * 1000;

    @Scheduled(fixedRate  = 5*ONE_Minute)
    public void updateAliyunDomainJob(){
        jobService.updateLocalHostAliyunDomainJob();
    }
}