package com.louis.o2o.service.impl;

import java.io.File;
import java.io.InputStream;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.louis.o2o.dao.ShopDao;
import com.louis.o2o.dto.ShopExecution;
import com.louis.o2o.entity.Shop;
import com.louis.o2o.enums.ShopStateEnum;
import com.louis.o2o.exceptions.ShopOperationException;
import com.louis.o2o.service.ShopService;
import com.louis.o2o.util.ImageUtil;
import com.louis.o2o.util.PathUtil;

@Service
public class ShopServiceImpl implements ShopService {

	private static Logger logger = LoggerFactory.getLogger(ShopServiceImpl.class);
	@Autowired
	private ShopDao shopDao;

	@Override
	@Transactional
	public ShopExecution addShop(Shop shop, InputStream shopImgInputStream, String fileName) {
		// 空值判断
		if (shop == null)
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		try {
			// 给店铺信息赋初始值
			shop.setEnableStatus(0);
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			// 添加店铺信息
			int effectedNum = shopDao.insertShop(shop);
			if (effectedNum <= 0) {
				throw new ShopOperationException("店铺创建失败");
			} else {
				if (shopImgInputStream != null) {
					// 存储图片
					try {
						addShopImg(shop, shopImgInputStream,fileName);
					} catch (Exception e) {
						throw new ShopOperationException("addShopImg Error:" + e.getMessage());
					}
					// 更新店铺的图片地址
					effectedNum = shopDao.updateShop(shop);
					if (effectedNum <= 0) {
						throw new ShopOperationException("更新图片地址失败");
					}
				}
			}
		} catch (Exception e) {
			throw new ShopOperationException("addShop Error:" + e.getMessage());
		}
		return new ShopExecution(ShopStateEnum.CHECK, shop);
	}

	private void addShopImg(Shop shop, InputStream shopImgInputStream, String fileName) {
		// 获取shop图片目录的相对值路径
		String dest = PathUtil.getShopImagePath(shop.getShopId());
		logger.debug("dest:" + dest);
		String shopImgAddr = ImageUtil.generateThumbnail(shopImgInputStream,fileName, dest);
		logger.debug("shopImgAddr:" + shopImgAddr);
		shop.setShopImg(shopImgAddr);
	}
}
