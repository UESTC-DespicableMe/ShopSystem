package pers.mine.dao;


import pers.mine.vo.Member;

public interface MemberDAO {
	/**
	 * 注册会员
	 * @param Member 传入VO对象
	 * @return 验证结果
	 * @throws Exception
	 */
	
	public boolean regist(Member me) throws Exception;
	
	/**
     * 登录操作
     * @param Member 传入VO对象
     * @return  验证结果
     * @throws Exception
     */
	public boolean login(Member me) throws Exception;
	
	/**
     * 验证用户名是否存在操作
     * @param Member 传入VO对象
     * @return  验证结果
     * @throws Exception
     */
	public boolean doValidate(Member me) throws Exception;
	
	/**
     * 当前map转换为集合并显示
     * @return  void
     * @throws Exception
     */
	public void showAll() throws Exception;

	/**
     * 将Member中最新的购物车信息或个人信息覆盖到源记录下
     * @param Member  传入最新数据
     * @return  boolean
     * @throws Exception
     */
	public boolean save(Member nowMe) throws Exception;
}
