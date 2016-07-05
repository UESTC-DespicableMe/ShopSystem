package pers.mine.menu;

import java.util.Scanner;

import pers.mine.factory.DAOFactory;

public class Index {

	public static void main(String[] args) throws Exception {
		// TODO 自动生成的方法存根
		AdminMenu am=new AdminMenu();
		MemberMenu mm=new MemberMenu();
		System.err.println("(☂)说明:\n\t[a]: data文件夹用于存放数据\n\t[b]: data文件内容为空将会报错\n\t"
				+ "[c]: 可用pers.mine.test.DataIOTest类给data文件填充数据\n\t[d]: 为了方便，购买商品时，拒绝使商品库存变为0"
				+ "\n");
		System.out.println("********************欢迎进入购物管理系统********************");
		
		
		Scanner in=new Scanner(System.in);
		String code="";
		do{
			System.out.println(">您可以选择要进入的界面:\n 1.管理员后台界面     2.会员购物前台界面     Q|q.退出系统");
			System.err.print("注:[super] 进入super数据后台[查看所有数据信息]\n");
   		 	code=in.next();
   		 	
   		 switch(code){
		    case "Q":;
		    case "q":{ System.out.println("\n【已退出系统】");System.exit(0);}
		    case "1":am.show();break;
		    case "2":mm.show();break;
		    case "super":showData();break;
		    default:{code="err";System.err.print("错误信息:输入有误,请重新输入...\n");break;}
		 
		 }
   		if("err".equals(code)){
   			System.out.println("-------------------------------------------------------");
   		}
   		
		}while(true);
	}
	private static void showData() throws Exception{
		System.out.println("------------------*****super数据后台界面*****------------------\n");
		System.out.println("        >>>>>管理员信息列表<<<<< ");
		System.out.println("---------------------------------------");
		DAOFactory.getAdminAchieves().showAll();
		System.out.println("---------------------------------------\n");
		System.out.println("                       >>>>>会员信息列表<<<<< ");
		System.out.println("------------------------------------------------------------------------");
		DAOFactory.getMemberAchieves().showAll();
		System.out.println("------------------------------------------------------------------------\n");
		DAOFactory.getGoodsItemrAchieves().showAll();
		Scanner in=new Scanner(System.in);
		String code="";
		do{
			System.out.println("\n>选择操作:\n 0.返回上一级     Q|q.退出系统");
			
   		 	code=in.next();
   		 	
   		 switch(code){
		    case "Q":;
		    case "q":{ System.out.println("\n【已退出系统】");System.exit(0);}
		    case "0":System.out.println("********************已返回到购物管理菜单*********************");return;
		    default:System.err.print("错误信息:输入有误,请重新输入...\n");break;
		 
		 }
   		
   		System.out.println("-------------------------------------------------------");
   		
		}while(true);
	}

}
