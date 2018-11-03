package com.louis.o2o.service.impl;

import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.louis.o2o.dao.AreaDao;
import com.louis.o2o.dto.ShopExecution;
import com.louis.o2o.entity.Area;
import com.louis.o2o.entity.Shop;
import com.louis.o2o.exceptions.ShopOperationException;
import com.louis.o2o.service.AreaService;

@Service
public class AreaServiceImpl implements AreaService{
	
	@Autowired
	private AreaDao areaDao;

	@Override
	public List<Area> getAreaList() {
		
		return areaDao.queryArea();
	}

	
	
}
