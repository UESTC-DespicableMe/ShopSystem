package pers.mine.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import pers.mine.factory.DAOFactory;

public class Cart implements Serializable{
	
	private Map<String,GoodsItem> gsits=null;
	private double priceSum;
	private int numSum;
	
	public Cart(){
		this.gsits= new HashMap<String,GoodsItem>();
	}
	public double getPriceSum() {
		return priceSum;
	}
	public int getNumSum() {
		return numSum;
	}
	
	@Override
	public String toString() {
		return "CartNumSum= ["+this.numSum+"]";
	}
	private void setSum() {
		if(gsits==null){
			this.numSum=0;
			this.priceSum=0;
			return;
		}
		Collection <GoodsItem> ms=gsits.values();
		Iterator<GoodsItem> it=ms.iterator();
		double priceSum=0;
		int numSum=0;
		while(it.hasNext()){
			GoodsItem gsit=it.next();
			numSum+=gsit.getNum();
			priceSum+=(gsit.getGoods().getPrice()*gsit.getNum());
		}
		this.priceSum=priceSum;
		this.numSum=numSum;
	}
	
	public boolean put(GoodsItem gsit){
		
		String id=gsit.getGoods().getId();
		int num=gsit.getNum();
		if(gsits.get(id)==null){//新添加的商品
			GoodsItem dataGsit=new GoodsItem(id);
			try {
				DAOFactory.getGoodsItemrAchieves().getOne(dataGsit);
				
				int dataNum=dataGsit.getNum();
				if(dataNum==0){
					System.err.println("不存在[id="+id+"]的商品！");
					return false;
				}
				if(num==0||num>dataNum){//商品数量有误
					System.err.println("商品数量非法！");
					return false;
				}
				
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				return false;
			}
			gsits.put(id, gsit);
			this.setSum();//设置新总数
		}else{
			gsit.setNum(num+gsits.get(id).getNum());//新旧数量之和
			if(!this.doUpdate(gsit)){//新数量更新
				return false;
			}
		}
		
		return true;
	}
	
	public boolean doUpdate(GoodsItem gsit){
		String id=gsit.getGoods().getId();
		
		if(gsits.get(id)==null){
			System.err.println("购物车不存在[id="+id+"]的商品！");
			return false;
		}
		int newNum=gsit.getNum();
		GoodsItem dataGsit=new GoodsItem(id);
		try {
			DAOFactory.getGoodsItemrAchieves().getOne(dataGsit);
			
			int dataNum=dataGsit.getNum();
			
			if(newNum==0||dataNum==0||newNum>(dataNum-1)){//商品数量有误
				System.err.println("商品数量非法！");
				return false;
			}
			
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return false;
		}
		gsits.get(id).setNum(newNum); //设置新的商品数量
		this.setSum();//设置新总数
		return true;
	}
	
	public boolean remove(GoodsItem gsit){
		if(gsits==null){
			System.err.println("购物车为空！");
			return false;
		}
		String id=gsit.getGoods().getId();
		if(gsits.get(id)==null){
			System.err.println("购物车不存在[id="+id+"]的商品！");
			return false;
		}
		gsits.remove(id);//移除
		this.setSum();//设置新总数
		return true;
	}
	public void show(){
		if(gsits==null){
			System.err.println("购物车为空！");
			return;
		}
		Collection <GoodsItem> ms=gsits.values();
		Iterator<GoodsItem> it=ms.iterator();
		
		System.out.println("                         >>>>>购物车商品列表<<<<<                          ");
		System.out.println(" -------------------------------------------------------------------------");
		System.out.println("| 商品ID  | 商品名                          | 价格  元            | 品牌名             | 数量  件            | 小计                  |");
		System.out.println(" -------------------------------------------------------------------------");
		
		while(it.hasNext()){
			GoodsItem gsit=it.next();
			Goods gs=gsit.getGoods();
			Double x=gs.getPrice()*gsit.getNum();
			System.out.printf("| %-7s| %-15s| %-10s| %-10s| %-10s| %-10s|\n",
						gs.getId(),gs.getName(),gs.getPrice(),gs.getBrand(),gsit.getNum(),String.format("%5.2f", x));
		}
		
		System.out.println(" --------------------------------------------------------------------------");
		System.out.printf("\n总数量: %-5s(件)                                        总计: %-10s(元)\n",this.getNumSum(),String.format("%5.2f",this.getPriceSum()));
	}
	
	
	public boolean checkout(){//结账
		if(gsits==null){
			System.err.println("购物车为空！");
			return false;
		}
		try {
			if(!DAOFactory.getGoodsItemrAchieves().doUpdateMap(gsits)){//如果返回false
				return false;
			}
			
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return false;
		}
		this.clear();
		return true;
	}
	
	public void  clear(){
		this.priceSum=0;
		this.numSum=0;
		gsits.clear();
	}
	
	
	

}
