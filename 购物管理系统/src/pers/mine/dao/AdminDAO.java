package pers.mine.dao;

import pers.mine.vo.Admin;

public interface AdminDAO {
	/**
	 * 注册管理员
	 * @param admin 传入VO对象
	 * @return 验证结果
	 * @throws Exception
	 */
	
	public boolean regist(Admin ad) throws Exception;
	
	/**
     * 登录操作
     * @param admin 传入VO对象
     * @return  验证结果
     * @throws Exception
     */
	public boolean login(Admin ad) throws Exception;
	
	/**
     * 验证用户名是否存在操作
     * @param admin 传入VO对象
     * @return  验证结果
     * @throws Exception
     */
	public boolean doValidate(Admin ad) throws Exception;
	
	/**
     * 当前map转换为List
     * @return  void
     * @throws Exception
     */
	public void showAll() throws Exception;
	
	/**
     * 删除某一项
     * @param admin 传入VO对象
     * @return  删除结果
     * @throws Exception
     */
	public boolean removeOne(Admin ad) throws Exception;
	
}
