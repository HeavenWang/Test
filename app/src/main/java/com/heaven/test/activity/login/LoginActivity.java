package com.heaven.test.activity.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.heaven.test.MyApplication;
import com.heaven.test.R;
import com.heaven.test.activity.BaseActivity;
import com.heaven.test.activity.listview.ListViewActivity;

import org.xutils.view.annotation.ContentView;

/**
 * Created by Administrator on 2016/8/25.
 * Longin (登陆) 页
 */

@ContentView(R.layout.activity_login)
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_OrgId, et_UserName, et_Password, et_EmailAddress;
    private Button btn_LogIn;
    private MyApplication myApplication;

    /*记账密码账号（自动登陆）*/
    private SharedPreferences sharedPreferences;

    private ProgressDialog waitingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        myApplication = (MyApplication) getApplication();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);//透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);//透明导航栏

        if (!isTaskRoot()) {
            finish();
            return;
        }
        findViewById();
    }

    public void findViewById() {
        et_OrgId = (EditText) findViewById(R.id.et_login_OrgId);//org ID
        et_UserName = (EditText) findViewById(R.id.et_login_UserName);//用户名称
        et_Password = (EditText) findViewById(R.id.et_login_Password);//用户密码
        et_EmailAddress = (EditText) findViewById(R.id.et_login_EmailAddress);//用户Email地址

        btn_LogIn = (Button) findViewById(R.id.btn_login_LogIn);//登陆按钮

        sharedPreferences = getSharedPreferences("userInfo", 0);
        String orgID = sharedPreferences.getString("OrgID", "");
        String userName = sharedPreferences.getString("UserName", "");
        String password = sharedPreferences.getString("Password", "");
        String emailAddress = sharedPreferences.getString("EmailAddress", "");

        et_OrgId.setText(orgID);
        et_UserName.setText(userName);
        et_Password.setText(password);
        et_EmailAddress.setText(emailAddress);

        btn_LogIn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login_LogIn:
                startActivity(new Intent(this, ListViewActivity.class));
                break;

            default:
                break;
        }
    }

    private void showWaitingDialog() {
    /* 等待Dialog具有屏蔽其他控件的交互能力
     * @setCancelable 为使屏幕不可点击，设置为不可取消(false)
     * 下载等事件完成后，主动调用函数关闭该Dialog
     */
        waitingDialog = new ProgressDialog(LoginActivity.this);
        waitingDialog.setMessage("Loading...");
        waitingDialog.setIndeterminate(true);
        waitingDialog.setCancelable(false);
        waitingDialog.show();
    }


    public void invokeWebBrowser() {
//        Intent intent = new Intent(Intent.ACTION_VIEW);
//        intent.setData(Uri.parse("http://www.google.com.hk"));
//        startActivity(intent);

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://123.sogou.com/"));
        startActivity(intent);
    }
}
