package com.example.stopgap.mydemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.button)
    AppCompatButton button;
    @BindView(R.id.et_name)
    EditText etName;


    @OnClick(R.id.button)
    public void login(View view) {
        switch (view.getId()) {
            case R.id.button:
                etName.setText("随机生成的用户");
                break;
        }
        String username = etName.getText().toString();
        if (!TextUtils.isEmpty(username)) {
            //Toast.makeText(this, "用户" + username + "登录了", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("MESSAGE", "登录成功！");
            LoginActivity.this.startActivity(intent);
            LoginActivity.this.finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }
}
