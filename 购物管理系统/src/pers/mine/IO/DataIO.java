package pers.mine.IO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;

import pers.mine.vo.DataName;


public class  DataIO<K, V> {
	private final String FILE_PATH="data/"; //相对路径
	private final String FILE_TAIL=".data"; //后缀
	
	public synchronized Map<K,V> readData(DataName dn){
		Map<K,V> dataMap=null;
		String fileName=this.FILE_PATH+dn+this.FILE_TAIL;
		FileInputStream fis=null;
		ObjectInputStream ois=null;
		File file=new File(fileName);
		
		try{
			if(!file.exists()){
				file.createNewFile();
			}
			fis=new FileInputStream(file);
			ois=new ObjectInputStream(fis);
			dataMap=(Map<K, V>)ois.readObject();
			
		}catch(IOException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(fis!=null) fis.close();
				if(ois!=null) ois.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		
		return dataMap;
	}
	
	public synchronized boolean writeData(Map<K,V> dataMap,DataName dn){
		String fileName=this.FILE_PATH+dn+this.FILE_TAIL;
		
		FileOutputStream fos=null;
		ObjectOutputStream oos=null;
		File file=new File(fileName);
		
		try{
			if(!file.exists()){
				file.createNewFile();
			}
			fos=new FileOutputStream(file);
			oos=new ObjectOutputStream(fos);
			oos.writeObject(dataMap);
			return true;
		}catch(IOException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
				if(fos!=null) fos.close();
				if(oos!=null) oos.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return false;
	}

}
