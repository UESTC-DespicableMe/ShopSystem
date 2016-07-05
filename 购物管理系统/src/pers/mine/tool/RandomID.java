package pers.mine.tool;

import pers.mine.factory.DAOFactory;

import pers.mine.vo.Member;

public class RandomID {
	public static String getMemberID(int length){
		if(length<1){
			System.err.println("length不能小于1!");
			return null;
		}
		StringBuilder sb=new StringBuilder();
		
		
		int body;
		int bodyMax=(int) Math.pow(10,length);//去除第一位后最大值
		//System.out.println(bodyMax);
		do{
			
			body=(int) (Math.random()*bodyMax);
			//System.out.println("body:"+body);
			if(body<(bodyMax/10)){//body小于length-1位
				continue;
			}
		
			sb.append(body+"");
			
			try {
				if(DAOFactory.getMemberAchieves().doValidate(new Member(sb.toString()))){//验证通过
					break;
				}
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}while(true);
		return sb.toString();
	}
}
