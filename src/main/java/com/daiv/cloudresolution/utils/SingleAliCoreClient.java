package com.daiv.cloudresolution.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.daiv.cloudresolution.ymlEntity.ParaMeterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SingleAliCoreClient {

    @Autowired
    ParaMeterEntity paraMeterEntity;

    volatile private static  IAcsClient client=null;
    private SingleAliCoreClient(){
    }
    public IAcsClient getClient(){
        if(null!=client){
            return client;
        }else{
            try {
                //创建实例之前可能会有一些准备性的耗时工作
                Thread.sleep(300);
                synchronized (SingleAliCoreClient.class){
                    if(null==client){
                        // 创建DefaultAcsClient实例并初始化
                        DefaultProfile profile = DefaultProfile.getProfile(
                                paraMeterEntity.getRegionId(),          // 您的登录帐号
                                paraMeterEntity.getAccessKeyId(),      // 您的Access Key ID
                                paraMeterEntity.getSecret());          // 您的Access Key Secret
                        return client=new DefaultAcsClient(profile);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    return client;
    }
}