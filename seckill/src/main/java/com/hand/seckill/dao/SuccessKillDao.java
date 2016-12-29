package com.hand.seckill.dao;

import com.hand.seckill.entity.SuccessKill;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Jo on 10/17/16.
 */
public interface SuccessKillDao {

	/**
	 * 插入购买明细，可以过滤重复
	 * @param seckillId
	 * @param userPhone
	 * @return insert lines
	 */
	int insertSuccessKill(@Param("seckillId") long seckillId,@Param("userPhone")long userPhone);

	/**
	 * 根据id查询successKill  并携带秒杀产品实体对象
	 * @param seckillId
	 * @return
	 */
	SuccessKill queryByIdWithSeckill(@Param("seckillId") long seckillId,@Param("userPhone") long userPhone);



}
