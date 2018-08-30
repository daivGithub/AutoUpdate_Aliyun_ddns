package com.wqw.cloudresolution.service.impl;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.IAcsClient;
import com.wqw.cloudresolution.jsonEntity.DescribeDomainRecords;
import com.wqw.cloudresolution.service.JobService;
import com.wqw.cloudresolution.utils.SingleAliCoreClient;
import com.wqw.cloudresolution.utils.SystemUtils;
import com.wqw.cloudresolution.ymlEntity.ParaMeterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class JobServiceImpl implements JobService {
    @Autowired
    private SystemUtils systemUtils;
    @Autowired
    private SingleAliCoreClient singleAliCoreClient;
    @Autowired
    private ParaMeterEntity paraMeterEntity;
    @Autowired
    private StringRedisTemplate redisTemplate;
    @Override
    public void updateLocalHostAliyunDomainJob() {
        try {
        String ip=systemUtils.getLocalAddress();
        System.out.println(ip);
        if(null==ip||"".equals(ip)){
            throw new RuntimeException("获取外网IP失败");
        }
        if(null==redisTemplate.opsForValue().get("ip"))
            redisTemplate.opsForValue().set("ip",ip);
        else{
            if(redisTemplate.opsForValue().get("ip").equals(ip)){
                return;
            }else{
                redisTemplate.opsForValue().set("ip",ip);
            }
        }

        IAcsClient client = singleAliCoreClient.getClient();
        CommonRequest request1 = new CommonRequest();


        request1.setDomain("alidns.aliyuncs.com");
        request1.setAction("DescribeDomainRecords");
        request1.setVersion("2015-01-09");
        request1.putQueryParameter("DomainName",paraMeterEntity.getDomainName());
        request1.putQueryParameter("TypeKeyWord",paraMeterEntity.getTypeKeyWord());

        // 创建API请求并设置参数
        CommonRequest request = new CommonRequest();
        //获取用户域名下所有解析
        request.setDomain("alidns.aliyuncs.com");
        request.setVersion("2015-01-09");
        request.setAction("UpdateDomainRecord");
        request.putQueryParameter("Type", paraMeterEntity.getTypeKeyWord());
        request.putQueryParameter("Value",ip);


            String rRKeyWord=paraMeterEntity.getRRKeyWord();

            for (String s : rRKeyWord.split(",")) {

                request1.putQueryParameter("RRKeyWord",s);
                String json=client.getCommonResponse(request1).getData();
                DescribeDomainRecords obj=JSON.parseObject(json,DescribeDomainRecords.class);
                String RecordId=obj.getDomainRecords().getRecord().get(0).getRecordId();
                request.putQueryParameter("RecordId",RecordId);
                request.putQueryParameter("RR", s);

                CommonResponse response = client.getCommonResponse(request);
                System.out.println(String.format(s + paraMeterEntity.getDomainName() + "--------更新" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())));
                System.out.println("更细结果："+response.getData());

            }




        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}