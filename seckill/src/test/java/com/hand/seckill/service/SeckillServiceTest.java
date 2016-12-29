package com.hand.seckill.service;

import com.hand.seckill.dto.Exposer;
import com.hand.seckill.dto.SeckillExcution;
import com.hand.seckill.entity.Seckill;
import com.hand.seckill.exception.RepeatKillException;
import com.hand.seckill.exception.SeckillCloseException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.List;



/**
 * Created by Jo on 10/19/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
		"classpath:spring/spring-service.xml"})
public class SeckillServiceTest {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Resource
	SeckillService seckillService;

	@Test
	public void testGetSeckillList() throws Exception {
		List<Seckill> seckills = seckillService.getSeckillList();
		logger.info("1.seckills={}",seckills);
	}
	

	@Test
	public void testGetById() throws Exception {
		long id = 1000;
		Seckill seckill = seckillService.getById(id);
		logger.info("2.seckill={}",seckill);
	}
	
	@Test
	public void testExportSeckillUrl() throws Exception{
		long id=1000;
		Exposer exposer =seckillService.exposeSeckillUrl(id);
		logger.info("exposer={}", exposer);
	/**
	 * exposed=true, md5='67b4d1570056cbe97f6018cb6ade0240', seckillId=1000, now=0, startTime=0, endTime=0
	 */
	}
	
	@Test
	public void testSeckill() throws Exception{
		/**
		 * 再次提交会爆出异常
		 * com.hand.seckill.exception.SeckillException: seckill data rewrite
		 */
		long id =1000;
		long phone=123453463L;
		String md5="67b4d1570056cbe97f6018cb6ade0240";
		try{
			SeckillExcution excution=seckillService.executeSeckill(id, phone, md5);
			logger.info("result={}", excution);
		}catch (SeckillCloseException e1){
			logger.error(e1.getMessage());
		}catch (RepeatKillException e2){
			logger.error(e2.getMessage());
		}
		
		
		/**
		 * seckillId=1000, status=1, statusInfo='successkill',
		 *  successKill=SuccessKill{seckillId=1000, userPhone=123453463, 
		 *  status=0, createTime=Sat Dec 17 17:29:51 GMT+08:00 2016,
		 *   seckill=Seckill{seckillId=1000, name='1000元秒杀iphone6', 
		 *   number=99, startTime=Sat Dec 17 17:29:51 GMT+08:00 2016, 
		 *   endTime=Sun Dec 18 00:00:00 GMT+08:00 2016,
		 *    createTime=Sat Dec 17 11:00:34 GMT+08:00 2016
		 */
	}
	
	@Test
	public void testSeckillLogic() throws Exception {
		long id = 1003;
		Exposer exposer = seckillService.exposeSeckillUrl(id);
		if(exposer.isExposed()){
			logger.info("exposer={}",exposer);
			String md5 = exposer.getMd5();
			long phone = 18868831752l;
			try{
				SeckillExcution seckillExcution = seckillService.executeSeckill(id, phone, md5);
				logger.info("seckillExcuation={}"+seckillExcution);
			}catch (RepeatKillException e){
				logger.error(e.getMessage());
			}catch (SeckillCloseException e){
				logger.error(e.getMessage());
			}
		}
		else{
			//seckill don't start
			logger.warn("exposer={}",exposer);
		}
	}

	/*@Test
	public void testExecuteSeckill() throws Exception {
		long id = 1002l;
		long phone = 18868831752l;
		try{
			String md5 = "2bf959ac10da022a1bc9a7c98c4b42c8";
			SeckillExcution seckillExcution = seckillService.executeSeckill(id, phone, md5);
			logger.info("seckillExcuation={}"+seckillExcution);
		}catch (RepeatKillException e){
			logger.error(e.getMessage());
		}catch (SeckillCloseException e){
			logger.error(e.getMessage());
		}

	}*/
	/*@Test
	public void executeSeckillProcedure(){
		long seckillId = 1004;
		long phone = 18868831756l;
		Exposer exposer = seckillService.exposeSeckillUrl(seckillId);
		if(exposer.isExposed()){
			String md5 = exposer.getMd5();
			SeckillExcution seckillExcution = seckillService.executeSeckillProcedure(seckillId, phone, md5);
			logger.info("============="+seckillExcution.getStatusInfo());
		}
	}*/

}