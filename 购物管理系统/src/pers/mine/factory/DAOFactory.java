package pers.mine.factory;

import pers.mine.achieves.AdminAchieves;
import pers.mine.achieves.GoodsItemAchieves;
import pers.mine.achieves.MemberAchieves;

public class DAOFactory {
	public static AdminAchieves getAdminAchieves(){
		return new AdminAchieves();
	}
	public static MemberAchieves getMemberAchieves(){
		return new MemberAchieves();
	}
	public static GoodsItemAchieves getGoodsItemrAchieves(){
		return new GoodsItemAchieves();
	}
}
