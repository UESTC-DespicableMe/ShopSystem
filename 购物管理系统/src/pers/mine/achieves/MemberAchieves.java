package pers.mine.achieves;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import pers.mine.IO.DataIO;
import pers.mine.dao.MemberDAO;
import pers.mine.vo.DataName;
import pers.mine.vo.Member;

public class MemberAchieves implements MemberDAO {
	private Map<String,Member> members=null;
	
	/**
	 * 获取data 的会员map
	 */
	public MemberAchieves() {
		DataIO<String,Member> dio=new DataIO<String,Member>();
		Map<String,Member> members=new HashMap <String,Member>();
		members=dio.readData(DataName.memberMap);
		if(members==null){
			members=new HashMap<String,Member>();
		}
		this.members=members;
		
	}

	@Override
	public boolean regist(Member me) throws Exception {
		// TODO 自动生成的方法存根
		if(!this.doValidate(me)){//如果验证不通过
			System.err.println("存在相同用户名");
			return false;
		}
		DataIO<String,Member> dio=new DataIO<String,Member>();
		members.put(me.getId(),me);//放入Map
		if(!dio.writeData(members, DataName.memberMap)){//写入失败
			System.err.println("注册操作写入失败");
			return false;
		}
		return true;
	}

	@Override
	public boolean login(Member me) throws Exception {
		// TODO 自动生成的方法存根
		if(this.doValidate(me)){//如果用户名不存在
			System.err.println("用户名不存在");
			return false;
		}
		String mapPw=members.get(me.getId()).getPW();//获取原始密码
		if(!me.getPW().equals(mapPw)){//密码不匹配
			System.err.println("密码不匹配");
			return false;
		}
		Member one=members.get(me.getId());//转移信息到参数
		me.setName(one.getName());
		me.setCart(one.getCart());
		me.setLevel(one.getLevel());
		me.setSex(one.getSex());
		me.setTel(one.getTel());
		
		return true;
	}

	@Override
	public boolean doValidate(Member me) throws Exception {
		// TODO 自动生成的方法存根
		if(members.get(me.getId())!=null){//存在相同的用户名
			return false;
		}
		return true;
	}

	@Override
	public void showAll() throws Exception {
		// TODO 自动生成的方法存根
		Collection <Member> ms=members.values();
		Iterator<Member> it=ms.iterator();
		int i=1;
		while(it.hasNext()){
			System.out.println(i+":"+it.next());
			i++;
		}
		
	}

	/**
	 * 由于map特性，和注册的代码几乎一样
	 * */
	@Override
	public boolean save(Member nowMe) throws Exception {
		// TODO 自动生成的方法存根
		if(this.doValidate(nowMe)){//如果用户名不存在
			System.err.println("用户名不存在");
			return false;
		}
		DataIO<String,Member> dio=new DataIO<String,Member>();
		members.put(nowMe.getId(),nowMe);//放入Map,存在相同的key会自己覆盖
		if(!dio.writeData(members, DataName.memberMap)){//写入失败
			System.err.println("更新操作写入失败");
			return false;
		}
		return true;
	}

}
