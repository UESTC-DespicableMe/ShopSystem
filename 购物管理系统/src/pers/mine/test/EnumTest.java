package pers.mine.test;

import pers.mine.vo.DataName;

public class EnumTest {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		
		System.out.println(DataName.adminMap);
		System.out.println(get(DataName.GoodsIteamMap));
	}
	
	public static DataName get(DataName dn){
		return dn;
	}

}
