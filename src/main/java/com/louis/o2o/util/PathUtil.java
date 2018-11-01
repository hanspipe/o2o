package com.louis.o2o.util;

public class PathUtil {
	private static String separator = System.getProperty("file.separator");

	//返回项目图片的根路径
	public static String getImgBasePath() {
		String os = System.getProperty("os.name");
		String basePath = "";
		if (os.toLowerCase().startsWith("win")) {
			basePath = "C:/Users/louis/Pictures/";
		} else {
			basePath = "/home/luois/image/";
		}
		basePath = basePath.replace("/", separator);
		return basePath;
	}

	//返回项目图片子路径
	public static String getShopImagePath(long shopId) {
		String imagePath = "upload/item/shop/" + shopId + "/";
		return imagePath.replace("/", separator);
	}
}
