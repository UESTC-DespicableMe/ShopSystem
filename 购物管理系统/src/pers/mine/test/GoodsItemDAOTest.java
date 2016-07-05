package pers.mine.test;

import java.util.HashMap;
import java.util.Map;

import pers.mine.factory.DAOFactory;
import pers.mine.vo.Goods;
import pers.mine.vo.GoodsItem;

public class GoodsItemDAOTest {

	public static void main(String[] args) throws Exception {
		// TODO 自动生成的方法存根
		
		GoodsItem gsit=new GoodsItem("2");
		//Goods gs1=new Goods("4","lala",2.99,"wahaha","good!");
		//GoodsItem gsit=new GoodsItem("4",88);
		
		//System.out.println(DAOFactory.getGoodsItemrAchieves().doAdd(gsit));
		//System.out.println(DAOFactory.getGoodsItemrAchieves().doDelete(gsit));
		//System.out.println(DAOFactory.getGoodsItemrAchieves().doUpdate(gsit));
		System.out.println(DAOFactory.getGoodsItemrAchieves().getOne(gsit));
		System.out.println(gsit);
		System.out.println(gsit.getGoods());
		
		//结账测试    结账一个购物车下的修改库存操作
		/*Map<String,GoodsItem> gsits=new HashMap <String,GoodsItem>();
		Goods gs1=new Goods("1","earphone",99.99,"Sony","Nice!");
		GoodsItem gsit1=new	GoodsItem(gs1,1);
		
		Goods gs2=new Goods("2","SurfaceBook",9999.98,"Microsoft","Cool!!!");
		GoodsItem gsit2=new	GoodsItem(gs2,2);
		
		Goods gs3=new Goods("3","Lumia920",2399.99,"NOKIA","Beautiful!");
		GoodsItem gsit3=new	GoodsItem(gs3,3);
		
		gsits.put("1", gsit1);
		gsits.put("2", gsit2);
		gsits.put("3", gsit3);
		System.out.println(DAOFactory.getGoodsItemrAchieves().doUpdateMap(gsits));*/
		
		//DAOFactory.getGoodsItemrAchieves().showAll();
	}

}
