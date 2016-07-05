package pers.mine.test;

import java.util.HashMap;
import java.util.Map;

import pers.mine.factory.DAOFactory;
import pers.mine.vo.Cart;
import pers.mine.vo.Goods;
import pers.mine.vo.GoodsItem;

public class CartTest {

	public static void main(String[] args) throws Exception {
		// TODO 自动生成的方法存根
		//Map<String,GoodsItem> gsits=new HashMap <String,GoodsItem>();
		Goods gs1=new Goods("1","earphone",99.99,"Sony","Nice!");
		GoodsItem gsit1=new	GoodsItem(gs1,1);
		
		Goods gs2=new Goods("2","SurfaceBook",9999.98,"Microsoft","Cool!!!");
		GoodsItem gsit2=new	GoodsItem(gs2,2);
		
		Goods gs3=new Goods("3","Lumia920",2399.99,"NOKIA","Beautiful!");
		GoodsItem gsit3=new	GoodsItem(gs3,3);
		
		//gsits.put("1", gsit1);
		//gsits.put("2", gsit2);
		//gsits.put("3", gsit3);
		
		Cart cart=new Cart();
		cart.put(gsit1);
		cart.put(gsit2);
		cart.put(gsit3);
		
//		gs1=new Goods("4","earphone",99.99,"Sony","Nice!");
//		gsit1=new GoodsItem(gs1,1);
		cart.remove(gsit1);
		//cart.doUpdate(gsit1);
		//cart.checkout();
		cart.show();
		//DAOFactory.getGoodsItemrAchieves().showAll();
	}

}
