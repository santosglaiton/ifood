package com.santosglaiton.ifood.helper;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FirebaseConfiguration {

    private static DatabaseReference firebaseReference;
    private static FirebaseAuth firebaseAuthReference;
    private static StorageReference storageReference;

    public static String getUserId (){
        FirebaseAuth auth = getFirebaseAuth();
        return auth.getCurrentUser().getUid();
    }



    public static DatabaseReference getFirebase (){
        if(firebaseReference == null ){
            firebaseReference = FirebaseDatabase.getInstance().getReference();
        }
        return firebaseReference;
    }

    public static FirebaseAuth getFirebaseAuth (){
        if( firebaseAuthReference == null ){
            firebaseAuthReference = FirebaseAuth.getInstance();
        }
        return firebaseAuthReference;
    }

    public static StorageReference getStorageReference (){
        if ( storageReference == null ){
            storageReference = FirebaseStorage.getInstance().getReference();
        }
        return storageReference;
    }
}
