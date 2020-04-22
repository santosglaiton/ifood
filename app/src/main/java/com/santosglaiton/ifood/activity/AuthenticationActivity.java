package com.santosglaiton.ifood.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.santosglaiton.ifood.helper.FirebaseConfiguration;
import com.google.firebase.auth.FirebaseAuth;
import com.santosglaiton.ifood.R;

public class AuthenticationActivity extends AppCompatActivity {

    private Button accessButton;
    private EditText fieldEmail, fieldPassword;
    private Switch accessType;
    private FirebaseAuth authentication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
         getSupportActionBar().hide();
         inicializaComponentes();
         authentication = FirebaseConfiguration.getFirebaseAuth();

         verificaUsuarioLogado();

         accessButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String email = fieldEmail.getText().toString();
                 String password = fieldPassword.getText().toString();

                 if ( !email.isEmpty() ){
                     if ( !password.isEmpty() ){
                            // verificar o estado do switch
                         if( accessType.isChecked()){ //cadastro
                                authentication.createUserWithEmailAndPassword(
                                  email,
                                  password
                                ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {

                                        if (task.isSuccessful()){

                                            Toast.makeText(AuthenticationActivity.this,
                                                    "Cadastro realizado com sucesso!",
                                                    Toast.LENGTH_LONG).show();
                                            abrirTelaPrincipal();

                                        }else {
                                            String exceptionError ="";
                                            try{
                                                throw task.getException();
                                            }catch (FirebaseAuthWeakPasswordException e){
                                                exceptionError = "Digite uma senha mais forte";
                                            }catch (FirebaseAuthInvalidCredentialsException e){
                                                exceptionError = "Por favor digite um email válido";
                                            }catch (FirebaseAuthUserCollisionException e){
                                                exceptionError = "Esta conta já existe";
                                            }catch (Exception e){
                                                exceptionError = "ao cadastrar o usuário: " + e.getMessage();
                                            }

                                            Toast.makeText(AuthenticationActivity.this,
                                                    "Erro" + exceptionError,
                                                    Toast.LENGTH_LONG).show();
                                        }

                                    }
                                });
                         }else{ //login

                             authentication.signInWithEmailAndPassword(
                                     email,
                                     password
                             ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                 @Override
                                 public void onComplete(@NonNull Task<AuthResult> task) {
                                     if( task.isSuccessful()){

                                         Toast.makeText(AuthenticationActivity.this,
                                                 "Autenticado com sucesso",
                                                 Toast.LENGTH_LONG).show();
                                         abrirTelaPrincipal();
                                     }else{
                                         Toast.makeText(AuthenticationActivity.this,
                                                 "Erro ao fazer login" + task.getException(),
                                                 Toast.LENGTH_LONG).show();
                                     }
                                 }
                             });

                         }
                     }else{
                         Toast.makeText(AuthenticationActivity.this, "Informe a senha", Toast.LENGTH_LONG).show();
                     }
                 }else{
                     Toast.makeText(AuthenticationActivity.this, "Preencha o Email", Toast.LENGTH_LONG).show();
                 }

             }
         });

    }

    private void verificaUsuarioLogado(){
        FirebaseUser currentUser = authentication.getCurrentUser();
        if ( currentUser != null ){
            abrirTelaPrincipal();
        }
    }

    private void abrirTelaPrincipal(){
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
    }

    private void inicializaComponentes(){
        accessButton = findViewById(R.id.buttonLogin);
        fieldEmail = findViewById(R.id.editLoginEmail);
        fieldPassword = findViewById(R.id.editLoginPassword);
        accessType = findViewById(R.id.switchLogin);
    }
}
