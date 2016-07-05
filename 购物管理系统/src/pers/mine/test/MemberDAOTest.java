package pers.mine.test;

import pers.mine.factory.DAOFactory;
import pers.mine.vo.Member;

public class MemberDAOTest {

	public static void main(String[] args) throws Exception {
		// TODO 自动生成的方法存根
		//Member me=new Member("110","110");
		Member me=new Member("王小帅","119","119");
		//System.out.println(DAOFactory.getMemberAchieves().doValidate(me));
		//System.out.println(DAOFactory.getMemberAchieves().regist(me));
		//System.out.println(DAOFactory.getMemberAchieves().login(me));
		DAOFactory.getMemberAchieves().showAll();
	}

}
