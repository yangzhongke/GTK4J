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
		byte[] buffer = new byte[512 * 1024];// 0.5MB 的缓冲区
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
		// 如果这些dll放到user.dir根目录下，则只要在gtk4j.dll加载之前把libffi-6.dll等这些被依赖的放到user.dir根目录下即可
		// 但是如果放到其他目录下（放到根目录下在项目里看到一堆dll文件，太难看，所以放到gtkbin下），就要注意加载顺序
		// 要先加载被依赖的dll。使用DependencyWalker可以看dll的依赖关系

		loadDll("libffi-6.dll");
		loadDll("libpixman-1-0.dll");
		loadDll("zlib1.dll");
		loadDll("liblzma-5.dll");
		loadDll("libiconv-2.dll");
		loadDll("pthreadGC2.dll");

		loadDll("libxml2-2.dll");// 依赖于libiconv-2.dll、liblzma-5.dll

		loadDll("libpng15-15.dll");// 依赖于zlib1.dll
		loadDll("libfreetype-6.dll");// 依赖于zlib1.dll

		loadDll("libfontconfig-1.dll");// 依赖于libfreetype-6.dll、libxml2-2.dll

		loadDll("libintl-8.dll");// 依赖于libiconv-2.dll、pthreadGC2.dll

		loadDll("libglib-2.0-0.dll");// 依赖于libintl-8.dll

		loadDll("libgobject-2.0-0.dll");// 依赖于libffi-6.dll、libglib-2.0-0.dll

		loadDll("libgmodule-2.0-0.dll");// 依赖于libglib-2.0-0.dll

		loadDll("libgio-2.0-0.dll");// 依赖于libglib-2.0-0.dll、libgmodule-2.0-0.dll、libgobject-2.0-0.dll、libintl-8.dll、zlib1.dll、zlib1.dll

		loadDll("libcairo-2.dll");// 依赖于libfontconfig-1.dll、libfreetype-6.dll、libpixman-1-0.dll、libpng15-15.dll、zlib1.dll

		loadDll("libcairo-gobject-2.dll");// 依赖于libcairo-2.dll、libglib-2.0-0.dll、libgobject-2.0-0.dll

		loadDll("libgdk_pixbuf-2.0-0.dll");// 依赖于libgio-2.0-0.dll、libglib-2.0-0.dll、libgmodule-2.0-0.dll、
											// libgobject-2.0-0.dll、libintl-8.dll、libpng15-15.dll

		loadDll("libpango-1.0-0.dll");// 依赖于libglib-2.0-0.dll、libgmodule-2.0-0.dll、libgobject-2.0-0.dll、

		loadDll("libpangoft2-1.0-0.dll");// 依赖于libpango-1.0-0.dll、libfontconfig-1.dll、libfreetype-6.dll、libglib-2.0-0.dll、libgobject-2.0-0.dll

		loadDll("libpangowin32-1.0-0.dll");// 依赖于libpango-1.0-0.dll、libglib-2.0-0.dll、libgobject-2.0-0.dll

		loadDll("libpangocairo-1.0-0.dll"); // 依赖于libpango-1.0-0.dll、libpangoft2-1.0-0.dll、libpangowin32-1.0-0.dll、libcairo-2.dll、libfontconfig-1.dll
		// libfreetype-6.dll、libglib-2.0-0.dll、libgobject-2.0-0.dll

		loadDll("libgdk-3-0.dll");// 依赖于libcairo-gobject-2.dll、libcairo-2.dll、libgdk_pixbuf-2.0-0.dll、libgio-2.0-0.dll、libglib-2.0-0.dll、
		// libgobject-2.0-0.dll、libintl-8.dll、libpango-1.0-0.dll、libpangocairo-1.0-0.dll

		loadDll("libatk-1.0-0.dll");// 依赖于libglib-2.0-0.dll、libgobject-2.0-0.dll、libintl-8.dll

		loadDll("libgtk-3-0.dll");// 依赖于libgdk-3-0.dll、libatk-1.0-0.dll、libcairo-gobject-2.dll、libcairo-2.dll、libgdk_pixbuf-2.0-0.dll、
		// libgio-2.0-0.dll、libglib-2.0-0.dll、libgmodule-2.0-0.dll、libgobject-2.0-0.dll、libintl-8.dll、libpango-1.0-0.dll、libpangocairo-1.0-0.dll、
		// libpangowin32-1.0-0.dll

		// GTK4J依赖于libgdk-3-0.dll、libgtk-3-0.dll、libglib-2.0-0.dll、libgobject-2.0-0.dll
		// CAIRO4J.DLL依赖于libcairo-2.dll、libglib-2.0-0.dll

		// 以下库没发现被依赖，暂时注释掉
		/*
		 * loadDll("libcroco-0.6-3.dll");
		 * loadDll("libcairo-script-interpreter-2.dll");
		 * loadDll("libgailutil-3-0.dll"); loadDll("libgthread-2.0-0.dll");
		 * loadDll("libjpeg-9.dll"); loadDll("libjasper-1.dll");//
		 * 依赖于libjpeg-9.dll loadDll("librsvg-2-2.dll");
		 * loadDll("libtiff-5.dll");
		 */
	}

	/**
	 * 把dll拷贝到项目的gtkbin目录下，并且用System.load加载
	 * 
	 * @param dllName
	 */
	static void loadDll(String dllName)
	{
		// 把dll拷贝到项目根目录下
		File gtkBinDir = new File(System.getProperty("user.dir"), "gtk/bin");// *.dll放的文件夹
		if (!gtkBinDir.exists())
		{
			gtkBinDir.mkdirs();
		}
		File destDllFile = new File(gtkBinDir, dllName);

		// dll位于项目的lib目录下，并且把lib设定为“源码文件夹”，这样这些dll就会生成到jar的根目录下了
		InputStream inStream = Utils.class.getResourceAsStream("/" + dllName);
		if (inStream == null)
		{
			throw new UnsatisfiedLinkError("没找到" + dllName);
		}

		// 如果dll已经存在，则尝试删除，再拷贝新的，这样保证能自动升级
		if (destDllFile.exists())
		{
			// 如果文件已经存在，则不覆盖，提高运行效率，如果需要升级，则需要手动清理dll
			System.out.println(dllName + "已经存在，默认加载。如果需要采用新版本dll，请先删除文件夹"
					+ gtkBinDir + "下所有的*.dll");
		} else
		{
			// 拷贝来新的
			FileOutputStream fileOutStream = null;
			try
			{
				fileOutStream = new FileOutputStream(destDllFile);
				copy(inStream, fileOutStream);
			} catch (IOException e)
			{
				throw new RuntimeException("拷贝" + dllName + "失败", e);
			} finally
			{
				close(inStream);
				close(fileOutStream);
			}
		}
		System.load(destDllFile.toString());
	}

	/**
	 * 把gtkshare.zip解压到/gtk文件夹下
	 * 因为FileChooserDialog如果没有share/glib-2.0/schemas文件会报错
	 * GLib-GIO-ERROR **: No GSettings schemas are installed on the system
	 * 同时，如果没有share/locale，则MessageDialog、FileChooserDialog等的按钮就会是显示为英文
	 */
	public static void unZipShared()
	{
		File gtkDir = new File(System.getProperty("user.dir"), "gtk");// *.dll放的文件夹
		if (!gtkDir.exists())
		{
			gtkDir.mkdirs();
		}
		InputStream inStream = Utils.class.getResourceAsStream("/gtkshare.zip");
		if (inStream == null)
		{
			throw new UnsatisfiedLinkError("没找到gtkshare.zip");
		}
		try
		{			
			unZip(inStream, gtkDir.toString());
		} catch (IOException e)
		{
			System.err.println("解压缩gtkshare.zip失败" + toFullString(e));
		}
	}

	/**
	 * 把streamToZip这个zip文件流解压到硬盘的destDir文件夹，支持多级目录
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
		char c0 = hexDigits[(bt & 0xf0) >> 4];// 取字节中高 4 位的数字转换
		// 为逻辑右移，将符号位一起右移,此处未发现两种符号有何不同
		char c1 = hexDigits[bt & 0xf];// 取字节中低 4 位的数字转换
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
			throw new IllegalArgumentException("计算MD5失败", e);
		}
		catch (IOException e)
		{
			throw new IllegalArgumentException("计算MD5失败", e);
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
		GTK.gtk_window_set_title(dlg, "出错了");
		GTK.gtk_dialog_run(dlg);
		GTK.gtk_widget_destroy(dlg);
	}
}
