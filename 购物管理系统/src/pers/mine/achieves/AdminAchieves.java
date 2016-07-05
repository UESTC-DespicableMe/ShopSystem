package pers.mine.achieves;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import pers.mine.IO.DataIO;
import pers.mine.dao.AdminDAO;
import pers.mine.vo.Admin;
import pers.mine.vo.DataName;

public class AdminAchieves implements AdminDAO {
	private Map<String,Admin> admins=null;
	/**
	 * 使用构造方法先获取data  中的管理员列表
	 */
	public AdminAchieves(){
		DataIO<String,Admin> dio=new DataIO<String,Admin>();
		Map<String,Admin> admins=new HashMap <String,Admin>();
		admins=dio.readData(DataName.adminMap);
		if(admins==null){
			admins=new HashMap<String,Admin>();
		}
		this.admins=admins;
	}
	@Override
	public boolean regist(Admin ad) throws Exception {
		// TODO 自动生成的方法存根
		if(!this.doValidate(ad)){//如果验证不通过
			System.err.println("存在相同用户名");
			return false;
		}
		DataIO<String,Admin> dio=new DataIO<String,Admin>();
		admins.put(ad.getId(), ad);//放入Map
		if(!dio.writeData(admins, DataName.adminMap)){//写入失败
			System.err.println("注册操作写入失败");
			return false;
		}
		return true;
	}

	@Override
	public boolean login(Admin ad) throws Exception {
		// TODO 自动生成的方法存根
		if(this.doValidate(ad)){//如果用户名不存在
			System.err.println("用户名不存在");
			return false;
		}
		String mapPw=admins.get(ad.getId()).getPW();//获取原始密码
		if(!ad.getPW().equals(mapPw)){//密码不匹配
			System.err.println("密码不匹配");
			return false;
		}
		ad.setName(admins.get(ad.getId()).getName());//将用户姓名取出来
		return true;
	}

	@Override
	/**
	 * @return false表示存在相同的用户名  true表示可以注册
	 */
	public boolean doValidate(Admin ad) throws Exception {
		// TODO 自动生成的方法存根
		if(admins.get(ad.getId())!=null){//存在相同的用户名
				return false;
		}
		return true;
	}
	
	@Override
	public boolean removeOne(Admin ad) throws Exception {
		// TODO 自动生成的方法存根
		return false;
	}
	@Override
	public void showAll() throws Exception {
		// TODO 自动生成的方法存根
		
		Collection <Admin> as=admins.values();
		Iterator<Admin> it=as.iterator();
		int i=1;
		while(it.hasNext()){
			System.out.println(i+":"+it.next());
			i++;
		}
	}
}
