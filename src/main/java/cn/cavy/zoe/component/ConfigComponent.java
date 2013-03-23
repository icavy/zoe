package cn.cavy.zoe.component;

import java.util.Properties;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import cn.cavy.common.util.StringUtil;
import cn.cavy.zoe.service.ConfigService;

@Service("configComponent")
public class ConfigComponent {
    
    @Resource
    Properties configProperties;

    @Resource
    ConfigService configService;

    public String getConfig(String key) {
        String value = configService.getValue(key);
        if (StringUtil.isEmpty(value))
            value = configProperties.getProperty(key);
        return value;
    }
}