package pers.mine.menu;

import java.util.InputMismatchException;
import java.util.Scanner;

import pers.mine.factory.DAOFactory;
import pers.mine.tool.RandomID;
import pers.mine.vo.Cart;
import pers.mine.vo.Goods;
import pers.mine.vo.GoodsItem;
import pers.mine.vo.Member;

public class MemberMenu {
	private Member me=null;
	private Cart cart=null;
	public  void show(){
		System.out.println("---------------------会员购物前台界面----------------------");
		Scanner in=new Scanner(System.in);
		String code="";
		do{
			System.out.println(">您可以选择购物,登录或注册:\n 1.直接购物     2.会员登录     3.会员注册     0.上一级    Q|q.退出系统");
			System.err.print("注:如果跳过登录直接购物，期间生成的购物车会在后续登录后[覆盖]掉data中的购物车信息\n");
   		 	code=in.next();
   		 	
   		 switch(code){
		    case "Q":;
		    case "q":{ System.out.println("\n【已退出系统】");System.exit(0);}
		    case "0":System.out.println("********************已返回到购物管理菜单*********************");return;
		    case "1":this.showGoods();break;
		    case "2":this.login();break;
		    case "3":this.register();break;
		    default:{code="err";System.err.print("错误信息:输入有误,请重新输入...\n");break;}
		   
		 }
   		
   		if("err".equals(code)){
   			System.out.println("-------------------------------------------------------");
   		}
		}while(true);
	}

