package com.hand.seckill.dao;

import com.hand.seckill.entity.Seckill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Jo on 10/17/16.
 */
public interface SeckillDao {

	/**
	 * reduce product number
	 * 减少库存
	 * @param seckillId
	 * @param killTime
	 * @return if effect line number >=1 ,means update lines. if return 0 means reduce fail
	 */
	int reduceNumber(@Param("seckillId") long seckillId,@Param("killTime") Date killTime);

	/**
	 * query seckill product
	 * 根据id查询产品
	 * @param seckillId
	 * @return
	 */
	Seckill queryById(long seckillId);

	/**
	 * query products from seckill
	 * 根据偏移量查询产品列表
	 * @param offset
	 * @param limit
	 * @return
	 */
	List<Seckill> queryAll(@Param("offset") int offset,@Param("limit") int limit);

	void killByProcedure(Map<String,Object> paramMap);

}
