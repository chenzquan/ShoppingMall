package com.example.shopping_core.util.file;

import android.os.Environment;

import com.example.shopping_core.app.Latte;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by 权 on 2018/8/13.
 */

public class FileUtil {



    private static final String SDCARD_DIR = Environment.getExternalStorageDirectory().getAbsolutePath();

    private static final String TIME_FORMAT = "_yyyyMMdd_HHmmss";

    public static String getSdDir(){
        return SDCARD_DIR;
    }

    public static File createFile(String sdcardDirName,String filename){
        return new File(createDir(sdcardDirName),filename);
    }

    private static File createDir(String sdcardDirName) {
        final String dir = SDCARD_DIR + "/" + sdcardDirName + "/";
        final File fildDir = new File(dir);
        if(!fildDir.exists()){
            fildDir.mkdirs();
        }
        return fildDir;
    }


    /**
     * 以时间为名字 创建文件名
     * @param sdcardDirName //文件的路径
     * @param timeFormatHeader 时间
     * @param extension 文件格式 后缀
     * @return wenjain
     */
    private static File createFileByTime(String sdcardDirName,String timeFormatHeader,String extension){
        final String fileName = getFileNameByTime(timeFormatHeader,extension);
        return createFile(sdcardDirName,fileName);
    }

    private static String getFileNameByTime(String timeFormatHeader, String extension) {
        return getTimeFormatName(timeFormatHeader) + "." + extension;
    }

    private static String getTimeFormatName(String timeFormatHeader) {
        final Date date = new Date(System.currentTimeMillis());
        final SimpleDateFormat dateFormat = new SimpleDateFormat("'" + timeFormatHeader + "'" + TIME_FORMAT, Locale.getDefault());
        return dateFormat.format(date);
    }


    /**
     *
     * @param is 输入流
     * @param dir 文件路径
     * @param name 文件名字
     * @return 文件
     */
    public static File writeToDisk(InputStream is,String dir,String name){
        final File file = FileUtil.createFile(dir,name);
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;

        try{
            bis = new BufferedInputStream(is);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);

            byte [] data = new byte[1024 * 4];
            int count;
            while((count = bis.read()) != -1){
                bos.write(data,0,count);
            }

            bos.flush();
            fos.flush();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                if(bos != null){
                    bos.close();
                }

                if(fos != null){
                    fos.close();
                }

                if(bis != null){
                    bis.close();
                }

                is.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return file;
    }


    public static File writeToDisk(InputStream is,String dir,String prefix,String extension){
        final File file = FileUtil.createFileByTime(dir,prefix,extension);
        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;

        try{
            bis = new BufferedInputStream(is);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);

            byte [] data = new byte[1024 * 4];
            int count;
            while((count = bis.read()) != -1){
                bos.write(data,0,count);
            }

            bos.flush();
            fos.flush();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                if(bos != null){
                    bos.close();
                }

                if(fos != null){
                    fos.close();
                }

                if(bis != null){
                    bis.close();
                }

                is.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return file;
    }

    /*
    获取文件的后缀名
     */
    public static String getExtension(String filePath) {
        String suffix = "";
        final File file = new File(filePath);
        final String name = file.getName();
        final int inx = name.lastIndexOf('.');
        if(inx > 0){
            suffix = name.substring(inx + 1);
        }
        return suffix;
    }

    /*
    读取raw目录中的文件夹，并返回字符串
     */
    public static String getRawFile(int rawId) {
        final InputStream is = Latte.getApplicationContext().getResources().openRawResource(rawId);
        final BufferedInputStream bis = new BufferedInputStream(is);
        final InputStreamReader isr = new InputStreamReader(bis);
        final BufferedReader br = new BufferedReader(isr);
        final StringBuilder stringBuilder = new StringBuilder();
        String str;
        try{
            while((str = br.readLine()) != null){
                stringBuilder.append(str);
            }

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try{
                br.close();
                isr.close();
                bis.close();
                is.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }
}
