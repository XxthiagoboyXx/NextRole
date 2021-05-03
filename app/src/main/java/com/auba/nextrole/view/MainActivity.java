package com.auba.nextrole.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.auba.nextrole.R;
import com.auba.nextrole.constant.ProxRole;
import com.auba.nextrole.data.SecurityPreferences;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.text.ParseException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ViewHolder mViewHolder = new ViewHolder();
    private static SimpleDateFormat SIMPLE_DATA_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    private SecurityPreferences mSecurityPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHolder.textToday = findViewById(R.id.text_today);
        this.mViewHolder.textDays = findViewById(R.id.text_days);
        this.mViewHolder.buttonConfirm = findViewById(R.id.button_confirm);

        this.mViewHolder.buttonConfirm.setOnClickListener(this);

        //Pegar o dia de hoje e armazenar no textToday
        this.mViewHolder.textToday.setText(SIMPLE_DATA_FORMAT.format(Calendar.getInstance().getTime()));

        // Concatena o número de dias restantes com a string "dias"
        String daysLeft = null;
        try {
            daysLeft = String.format("%s %s", String.valueOf(this.getDaysLeft(SIMPLE_DATA_FORMAT.format(Calendar.getInstance().getTime()))), getString(R.string.dias));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.mViewHolder.textDays.setText(daysLeft);  //coloca o valor de dias restantes no textDays

        //this.verifyPresence();

    }

    @Override
    protected void onResume() {
        super.onResume();
        this.verifyPresence();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_confirm) {

            String presence = this.mSecurityPreferences.getStoredString(ProxRole.PRESENCE_KEY);
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra(ProxRole.PRESENCE_KEY, presence);

            startActivity(intent);
        }
    }

    private static long getDaysLeft(String dtparty) throws ParseException {
        //Data de
        //----------------------------------------------------
        Calendar calendarToday = Calendar.getInstance();
        int today = calendarToday.get(Calendar.DAY_OF_YEAR);
        //----------------------------------------------------


        //Dia da Festa
        //----------------------------------------------------

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        Date dtInicial = df.parse(dtparty);
        Date dtFinal =  df.parse("26/03/2021");


        //int partyday = calendarPartyDay.getActualMaximum(Calendar.DAY_OF_YEAR);
        //----------------------------------------------------
        return (dtFinal.getTime() - dtInicial.getTime() + 3600000L) / 86400000L;
        //return (dtInicial.getTime());

    }

    private void verifyPresence(){
        // nao confirmado ou sim ou nao
        String presence = this.mSecurityPreferences.getStoredString(ProxRole.PRESENCE_KEY);
        if(presence.equals("")){
            this.mViewHolder.buttonConfirm.setText(getString(R.string.nao_confirmado));
        } else if (presence.equals(ProxRole.CONFIRMATION_YES)){
            this.mViewHolder.buttonConfirm.setText(getString(R.string.sim));
        } else {
            this.mViewHolder.buttonConfirm.setText(getString(R.string.nao));
        }
    }


    private static class ViewHolder{
        TextView textToday;
        TextView textDays;
        Button buttonConfirm;
    }

}