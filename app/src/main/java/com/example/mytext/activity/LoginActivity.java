package com.example.mytext.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.alibaba.fastjson.JSON;
import com.example.mytext.R;

import com.example.mytext.db.DBConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {
    Button button2;
    ImageView ImageView3;
    EditText editTextName,editTextPwd;
    TextView textView10;
    private Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button2=(Button) this.findViewById(R.id.button2);
        ImageView3=(ImageView) this.findViewById(R.id.imageView3);
        editTextName=(EditText)this.findViewById(R.id.editTextName);
        editTextPwd=(EditText)this.findViewById(R.id.editTextPwd);
        textView10=(TextView)this.findViewById(R.id.textView10);
//        button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new Thread(new Runnable(){
//                    public void run(){
//                        DBConnection.linkMysql();
//                    }
//                }).start();
//            }
//        });
        textView10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ForgetPW();
            }
        });
        ImageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phonenumber=editTextName.getText().toString();
                String pwd=editTextPwd.getText().toString();
                if (!(phonenumber.isEmpty())&& !(pwd.isEmpty())) {
                    login(phonenumber, pwd);
                } else {
                    Toast.makeText(LoginActivity.this, "账号、密码都不能为空！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private  void  main(){
        Intent intent=new Intent();
        intent.setClass(this,MainActivity.class);
        startActivity(intent);
    }
    private  void  ForgetPW(){
        Intent intent=new Intent();
        intent.setClass(this,ForgetPWActivity.class);
        startActivity(intent);
    }
    private  void  jumptostudent(){
        Intent intent=new Intent();
        intent.setClass(this,StudentActivity.class);
        startActivity(intent);
    }
    private  void  jumptoteacher(){
        Intent intent=new Intent();
        intent.setClass(this,testActivity.class);
        startActivity(intent);
    }
    private void  login(String phonenumber, String password){
        String loginUrlStr = Constant.URL_Login + "?phonenumber=" + phonenumber + "&userPassword=" + password;
        Log.d("JSON","指令："+loginUrlStr);
        Login_AsyncTask login_asyncTask =new Login_AsyncTask();
        login_asyncTask.execute(loginUrlStr);
    }
    class Login_AsyncTask extends AsyncTask<String, Integer, String> {

        public Login_AsyncTask() {
            Log.d("JSON","验证前");
        }
        @Override
        public void onPreExecute() {
            Log.w("JSON", "开始验证.........");
        }
        /**
         * @param params 这里的params是一个数组，即AsyncTask在激活运行是调用execute()方法传入的参数
         */
        @Override
        public String doInBackground(String... params) {
            HttpURLConnection connection = null;
            StringBuilder response = new StringBuilder();
            try {
                URL url = new URL(params[0]); // 声明一个URL
                connection = (HttpURLConnection) url.openConnection(); // 打开该URL连接
                connection.setRequestMethod("get"); // 设置请求方法，“POST或GET”，我们这里用GET，在说到POST的时候再用POST
                connection.setConnectTimeout(80000); // 设置连接建立的超时时间
                connection.setReadTimeout(80000); // 设置网络报文收发超时时间
                BufferedReader reader =new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response.toString(); // 这里返回的结果就作为onPostExecute方法的入参
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            // 如果在doInBackground方法，那么就会立刻执行本方法
            // 本方法在UI线程中执行，可以更新UI元素，典型的就是更新进度条进度，一般是在下载时候使用
        }
        /**
         * 运行在UI线程中，所以可以直接操作UI元素
         * @param s
         */
        @Override
        protected void onPostExecute(String s) {
            Log.d("JSON",s);//打印服务器返回标签
            //flag=true;
            switch (s){
                //判断返回的状态码，并把对应的说明显示在UI
                case "100":
                    Toast.makeText(LoginActivity.this, "登录失败，密码不匹配或账号未注册", Toast.LENGTH_SHORT).show();
                    break;
                case "200":
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    jumptostudent();
                    break;
                case "300":
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    jumptoteacher();
                    break;
                default:
                    Toast.makeText(LoginActivity.this, "异常", Toast.LENGTH_SHORT).show();
            }
            Log.d("JSON","验证后");
        }
    }
}
