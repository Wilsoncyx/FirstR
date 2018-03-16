package mipstomachine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class change {
	private static String signs[];
	private static String regex1,regex2,regex3,regex4;
	public static void main(String a,String b){
		int countl=0;
		
		int start,end;//取字符串的位置
		
		regex1=":";regex2=" ";regex3=",";regex4="\\.|;";
		
		signs=new String[400];
		String op="",r1="",r2="",r3="";//标志，操作名，寄存器1，2，3
		try{
			StringBuffer sb=new StringBuffer("");
			
//			FileReader reader=new FileReader("D://translate.txt");
			FileReader reader=new FileReader(a);
			BufferedReader br=new BufferedReader(reader);
			
			String str="";
			while((str=br.readLine())!=null){
				countl++;start=0;end=0;
				
				Pattern p1=Pattern.compile(regex1);//将regex1中内容编译成寻找规则存入p1
				Matcher m1=p1.matcher(str);//在str中寻找p1
		
				if(m1.find()){
					end=m1.end()-1;
					signs[countl]=str.substring(start,end);//signs[]=标志，countl=标志行数
					start=m1.end();
					
				
				}
				//System.out.println(signs[countl]);
			}
			
			
			br.close();
			reader.close();
			
//			reader=new FileReader("D://translate.txt");
			
			reader=new FileReader(a);
			br=new BufferedReader(reader);
			
			countl=0;
			String ops="";
			String scut="";
			str="";
			String machine="";
			//按行读入数据
			while((str=br.readLine())!=null){
				countl++;start=0;end=0;int firststart=0;
				str+=".";
				
				System.out.println("countl:"+countl);
				if(issign(signs,countl))
					start=signs[countl].length()+1;
				Pattern p2=Pattern.compile(regex2);//将regex1中内容编译成寻找规则存入p2
				Pattern p3=Pattern.compile(regex3);//将regex1中内容编译成寻找规则存入p3
				Pattern p4=Pattern.compile(regex4);//将regex1中内容编译成寻找规则存入p4
				
				Matcher m=p2.matcher(str);
				if(m.find()){
					//System.out.println(m.start()+"\n"+m.end());
					end=m.end()-1;
					scut=str.substring(start,end);
					op=codech(scut);
					ops=scut;
					start=m.end();
					firststart=m.end();
				}
				
				m=p3.matcher(str);
				if(m.find()){
					//System.out.println(m.start()+"\n"+m.end());
					end=m.end()-1;
					scut=str.substring(start,end);
					r1=regch(scut);
					start=m.end();
				}
				
				if(m.find()){
					//System.out.println(m.start()+"\n"+m.end());
					end=m.end()-1;
					scut=str.substring(start,end);
					if(regch(scut)==null)	r2=numch(scut,ops,countl);
					else	r2=regch(scut);
					start=m.end();
					//System.out.println(end+"4");
					//System.out.println(":"+m.end());
				}
				
				m=p4.matcher(str);
				if(m.find()){
					//System.out.println(start+"\n"+m.start()+"\n"+m.end());
					end=m.start();
					if(start>end)
						start=firststart;
					scut=str.substring(start,end);
					if(regch(scut)==null)	r3=numch(scut,ops,countl);
					else	r3=regch(scut);
					start=m.end();
					//System.out.println("str:"+str);
					
				}
				
				machine=connect(ops,op,r1,r2,r3);
						
				sb.append(machine+"\r\n");
				
				//System.out.println(machine);
			}
			
			br.close();
			reader.close();//关闭文件
			//写文件
			FileWriter writer=new FileWriter(b);
			BufferedWriter bw=new BufferedWriter(writer);
			bw.write(sb.toString());
			
			bw.close();
			writer.close();
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static String codech(String string){
		switch(string){
		case "add":
			string="000000";
			break;
		case "addi":
			string="001000";
			break;
		case "addiu":
			string="001001";
			break;
		case "addu":
			string="000000";
			break;
		case "and":
			string="000000";
			break;
		case "andi":
			string="001100";
			break;
		case "beq":
			string="000100";
			break;
		case "bgtz":
			string="000111";
			break;
		case "bltz":
			string="000001";
			break;
		case "bne":
			string="000101";
			break;
		case "jal":
			string="000011";
			break;
		case "j":
			string="000010";
			break;
		case "jr":
			string="000000";
			break;
		case "lbu":
			string="100100";
			break;
		case "lhu":
			string="100101";
			break;
		case "lui":
			string="001111";
			break;
		case "lw":
			string="100011";
			break;
		case "nor":
			string="000000";
			break;
		case "or":
			string="000000";
			break;
		case "ori":
			string="001101";
			break;
		case "sb":
			string="101000";
			break;
		case "sh":
			string="101001";
			break;
		case "sll":
			string="000000";
			break;
		case "sllv":
			string="000000";
			break;
		case "slt":
			string="000000";
			break;
		case "slti":
			string="001010";
			break;
		case "sltiu":
			string="001011";
			break;
		case "sltu":
			string="000000";
			break;
		case "sra":
			string="000000";
			break;
		case "srav":
			string="000000";
			break;
		case "srl":
			string="000000";
			break;
		case "sub":
			string="000000";
			break;
		case "subu":
			string="000000";
			break;
		case "sw":
			string="101011";
			break;
		case "xor":
			string="000000";
			break;
		case "xori":
			string="001110";
			break;
		default:
			break;
		}
		return string;
	}
	public static String regch(String string){
		switch(string){
		case "$t0":
			string="01000";
			break;
		case "$t1":
			string="01001";
			break;
		case "$t2":
			string="01010";
			break;
		case "$t3":
			string="01011";
			break;
		case "$t4":
			string="01100";
			break;
		case "$t5":
			string="01101";
			break;
		case "$t6":
			string="01110";
			break;
		case "$t7":
			string="01111";
			break;
		case "$t8":
			string="11000";
			break;
		case "$t9":
			string="11001";
			break;
		case "$zero":
			string="00000";
			break;
		case "$s0":
			string="10000";
			break;	
		case "$s1":
			string="10001";
			break;
		case "$s2":
			string="10010";
			break;
		case "$s3":
			string="10011";
			break;
		case "$s4":
			string="10100";
			break;
		case "$s5":
			string="10101";
			break;
		case "$s6":
			string="10110";
			break;
		case "$s7":
			string="10111";
		case "$s8":
			string="11110";
			break;
		case "$fp":
			string="11110";
			break;
		case "$sp":
			string="11101";
			break;
		case "$gp":
			string="11100";
			break;
		case "$at":
			string="00001";
			break;
		case "$ra":
			string="11111";
			break;
		case "$v0":
			string="00010";
			break;
		case "$v1":
			string="00011";
			break;
		case "$a0":
			string="00100";
			break;
		case "$a1":
			string="00101";
			break;
		case "$a2":
			string="00110";
			break;
		case "$a3":
			string="00111";
			break;
		case "$k0":
			string="11010";
			break;
		case "$k1":
			string="11011";
			break;
		default:
			string=null;
			break;
		}
		
		return string;
	}
	public static String numch(String string,String ops,int countl){
		int num,size;//转换数字，规模为offset或target
		int find=0;
		size=16;
		switch(ops){
		case "beq":
			string=bjump(string,countl);
			break;
		case "bgtz":
			string=bjump(string,countl);
			break;
		case "bltz":
			string=bjump(string,countl);
			break;
		case "bne":
			string=bjump(string,countl);
			break;
		case "jal":
			while(find<400){
				if(string.equals(signs[find])){//寻找与string标签相符的行数
					break;
				}
				find++;
			}
			
			System.out.println("find:"+find);
			
			string=jumpedit2(string,find);
			break;
		case "j":
			while(find<400){
				if(string.equals(signs[find])){//寻找与string标签相符的行数
					break;
				}
				find++;
			}
			
			System.out.println("find:"+find);
			
			string=jumpedit2(string,find);
			break;
		case "sll":
			String cut=string.substring(0,1);
			//System.out.println(string.substring(0,1));
			if(cut.equals("h")){
				string=string.substring(1,string.length());
				string=Integer.valueOf(string,16).toString();
				num=Integer.valueOf(string);
				string=Integer.toBinaryString(num).toString();
			}else if(cut.equals("b")){
				string=string.substring(1,string.length());
			}else{
				num=Integer.valueOf(string);
				//System.out.println(num);
				if(num<0){
					num=-num;
					string=Integer.toBinaryString(num).toString();
					while(string.length()<4)
						string="0"+string;
					string="1"+string;
				}else
					string=Integer.toBinaryString(num).toString();
			}
			while(string.length()<5)
				string="0"+string;
			break;
		case "srl":
			String cut1=string.substring(0,1);
			//System.out.println(string.substring(0,1));
			if(cut1.equals("h")){
				string=string.substring(1,string.length());
				string=Integer.valueOf(string,16).toString();
				num=Integer.valueOf(string);
				string=Integer.toBinaryString(num).toString();
			}else if(cut1.equals("b")){
				string=string.substring(1,string.length());
			}else{
				num=Integer.valueOf(string);
				//System.out.println(num);
				if(num<0){
					num=-num;
					string=Integer.toBinaryString(num).toString();
					while(string.length()<4)
						string="0"+string;
					string="1"+string;
				}else
					string=Integer.toBinaryString(num).toString();
			}
			while(string.length()<5)
				string="0"+string;
			break;
		default:
			String cut11=string.substring(0,1);
			//System.out.println(string.substring(0,1));
			if(cut11.equals("h")){
				string=string.substring(1,string.length());
				string=Integer.valueOf(string,16).toString();
				num=Integer.valueOf(string);
				string=Integer.toBinaryString(num).toString();
			}else if(cut11.equals("b")){
				string=string.substring(1,string.length());
			}else{
				num=Integer.valueOf(string);
				//System.out.println(num);
				if(num<0){
					num=-num;
					string=Integer.toBinaryString(num).toString();
					while(string.length()<size-1)
						string="0"+string;
					string="1"+string;
				}else
					string=Integer.toBinaryString(num).toString();
			}
			while(string.length()<size)
				string="0"+string;
			//System.out.println(string);
			break;
		}
		return string;
	}
	public static String lastcode(String string){
		switch(string){
		case "add":
			string="00000100000";
			break;
		case "addu":
			string="00000100001";
			break;
		case "and":
			string="00000100100";
			break;
		case "jr":
			string="000000000000000001000";
			break;
		case "nor":
			string="00000100111";
			break;
		case "or":
			string="00000100101";
			break;
		case "sll":
			string="000000";
			break;
		case "sllv":
			string="00000000100";
			break;
		case "slt":
			string="00000101010";
			break;
		case "sltu":
			string="00000101011";
			break;
		case "sra":
			string="00000000011";
			break;
		case "srav":
			string="00000000111";
			break;
		case "srl":
			string="000010";
			break;
		case "sub":
			string="00000100010";
			break;
		case "subu":
			string="00000100011";
			break;
		case "xor":
			string="00000100110";
			break;
		default:
			string="";
			break;
		}
		return string;
	}
	public static Boolean issign(String signs[],int countl){
		if(signs[countl]==null)	return false;
		else	return true;
	}
	public static String jumpedit1(String string,int find,int countl){
		int num;
		if(find>countl){
			num=find-countl-1;
			//System.out.println(num);
			string=Integer.toBinaryString(num).toString();
			while(string.length()<16)
				string="0"+string;
		}else{
			num=countl+1-find;
			//System.out.println(num);
			string=Integer.toBinaryString(num).toString();
			while(string.length()<15)
				string="0"+string;
			string="1"+string;
		}
		return string;
	}
	public static String jumpedit2(String string,int find){
		int num=find-1;
		string=Integer.toBinaryString(num).toString();
		while(string.length()<26)
			string="0"+string;
		return string;
	}
	public static String connect(String ops,String op,String r1,String r2,String r3){
		String machine=null;
		switch(ops){
		case "add":
			machine=op+r2+r3+r1;
			break;
		case "addi":
			machine=op+r2+r1+r3;
			break;
		case "addiu":
			machine=op+r2+r1+r3;
			break;
		case "addu":
			machine=op+r2+r3+r1;
			break;
		case "and":
			machine=op+r2+r3+r1;
			break;
		case "andi":
			machine=op+r2+r1+r3;
			break;
		case "beq":
			machine=op+r1+r2+r3;
			break;
		case "bgtz":
			machine=op+r1+"00000"+r3;
			break;
		case "bltz":
			machine=op+r1+"00000"+r3;
			break;
		case "bne":
			machine=op+r1+r2+r3;
			break;
		case "jal":
			machine=op+r3;
			break;
		case "j":
			machine=op+r3;
			break;
		case "jr":
			machine=op+r3;
			break;
		case "lbu":
			machine=op+r3+r1+r2;
			break;
		case "lhu":
			machine=op+r3+r1+r2;
			break;
		case "lui":
			machine=op+"00000"+r1+r3;
			break;
		case "lw":
			machine=op+r3+r1+r2;
			break;
		case "nor":
			machine=op+r2+r3+r1;
			break;
		case "or":
			machine=op+r2+r3+r1;
			break;
		case "ori":
			machine=op+r2+r1+r3;
			break;
		case "sb":
			machine=op+r3+r1+r2;
			break;
		case "sh":
			machine=op+r3+r1+r2;
			break;
		case "sll":
			machine=op+"00000"+r2+r1+r3;
			break;
		case "sllv":
			machine=op+r3+r2+r1;
			break;
		case "slt":
			machine=op+r2+r3+r1;
			break;
		case "slti":
			machine=op+r2+r3+r1;
			break;
		case "sltiu":
			machine=op+r2+r1+r3;
			break;
		case "sltu":
			machine=op+r2+r3+r1;
			break;
		case "sra":
			machine=op+"00000"+r2+r1+r3;
			break;
		case "srav":
			machine=op+r3+r2+r1;
			break;
		case "srl":
			machine=op+"00000"+r2+r1+r3;
			break;
		case "sub":
			machine=op+r2+r3+r1;
			break;
		case "subu":
			machine=op+r2+r3+r1;
			break;
		case "sw":
			machine=op+r3+r1+r2;
			break;
		case "xor":
			machine=op+r2+r3+r1;
			break;
		case "xori":
			machine=op+r2+r1+r3;
			break;
		default:
			break;
		}
		machine+=lastcode(ops);
		return machine;
	}
	public static String bjump(String string,int countl){
		int find=0;
		while(find<400){
			if(string.equals(signs[find]))//寻找与string标签相符的行数
				break;
			find++;
		}
		System.out.println("find:"+find);
		string=jumpedit1(string,find,countl);
		return string;
	}
	
	
}
