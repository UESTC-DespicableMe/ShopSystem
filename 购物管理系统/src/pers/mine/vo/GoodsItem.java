package pers.mine.vo;

import java.io.Serializable;


public class GoodsItem implements Serializable{
	private Goods goods;
	private int num;
	/*public Goods gsit() {
		return goods;
	}*/
	
	public void setGoods(Goods goods) {
		this.goods = goods;
	}
	public Goods getGoods() {
		return goods;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	/**
	 * @param goods
	 * @param num
	 */
	public GoodsItem(Goods goods, int num) {
		super();
		this.goods = goods;
		this.num = num;
	}
	public GoodsItem(){}
	public GoodsItem(String id){
		super();
		this.goods=new Goods(id);
		
	}
	public GoodsItem(String id,int num){
		super();
		this.goods=new Goods(id);
		this.num = num;
	}
	
	public GoodsItem(Goods goods) {
		super();
		this.goods = goods;
	}
	@Override
	public String toString() {
		return "GoodsItem [goodsID=" + goods.getId() + ", goodsName=" + goods.getName() + ", num=" + num + "]";
	}
	
	
}
