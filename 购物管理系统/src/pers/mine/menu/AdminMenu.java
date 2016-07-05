package pers.mine.menu;

import java.util.InputMismatchException;
import java.util.Scanner;

import pers.mine.factory.DAOFactory;
import pers.mine.vo.Admin;
import pers.mine.vo.Goods;
import pers.mine.vo.GoodsItem;


public class AdminMenu {
	private Admin admin=null;
	
	public  void show() throws Exception{
		System.out.println("----------------------管理员后台界面-----------------------");
		Scanner in=new Scanner(System.in);
		String code="";
		do{
			System.out.println(">您可以选择登录或注册:\n 1.管理员登录     2.管理员注册     0.上一级     Q|q.退出系统");
			
   		 	code=in.next();
   		 	
   		 switch(code){
		    case "Q":;
		    case "q":{ System.out.println("\n【已退出系统】");System.exit(0);}
		    case "0":System.out.println("********************已返回到购物管理菜单*********************");return;
		    case "1":this.login();break;
		    case "2":this.register();break;
		    default:{code="err";System.err.print("错误信息:输入有误,请重新输入...\n");break;}
		 
		 }
   		if("err".equals(code)){
   			System.out.println("-------------------------------------------------------");
   		}
   		
		}while(true);
		
	}
	
	private void register() {
		System.out.println("----------------------管理员注册界面-----------------------");
		Scanner in=new Scanner(System.in);
		do{
			
			String id=null;
			String name=null;
			String pw=null;
			String pwPlus=null;
			Admin ad=null;
			
			System.out.println(">请输入要注册的管理员ID:     若ID=[0.上一级     Q|q.退出系统]");
			id=in.next();
			if("0".equals(id)){
				System.out.println("--------------------已返回管理员后台界面---------------------");
				
				return;
			}else if("q".equalsIgnoreCase(id)){
				in.close();
				System.out.println("\n【已退出系统】");
				System.exit(0);
			}
			System.out.println(">请设置管理员姓名:");
			name=in.next();
			System.out.println(">请设置管理员密码:");
			do{
				pw=in.next();
				System.out.println(">请重新输入管理员密码:");
				pwPlus=in.next();
				
				if(pw.equals(pwPlus)){//前后密码一致
					break;
				}
				System.err.println("错误消息:两次密码不一致 日_日\n>重新设置管理员密码:");
				System.err.flush();
				
			}while(true);
			
			ad=new Admin(name,id,pw);
			try {
				if(DAOFactory.getAdminAchieves().regist(ad)==true){
					System.out.println("注册成功,信息为:"+ad);
					System.out.println("--------------------已返回管理员后台界面---------------------");
					return;
				}else{
					System.err.println("注册失败! 您可以重新注册。。。\n");
				}
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				System.err.println("注册失败! 发生异常,您可以重新注册。。。\n");
			}
		System.out.println("-------------------------------------------------------");
   	
		}while(true);
	}	

	private void login(){
		System.out.println("----------------------管理员登录界面-----------------------");
		Scanner in=new Scanner(System.in);
		do{
			
			String id=null;
			String pw=null;
			System.out.println(">请输入管理员ID:     若ID=[0.上一级     Q|q.退出系统]");
			id=in.next();
			if("0".equals(id)){
				System.out.println("--------------------已返回管理员后台界面---------------------");
				
				return;
			}else if("q".equalsIgnoreCase(id)){
				
				System.out.println("\n【已退出系统】");
				System.exit(0);
			}
			System.out.println(">请输入管理员密码:");
			pw=in.next();
			
			admin=new Admin(id,pw);
			try {
				if(DAOFactory.getAdminAchieves().login(admin)==true){
					System.out.println("恭喜名为["+admin.getName()+"]管理员,登录成功!");
					//进入管理商品后台页面
					this.showGoods();
					return; //从商品后台页面退出,直接进入登录或注册页面
				}else{
					admin=null;
					System.err.println("登录失败! 您可以重新登录。。。\n");
				}
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				System.err.println("登录失败! 发生异常,您可以重新登录。。。\n");
				admin=null;
			}
			
   		System.out.println("-------------------------------------------------------");
   		
		}while(true);
		
	}
	
	private void showGoods(){
		System.out.println("-------------------------------[后台管理商品界面]------------------------------------\n");
		
		Scanner in=new Scanner(System.in);
		String code="";
		do{
			System.err.println(">管理员ID:"+this.admin.getId()+"\t管理员姓名:"+this.admin.getName()+"\n");
			try {
				DAOFactory.getGoodsItemrAchieves().showAll();
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				System.err.println("发生异常。。。\n");
			}
			System.out.println("\n>选择操作:\n 1.上架新商品     2.修改商品库存     3.下架商品     0.(注销)返回上一级     Q|q.退出系统");
			
   		 	code=in.next();
   		 	
   		 switch(code){
		    case "Q":;
		    case "q":{ System.out.println("\n【已退出系统】");System.exit(0);}
		    case "0":{	System.err.println("提示信息:注销成功...");
		    			admin=null;
		    			System.out.println("--------------------已返回管理员后台界面---------------------");
		    			return;}
		    case "1":{this.shelveGoods();}
		    		break;//上架新商品页面
		    case "2":{this.updataGoods();}
		    		break;//修改库存页面
		    case "3":{this.delGoods();}
    		break;//修改库存页面
		    default:{code="err";System.err.print("错误信息:输入有误,请重新输入...\n");break;}
		 
		 }
   		
   		if("err".equals(code)){
   			System.out.println(" ---------------------------------------------------------------------------------");
   		}
   		
		}while(true);
	}

	

