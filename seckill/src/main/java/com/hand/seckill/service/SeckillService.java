package com.hand.seckill.service;



import java.util.List;

import com.hand.seckill.dto.Exposer;
import com.hand.seckill.dto.SeckillExcution;
import com.hand.seckill.entity.Seckill;
import com.hand.seckill.exception.RepeatKillException;
import com.hand.seckill.exception.SeckillCloseException;
import com.hand.seckill.exception.SeckillException;

/**
 * Created by Jo on 10/18/16.
 */
public interface SeckillService {

	/**
	 * get all seckill
	 * @return
	 */
	List<Seckill> getSeckillList();

	/**
	 * get seckill by id
	 * @param seckillId
	 * @return
	 */
	Seckill getById(long seckillId);

	/**
	 * 秒杀开始是暴露秒杀地址
	 * 否则输出系统时间和秒杀时间
	 * @param seckillId
	 */
	Exposer exposeSeckillUrl(long seckillId);

	/**
	 * execute seckill 执行秒杀操作
	 * @param seckillId
	 * @param userPhone
	 * @param md5
	 */
	SeckillExcution  executeSeckill(long seckillId,long userPhone,String md5) throws SeckillException,RepeatKillException,SeckillCloseException;

	// SeckillExcution  executeSeckillProcedure(long seckillId,long userPhone,String md5) throws SeckillException,RepeatKillException,SeckillCloseException;
}
