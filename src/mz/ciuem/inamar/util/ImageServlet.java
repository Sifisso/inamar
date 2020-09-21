package mz.ciuem.inamar.util;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Reader;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Decoder;

public class ImageServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	String path = "";



@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			StringBuffer buffer = new StringBuffer();
			Reader reader = request.getReader();
			int current;

			while((current = reader.read()) >= 0)
				buffer.append((char) current);
			
			String data = new String(buffer);
			data = data.substring(data.indexOf(",") + 1);

			String path = 	(String) request.getSession().getAttribute("uri_foto");

			FileOutputStream output = new FileOutputStream(new java.io.File(path));

			output.write(new BASE64Decoder().decodeBuffer(data));
			output.flush();
			output.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}





