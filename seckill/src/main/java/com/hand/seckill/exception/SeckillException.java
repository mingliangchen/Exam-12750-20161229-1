package com.hand.seckill.exception;

/**
 * Created by Jo on 10/18/16.
 * seckill exception
 * 秒杀相关的异常
 */
public class SeckillException extends RuntimeException{

	public SeckillException(String message) {
		super(message);
	}

	public SeckillException(String message, Throwable cause) {
		super(message, cause);
	}
}