	private void register() {
		// TODO 自动生成的方法存根
		System.out.println("----------------------新会员注册界面-----------------------");
		Scanner in=new Scanner(System.in);
		do{
			
			
			String pwPlus=null;
			Member me=new Member();
			me.setLevel(1);//设置级别为普通
			//会员ID自动生成
			String id=RandomID.getMemberID(6);
			System.out.println(">自动生成新会员ID:");
			me.setId(id);
			System.out.println("ID:"+ me.getId());
			System.out.println(">请输入要注册的会员姓名:     若ID=[0.上一级     Q|q.退出系统]");
			me.setName(in.next());
			if("0".equals(me.getName())){
				System.out.println("-------------------已返回会员购物前台界面--------------------");
				
				return;
			}else if("q".equalsIgnoreCase(me.getName())){
				in.close();
				System.out.println("\n【已退出系统】");
				System.exit(0);
			}
			
			String sexCode;
			do{
				System.out.println(">请选择会员性别<1.男  2.女  3.其他>:");
				sexCode=in.next();
				if("1".equals(sexCode)){
					me.setSex("男");
				}else if("2".equals(sexCode)){
					me.setSex("女");
				}else if("3".equals(sexCode)){
					me.setSex("其他");
				}
				if(me.getName()!=null||(!"".equals(me.getName()))){
					break;
				}
				System.err.print("输入有误....重新选择 0_0 \n");
			}while(true);
			System.out.println(">请输入联系方式:");
			me.setTel(in.next());
			System.out.println(">请设置会员密码:");
			do{
				me.setPW(in.next());
				System.out.println(">请重新输入会员密码:");
				pwPlus=in.next();
				
				if(me.getPW().equals(pwPlus)){//前后密码一致
					break;
				}
				System.err.println("错误消息:两次密码不一致 日_日\n>重新设置管理员密码:");
				System.err.flush();
				
			}while(true);
			
			
			try {
				if(DAOFactory.getMemberAchieves().regist(me)){
					System.out.println("注册成功,信息为:"+me);
					System.out.println("-------------------已返回会员购物前台界面--------------------");
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

	private void login() {
		// TODO 自动生成的方法存根
		System.out.println("----------------------会员登录界面-----------------------");
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
			me=new Member(id);
			try {
				if(DAOFactory.getMemberAchieves().doValidate(me)){
					me=null;
					System.err.print("不存在的会员ID...重新登录0_0\n");
					continue;
				}
			} catch (Exception e1) {
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			
			System.out.println(">请输入管理员密码:");
			pw=in.next();
			
			me.setPW(pw);
			try {
				if(DAOFactory.getMemberAchieves().login(me)){
					System.out.println("恭喜名为["+me.getName()+"]会员,登录成功!");
					if(cart!=null&&cart.getNumSum()!=0){
						me.setCart(cart);
						System.err.println("之前存在购物车，已覆盖掉以前的购物车");
					}else{
						cart=me.getCart();
					}
					//System.out.println("信息:"+me);
					
					//进购买页面
					this.showGoods();
					return; //从商品后台页面退出,直接进入登录或注册页面
				}else{
					me=null;
					System.err.println("登录失败! 您可以重新登录。。。\n");
				}
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				System.err.println("登录失败! 发生异常,您可以重新登录。。。\n");
				me=null;
			}
			
   		System.out.println("-------------------------------------------------------");
   		
		}while(true);
		
	}

	private void showGoods() {
		// TODO 自动生成的方法存根
		
		Scanner in=new Scanner(System.in);
		String code="";
		String info="注销";
		String backInfo="提示信息:注销成功...";
		do{
			System.out.println("---------------------------------[前台购买商品界面]------------------------------------\n");
			System.out.flush();
			System.err.print("注:购买物品时请至少留出1件商品库存，否则购买失败！\n");
			if(me==null){
			info="登录";
				backInfo="返回上一级成功";
				System.err.println(">当前处于未登录状态。。。\n");
				if(cart==null){
					cart=new Cart();//新给一个购物车
					System.err.println("注:已分配一个空购物车");
				}
			}else{
				System.err.println(">会员ID:"+me.getId()+"\t会员姓名:"+me.getName()+"\n");
			}
			try {
				DAOFactory.getGoodsItemrAchieves().showAll();
			} catch (Exception e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
				System.err.println("发生异常。。。\n");
			}
			System.out.println("\n>选择操作:\n 1.直接购买某件商品     2.放入商品到购物车     3.查看购物车    4.保存购物车    0.("+info+")返回上一级     Q|q.退出系统");
			
   		 	code=in.next();
   		 	
   		 switch(code){
		    case "Q":;
		    case "q":{ System.out.println("\n【已退出系统】");System.exit(0);}
		    case "0":{	System.err.println(backInfo);
		    			me=null;
		    			System.out.println("--------------------已返回会员购物前台界面---------------------");
		    			return;}
		    case "1":{this.buyGoods();}
		    		break;//上架新商品页面
		    case "2":{this.putCart();}
		    		break;//修改库存页面
		    case "3":{this.showCartMenu();break;}  //展示购物车界面
		    case "4":{this.saveCart();break;}  //保存购物车操作
    		
		    default:{code="err";System.err.print("错误信息:输入有误,请重新输入...\n");break;}
		 
		 }
   		
   		if("err".equals(code)){
   			System.out.println(" ---------------------------------------------------------------------------------");
   		}
   		
		}while(true);
		
	}
	private void saveCart() {
		// TODO 自动生成的方法存根
		if(me==null){
			System.err.println("用户未登录，无法保存，请先登录。。。。");
			return;
		}
		me.setCart(cart);//保险一点
		try {
			if(DAOFactory.getMemberAchieves().save(me)){
				System.err.println("✌:购物车已保存到data。。。");
				return;
			}
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		System.err.println("未知原因，无法保存。。。");
	}

	private void putCart() {//将指定id和数量的商品放入购物车
		// TODO 自动生成的方法存根
		System.out.println("--------------------添加到购物车界面---------------------");
		Scanner in=new Scanner(System.in);
		
		GoodsItem gsit=null;
		one:{	
			Goods gs=new Goods();
			System.out.println(">请输入要添加到购物车的商品ID:");
			gs.setId(in.next());
			gsit=new GoodsItem(gs);
			try {
				if(DAOFactory.getGoodsItemrAchieves().doValidate(gsit)){
					System.err.println("不存在ID=["+gs.getId()+"]的商品,返回商品列表。。。");
					break one;
				}
				if(!DAOFactory.getGoodsItemrAchieves().getOne(gsit)){
					System.err.println("ID=["+gs.getId()+"]的商品信息获取失败,返回商品列表。。。");
					break one;
				}
				System.out.println("商品信息:"+gsit);
				System.out.println(">请输入要添加的数量:");
				int num=in.nextInt();
				int newNum=gsit.getNum()-num;
				if(newNum<1){
					//System.out.println(newNum);
					System.err.println("商品数量非法,返回商品列表。。。");
					break one;
				}
				gsit.setNum(num);
				cart.put(gsit);
				System.err.println("添加["+num+"]件,ID=["+gs.getId()+"]的商品到购物车成功,返回商品列表。。。");
				
				
				
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
		
	}
	

	private void buyGoods() {//直接购买指定数量的商品
		// TODO 自动生成的方法存根
		System.out.println("----------------------直接购买界面-----------------------");
		Scanner in=new Scanner(System.in);
		
		GoodsItem gsit=null;
		one:{	
			Goods gs=new Goods();
			System.out.println(">请输入要购买的商品ID:");
			gs.setId(in.next());
			gsit=new GoodsItem(gs);
			try {
				if(DAOFactory.getGoodsItemrAchieves().doValidate(gsit)){
					System.err.println("不存在ID=["+gs.getId()+"]的商品,返回商品列表。。。");
					break one;
				}
				if(!DAOFactory.getGoodsItemrAchieves().getOne(gsit)){
					System.err.println("ID=["+gs.getId()+"]的商品信息获取失败,返回商品列表。。。");
					break one;
				}
				System.out.println("商品信息:"+gsit);
				System.out.println(">请输入要购买的数量:");
				int num=in.nextInt();
				int newNum=gsit.getNum()-num;
				if(newNum<1){
					System.err.println("商品数量非法,返回商品列表。。。");
					break one;
				}
				gsit.setNum(newNum);
				System.out.println(">需要金额:"+String.format("%5.2f(￥)", (num*gsit.getGoods().getPrice())));
				do{
					System.out.println(" 是否结账?(Y.是     N.否):");
					String code=in.next();
				
					if("y".equalsIgnoreCase(code)){
					//结账
						DAOFactory.getGoodsItemrAchieves().doUpdate(gsit);
						System.err.println("^_^结账成功！返回商品界面");
						break one;
					}else if("n".equalsIgnoreCase(code)){
					//不结账
						System.err.println("0_0没有结账！返回商品界面");
						break one;
					}
					System.err.print("错误信息:输入有误,请重新输入...\n");

				}while(true);
				
				
				
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
		
	}

	private void showCartMenu() {//显示购物车商品列表界面
		// TODO 自动生成的方法存根
		if(cart!=null&&cart.getNumSum()!=0){
			if(me==null){
				System.err.println("☒ 游客的匿名购物车");
			}else{
				System.out.println("☑ 会员:"+me.getName()+" 的购物车");
			}
			CartMenu cm=new CartMenu(cart); 
			cm.showCart();
			/*System.out.println("外侧cart信息:");
			cart.show();*/
			return;
		}else{
			System.err.println("购物车为空，请先添加商品到购物车...\n>返回购物界面\n");
			return;
		}
	}

	
}
