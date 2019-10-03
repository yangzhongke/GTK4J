package com.rupeng.gtk4j;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

class Utils
{
	static void copy(InputStream inStream, OutputStream outStream)
			throws IOException
	{
		byte[] buffer = new byte[512 * 1024];// 0.5MB �Ļ�����
		int len;
		while ((len = inStream.read(buffer)) >= 0)
		{
			outStream.write(buffer, 0, len);
		}
	}

	static void close(Closeable closeable)
	{
		if (closeable != null)
		{
			try
			{
				closeable.close();
			} catch (IOException e)
			{

			}
		}
	}

	static void loadGtkLibs()
	{
		// �����Щdll�ŵ�user.dir��Ŀ¼�£���ֻҪ��gtk4j.dll����֮ǰ��libffi-6.dll����Щ�������ķŵ�user.dir��Ŀ¼�¼���
		// ��������ŵ�����Ŀ¼�£��ŵ���Ŀ¼������Ŀ�￴��һ��dll�ļ���̫�ѿ������Էŵ�gtkbin�£�����Ҫע�����˳��
		// Ҫ�ȼ��ر�������dll��ʹ��DependencyWalker���Կ�dll��������ϵ

		loadDll("libffi-6.dll");
		loadDll("libpixman-1-0.dll");
		loadDll("zlib1.dll");
		loadDll("liblzma-5.dll");
		loadDll("libiconv-2.dll");
		loadDll("pthreadGC2.dll");

		loadDll("libxml2-2.dll");// ������libiconv-2.dll��liblzma-5.dll

		loadDll("libpng15-15.dll");// ������zlib1.dll
		loadDll("libfreetype-6.dll");// ������zlib1.dll

		loadDll("libfontconfig-1.dll");// ������libfreetype-6.dll��libxml2-2.dll

		loadDll("libintl-8.dll");// ������libiconv-2.dll��pthreadGC2.dll

		loadDll("libglib-2.0-0.dll");// ������libintl-8.dll

		loadDll("libgobject-2.0-0.dll");// ������libffi-6.dll��libglib-2.0-0.dll

		loadDll("libgmodule-2.0-0.dll");// ������libglib-2.0-0.dll

		loadDll("libgio-2.0-0.dll");// ������libglib-2.0-0.dll��libgmodule-2.0-0.dll��libgobject-2.0-0.dll��libintl-8.dll��zlib1.dll��zlib1.dll

		loadDll("libcairo-2.dll");// ������libfontconfig-1.dll��libfreetype-6.dll��libpixman-1-0.dll��libpng15-15.dll��zlib1.dll

		loadDll("libcairo-gobject-2.dll");// ������libcairo-2.dll��libglib-2.0-0.dll��libgobject-2.0-0.dll

		loadDll("libgdk_pixbuf-2.0-0.dll");// ������libgio-2.0-0.dll��libglib-2.0-0.dll��libgmodule-2.0-0.dll��
											// libgobject-2.0-0.dll��libintl-8.dll��libpng15-15.dll

		loadDll("libpango-1.0-0.dll");// ������libglib-2.0-0.dll��libgmodule-2.0-0.dll��libgobject-2.0-0.dll��

		loadDll("libpangoft2-1.0-0.dll");// ������libpango-1.0-0.dll��libfontconfig-1.dll��libfreetype-6.dll��libglib-2.0-0.dll��libgobject-2.0-0.dll

		loadDll("libpangowin32-1.0-0.dll");// ������libpango-1.0-0.dll��libglib-2.0-0.dll��libgobject-2.0-0.dll

		loadDll("libpangocairo-1.0-0.dll"); // ������libpango-1.0-0.dll��libpangoft2-1.0-0.dll��libpangowin32-1.0-0.dll��libcairo-2.dll��libfontconfig-1.dll
		// libfreetype-6.dll��libglib-2.0-0.dll��libgobject-2.0-0.dll

		loadDll("libgdk-3-0.dll");// ������libcairo-gobject-2.dll��libcairo-2.dll��libgdk_pixbuf-2.0-0.dll��libgio-2.0-0.dll��libglib-2.0-0.dll��
		// libgobject-2.0-0.dll��libintl-8.dll��libpango-1.0-0.dll��libpangocairo-1.0-0.dll

		loadDll("libatk-1.0-0.dll");// ������libglib-2.0-0.dll��libgobject-2.0-0.dll��libintl-8.dll

		loadDll("libgtk-3-0.dll");// ������libgdk-3-0.dll��libatk-1.0-0.dll��libcairo-gobject-2.dll��libcairo-2.dll��libgdk_pixbuf-2.0-0.dll��
		// libgio-2.0-0.dll��libglib-2.0-0.dll��libgmodule-2.0-0.dll��libgobject-2.0-0.dll��libintl-8.dll��libpango-1.0-0.dll��libpangocairo-1.0-0.dll��
		// libpangowin32-1.0-0.dll

		// GTK4J������libgdk-3-0.dll��libgtk-3-0.dll��libglib-2.0-0.dll��libgobject-2.0-0.dll
		// CAIRO4J.DLL������libcairo-2.dll��libglib-2.0-0.dll

		// ���¿�û���ֱ���������ʱע�͵�
		/*
		 * loadDll("libcroco-0.6-3.dll");
		 * loadDll("libcairo-script-interpreter-2.dll");
		 * loadDll("libgailutil-3-0.dll"); loadDll("libgthread-2.0-0.dll");
		 * loadDll("libjpeg-9.dll"); loadDll("libjasper-1.dll");//
		 * ������libjpeg-9.dll loadDll("librsvg-2-2.dll");
		 * loadDll("libtiff-5.dll");
		 */
	}

