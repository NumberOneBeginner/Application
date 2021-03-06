package com.example.peter.mystaffapplication.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.peter.mystaffapplication.R;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.ByteArrayRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpMethodParams;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.util.ArrayList;

public class OCRInvoiceScanActivity extends AppCompatActivity {
    String filePath="mnt/sdcard/test-vatInvoice.jpg";//测试图片物理路径
    public static final String ENGINE_URL="http://www.yunmaiocr.com/SrvXMLAPI";
    public static final String username = "d87edfd0-a918-4f67-9e3f-acf5e12003d8";//替换OCR SDK开发者平台API帐号
    public static final String password = "KCCOXznBDVmdKfkVSyLqmWcikWMNiZ";//替换OCR SDK开发者平台API密码
    File file=null;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ocrinvoice_scan);
        Button btn = (Button) findViewById(R.id.btn_test);
         tv= (TextView) findViewById(R.id.tv_sh);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createFile(filePath);
            }
        });

    }
    //创建文件
    private void createFile(String filePath){
        try{
            file=new File(filePath);//要识别的文件路径
        }catch(Exception e){
            e.printStackTrace();
            return;
        }
        String result = "123";
        try {
            result=Scan(file2byte(file),filePath.substring(filePath.lastIndexOf(".")+1));//调用网络接口识别
            tv.setText(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        out.print(result);//打印出识别结果
//        System.out.println(">> end demo.");
        Log.e("OCR","==+:"+result);
    }
    public static String Scan(byte[] file,String ext){
        String xml = getSendXML( "vatInvoice.scan",ext);
        return send(xml,file);
    }
    private static String getSendXML(String action,String ext) {
        ArrayList<String[]> arr = new ArrayList<String[]>();
        arr.add(new String[] { "action", action});
        arr.add(new String[] { "client", username });
        arr.add(new String[] { "system", "web_saas"});
        arr.add(new String[] { "password", MD5(password)});
        arr.add(new String[] { "ext",ext });
        arr.add(new String[] { "json", "1" });//返回结果是否转成json格式，不传及默认是xml格式，为1时：转换成json格式
        return getXML(arr,false);
    }
    public static String getXML(ArrayList<String[]> arr,boolean IsUpper) {
        if (arr == null || arr.size() == 0)
            return "";
        StringBuffer sb = new StringBuffer();
        String tag="";
        for (int idx = 0; idx < arr.size(); idx++) {
            tag=arr.get(idx)[0];
            if(IsUpper){
                tag=tag.toUpperCase();//将小写字母转成大写字母 toUpperCase的意思是将所有的英文字符转换为大写字母，如：
                                     // “aBc123”.toUpperCase();结果就是：ABC123。
            }
            sb.append("<");
            sb.append(tag);
            sb.append(">");
            sb.append(arr.get(idx)[1]);
            //sb.append(XMLFunctions.code(arr.get(idx)[1]));
            sb.append("</");
            sb.append(tag);
            sb.append(">");
        }
        return sb.toString();
    }

    private static String send(String xml,byte[] file){
        byte[] dest = new byte[xml.getBytes().length+file.length+"<file></file>".getBytes().length];
        int pos = 0;
        System.arraycopy(xml.getBytes(), 0, dest, pos, xml.getBytes().length);
        pos += xml.getBytes().length;
        System.arraycopy("<file>".getBytes(), 0, dest, pos, "<file>".getBytes().length);
        pos += "<file>".getBytes().length;
        System.arraycopy(file, 0, dest, pos, file.length);
        pos += file.length;
        System.arraycopy("</file>".getBytes(), 0, dest, pos, "</file>".getBytes().length);
        try {
            return httpClient(ENGINE_URL, dest);
        } catch (IOException e) {
            return "-1";
        }
    }

    public static String httpClient(String url,byte[] content) throws IOException{
        HttpClient httpClient = new HttpClient();
        HttpClientParams httparams = new HttpClientParams();
        httpClient.setParams(httparams);

        PostMethod method = new PostMethod(url);
        RequestEntity requestEntity = new ByteArrayRequestEntity(content);
        method.setRequestEntity(requestEntity);
        String responseBody = null;
        try {
            method.getParams().setContentCharset("utf-8");
            method.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
            int statusCode = httpClient.executeMethod(method);
            if (statusCode != HttpStatus.SC_OK) {
                System.out.println("\r\nMethod failed: " + method.getStatusLine() + ",url:\r\n" + url + "\r\n");
            }
            StringBuffer resultBuffer = new StringBuffer();
            BufferedReader in = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(),
                    method.getResponseCharSet()));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                resultBuffer.append(inputLine);
                resultBuffer.append("\r\n");
            }
            in.close();
            responseBody = resultBuffer.toString().trim();
        } catch (Exception e) {
            System.out.println(">>> http请求异常，url=" + url);
            e.printStackTrace();
            responseBody = "-2";
        } finally {
            if (method != null) {
                method.releaseConnection();
                method = null;
            }
            return responseBody;
        }

    }
    public static byte[] file2byte(File file) throws IOException {
        byte[] bytes = null;
        if (file != null) {
            InputStream is = new FileInputStream(file);
            int length = (int) file.length();
            if (length > Integer.MAX_VALUE) // 当文件的长度超过了int的最大值
            {
                System.out.println("this file is max ");
                return null;
            }
            bytes = new byte[length];
            int offset = 0;
            int numRead = 0;
            while (offset < bytes.length&& (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
                offset += numRead;
            }
            // 如果得到的字节长度和file实际的长度不一致就可能出错了
            if (offset < bytes.length) {
                System.out.println("file length is error");
                return null;
            }
            is.close();
        }
        return bytes;
    }
    public final static String MD5(String pwd) {
        //用于加密的字符
        char md5String[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F' };
        try {
            byte[] btInput = pwd.getBytes();

            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {   //  i = 0
                byte byte0 = md[i];  //95
                str[k++] = md5String[byte0 >>> 4 & 0xf];    //    5
                str[k++] = md5String[byte0 & 0xf];   //   F
            }
            return new String(str);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
