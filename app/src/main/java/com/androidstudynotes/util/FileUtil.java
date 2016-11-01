package com.androidstudynotes.util;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by pyysotne on 2015/08/22/0022.
 * email: pyystone@163.com
 * QQ: 862429936
 */
public class FileUtil {
    public static final String HOME_PHOTO_PATH = "/pyystone/study/photo/";
    public static final String SDPATH = Environment.getExternalStorageDirectory() + "/";
    private static final int FILESIZE = 4 * 1024;

    /**
     * 把一个文件转化为字节
     * @param file
     * @return   byte[]
     * @throws Exception
     */
    public static byte[] getByte(String path,String fileName) throws Exception
    {
        File file = createSDDir(path + fileName);
        byte[] bytes = null;
        if(file!=null)
        {
            InputStream is = new FileInputStream(file);
            int length = (int) file.length();
            if(length>Integer.MAX_VALUE)   //当文件的长度超过了int的最大值
            {
                System.out.println("this file is max ");
                return null;
            }
            bytes = new byte[length];
            int offset = 0;
            int numRead = 0;
            while(offset<bytes.length&&(numRead=is.read(bytes,offset,bytes.length-offset))>=0)
            {
                offset+=numRead;
            }
            //如果得到的字节长度和file实际的长度不一致就可能出错了
            if(offset<bytes.length)
            {
                System.out.println("file length is error");
                return null;
            }
            is.close();
        }
        return bytes;
    }

    public static void createFilePath(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    public String getSDPATH(){
        return SDPATH;
    }

    /**
     * 在SD卡上创建文件
     * @param fileName
     * @return
     * @throws IOException
     */
    public  static File createSDFile(String fileName) throws IOException{
        File file = new File(SDPATH + fileName);
        file.createNewFile();
        return file;
    }

    /**
     * 在SD卡上创建目录
     * @param dirName
     * @return
     */
    public static File createSDDir(String dirName){
        File dir = new File(SDPATH + dirName);
        dir.mkdir();
        return dir;
    }

    /**
     * 判断SD卡上的文件夹是否存在
     * @param fileName
     * @return
     */
    public static boolean isFileExist(String fileName){
        File file = new File(SDPATH + fileName);
        return file.exists();
    }

    /**
     * 将一个InputStream里面的数据写入到SD卡中
     * @param path
     * @param fileName
     * @param input
     * @return
     */
    public static File write2SDFromInput(String path,String fileName,InputStream input){
        File file = null;
        OutputStream output = null;
        try {
            createSDDir(path);
            file = createSDFile(path + fileName);
            output = new FileOutputStream(file);
            byte[] buffer = new byte[FILESIZE];
            int length;
            while((length=(input.read(buffer))) >0){
                output.write(buffer,0,length);
            }
            output.flush();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally{
            try {
                if (output != null) {
                    output.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return file;
    }
}
