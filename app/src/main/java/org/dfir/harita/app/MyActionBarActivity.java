package org.dfir.harita.app;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import org.dfir.harita.app.model.DaoAccess;
import org.dfir.harita.app.model.dao.Isletme;
import org.dfir.harita.app.model.dao.IsletmeDao;
import org.dfir.harita.app.model.dao.Musteri;
import org.dfir.harita.app.model.dao.MusteriDao;

/**
 * Created by ismail ARILIK on 17.05.2014.
 */
public class MyActionBarActivity extends ActionBarActivity {

    private DaoAccess daoAccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // initialize
        daoAccess = DaoAccess.getSingletonObject(getApplicationContext());
        IsletmeDao isletmeDao = daoAccess.getIsletmeDao();
        MusteriDao musteriDao = daoAccess.getMusteriDao();

        // add dummy isletmeler
        Isletme isletmeNike = new Isletme();
        isletmeNike.setAd("Nike");
        isletmeNike.setEnlem(39.897898);
        isletmeNike.setBoylam(32.778601);
        isletmeNike.setAdres("ODTÜ KKM Sol Üst");
        isletmeNike.setOy(3.5f);
        isletmeNike.setAciklama("Spor malzemeleri satan bir mağaza.");
        isletmeNike.setKullanici_adi("nike");
        isletmeNike.setSifre("nike");
        isletmeNike.setKategori("Spor Giyim");
        isletmeDao.insertOrReplace(isletmeNike);

        Isletme isletmeKfc = new Isletme();
        isletmeKfc.setAd("KFC");
        isletmeKfc.setEnlem(39.889551);
        isletmeKfc.setBoylam(32.779330);
        isletmeKfc.setAdres("ODTÜ KKM Sol Alt");
        isletmeKfc.setOy(4f);
        isletmeKfc.setAciklama("Kızarmış tavuk yiyebileceğiniz restaurant");
        isletmeKfc.setKullanici_adi("kfc");
        isletmeKfc.setSifre("kfc");
        isletmeKfc.setKategori("Fastfood Gıda");
        isletmeDao.insertOrReplace(isletmeKfc);

        Isletme isletmeVatan = new Isletme();
        isletmeVatan.setAd("Vatan");
        isletmeVatan.setEnlem(39.891083);
        isletmeVatan.setBoylam(32.791518);
        isletmeVatan.setAdres("ODTÜ KKM Sağ Alt");
        isletmeVatan.setOy(3.25f);
        isletmeVatan.setAciklama("Elektronik Eşya Alabileceğiniz Mağaza");
        isletmeVatan.setKullanici_adi("vatan");
        isletmeVatan.setSifre("vatan");
        isletmeVatan.setKategori("Elektronik Eşya");
        isletmeDao.insertOrReplace(isletmeVatan);

        Isletme isletmeZara = new Isletme();
        isletmeZara.setAd("Zara");
        isletmeZara.setEnlem(39.899100);
        isletmeZara.setBoylam(32.786433);
        isletmeZara.setAdres("ODTÜ KKM Sağ Üst");
        isletmeZara.setOy(4.75f);
        isletmeZara.setAciklama("Elit kesime hitap eden şık ve zarif elbiseler satan mağaza");
        isletmeZara.setKullanici_adi("zara");
        isletmeZara.setSifre("zara");
        isletmeZara.setKategori("Normal Giyim");
        isletmeDao.insertOrReplace(isletmeZara);

        // add dummy musteriler
        Musteri musteri1 = new Musteri();
        musteri1.setEnlem(39.893303);
        musteri1.setBoylam(32.775287);
        musteri1.setOy(3.25f);
        musteriDao.insertOrReplace(musteri1);

        Musteri musteri2 = new Musteri();
        musteri2.setEnlem(39.899016);
        musteri2.setBoylam(32.785394);
        musteri2.setOy(4.25f);
        musteriDao.insertOrReplace(musteri2);

        Musteri musteri3 = new Musteri();
        musteri3.setEnlem(39.893485);
        musteri3.setBoylam(32.790801);
        musteri3.setOy(3.5f);
        musteriDao.insertOrReplace(musteri3);

        Musteri musteri4 = new Musteri();
        musteri4.setEnlem(39.889056);
        musteri4.setBoylam(32.785673);
        musteri4.setOy(4.5f);
        musteriDao.insertOrReplace(musteri4);
    }
}
