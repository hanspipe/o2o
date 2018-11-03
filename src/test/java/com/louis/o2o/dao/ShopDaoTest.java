package com.louis.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.louis.o2o.BaseTest;
import com.louis.o2o.entity.Area;
import com.louis.o2o.entity.PersonInfo;
import com.louis.o2o.entity.Shop;
import com.louis.o2o.entity.ShopCategory;

public class ShopDaoTest extends BaseTest{

		@Autowired
		private ShopDao shopDao;
		
		@Test
		public void testQueryByShopId() {
			long shopId = 1;
			Shop shop = shopDao.queryByShopId(shopId);
			System.out.println(shop.getArea().getAreaId());
			System.out.println(shop.getArea().getAreaName());
		}
		
		
		@Test
		@Ignore
		public void testInsertShop() {
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
			shop.setShopName("测试的店铺");
			shop.setShopDesc("test");
			shop.setShopAddr("test");
			shop.setPhone("test");
			shop.setShopImg("test");
			shop.setCreateTime(new Date());
			shop.setEnableStatus(1);
			shop.setAdvice("审核中");
			int effectedNum = shopDao.insertShop(shop);
			assertEquals(1, effectedNum);
		}
		
		@Test
		@Ignore
		public void testUpdateShop() {
			Shop shop = new Shop();
			shop.setShopId(1L);
			shop.setShopDesc("测试的描述");
			shop.setShopAddr("测试地址");
			shop.setLastEditTime(new Date());
			int effectedNum = shopDao.updateShop(shop);
			assertEquals(1, effectedNum);
		}
}
