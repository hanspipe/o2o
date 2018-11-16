package com.louis.o2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.louis.o2o.BaseTest;
import com.louis.o2o.dto.ImageHolder;
import com.louis.o2o.dto.ShopExecution;
import com.louis.o2o.entity.Area;
import com.louis.o2o.entity.PersonInfo;
import com.louis.o2o.entity.Shop;
import com.louis.o2o.entity.ShopCategory;
import com.louis.o2o.enums.ShopStateEnum;
import com.louis.o2o.exceptions.ShopOperationException;

public class ShopServiceTest extends BaseTest{

	@Autowired
	private ShopService shopService;
	
	@Test
	
	public void testModifyShop() throws ShopOperationException, FileNotFoundException{
		Shop shop = new Shop();
		shop.setShopId(1L);
		shop.setShopName("一点点");
		File shopImg = new File("C:/Users/louis/Pictures/12.jpg");
		InputStream is = new FileInputStream(shopImg);
		ImageHolder imageHolder = new ImageHolder("12.jpg", is);
		ShopExecution se = shopService.modifyShop(shop, imageHolder);
		System.out.println(se.getShop().getShopImg());
	}
	
	@Test
	@Ignore
	public void testAddShop() throws FileNotFoundException {
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
		shop.setShopName("测试的店铺9");
		shop.setShopDesc("test9");
		shop.setShopAddr("test9");
		shop.setPhone("test9");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(ShopStateEnum.CHECK.getState());
		shop.setAdvice("审核中");
		File shopImg = new File("C:/Users/louis/Pictures/Tom.jpg");
		InputStream is = new FileInputStream(shopImg);
		ImageHolder imageHolder = new ImageHolder(shopImg.getName(), is);
		ShopExecution se = shopService.addShop(shop, imageHolder );
		assertEquals(ShopStateEnum.CHECK.getState(), se.getState());
	}
}
