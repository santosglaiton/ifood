package com.santosglaiton.ifood.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.santosglaiton.ifood.R;
import com.santosglaiton.ifood.helper.FirebaseConfiguration;

public class CompanyActivity extends AppCompatActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);

        auth = FirebaseConfiguration.getFirebaseAuth();

//        configurar a toolbar

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Ifood - Empresa");
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_company, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch ( item.getItemId() ){
            case R.id.sair :
                userLogout();
                break;
            case R.id.menuConfiguracoes :
                openConfigurations();
                break;
            case R.id.menuNovoProduto :
                openNewProduct();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void userLogout(){
        try{
            auth.signOut();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void openConfigurations(){
        startActivity(new Intent(CompanyActivity.this, CompanyConfigurationActivity.class));
    }

    private void openNewProduct(){
        startActivity(new Intent(CompanyActivity.this, NewProductCompanyActivity.class));
    }

}
