package org.dfir.harita.app;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.dfir.harita.app.model.DaoAccess;
import org.dfir.harita.app.model.dao.Firsat;
import org.dfir.harita.app.model.dao.FirsatDao;
import org.dfir.harita.app.model.dao.Isletme;
import org.dfir.harita.app.model.dao.IsletmeDao;

import java.sql.Date;
import java.text.SimpleDateFormat;


public class FirsatEkle extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firsat_ekle);

        final Button btn_ekle = (Button) findViewById(R.id.btnEkle);
        final EditText et_baslangic = (EditText) findViewById(R.id.edittext1);
        final EditText et_sure = (EditText) findViewById(R.id.edittext2);
        final EditText et_kac_kisi = (EditText)  findViewById(R.id.edittext3);
        final EditText et_aciklama = (EditText)  findViewById(R.id.edittext4);
        final EditText et_firsat_turu = (EditText)  findViewById(R.id.edittext5);
        final EditText et_firsat_kodu = (EditText)  findViewById(R.id.edittext6);
        final EditText et_kategori = (EditText)  findViewById(R.id.edittext7);

        btn_ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Date date_baslangic = Date.valueOf(et_baslangic.getText().toString());
                String str_sure = et_sure.getText().toString();
                String str_kac_kisi=et_kac_kisi.getText().toString();
                String str_aciklama=et_aciklama.getText().toString();
                String str_fırsat_turu=et_firsat_turu.getText().toString();
                String str_fırsat_kodu=et_firsat_kodu.getText().toString();
                String str_kategori=et_kategori.getText().toString();


                DaoAccess dao= DaoAccess.getSingletonObject(FirsatEkle.this);
                FirsatDao firsat_dao= dao.getFirsatDao();
                Firsat firsat = new Firsat();

                firsat.setBaslangic(date_baslangic);
                firsat.setSure(Integer.parseInt(str_sure));
                firsat.setKac_kisi(Integer.parseInt(str_kac_kisi));
                firsat.setAciklama(str_aciklama);
                firsat.setFirsat_turu(str_fırsat_turu);
                firsat.setFirsat_kodu(str_fırsat_kodu);
                firsat.setKategori(str_kategori);
                firsat.setAktif_mi(true);
                firsat.setIsletme_id(MapsActivity.isletme.getId());

                try
                {
                    firsat_dao.insert(firsat);
                    firsat_dao.refresh(firsat);
                }
                catch(Exception e)
                {
                    Toast.makeText(getApplicationContext(),"Fırsat Eklenemedi!",Toast.LENGTH_LONG).show();
                }

                Intent firsat_sayfasi = new Intent(FirsatEkle.this, FirsatSayfasi.class);
                startActivity(firsat_sayfasi);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.firsat_ekle, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
