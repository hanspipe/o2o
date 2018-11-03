package com.louis.o2o.service;

import java.io.File;
import java.io.InputStream;

import com.louis.o2o.dto.ShopExecution;
import com.louis.o2o.entity.Shop;
import com.louis.o2o.exceptions.ShopOperationException;


public interface ShopService {
	
	/**
	 * 通过店铺id获取店铺信息
	 * @param shopId
	 * @return
	 */
	Shop getByShopId(long shopId);
	
	/**
	 * 更新店铺信息,包括对图片的处理
	 * @param shop
	 * @param shopImgInputStream
	 * @param fileName
	 * @return
	 * @throws ShopOperationException
	 */
	ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream, String fileName) throws ShopOperationException;

	/**
	 * 添加店铺
	 * @param shop
	 * @param shopImgInputStream
	 * @param fileName
	 * @return
	 * @throws ShopOperationException
	 */
	ShopExecution addShop(Shop shop,InputStream shopImgInputStream, String fileName) throws ShopOperationException;
}
