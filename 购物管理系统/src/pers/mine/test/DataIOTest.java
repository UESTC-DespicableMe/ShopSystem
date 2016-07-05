package pers.mine.test;

import java.util.HashMap;
import java.util.Map;

import pers.mine.IO.DataIO;
import pers.mine.vo.Admin;
import pers.mine.vo.DataName;
import pers.mine.vo.Goods;
import pers.mine.vo.GoodsItem;
import pers.mine.vo.Member;

public class DataIOTest {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		Map<Integer,String> map=new HashMap<Integer,String>();
	/*	DataIO<Integer,String> dIO=new DataIO<Integer,String>();
		map.put(1, "1");
		map.put(2, "1");
		map.put(3, "1");
		map.put(4, "1");
		
		//System.out.println(dIO.writeData(map, DataName.adminMap));
		Map<Integer,String> m=new HashMap<Integer,String>();
		m=dIO.readData(DataName.adminMap);
		System.out.println(m.get(2));
		
		Map<String,Admin> admins=new HashMap <String,Admin>();
		DataIO<String,Admin> dio=new DataIO<String,Admin>();
		Admin ad=new Admin("叶不空","10086","10086");
		Admin ad2=new Admin("联通","10010","10010");
		admins.put(ad.getId(), ad);
		admins.put(ad2.getId(), ad2);
		//System.out.println(dio.writeData(admins, DataName.adminMap));
		Admin ad3=new Admin();
		ad3=dio.readData(DataName.adminMap).get("12580");
		System.out.println(ad3);*/
		
		
		
		
		Map<String,Member> members=new HashMap <String,Member>();
		DataIO<String,Member> dio=new DataIO<String,Member>();
		Member me1=new Member("小明","110","110");
		Member me2=new Member("小王","220","220");
		Member me3=new Member("小刘","330","330");
		System.out.println(me1);
		members.put(me1.getId(), me1);
		members.put(me2.getId(), me2);
		members.put(me3.getId(), me3);
		
		System.out.println(dio.writeData(members, DataName.memberMap));
		Member me=new Member();
		
		me=dio.readData(DataName.memberMap).get("220");
		System.out.println(me);
		
		/*Map<String,GoodsItem> gsits=new HashMap <String,GoodsItem>();
		DataIO<String,GoodsItem> dio=new DataIO<String,GoodsItem>();
		Goods gs1=new Goods("1","earphone",99.99,"Sony","Nice!");
		GoodsItem gsit1=new	GoodsItem(gs1,999);
		
		Goods gs2=new Goods("2","SurfaceBook",9999.98,"Microsoft","Cool!!!");
		GoodsItem gsit2=new	GoodsItem(gs2,96);
		
		Goods gs3=new Goods("3","Lumia920",2399.99,"NOKIA","Beautiful!");
		GoodsItem gsit3=new	GoodsItem(gs3,30);
		
		gsits.put("1", gsit1);
		gsits.put("2", gsit2);
		gsits.put("3", gsit3);
		
		System.out.println(dio.writeData(gsits, DataName.GoodsIteamMap));
		
		GoodsItem gsit=dio.readData(DataName.GoodsIteamMap).get("3");
		System.out.println(gsit);
		
		*/
		
		}

}
