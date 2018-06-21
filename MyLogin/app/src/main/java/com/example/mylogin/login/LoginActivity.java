/*
 *    Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.example.mylogin.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mylogin.App;
import com.example.mylogin.MainActivity;
import com.example.mylogin.R;
import com.example.mylogin.data.DataManager;

public class LoginActivity extends Activity implements LoginMvpView {

    LoginPresenter loginPresenter;
    EditText editTextEmail, editTextPassword;
    Button button;

    View focusView = null;

    public static Intent getStartIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Log.d("MyDummyError", "I am crashed here");

        DataManager dataManager = ((App) getApplication()).getDataManager();
        loginPresenter = new LoginPresenter(dataManager);

        loginPresenter.onAttach(this);
        editTextEmail = (EditText) findViewById(R.id.email);
        editTextPassword = (EditText) findViewById(R.id.password);

        button = (Button) findViewById(R.id.email_sign_in_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLoginButtonClick();
            }
        });

    }

    @Override
    public void openMainActivity() {
        Intent intent = MainActivity.getStartIntent(this);
        startActivity(intent);
        finish();
    }

    @Override
    public void onLoginButtonClick() {

        String emailId = editTextEmail.getText().toString();
        String password = editTextPassword.getText().toString();

        if (emailId == null || emailId.isEmpty()) {
            editTextEmail.setError(getString(R.string.error_invalid_email));
            focusView = editTextEmail;
            return;
        }

        if (password == null || password.isEmpty()) {
            editTextPassword.setError(getString(R.string.error_invalid_password));
            focusView = editTextPassword;
            return;
        }

        loginPresenter.validate(emailId, password);
    }

    @Override
    public void refresh() {
        Toast.makeText(this, "Wrong email or password", Toast.LENGTH_LONG).show();
        return;
    }
}
