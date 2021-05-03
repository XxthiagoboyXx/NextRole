package com.auba.nextrole.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

import com.auba.nextrole.R;
import com.auba.nextrole.constant.ProxRole;
import com.auba.nextrole.data.SecurityPreferences;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHolder.checkParticipate = findViewById(R.id.checkbox_participate);
        this.mViewHolder.checkParticipate.setOnClickListener(this);

        this.loadDataFromActivity();
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.checkbox_participate){

            if(this.mViewHolder.checkParticipate.isChecked()){
                // Salvar a presença
                this.mSecurityPreferences.storedString(ProxRole.PRESENCE_KEY,ProxRole.CONFIRMATION_YES);
            }
            else{
                // Salvar a ausência
                this.mSecurityPreferences.storedString(ProxRole.PRESENCE_KEY,ProxRole.CONFIRMATION_NO);
            }

        }
    }

    public void loadDataFromActivity(){
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            String presence = extras.getString(ProxRole.PRESENCE_KEY);
            if (presence != null && presence.equals(ProxRole.CONFIRMATION_YES)){
                this.mViewHolder.checkParticipate.setChecked(true);
            }
            else {
                this.mViewHolder.checkParticipate.setChecked(false);
            }
        }

    }


    private static class ViewHolder{
        CheckBox checkParticipate;
    }
}