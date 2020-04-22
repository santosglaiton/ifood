package com.santosglaiton.ifood.helper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;

public class FirebaseUser {

    public static String getUserId (){
        FirebaseAuth auth = FirebaseConfiguration.getFirebaseAuth();
        return auth.getCurrentUser().getUid();

    }

    public static com.google.firebase.auth.FirebaseUser getCurrentUser(){
        FirebaseAuth user  = FirebaseConfiguration.getFirebaseAuth();
        return user.getCurrentUser();
    }

    public static boolean updateUserType(String type){
            try{
                com.google.firebase.auth.FirebaseUser user = getCurrentUser();
                UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder()
                        .setDisplayName(type).build();
                user.updateProfile(profile);
            return true;
            }catch (Exception e){
                e.printStackTrace();
                return false;
            }
    }

}
