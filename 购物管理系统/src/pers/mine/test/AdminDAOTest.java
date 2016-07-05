package pers.mine.test;

import pers.mine.factory.DAOFactory;
import pers.mine.vo.Admin;

public class AdminDAOTest {

	public static void main(String[] args) throws Exception {
		// TODO 自动生成的方法存根
		Admin ad=new Admin("12580","12580");
		System.out.println(DAOFactory.getAdminAchieves().doValidate(ad));
		//System.out.println(DAOFactory.getAdminAchieves().regist(ad));
		//System.out.println(DAOFactory.getAdminAchieves().login(ad));
		//DAOFactory.getAdminAchieves().showAll();
		//System.out.println(ad);
	}

}
