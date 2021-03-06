package com.saileikeji.wllibrary.util;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;

public class FileUtil {

	/**
	 * SD卡操作权限
	 */
	private static final String EXTERNAL_STORAGE_PERMISSION = "android.permission.WRITE_EXTERNAL_STORAGE";

	/**
	 * 获取当前SD卡是否可用
	 * 
	 * @return
	 */

	public static boolean isSDCardExist() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }
	/**
	 * 得到内置或外置SD卡的路径
	 *
	 * @param mContext
	 * @param isExSD   true=外置SD卡
	 * @return
	 */
	public static String getStoragePath(Context mContext, boolean isExSD) {
		StorageManager mStorageManager = (StorageManager) mContext.getSystemService(Context.STORAGE_SERVICE);
		Class<?> storageVolumeClazz = null;
		try {
			storageVolumeClazz = Class.forName("android.os.storage.StorageVolume");
			Method getVolumeList = mStorageManager.getClass().getMethod("getVolumeList");
			Method getPath = storageVolumeClazz.getMethod("getPath");
			Method isRemovable = storageVolumeClazz.getMethod("isRemovable");
			Object result = getVolumeList.invoke(mStorageManager);
			final int length = Array.getLength(result);
			for (int i = 0; i < length; i++) {
				Object storageVolumeElement = Array.get(result, i);
				String path = (String) getPath.invoke(storageVolumeElement);
				boolean removable = (Boolean) isRemovable.invoke(storageVolumeElement);
				if (isExSD == removable) {
					return path;
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 如果文件末尾有了"/"则判断是否有多个"/"，是则保留一个，没有则添加
	 *
	 * @param path
	 * @return
	 */
	public static String checkFileSeparator(String path) {
		if (!TextUtils.isEmpty(path)) {
			if (!path.endsWith(File.separator)) {
				return path.concat(File.separator);
			} else {
				final int sourceStringLength = path.length();
				int index = sourceStringLength;
				if (index >= 0) {
					while (index >= 0) {
						index--;
						if (path.charAt(index) != File.separatorChar) {
							break;
						}
					}
				}
				if (index < sourceStringLength) {
					path = path.substring(0, index + 1);
					return path.concat(File.separator);
				}
			}
		}
		return path;
	}
	/**
	 * 判断是否有读取SD权限
	 * 
	 * @param context
	 * @return
	 */
	public static boolean isHasSDCardPermission(Context context) {
		int permission = context
				.checkCallingOrSelfPermission(EXTERNAL_STORAGE_PERMISSION);
		return permission == PackageManager.PERMISSION_GRANTED;
	}

	/**
	 * 判断SD卡是否有可用内存
	 * 
	 * @return
	 */
	public static boolean isHasAvailableMemory(long minAvailableMemory) {
		StatFs statFs = new StatFs(Environment.getExternalStorageDirectory()
				.getPath());
        return statFs.getAvailableBlocks() > minAvailableMemory;
    }

	/**
	 * 创建文件根目录
	 * 
	 * @param context
	 * @param path
	 * @return
	 */
	public static File createDirFile(Context context, String path) {
		if (TextUtils.isEmpty(path)) {
			return null;
		}
		File dirFile = null;
		if (!isSDCardExist()) {
			return null;
		}
		if (!isHasSDCardPermission(context)) {
			return null;
		}
		if (path == null) {
			return null;
		}
		dirFile = new File(path);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
		return dirFile;
	}

	/**
	 * 创建文件
	 * 
	 * @param context
	 * @param path
	 * @return
	 */
	public static File createNewFile(Context context, String path) {
		File file = null;
		if (TextUtils.isEmpty(path)) {
			return null;
		}
		if (!isSDCardExist()) {
			return null;
		}
		if (!isHasSDCardPermission(context)) {
			return null;
		}
		if (path == null) {
			return null;
		}
		file = new File(path);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				return null;
			}
		}
		return file;
	}

	/**
	 * 判断文件是否存在
	 * 
	 * @param filePath
	 * @return
	 */
	public static boolean isFileExist(String filePath) {
		if (TextUtils.isEmpty(filePath)) {
			return false;
		}

		File file = new File(filePath);
		return (file.exists() && file.isFile());
	}

	/**
	 * 判断文件夹是否存在
	 * 
	 * @param directoryPath
	 * @return
	 */
	public static boolean isFolderExist(String directoryPath) {
		if (TextUtils.isEmpty(directoryPath)) {
			return false;
		}

		File dire = new File(directoryPath);
		return (dire.exists() && dire.isDirectory());
	}

	/**
	 * 删除文件
	 * 
	 * @param path
	 * @return
	 */
	public static boolean deleteFile(String path) {
		if (TextUtils.isEmpty(path)) {
			return true;
		}

		File file = new File(path);
		if (!file.exists()) {
			return true;
		}
		if (file.isFile()) {
			return file.delete();
		}
		if (!file.isDirectory()) {
			return false;
		}
		for (File f : file.listFiles()) {
			if (f.isFile()) {
				f.delete();
			} else if (f.isDirectory()) {
				deleteFile(f.getAbsolutePath());
			}
		}
		return file.delete();
	}

	/**
	 * 获取文件的大小
	 * 
	 * @param path
	 * @return
	 */
	public static long getFileSize(String path) {
		if (TextUtils.isEmpty(path)) {
			return -1;
		}
		File file = new File(path);
		return (file.exists() && file.isFile() ? file.length() : -1);
	}

	/**
	 * 获取文件的Uri
	 * 
	 * @param context
	 * @param path
	 * @return
	 */
	public static Uri getUriFromFile(Context context, String path) {
		if (TextUtils.isEmpty(path)) {
			return null;
		}
		File file = null;
		if (!isSDCardExist()) {
			return null;
		}
		if (!isHasSDCardPermission(context)) {
			return null;
		}
		if (path == null) {
			return null;
		}
		file = new File(path);
		if (!file.exists()) {
			createNewFile(context,path);
		}
		return Uri.fromFile(file);
	}

	/**
	 * 换算文件大小
	 * 
	 * @param size
	 * @return
	 */
	public static String formatFileSize(long size) {
		if (size < 0) {
			return "0B";
		}
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "未知大小";
		if (size < 1024) {
			fileSizeString = df.format((double) size) + "B";
		} else if (size < 1048576) {
			fileSizeString = df.format((double) size / 1024) + "K";
		} else if (size < 1073741824) {
			fileSizeString = df.format((double) size / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) size / 1073741824) + "G";
		}
		return fileSizeString;
	}

	/**
	 * 读取文件
	 * 
	 * @param context
	 * @param file
	 * @param charsetName
	 * @return
	 */
	public static StringBuilder readFile(Context context, File file,
			String charsetName) {
		if (isSDCardExist() && isHasSDCardPermission(context)) {
			if (TextUtils.isEmpty(charsetName)) {
				charsetName = "UTF-8";
			}
			StringBuilder fileContent = new StringBuilder("");
			if (file == null || !file.isFile()) {
				return null;
			}

			BufferedReader reader = null;
			try {
				InputStreamReader is = new InputStreamReader(
						new FileInputStream(file), charsetName);
				reader = new BufferedReader(is);
				String line = null;
				while ((line = reader.readLine()) != null) {
					if (!fileContent.toString().equals("")) {
						fileContent.append("\r\n");
					}
					fileContent.append(line);
				}
				reader.close();
				return fileContent;
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return null;
	}

	/**
	 * 根据字符串写文件
	 * 
	 * @param file
	 * @param content
	 * @param append
	 * @return
	 */
	public static boolean writeFile(Context context, File file, String content,
			boolean append) {
		if (isSDCardExist() && isHasAvailableMemory(content.getBytes().length)
				&& isHasSDCardPermission(context)) {
			if (file == null || TextUtils.isEmpty(content)) {
				return false;
			}
			if (!isFileExist(file.getPath())) {
				String dirPath = file.getParentFile().getPath();
				File dirFile = createDirFile(context, dirPath);
				if (dirFile != null) {
					file = createNewFile(context, file.getPath());
				}
				if (file == null) {
					return false;
				}
			}
			FileWriter fileWriter = null;
			try {
				fileWriter = new FileWriter(file, append);
				fileWriter.write(content);
				fileWriter.close();
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (fileWriter != null) {
					try {
						fileWriter.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return false;
	}

	/**
	 * 通过输入流写文件
	 * 
	 * @param filePath
	 * @param stream
	 * @return
	 */
	public static boolean writeFile(Context context, String filePath,
			InputStream stream) {
		try {
			if (isSDCardExist() && isHasAvailableMemory(stream.available())
					&& isHasSDCardPermission(context)) {
				if (TextUtils.isEmpty(filePath) || stream == null) {
					return false;
				}
				OutputStream o = null;
				try {
					o = new FileOutputStream(filePath);
					byte data[] = new byte[1024];
					int length = -1;
					while ((length = stream.read(data)) != -1) {
						o.write(data, 0, length);
					}
					o.flush();
					return true;
				} catch (FileNotFoundException e) {
					throw new RuntimeException(
							"FileNotFoundException occurred. ", e);
				} catch (IOException e) {
					throw new RuntimeException("IOException occurred. ", e);
				} finally {
					if (o != null) {
						try {
							o.close();
							stream.close();
						} catch (IOException e) {
							throw new RuntimeException(
									"IOException occurred. ", e);
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 获取路径文件名称,不包含扩展名
	 * 
	 * <pre>
	 *      getFileNameWithoutExtension(null)               =   null
	 *      getFileNameWithoutExtension("")                 =   ""
	 *      getFileNameWithoutExtension("   ")              =   "   "
	 *      getFileNameWithoutExtension("abc")              =   "abc"
	 *      getFileNameWithoutExtension("a.mp3")            =   "a"
	 *      getFileNameWithoutExtension("a.b.rmvb")         =   "a.b"
	 *      getFileNameWithoutExtension("c:\\")              =   ""
	 *      getFileNameWithoutExtension("c:\\a")             =   "a"
	 *      getFileNameWithoutExtension("c:\\a.b")           =   "a"
	 *      getFileNameWithoutExtension("c:a.txt\\a")        =   "a"
	 *      getFileNameWithoutExtension("/home/admin")      =   "admin"
	 *      getFileNameWithoutExtension("/home/admin/a.txt/b.mp3")  =   "b"
	 * </pre>
	 * 
	 * @param filePath
	 * @return
	 */
	public static String getFileNameWithoutExtension(String filePath) {
		if (TextUtils.isEmpty(filePath)) {
			return filePath;
		}

		int extenPosi = filePath.lastIndexOf(".");
		int filePosi = filePath.lastIndexOf(File.separator);
		if (filePosi == -1) {
			return (extenPosi == -1 ? filePath : filePath.substring(0,
					extenPosi));
		}
		if (extenPosi == -1) {
			return filePath.substring(filePosi + 1);
		}
		return (filePosi < extenPosi ? filePath.substring(filePosi + 1,
				extenPosi) : filePath.substring(filePosi + 1));
	}

	/**
	 * 获取路径文件名称,包含扩展名
	 * 
	 * <pre>
	 *      getFileName(null)               =   null
	 *      getFileName("")                 =   ""
	 *      getFileName("   ")              =   "   "
	 *      getFileName("a.mp3")            =   "a.mp3"
	 *      getFileName("a.b.rmvb")         =   "a.b.rmvb"
	 *      getFileName("abc")              =   "abc"
	 *      getFileName("c:\\")              =   ""
	 *      getFileName("c:\\a")             =   "a"
	 *      getFileName("c:\\a.b")           =   "a.b"
	 *      getFileName("c:a.txt\\a")        =   "a"
	 *      getFileName("/home/admin")      =   "admin"
	 *      getFileName("/home/admin/a.txt/b.mp3")  =   "b.mp3"
	 * </pre>
	 * 
	 * @param filePath
	 * @return
	 */
	public static String getFileName(String filePath) {
		if (TextUtils.isEmpty(filePath)) {
			return filePath;
		}

		int filePosi = filePath.lastIndexOf(File.separator);
		return (filePosi == -1) ? filePath : filePath.substring(filePosi + 1);
	}

	/**
	 * 获取路径文件夹名称
	 * 
	 * <pre>
	 *      getFolderName(null)               =   null
	 *      getFolderName("")                 =   ""
	 *      getFolderName("   ")              =   ""
	 *      getFolderName("a.mp3")            =   ""
	 *      getFolderName("a.b.rmvb")         =   ""
	 *      getFolderName("abc")              =   ""
	 *      getFolderName("c:\\")              =   "c:"
	 *      getFolderName("c:\\a")             =   "c:"
	 *      getFolderName("c:\\a.b")           =   "c:"
	 *      getFolderName("c:a.txt\\a")        =   "c:a.txt"
	 *      getFolderName("c:a\\b\\c\\d.txt")    =   "c:a\\b\\c"
	 *      getFolderName("/home/admin")      =   "/home"
	 *      getFolderName("/home/admin/a.txt/b.mp3")  =   "/home/admin/a.txt"
	 * </pre>
	 * 
	 * @param filePath
	 * @return
	 */
	public static String getFolderName(String filePath) {

		if (TextUtils.isEmpty(filePath)) {
			return filePath;
		}

		int filePosi = filePath.lastIndexOf(File.separator);
		return (filePosi == -1) ? "" : filePath.substring(0, filePosi);
	}

	/**
	 * 获取路径文件的扩展名
	 * 
	 * <pre>
	 *      getFileExtension(null)               =   ""
	 *      getFileExtension("")                 =   ""
	 *      getFileExtension("   ")              =   "   "
	 *      getFileExtension("a.mp3")            =   "mp3"
	 *      getFileExtension("a.b.rmvb")         =   "rmvb"
	 *      getFileExtension("abc")              =   ""
	 *      getFileExtension("c:\\")              =   ""
	 *      getFileExtension("c:\\a")             =   ""
	 *      getFileExtension("c:\\a.b")           =   "b"
	 *      getFileExtension("c:a.txt\\a")        =   ""
	 *      getFileExtension("/home/admin")      =   ""
	 *      getFileExtension("/home/admin/a.txt/b")  =   ""
	 *      getFileExtension("/home/admin/a.txt/b.mp3")  =   "mp3"
	 * </pre>
	 * 
	 * @param filePath
	 * @return
	 */
	public static String getFileExtension(String filePath) {
		if (TextUtils.isEmpty(filePath)) {
			return filePath;
		}

		int extenPosi = filePath.lastIndexOf(".");
		int filePosi = filePath.lastIndexOf(File.separator);
		if (extenPosi == -1) {
			return "";
		}
		return (filePosi >= extenPosi) ? "" : filePath.substring(extenPosi + 1);
	}

	/**
	 * 保存图片
	 * @param fileName
	 * @param mBitmap
	 */
	public static void saveBitmap(String fileName, Bitmap mBitmap) {
		File f = new File(fileName);
		FileOutputStream fOut = null;
		try {
			f.createNewFile();
			fOut = new FileOutputStream(f);
			mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
			fOut.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * Returns a directory with the given name in the private cache directory of the application to
	 * use to store
	 * retrieved media and thumbnails.
	 *
	 * @param context A context.
	 * @param cacheName The name of the subdirectory in which to store the cache.
	 */
	public static File getPhotoCacheDir(Context context, String cacheName) {
		File cacheDir = context.getCacheDir();
		if (cacheDir != null) {
			File result = new File(cacheDir, cacheName);
			if (!result.mkdirs() && (!result.exists() || !result.isDirectory())) {
				// File wasn't able to create a directory, or the result exists but not a directory
				return null;
			}
			return result;
		}
		LogUtil.e("default disk cache dir is null");
		return null;
	}
}
