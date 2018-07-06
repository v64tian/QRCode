package com.tian.deqrcode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Deque;
import javax.imageio.ImageIO;

import jp.sourceforge.qrcode.QRCodeDecoder;
import jp.sourceforge.qrcode.QRCodeEncoder;
import jp.sourceforge.qrcode.data.QRCodeImage;
/*
 * 
 * 解码程序
 */
public class DeCode {
	public static void main(String[] args) throws IOException {
		
		QRCodeEncoder qe=new QRCodeEncoder();
		QRCodeDecoder qd=new QRCodeDecoder();
		File f=new File("test.png");
		QrcodeImage qrcodeImg=new QrcodeImage(ImageIO.read(f));
		byte[] data=qd.decode(qrcodeImg);
		//将解析的内容输出到控制台
		System.out.print(new String(data,"utf-8"));
	}
}

class QrcodeImage implements QRCodeImage{

	BufferedImage bufferedImage;
	
	public QrcodeImage(BufferedImage bufferedImage) {

		this.bufferedImage=bufferedImage;
	}
	@Override
	public int getHeight() {
		
		return bufferedImage.getHeight();
	}

	@Override
	public int getPixel(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return bufferedImage.getRGB(arg0, arg1);
	}
	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return bufferedImage.getHeight();
	}
	
}