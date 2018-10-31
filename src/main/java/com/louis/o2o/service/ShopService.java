package com.louis.o2o.service;

import java.io.File;

import com.louis.o2o.dto.ShopExecution;
import com.louis.o2o.entity.Shop;


public interface ShopService {

	ShopExecution addShop(Shop shop,File shopImg);
}
