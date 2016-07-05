package pers.mine.achieves;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import pers.mine.IO.DataIO;
import pers.mine.dao.GoodsItemDAO;
import pers.mine.vo.DataName;
import pers.mine.vo.Goods;
import pers.mine.vo.GoodsItem;
import pers.mine.vo.Member;



public class GoodsItemAchieves implements GoodsItemDAO {
	private Map<String,GoodsItem> gsits=null;
	
	public GoodsItemAchieves(){
		DataIO<String,GoodsItem> dio=new DataIO<String,GoodsItem>();
		Map<String,GoodsItem>  gsits=new HashMap <String,GoodsItem>();
		 gsits=dio.readData(DataName.GoodsIteamMap);
		if( gsits==null){
			 gsits=new HashMap<String,GoodsItem>();
		}
		this. gsits= gsits;
	}
	@Override
	public void showAll() throws Exception {
		// TODO 自动生成的方法存根
		Collection <GoodsItem> ms=gsits.values();
		Iterator<GoodsItem> it=ms.iterator();
		
		System.out.println("                               >>>>>商品列表<<<<<                                   ");
		System.out.println(" ---------------------------------------------------------------------------------");
		System.out.println("| 编号     | 商品名                          | 价格  元            | 库存  件            | 品牌名             | 商品简介                                   |");
		System.out.println(" ---------------------------------------------------------------------------------");
		
		while(it.hasNext()){
			GoodsItem gsit=it.next();
			Goods gs=gsit.getGoods();
			System.out.printf("| %-5s| %-15s| %-10s| %-10s| %-10s| %-20s|\n",
						gs.getId(),gs.getName(),gs.getPrice(),gsit.getNum(),gs.getBrand(),gs.getBlurb());
			/*System.out.println("|"+gs.getId()+"\t|"+gs.getName()+"\t|"+gs.getPrice()+"\t|"+gsit.getNum()+"\t|"
						+gs.getBrand()+"\t\t|"+gs.getBlurb()+"\t|");*/
		}
		
		System.out.println(" ---------------------------------------------------------------------------------");
	}
	
	@Override
	public boolean getOne(GoodsItem gsit) throws Exception {
		// TODO 自动生成的方法存根
		if(this.doValidate(gsit)){//如果验证通过，说明商品不存在
			System.err.println("要获取的商品不存在");
			return false;
		}
		String id=gsit.getGoods().getId();
		
		
		
	/*	gsit=gsits.get(id);
		System.out.println(gsit);*/ //这里有显示，外面却没有？
		
		gsit.setGoods(gsits.get(id).getGoods());//可以获取吗？难以理解。。。
		gsit.setNum(gsits.get(id).getNum());
		return true;
	}
	//只是修改库存数量
	@Override
	public boolean doUpdate(GoodsItem gsit) throws Exception {
		// TODO 自动生成的方法存根
		if(this.doValidate(gsit)){//如果验证通过，说明商品不存在
			System.err.println("要修改的商品不存在");
			return false;
		}
		DataIO<String,GoodsItem > dio=new DataIO<String,GoodsItem >();
		String id=gsit.getGoods().getId();
		int num=gsit.getNum();
		if(num<1){
			System.err.println("商品库存数量非法");
			return false;
		}
		GoodsItem one=gsits.get(id);//从Map中取得要更新的商品信息
		one.setNum(num);//设置新库存
		
		gsits.put(id,one);//将新信息放入Map
		
		
		if(!dio.writeData(gsits, DataName.GoodsIteamMap)){//写入失败
			System.err.println("修改操作写入失败");
			return false;
		}
		return true;
	}
	
	@Override
	public boolean doUpdateMap(Map<String, GoodsItem> cartMap) throws Exception {
		// TODO 自动生成的方法存根
		if(cartMap.size()==0){
			System.err.println("购物车为空！");
			return false;
		}
		Collection <GoodsItem> ms=cartMap.values();//转换为集合
		Iterator<GoodsItem> it=ms.iterator();
		
		while(it.hasNext()){
			GoodsItem cartGsit=it.next();
			String cartGoodsId=cartGsit.getGoods().getId();
			GoodsItem dataGsit=this.gsits.get(cartGoodsId);
			if(dataGsit==null){
				System.err.println("不存在[id="+cartGoodsId+"] 的商品！");
				return false;
			}
			int cartNum=cartGsit.getNum();
			int dataNum=dataGsit.getNum();//获取库存
			if(cartNum<1||dataNum<1||cartNum>dataNum){
				System.err.println("购物车中[id="+cartGoodsId+"] 的商品数量非法！");
				return false;
			}
			//System.out.println(this.gsits.get(cartGoodsId).getNum());
			this.gsits.get(cartGoodsId).setNum(dataNum-cartNum);//感觉并不能修改库存信息。。。然而却修改了
			//System.out.println(this.gsits.get(cartGoodsId).getNum());
		}
		DataIO<String,GoodsItem > dio=new DataIO<String,GoodsItem >();
		if(!dio.writeData(gsits, DataName.GoodsIteamMap)){//写入失败
			System.err.println("结账操作[修改库存]写入失败");
			return false;
		}
		return true;
	}

	@Override
	public boolean doDelete(GoodsItem gsit) throws Exception {
		// TODO 自动生成的方法存根
		if(this.doValidate(gsit)){//如果验证通过，说明商品不存在
			System.err.println("要删除的商品不存在");
			return false;
		}
		DataIO<String,GoodsItem > dio=new DataIO<String,GoodsItem >();
		String id=gsit.getGoods().getId();
		gsits.remove(id);//从Map中删除
		if(!dio.writeData(gsits, DataName.GoodsIteamMap)){//写入失败
			System.err.println("删除操作写入失败");
			return false;
		}
		return true;
	}



	@Override
	public boolean doAdd(GoodsItem gsit) throws Exception {
		// TODO 自动生成的方法存根
		if(!this.doValidate(gsit)){//如果验证不通过
			System.err.println("存在相同的商品");
			return false;
		}
		if(gsit.getNum()<1){
			System.err.println("商品库存数量非法");
			return false;
		}
		DataIO<String,GoodsItem > dio=new DataIO<String,GoodsItem >();
		String id=gsit.getGoods().getId();
		gsits.put(id,gsit);//放入Map
		if(!dio.writeData(gsits, DataName.GoodsIteamMap)){//写入失败
			System.err.println("添加操作写入失败");
			return false;
		}
		return true;
	}
	@Override
	public boolean doValidate(GoodsItem gsit) throws Exception {
		// TODO 自动生成的方法存根
		String id=gsit.getGoods().getId();
		if(gsits.get(id)!=null){//存在相同的商品
			return false;
		}
		return true;
	}
	
	

}
