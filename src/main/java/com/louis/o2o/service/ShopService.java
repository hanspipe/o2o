package com.louis.o2o.service;

import java.io.File;
import java.io.InputStream;

import com.louis.o2o.dto.ShopExecution;
import com.louis.o2o.entity.Shop;
import com.louis.o2o.exceptions.ShopOperationException;


public interface ShopService {

	ShopExecution addShop(Shop shop,InputStream shopImgInputStream, String fileName) throws ShopOperationException;
}
