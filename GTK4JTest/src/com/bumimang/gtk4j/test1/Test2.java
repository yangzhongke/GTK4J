package com.bumimang.gtk4j.test1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Properties;
import java.util.stream.IntStream;

import org.apache.commons.io.IOUtils;

public class Test2
{

	public static void main(String[] args)
	{
		/*
		 * OutputStream fos = null;// 不写=null行不行？为什么？ try { fos = new
		 * FileOutputStream("a.txt"); byte[] bytes = "abcde".getBytes();
		 * fos.write(bytes); } catch (IOException ex) {
		 * System.err.println("写入文件失败" + ex.getMessage()); } finally { try { if
		 * (fos != null) { fos.close(); } } catch (IOException e) { // 什么都不做 } }
		 */

		/*
		 * FileInputStream fis = null; FileOutputStream fos = null; try { fis =
		 * new FileInputStream("e:\\temp\\jdk.exe"); fos = new
		 * FileOutputStream("e:\\temp\\2.exe"); byte[] bytes = new byte[500 *
		 * 1024]; int len = 0; while ((len = fis.read(bytes)) > 0) {
		 * fos.write(bytes, 0, len); } } catch (IOException ex) {
		 * System.err.println("拷贝文件出错：" + ex.getMessage()); } finally { if (fos
		 * != null) { try { fos.close(); } catch (IOException ex) { // 什么都不做 } }
		 * if (fis != null) { try { fis.close(); } catch (IOException e) { //
		 * 什么都不做 } } }
		 */

		/*
		 * for (Charset cs : Charset.availableCharsets().values()) {
		 * System.out.println(cs.displayName()); }
		 * 
		 * BufferedWriter buffWriter = null; OutputStreamWriter streamWriter =
		 * null; OutputStream outStream = null; try { outStream = new
		 * FileOutputStream("d:\\1.txt"); streamWriter = new
		 * OutputStreamWriter(outStream, "UTF8"); buffWriter = new
		 * BufferedWriter(streamWriter); buffWriter.write("我爱你中国！");
		 * buffWriter.write(System.lineSeparator());
		 * buffWriter.write("I love you 中国！");
		 * buffWriter.write(System.lineSeparator());
		 * buffWriter.write("thank you!"); } catch (IOException ex) {
		 * System.out.println("文件读取失败" + ex.getMessage()); } finally {
		 * closeQuietly(buffWriter); closeQuietly(streamWriter);
		 * closeQuietly(outStream); }
		 */

		/*
		 * BufferedReader buffReader = null; InputStreamReader streamReader =
		 * null; InputStream inStream = null; try { inStream = new
		 * FileInputStream("d:\\1.txt"); streamReader = new
		 * InputStreamReader(inStream,"UTF-8"); buffReader = new
		 * BufferedReader(streamReader); String line;
		 * while((line=buffReader.readLine())!=null) { System.out.println(line);
		 * } } catch(IOException ex) {
		 * System.err.println("文件读取失败"+ex.getMessage()); } finally {
		 * closeQuietly(buffReader); closeQuietly(streamReader);
		 * closeQuietly(inStream); }
		 */

		/*
		try
		{
			String html = downloadString(new URL("http://www.baidu.com"));
			System.out.println(html);
			
			download(new URL("http://www.baidu.com/img/baidu_jgylogo3.gif"),"d:\\1.gif");
		} catch (MalformedURLException e)
		{
			System.err.println("网址格式错误" + e.getMessage());
		} catch (IOException e)
		{
			System.err.println("下载失败：" + e.getMessage());
		}
		*/
		
		InputStream inStream = null;
		try
		{
inStream = new FileInputStream("config.properties");
Properties prop = new Properties();
prop.load(inStream);
String serverIP = prop.getProperty("ServerIP");
System.out.println("服务器IP地址："+serverIP);
String port = prop.getProperty("Port", "80");
System.out.println("端口号："+port);
		}
		catch(IOException ex)
		{
			System.err.println("读取配置失败"+ex.getMessage());
		}
		finally
		{
			IOUtils.closeQuietly(inStream);
		}
	}

	static String downloadString(URL url) throws IOException
	{
		InputStream inStream = url.openStream();
		InputStreamReader streamReader = null;
		BufferedReader buffReader = null;
		try
		{
			streamReader = new InputStreamReader(inStream);
			buffReader = new BufferedReader(streamReader);
			String result = "";
			String line;
			while ((line = buffReader.readLine()) != null)
			{
				result = result + line + System.lineSeparator();
			}
			return result;
		} finally
		{
			closeQuietly(inStream);
			closeQuietly(streamReader);
			closeQuietly(buffReader);
		}
	}

	static void copy(InputStream inStream, OutputStream outStream)
			throws IOException
	{
		copy(inStream, outStream, 500 * 1024);
	}

	static void copy(InputStream inStream, OutputStream outStream,
			int bufferSize) throws IOException
	{
		if (bufferSize <= 0)
		{
			throw new IllegalArgumentException("bufferSize必须是正数");
		}
		byte[] buffer = new byte[bufferSize];
		int len;
		while ((len = inStream.read(buffer)) > 0)
		{
			outStream.write(buffer, 0, len);
		}
	}

	static void download(URL url, String localFile) throws IOException
	{
		InputStream inStream = url.openStream();
		OutputStream fos = null;
		try
		{
			fos = new FileOutputStream(localFile);
			copy(inStream, fos);
		} finally
		{
			closeQuietly(inStream);
			closeQuietly(fos);
		}
	}

	// static void download(URL url,String localFile)

	static void closeQuietly(Closeable closeable)
	{
		if (closeable != null)
		{
			try
			{
				closeable.close();
			} catch (IOException ex)
			{
				// 什么都不做
			}
		}
	}
}

class IntClass
{
	private int value;

	public IntClass(int value)
	{
		this.value = value;
	}

	public int getInt()
	{
		return this.value;
	}
}

abstract class DiQiuRen
{
	public abstract void speak();

	public final void jump()
	{
	}
}

class Chinese extends DiQiuRen
{
	@Override
	public void speak()
	{
		System.out.println("我是中国人");
	}

	public void baiNian()
	{
		System.out.println("过年好！");
	}
}