package pers.mine.test;

import java.util.Scanner;

import pers.mine.factory.DAOFactory;
import pers.mine.tool.RandomID;
import pers.mine.vo.Member;

public class RandomIDTest {

	public static void main(String[] args) throws Exception {
		// TODO 自动生成的方法存根
		Scanner in=new Scanner(System.in);
		do{
		System.out.println(DAOFactory.getMemberAchieves().doValidate(new Member("110")));
		System.out.println(RandomID.getMemberID(1));
		
		in.next();
		}while(true);
	}

}