	/**
	 * ��dll��������Ŀ��gtkbinĿ¼�£�������System.load����
	 * 
	 * @param dllName
	 */
	static void loadDll(String dllName)
	{
		// ��dll��������Ŀ��Ŀ¼��
		File gtkBinDir = new File(System.getProperty("user.dir"), "gtk/bin");// *.dll�ŵ��ļ���
		if (!gtkBinDir.exists())
		{
			gtkBinDir.mkdirs();
		}
		File destDllFile = new File(gtkBinDir, dllName);

		// dllλ����Ŀ��libĿ¼�£����Ұ�lib�趨Ϊ��Դ���ļ��С���������Щdll�ͻ����ɵ�jar�ĸ�Ŀ¼����
		InputStream inStream = Utils.class.getResourceAsStream("/" + dllName);
		if (inStream == null)
		{
			throw new UnsatisfiedLinkError("û�ҵ�" + dllName);
		}

		// ���dll�Ѿ����ڣ�����ɾ�����ٿ����µģ�������֤���Զ�����
		if (destDllFile.exists())
		{
			// ����ļ��Ѿ����ڣ��򲻸��ǣ��������Ч�ʣ������Ҫ����������Ҫ�ֶ�����dll
			System.out.println(dllName + "�Ѿ����ڣ�Ĭ�ϼ��ء������Ҫ�����°汾dll������ɾ���ļ���"
					+ gtkBinDir + "�����е�*.dll");
		} else
		{
			// �������µ�
			FileOutputStream fileOutStream = null;
			try
			{
				fileOutStream = new FileOutputStream(destDllFile);
				copy(inStream, fileOutStream);
			} catch (IOException e)
			{
				throw new RuntimeException("����" + dllName + "ʧ��", e);
			} finally
			{
				close(inStream);
				close(fileOutStream);
			}
		}
		System.load(destDllFile.toString());
	}

