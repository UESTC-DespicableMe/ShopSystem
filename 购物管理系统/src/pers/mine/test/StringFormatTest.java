package pers.mine.test;

public class StringFormatTest {

	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		String s1="王a晓b强";
		String s2="王小强";
		String s3="王";
		int i1=0;
		int i2=198;
		double x=5.55;
		double x1=66.66;
		System.out.printf("%-6s|%-6s|%-6s\n",s1,s2,s3);
		System.out.printf("%-6s|%-6s|%-6s\n",s3,s2,s1);
		System.out.printf("%-6s|%-6s|%-6s\n",i1,x,s1);
		System.out.printf("%-6s|%-6s|%-6s",i2,x1,s1);
		System.out.println(s1+"\t"+s2+"\t"+s3+"\t");
		System.out.println(s2+"\t"+s1+"\t"+s3+"\t");
		System.out.println(s3+"\t"+s2+"\t"+s1+"\t");
	}

}
