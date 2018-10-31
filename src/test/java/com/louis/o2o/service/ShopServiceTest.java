package com.louis.o2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.louis.o2o.BaseTest;
import com.louis.o2o.dto.ShopExecution;
import com.louis.o2o.entity.Area;
import com.louis.o2o.entity.PersonInfo;
import com.louis.o2o.entity.Shop;
import com.louis.o2o.entity.ShopCategory;
import com.louis.o2o.enums.ShopStateEnum;

public class ShopServiceTest extends BaseTest{

	@Autowired
	private ShopService shopService;
	
	@Test
	public void testAddShop() {
		Shop shop = new Shop();
		PersonInfo owner = new PersonInfo();
		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();
		owner.setUserId(1L);
		area.setAreaId(2);
		shopCategory.setShopCategoryId(1L);
		shop.setShopCategory(shopCategory);
		shop.setOwner(owner);
		shop.setArea(area);
		shop.setShopName("测试的店铺1");
		shop.setShopDesc("test1");
		shop.setShopAddr("test1");
		shop.setPhone("test1");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(ShopStateEnum.CHECK.getState());
		shop.setAdvice("审核中");
		File shopImg = new File("C:/Users/louis/Pictures/Tom.jpg");
		ShopExecution se = shopService.addShop(shop, shopImg);
		assertEquals(ShopStateEnum.CHECK.getState(), se.getState());
	}
}
