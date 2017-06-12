package com.xriva.beam;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Beam
{

	public static void main(String[] args)
	{
		boolean rc = false; 
		
		if (args.length < 3)
		{
			System.out.println("beam [up|down] [system|IP] filename");
			return;
		}
		
		Beam transport = new Beam();

		if (args[1].toLowerCase().equals("up"))
			rc = transport.push(args[2], args[3]);
		
		if (args[1].toLowerCase().equals("down"))
			rc = transport.pull(args[2], args[3]);
		
		if (rc)
			System.out.println("Beamed.");
	}

	private boolean push(String dest, String file)
	{
		byte[] buffer = new byte[1000];
		
		try
		{
			FileInputStream in = new FileInputStream(file);
			Socket s = new Socket(dest, 8080);
			OutputStream out = s.getOutputStream();
			while (in.read(buffer) != -1)
				out.write(buffer);
			in.close();
			out.close();
			s.close();
 		}
		catch (UnknownHostException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} 

		return true;
	}
	
	private boolean pull(String dest, String file)
	{
		return true;
	}
}
