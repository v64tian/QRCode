package com.tian.qrcode;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

public class CreateQRCode {
	/**
	 * @author 0961160331����
	 * @param version
	 * @param path ���ɵ�·��
	 * @param begin ��ʼ��ɫ  ��ʽ  "r,g,b"
	 * @throws UnsupportedEncodingException 
	 */
	public static void getImage(int version,String content,String path,String begin ,String end) throws Exception{
		//����һ����ά�����
		Qrcode qrCode=new Qrcode();
		qrCode.setQrcodeVersion(version);
		qrCode.setQrcodeEncodeMode('H');
		int imgSize=67+(version-1)*12;
		//����һ��ͼƬ
		BufferedImage bufImg=new BufferedImage(imgSize, imgSize, BufferedImage.TYPE_INT_RGB);
		//�õ�����
		Graphics2D cg = bufImg.createGraphics();
		//���ñ����ͻ��ʵ���ɫ
		cg.setBackground(Color.WHITE);
		cg.setColor(Color.BLACK);
		cg.clearRect(0, 0,imgSize,imgSize);
		//�����ݻ���imgͼƬ��
		boolean[][] data=qrCode.calQrcode(content.getBytes("utf-8"));
		int pixoff=2;
		String[] beginData=begin.split(",");
		String[] endData=end.split(",");
		//�õ� ��ʼ ��ɫ����
		int startR=Integer.parseInt(beginData[0]) ;
		int startG=Integer.parseInt(beginData[1]);
		int startB=Integer.parseInt(beginData[2]);
		int endR=Integer.parseInt(endData[0]);
		int endG=Integer.parseInt(endData[1]);
		int endB=Integer.parseInt(endData[2]);
		for(int i=0;i<data.length;i++){
			for(int j=0;j<data.length;j++){
				if(data[i][j]){

					int r=startR+(endR-startR)*(i+1)/data.length;
					int g=startG+(endG-startG)*(i+1)/data.length;
					int b=startB+(endB-startB)*(i+1)/data.length;
					Color color=new Color(r,g,b);
					cg.setColor(color);
					cg.fillRect(i*3+pixoff, j*3+pixoff, 3, 3);
				}
			}
		}
		//����logo
		BufferedImage logo=ImageIO.read(new File("logo.png"));
		int logoSize=bufImg.getHeight()/4;
		int o=(bufImg.getHeight()-logoSize)/2;
		cg.drawImage(logo,o,o,logoSize,logoSize,null);
		//�رջ���
		cg.dispose();	
		//������ͼƬ�ŵ��ڴ���
		bufImg.flush();
		//��ͼƬ�浽Ӳ����
		try {
			ImageIO.write(bufImg, "png", new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
	}
	public static void main(String[] args) throws Exception {
		String content="BEGIN:VCARD\r\n" + 
				   "FN:����:����\r\n"+
				   "TITLE:ѧ��\r\n" + 
				   "TEL;WORK;VOICE:13080750564\r\n"+
				   "TEL;HOME;VOICE:12345678911\r\n"+
				   "TEL;CELL;VOICE:18603369235\r\n"+
				   "ADR;WORK:�ӱ��Ƽ�ʦ��ѧԺ\r\n"+
				   "ADR;HOME:�ӱ��Ƽ�ʦ��ѧԺ\r\n"+
				   "URL:www.qq.com\r\n"+
				   "EMAIL;HOME:2354447503@qq.com\r\n" + 
				  "END:VCARD";
		//�����ɵĶ�ά�뱣�浽����Ŀ¼�µ�test.png 
		getImage(15,content,"test.png","255,0,0","0,0,255");
			
	}
}
