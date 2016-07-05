package pers.mine.dao;



import java.util.Map;

import pers.mine.vo.GoodsItem;


public interface GoodsItemDAO {
	/**
     * 查询商品列表
     * @throws Exception
     */
	public void showAll() throws Exception;
	
	/**
	 * 获取一项商品所有信息
	  * @param Goods 传入VO对象
	 * @return 验证结果
	 * @throws Exception
	 */
	
	public boolean getOne(GoodsItem gsit) throws Exception;
	/**
	 * 增加商品
	  * @param Goods 传入VO对象
	 * @return 验证结果
	 * @throws Exception
	 */
	
	public boolean doAdd(GoodsItem gsit) throws Exception;
	
	/**
	 * 更改某一商品信息  只是实现修改库存
	 * @param Goods 传入VO对象
	 * @return 验证结果
	 * @throws Exception
	 */
	
	public boolean doUpdate(GoodsItem gsit) throws Exception;
	
	/**
	 * 更改一个Map中所有的商品信息  只是实现修改库存
	 * @param Map<String,GoodsItem>  gsits 传入VO对象
	 * @return 验证结果
	 * @throws Exception
	 */
	
	public boolean doUpdateMap(Map<String,GoodsItem>  gsits) throws Exception;
	/**
	 * 删除某一商品的所有信息
	 * @param Goods 传入VO对象
	 * @return 验证结果
	 * @throws Exception
	 */
	
	public boolean doDelete(GoodsItem gsit) throws Exception;
	
	/**
     * 验证商品是否已存在操作
     * @param GoodsItem  传入VO对象
     * @return  验证结果
     * @throws Exception
     */
	public boolean doValidate(GoodsItem gsit) throws Exception;


}
