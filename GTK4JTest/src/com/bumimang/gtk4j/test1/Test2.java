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
		 * OutputStream fos = null;// ��д=null�в��У�Ϊʲô�� try { fos = new
		 * FileOutputStream("a.txt"); byte[] bytes = "abcde".getBytes();
		 * fos.write(bytes); } catch (IOException ex) {
		 * System.err.println("д���ļ�ʧ��" + ex.getMessage()); } finally { try { if
		 * (fos != null) { fos.close(); } } catch (IOException e) { // ʲô������ } }
		 */

		/*
		 * FileInputStream fis = null; FileOutputStream fos = null; try { fis =
		 * new FileInputStream("e:\\temp\\jdk.exe"); fos = new
		 * FileOutputStream("e:\\temp\\2.exe"); byte[] bytes = new byte[500 *
		 * 1024]; int len = 0; while ((len = fis.read(bytes)) > 0) {
		 * fos.write(bytes, 0, len); } } catch (IOException ex) {
		 * System.err.println("�����ļ�����" + ex.getMessage()); } finally { if (fos
		 * != null) { try { fos.close(); } catch (IOException ex) { // ʲô������ } }
		 * if (fis != null) { try { fis.close(); } catch (IOException e) { //
		 * ʲô������ } } }
		 */

		/*
		 * for (Charset cs : Charset.availableCharsets().values()) {
		 * System.out.println(cs.displayName()); }
		 * 
		 * BufferedWriter buffWriter = null; OutputStreamWriter streamWriter =
		 * null; OutputStream outStream = null; try { outStream = new
		 * FileOutputStream("d:\\1.txt"); streamWriter = new
		 * OutputStreamWriter(outStream, "UTF8"); buffWriter = new
		 * BufferedWriter(streamWriter); buffWriter.write("�Ұ����й���");
		 * buffWriter.write(System.lineSeparator());
		 * buffWriter.write("I love you �й���");
		 * buffWriter.write(System.lineSeparator());
		 * buffWriter.write("thank you!"); } catch (IOException ex) {
		 * System.out.println("�ļ���ȡʧ��" + ex.getMessage()); } finally {
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
		 * System.err.println("�ļ���ȡʧ��"+ex.getMessage()); } finally {
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
			System.err.println("��ַ��ʽ����" + e.getMessage());
		} catch (IOException e)
		{
			System.err.println("����ʧ�ܣ�" + e.getMessage());
		}
		*/
		
		InputStream inStream = null;
		try
		{
inStream = new FileInputStream("config.properties");
Properties prop = new Properties();
prop.load(inStream);
String serverIP = prop.getProperty("ServerIP");
System.out.println("������IP��ַ��"+serverIP);
String port = prop.getProperty("Port", "80");
System.out.println("�˿ںţ�"+port);
		}
		catch(IOException ex)
		{
			System.err.println("��ȡ����ʧ��"+ex.getMessage());
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
			throw new IllegalArgumentException("bufferSize����������");
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
				// ʲô������
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
		System.out.println("�����й���");
	}

	public void baiNian()
	{
		System.out.println("����ã�");
	}
}