	private void shelveGoods(){//上架商品菜单
		System.out.println("----------------------新商品上架界面-----------------------");
		Scanner in=new Scanner(System.in);
		
		GoodsItem gsit=null;
		one:{	
			Goods gs=new Goods();
			System.out.println(">请输入商品ID:");
			gs.setId(in.next());
			gsit=new GoodsItem(gs);
			try {
				if(!DAOFactory.getGoodsItemrAchieves().doValidate(gsit)){
					System.err.println("存在相同ID的商品,你可以选择修改库存,返回商品列表。。。");
					break one;
				}
				System.out.println(">请输入要上架商品名:");
				gs.setName(in.next());
				System.out.println(">请输入商品价格:");
				gs.setPrice(in.nextDouble());
				System.out.println(">请输入商品品牌:");
				gs.setBrand(in.next());
				System.out.println(">请输入商品简介(不要有空格。。。):");
				gs.setBlurb(in.next()); 
				
				//gsit.setGoods(gs);//再放一次，不知道有没有必要，看结果好像并没有什么区别
				System.out.println(">请输入商品库存数量:");
				gsit.setNum(in.nextInt());
				
				if(DAOFactory.getGoodsItemrAchieves().doAdd(gsit)){
					System.err.println("添加商品成功^_^,返回商品列表。。。");
					break one;
				}else{
					System.err.println("添加商品失败o_o,返回商品列表。。。");
					break one;
				}
				
			}  catch (InputMismatchException e) {
				//e.printStackTrace();
				System.err.println("输入类型转换错误,返回商品列表。。。\n");
				break one;
			}  catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				System.err.println("发生异常,返回商品列表。。。\n");
				break one;
			}
		}
		System.out.println("-------------------------------------------------------");
		
	}
	
	private void updataGoods(){//修改商品库存菜单
		System.out.println("---------------------商品库存更新界面----------------------");
		Scanner in=new Scanner(System.in);
		
		GoodsItem gsit=null;
		one:{	
			Goods gs=new Goods();
			System.out.println(">请输入要更新的商品ID:");
			gs.setId(in.next());
			gsit=new GoodsItem(gs);
			try {
				if(DAOFactory.getGoodsItemrAchieves().doValidate(gsit)){
					System.err.println("不存在ID=["+gs.getId()+"]的商品,你可以选择上架新商品,返回商品列表。。。");
					break one;
				}
				
				System.out.println(">请输入商品新的库存数量:");
				gsit.setNum(in.nextInt());
				
				if(DAOFactory.getGoodsItemrAchieves().doUpdate(gsit)){
					System.err.println("更新商品库存成功^_^,返回商品列表。。。");
					break one;
				}else{
					System.err.println("更新商品库存失败o_o,返回商品列表。。。\n");
					break one;
				}
				
			}  catch (InputMismatchException e) {
				//e.printStackTrace();
				System.err.println("输入类型转换错误,返回商品列表。。。\n");
				break one;
			}  catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				System.err.println("发生异常,返回商品列表。。。\n");
				break one;
			}
		}
		System.out.println("-------------------------------------------------------");
		
	}
	private void delGoods() {//删除指定id的商品
		// TODO 自动生成的方法存根
		System.out.println("----------------------旧商品下架界面-----------------------");
		Scanner in=new Scanner(System.in);
		
		GoodsItem gsit=null;
		one:{	
			Goods gs=new Goods();
			System.out.println(">请输入要下架的商品ID:");
			gs.setId(in.next());
			gsit=new GoodsItem(gs);
			try {
				if(DAOFactory.getGoodsItemrAchieves().doValidate(gsit)){
					System.err.println("不存在ID=["+gs.getId()+"]的商品,返回商品列表。。。");
					break one;
				}
				
				if(DAOFactory.getGoodsItemrAchieves().doDelete(gsit)){
					System.err.println("下架ID=["+gs.getId()+"]的商品成功^_^,返回商品列表。。。");
					break one;
				}else{
					System.err.println("下架ID=["+gs.getId()+"]的商品失败o_o,返回商品列表。。。\n");
					break one;
				}
				
			}  catch (InputMismatchException e) {
				//e.printStackTrace();
				System.err.println("输入类型转换错误,返回商品列表。。。\n");
				break one;
			}  catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				System.err.println("发生异常,返回商品列表。。。\n");
				break one;
			}
		}
		System.out.println("-------------------------------------------------------");
	}
	
}
