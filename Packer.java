import java.io.*;
import java.util.*;

public class Packer {
	public static FileOutputStream fout=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		
		
		
		String FolderName,FileName;
		System.out.println("Enter folder name: ");
		FolderName=sc.next();
		
		System.out.println("Enter file name: ");
		FileName=sc.next();
		
		Packing(FolderName,FileName);
		
		
	}
	public static void Packing(String Dir,String Fname) 
	{
		
		try
		{

			File outfile=new File(Fname);
			fout=new FileOutputStream(Fname);
			TravelDir(Dir);
		}
		catch(Exception e)
		{
			System.out.println("exception occur");
		}
	}
	public static void TravelDir(String path)
	{
		
		File fin=new File(path);
		
		File arr[]=fin.listFiles();
		
		for(File filename:arr)
		{
			if(filename.getName().endsWith(".txt"))
			{
				PackFile(filename.getAbsolutePath());
			}
		}
		
	}
	public static void PackFile(String FilePath)
	{
		byte Header[]=new byte[100];
		byte data[]=new byte[1024];
		
		FileInputStream fin=null;
		
		File file=new File(FilePath);
		
		int length=0;
		String temp=FilePath+" "+file.length();
		
		for(int i=temp.length();i<100;i++)
		{
			temp=temp+" ";
		}
		
		Header=temp.getBytes();
		
		try
		{
			fin=new FileInputStream(FilePath);
			
			fout.write(Header,0,Header.length);
			while((length = fin.read(data)) > 0)
			{
				fout.write(data,0,length);
			}
			fin.close();
		}
		catch(Exception e)
		{
			
		}
		Unpacker uobj=new Unpacker();
		uobj.Unpack("combine.txt");
	}
	

}

class Unpacker
{
	FileOutputStream fout=null;
	
	public void Unpack(String src)
	{
		try
		{
			FileInputStream fin=new FileInputStream(src);
			
			byte Header[]=new byte[100];
			
			int length=0;
			while((length=fin.read(Header,0,100))>0)
			{
				String str=new String(Header);
				
				String ext=str.substring(str.lastIndexOf("\\"));
				
				ext=ext.substring(1);
				String Words[]=ext.split("\\s");
				
				String name=Words[0];
				
				int size=Integer.parseInt(Words[1]);
				byte arr[]=new byte[size];
				fin.read(arr,0,size);
				
				fout=new FileOutputStream(name);
				fout.write(arr,0,size);
	
			}
				
		}
		catch(Exception e)
		{
			
		}
		
		
		
		
	}
	
}

