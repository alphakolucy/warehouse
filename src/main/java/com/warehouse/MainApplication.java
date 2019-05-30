package com.warehouse;
 
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory; 
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix; 
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.warehouse.service.repository.wisely.WiselyRepositoryImpl;


@EnableFeignClients 
@EnableDiscoveryClient
@SpringBootApplication
@EnableHystrix      
@EnableJpaRepositories(repositoryBaseClass = WiselyRepositoryImpl.class)
public class MainApplication {  
	
	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args); 
	} 
	
//	@Override
//    public void run(String... args) throws Exception {
//        Logger logger = LoggerFactory.getLogger(MainApplication.class);
//        logger.info("测试log");
//
//        for (int i = 0; i < 10; i++) {
//            logger.error("something wrong. id={}; name=Ryan-{};", i, i);
//        }
//    }
	 
}