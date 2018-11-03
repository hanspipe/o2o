package com.louis.o2o.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

public class ImageUtil {
	private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
	private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	private static final Random r = new Random();
	private static Logger logger = LoggerFactory.getLogger(ImageUtil.class);

	/**
	 * 将CommonsMultipartFile转换成File
	 * @param cFile
	 * @return
	 */
	public static File transferCommonMultipartFileToFile(CommonsMultipartFile cFile) {
		File newFile = new File(cFile.getOriginalFilename());
		try {
			cFile.transferTo(newFile);
		} catch (IllegalStateException e) {
			logger.error(e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
		return newFile;
	}
	
	/**
	 * 处理缩略图，并返回目标图片的相对路径
	 * @param thumbnail
	 * @param targetAddr
	 * @return
	 */
	public static String generateThumbnail(InputStream thumbnailInputStream, String fileName, String targetAddr) {
		//获取不重复的随机名
		String realFileName = getRandomFileName();
		//获取文件扩展名
		String extension = getFileExtension(fileName);
		//如果目标路径不存在，自动创建
		makeDirPath(targetAddr);
		// 获取文件相对路径（带文件名）
		String relativeAddr = targetAddr + realFileName + extension;
		logger.debug("current relativeAddr:" + relativeAddr);
		// 获取文件要保存到的目标路径
		File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
		//File dest = new File(relativeAddr);
		logger.debug("current whole Addr:" + PathUtil.getImgBasePath() + relativeAddr);
		logger.debug("basePath:" + basePath);
		// 调用Thumbnails生成带有水印的图片
		try {
			Thumbnails.of(thumbnailInputStream).size(200, 200)
					.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f)
					.outputQuality(0.8f).toFile(dest);
		} catch (IOException e) {
			logger.error(e.toString());
			e.printStackTrace();
		}
		// 返回图片相对地址路径
		return relativeAddr;
	}

	/**
	 * 创建目标路径所涉及到的目录
	 * 
	 * @param targetAddr 目标文件所属文件夹的相对路径
	 */
	private static void makeDirPath(String targetAddr) {
		// 绝对路径
		String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
		File dirPath = new File(realFileParentPath);
		if (!dirPath.exists()) {
			dirPath.mkdirs();
		}
	}

	/**
	 * 获取输入文件流的扩展名
	 * 
	 * @param thumbnail
	 * @return
	 */
	private static String getFileExtension(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	/**
	 * 生成随机文件名，当前年月日小时分钟秒+五位随机数
	 * 
	 * @return
	 */
	public static String getRandomFileName() {
		// 获取随机五位数
		int rannum = r.nextInt(89999) + 10000;
		String nowTimeStr = sDateFormat.format(new Date());
		return nowTimeStr + rannum;
	}
	
	/**
	 * 先判断storePath是文件路径还是目录路径
	 * 如果是文件路径则删除该文件
	 * 如果是目录就删除目录下所有文件
	 * @param stroePath
	 */
	public static void deleteFileOrPath(String storePath) {
		File fileOrPath = new File(PathUtil.getImgBasePath() + storePath);
		if(fileOrPath.exists()) {
			if(fileOrPath.isDirectory()) {
				File[] files = fileOrPath.listFiles();
				for(int i = 0; i < files.length; i++)
					files[i].delete();
			}
			fileOrPath.delete();
		}
		
	}

	public static void main(String[] args) throws IOException {
		Thumbnails.of(new File("C:\\Users\\louis\\Pictures\\timg.jpg")).size(200, 200)
				.watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "/watermark.jpg")), 0.25f)
				.outputQuality(0.8f).toFile("C:\\Users\\louis\\Pictures\\timgnew.jpg");
		System.out.println(basePath);
	}
}
