package com.yusuf.sercan.studyjamsders2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int adet = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }



    public void arttir(View view){
        if(adet==100){
            return; }
        adet= adet+1;
        display(adet);
    }
    public void azalt(View view){

        if(adet==0){
        return; }

        adet=adet-1;
        display(adet);
    }
    public void siparis(View view){
        EditText isimyaz = (EditText) findViewById(R.id.isim);
            String isim = isimyaz.getText().toString();
        CheckBox sutlu = (CheckBox) findViewById(R.id.sütlü);
        boolean sut = sutlu.isChecked();

        CheckBox cikolatali = (CheckBox) findViewById(R.id.sütlü);
        boolean ciko = cikolatali.isChecked();

        int fiyat = fiyatHesapla(sut, ciko);
        String fiyatozeti = siparisozeti(isim,ciko,sut,fiyat);
        fiyat(fiyatozeti);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        //Write you mail adress
        intent.setData(Uri.parse("mailto:ironturkish@gmail.com"));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Sipariş isteyen Kişi:" + isim);
        intent.putExtra(Intent.EXTRA_TEXT, fiyatozeti);
        if(intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }

    }
    private String siparisozeti(String isim, boolean sut,boolean ciko , int fiyat){
        String fiyatozeti = "İsim: " + isim;
        fiyatozeti= fiyatozeti +"\nAdet: " + adet;
        fiyatozeti = fiyatozeti + "\nSüt istermisiniz? :" + sut;
        fiyatozeti = fiyatozeti + "\nCikolata İstermisiniz? :" + ciko;
        fiyatozeti = fiyatozeti + "\nFiyat: " + fiyat + "TL";
        fiyatozeti = fiyatozeti + "\nTeşekkürler!!";
        return fiyatozeti;

    }
        public int fiyatHesapla(boolean sut, boolean ciko){
            int base = 5;

            if(sut){
                base = base +1;
            }
            if(ciko){
                base = base +2;
            }

        int fiyat = adet * base;
        return fiyat;
    }

    private void display(int a){
        TextView adet = (TextView)
                findViewById(R.id.adet);
        adet.setText("" + a);
    }

    private void fiyat(String fiyat){
        TextView fiyatgoster = (TextView)
                findViewById(R.id.fiyat);
        fiyatgoster.setText("" + fiyat);

    }


}