	/**
	 * ��gtkshare.zip��ѹ��/gtk�ļ�����
	 * ��ΪFileChooserDialog���û��share/glib-2.0/schemas�ļ��ᱨ��
	 * GLib-GIO-ERROR **: No GSettings schemas are installed on the system
	 * ͬʱ�����û��share/locale����MessageDialog��FileChooserDialog�ȵİ�ť�ͻ�����ʾΪӢ��
	 */
	public static void unZipShared()
	{
		File gtkDir = new File(System.getProperty("user.dir"), "gtk");// *.dll�ŵ��ļ���
		if (!gtkDir.exists())
		{
			gtkDir.mkdirs();
		}
		InputStream inStream = Utils.class.getResourceAsStream("/gtkshare.zip");
		if (inStream == null)
		{
			throw new UnsatisfiedLinkError("û�ҵ�gtkshare.zip");
		}
		try
		{			
			unZip(inStream, gtkDir.toString());
		} catch (IOException e)
		{
			System.err.println("��ѹ��gtkshare.zipʧ��" + toFullString(e));
		}
	}

	/**
	 * ��streamToZip���zip�ļ�����ѹ��Ӳ�̵�destDir�ļ��У�֧�ֶ༶Ŀ¼
	 * @param streamToZip
	 * @param destDir
	 * @throws IOException
	 */
	public static void unZip(InputStream streamToZip,String destDir)throws IOException
	{  
		ZipInputStream zipStream = new ZipInputStream(streamToZip);
		try
		{
			ZipEntry zipEntry = null;
			while((zipEntry=zipStream.getNextEntry())!=null)
			{
				if(zipEntry.isDirectory())
				{
					File dir = new File(destDir,zipEntry.getName());
					if(!dir.exists())
					{
						dir.mkdirs();
					}
				}
				else
				{
					FileOutputStream fileOutStream = new FileOutputStream(new File(destDir,zipEntry.getName()));
					try
					{
						copy(zipStream, fileOutStream);
					}
					finally
					{
						close(fileOutStream);
					}
				}
			}
		}
		finally
		{
			close(zipStream);
		}
		
    }  


	private static String bufferToHex(byte bytes[])
	{
		StringBuffer stringbuffer = new StringBuffer(2 * bytes.length);
		int k = bytes.length;
		for (int l = 0; l < k; l++)
		{
			appendHexPair(bytes[l], stringbuffer);
		}
		return stringbuffer.toString();
	}

	private static void appendHexPair(byte bt, StringBuffer stringbuffer)
	{
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };
		char c0 = hexDigits[(bt & 0xf0) >> 4];// ȡ�ֽ��и� 4 λ������ת��
		// Ϊ�߼����ƣ�������λһ������,�˴�δ�������ַ����кβ�ͬ
		char c1 = hexDigits[bt & 0xf];// ȡ�ֽ��е� 4 λ������ת��
		stringbuffer.append(c0);
		stringbuffer.append(c1);
	}

	public static String getMD5(InputStream inStream)
	{
		try
		{
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] buffer = new byte[500 * 1024];
			int numRead = 0;
			while ((numRead = inStream.read(buffer)) > 0)
			{
				md5.update(buffer, 0, numRead);
			}
			return bufferToHex(md5.digest());
		}
		catch(NoSuchAlgorithmException e)
		{
			throw new IllegalArgumentException("����MD5ʧ��", e);
		}
		catch (IOException e)
		{
			throw new IllegalArgumentException("����MD5ʧ��", e);
		}
	}

	static String toFullString(Throwable throwable)
	{
		StringWriter sw = null;
		PrintWriter pw = null;
		try
		{
			sw = new StringWriter();
			pw = new PrintWriter(sw);
			throwable.printStackTrace(pw);
			return sw.toString();
		} finally
		{
			close(sw);
			close(pw);
		}
	}

	static void showError(Throwable throwable)
	{
		String stackTrace = Utils.toFullString(throwable);
		int dlg = GTK.gtk_message_dialog_new(0,
				GTK.GTK_DIALOG_DESTROY_WITH_PARENT | GTK.GTK_DIALOG_MODAL,
				GTK.GTK_MESSAGE_ERROR, GTK.GTK_BUTTONS_OK, stackTrace);
		GTK.gtk_window_set_title(dlg, "������");
		GTK.gtk_dialog_run(dlg);
		GTK.gtk_widget_destroy(dlg);
	}
}
