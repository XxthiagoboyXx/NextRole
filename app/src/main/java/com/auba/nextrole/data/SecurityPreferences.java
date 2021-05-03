package com.auba.nextrole.data;

import android.content.SharedPreferences;
import android.content.Context;

public class SecurityPreferences {
    //instanciando a classe mSharedPreferences
    private SharedPreferences mSharedPreferences;

    //construindo mSharedPreferences, é como se fosse um new de alguma classe, só q específico para mSharedPreferences.
    //---------------------------------------------------------------------------------------------------------
    public SecurityPreferences(Context mContext){
        this.mSharedPreferences = mContext.getSharedPreferences("QualFesta", Context.MODE_PRIVATE);
    }
    //---------------------------------------------------------------------------------------------------------

    // Armazena o valor no arquivo
    //---------------------------------------------------------------------------------------------------------
    public void storedString(String key, String value){
        this.mSharedPreferences.edit().putString(key, value).apply();
    }
    //---------------------------------------------------------------------------------------------------------

    // Pega o valor armazenado no arquivo para a classe MainActivity
    //---------------------------------------------------------------------------------------------------------
    public String getStoredString(String key){
        return this.mSharedPreferences.getString(key, "");
    }
    //---------------------------------------------------------------------------------------------------------

}
