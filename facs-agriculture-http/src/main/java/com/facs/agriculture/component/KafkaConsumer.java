package com.facs.agriculture.component;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * 接收从kafka传入的数据
 *
 * @author luke
 * @create 2017-11-20 11:48
 **/
//@Configuration
//@EnableKafka
public class KafkaConsumer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @KafkaListener(topics = {"tongyeyun_test"})
    public void testConsumer(String content) {
        System.out.println("test:"+content);
        if(StringUtils.isBlank(content)){
            return;
        }
        System.out.println("response:");
    }
}
