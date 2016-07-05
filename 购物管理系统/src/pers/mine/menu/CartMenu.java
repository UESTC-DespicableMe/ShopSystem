package pers.mine.menu;

import java.util.Scanner;

import pers.mine.factory.DAOFactory;
import pers.mine.vo.Cart;
import pers.mine.vo.Goods;
import pers.mine.vo.GoodsItem;

public class CartMenu {
		private Cart cart=null;
		
		/**
		 * @param cart
		 */
		public CartMenu(Cart cart) {
			super();
			this.cart = cart;
		}
		
		public void showCart(){
			
			System.out.println("✈ 进入购物车操作");
			Scanner in=new Scanner(System.in);
			String code="";
			do{
				System.err.println(">显示购物车商品");
				cart.show();
				System.out.println(">请选择:\n 1.修改商品数量     2.删除商品    3.结账      4.清空     0.退出购物车    Q|q.退出系统");
				code=in.next();
				
				 switch(code){
				    case "Q":;
				    case "q":{ System.out.println("\n【已退出系统】");System.exit(0);}
				    case "0":return;
				    case "1":this.update();break;
				    case "2":this.delete();break;
				    case "3":this.checkout();break;
				    case "4":{cart.clear();System.err.print("清空购物车成功！\n");return;}
				   
				    default:{code="err";System.err.print("错误信息:输入有误,请重新输入...\n");break;}
				 }
				 if("err".equals(code)){
			   			System.out.println(" ---------------------------------------------------------------------------------");
			   		}
				 if(cart.getNumSum()==0){
						System.err.println("✉:购物车已空，返回商品列表。。。");
						return;
					}
			}while(true);
		}

		

		private void checkout() {
			// TODO 自动生成的方法存根
			Scanner in=new Scanner(System.in);
			String code="";
			do{
				System.out.println("✈ 选择结账?(Y.是   N.否)");
				code=in.next();
			
				if("y".equalsIgnoreCase(code)){
				//结账
					if(cart.checkout()){
					System.err.println("^_^结账成功！返回商品界面");
					break;
					}else{
						System.err.println("0_0结账失败！返回商品界面");
					}
				}else if("n".equalsIgnoreCase(code)){
				//不结账
					System.err.println("0_0没有结账！返回商品界面");
					break;
				}
				System.err.print("错误信息:输入有误,请重新输入...\n");

			}while(true);
			
		}

		private void delete() {
			// TODO 自动生成的方法存根
			Scanner in=new Scanner(System.in);
			GoodsItem gsit=new GoodsItem(new Goods());
			
			System.out.println("✎ 输入要删除的商品编号:");
			gsit.getGoods().setId(in.next());
			if(cart.remove(gsit)){
				System.err.println("^_^移除成功！返回购物车...");
				
			}else{
				System.err.println("^_^移除失败！返回购物车...");
				
			}
			
		}

		private void update() {
			// TODO 自动生成的方法存根
			Scanner in=new Scanner(System.in);
			
			GoodsItem gsit=new GoodsItem(new Goods());
			
			System.out.println("✎ :输入要改变数量的商品编号:");
			gsit.getGoods().setId(in.next());
			
			do{
				System.out.println("✎ :输入改变后的数量:");			
				try{
					gsit.setNum(in.nextInt());
					break;
				}catch(Exception e){
					e.printStackTrace();
					System.err.println("输入有误，重新输入...");
					
				}
			}while(true);
			if(cart.doUpdate(gsit)){
				System.err.println("^_^更新成功！返回购物车...");
				
			}else{
				System.err.println("^_^更新失败，未知原因！返回购物车...");
				
			}
		}
		

}
