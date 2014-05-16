package org.dfir.harita.dao_generator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Property;
import de.greenrobot.daogenerator.Schema;

public class MyDaoGenerator
{
    //// declarations
    private static final int VERSION = 1;
    private static final String PACKAGE_NAME = "org.dfir.harita.app.model.dao";
    private static Map<String, Entity> entities = new HashMap<String, Entity>();
    private static Map<Entity, Map<String, Property>> propertyMaps = new HashMap<Entity, Map<String, Property>>();
    // table names
    private static final String TABLE_NAME_ISLETME = "Isletme";
    private static final String TABLE_NAME_FIRSAT = "Firsat";
    private static final String TABLE_NAME_MUSTERI = "Musteri";
    private static final String TABLE_NAME_MUSTERI_ISLETME = "Musteri_Isletme";
    private static final String TABLE_NAME_MUSTERI_FIRSAT = "Musteri_Firsat";
    private static final String TABLE_NAME_FIRSATLARIM = "Firsatlarim";


    public static void main(String[] args) {
        // prompt the user for the path of folder of model classes which will be generated
        Scanner scanner = new Scanner(System.in);
        String defaultPath = "/Users/okan/AndroidStudioProjects/Harita/app/src/main/java/";
        System.out.print("Enter the path of the folder of model classes which will be generated: "
                + "[" + defaultPath + "] ");
        String enteredPath = scanner.nextLine();
        if(!enteredPath.equals("")) {
            defaultPath = enteredPath;
        }

        // generate the schema
        Schema schema = new Schema(VERSION, PACKAGE_NAME);

        // add tables to the schema
        addIsletmeTable(schema);
        addFirsatTable(schema);
        addMusteriTable(schema);
        addMusteriIsletmeTable(schema);
        addMusteriFirsatTable(schema);
        addFirsatlarimTable(schema);

        // generate relations
        addIsletmeRelationships();
        addFirsatRelationships();
        addMusteriRelationships();
        addMusteriIsletmeRelationships();
        addMusteriFirsatRelationships();
        addFirsatlarimRelationships();

        // generate model classes
        try {
            new DaoGenerator().generateAll(schema, defaultPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////
    // ADDING TABLES

    private static void addIsletmeTable(Schema schema) {
        // generate the table
        Entity isletmeTable = schema.addEntity(TABLE_NAME_ISLETME);

        // generate the fields
        Map<String, Property> properties = new HashMap<String, Property>(10);
        properties.put("id", isletmeTable.addIdProperty().getProperty());
        properties.put("ad", isletmeTable.addStringProperty("ad").getProperty());
        properties.put("enlem", isletmeTable.addDoubleProperty("enlem").getProperty());
        properties.put("boylam", isletmeTable.addDoubleProperty("boylam").getProperty());
        properties.put("adres", isletmeTable.addStringProperty("adres").getProperty());
        properties.put("oy", isletmeTable.addFloatProperty("oy").getProperty());
        properties.put("aciklama", isletmeTable.addStringProperty("aciklama").getProperty());
        properties.put("kullanici_adi", isletmeTable.addStringProperty("kullanici_adi")
                .getProperty());
        properties.put("sifre", isletmeTable.addStringProperty("sifre").getProperty());
        properties.put("kategori", isletmeTable.addStringProperty("kategori").getProperty());

        // put tables and fields to appropriate maps
        entities.put(TABLE_NAME_ISLETME, isletmeTable);
        propertyMaps.put(isletmeTable, properties);
    }

    private static void addFirsatTable(Schema schema) {
        // generate the table
        Entity firsatTable = schema.addEntity(TABLE_NAME_FIRSAT);

        // generate the fields
        Map<String, Property> properties = new HashMap<String, Property>(10);
        properties.put("id", firsatTable.addIdProperty().getProperty());
        properties.put("isletme_id", firsatTable.addLongProperty("isletme_id").getProperty());
        properties.put("baslangic", firsatTable.addDateProperty("baslangic").getProperty());
        properties.put("sure", firsatTable.addIntProperty("sure").getProperty());
        properties.put("kac_kisi", firsatTable.addIntProperty("kac_kisi").getProperty());
        properties.put("aciklama", firsatTable.addStringProperty("aciklama").getProperty());
        properties.put("aktif_mi", firsatTable.addBooleanProperty("aktif_mi").getProperty());
        properties.put("firsat_turu", firsatTable.addStringProperty("firsat_turu").getProperty());
        properties.put("firsat_kodu", firsatTable.addStringProperty("firsat_kodu").getProperty());
        properties.put("kategori", firsatTable.addStringProperty("kategori").getProperty());

        // put tables and fields to appropriate maps
        entities.put(TABLE_NAME_FIRSAT, firsatTable);
        propertyMaps.put(firsatTable, properties);
    }

    private static void addMusteriTable(Schema schema) {
        // generate the table
        Entity musteriTable = schema.addEntity(TABLE_NAME_MUSTERI);

        // generate the fields
        Map<String, Property> properties = new HashMap<String, Property>(2);
        properties.put("id", musteriTable.addIdProperty().getProperty());
        properties.put("oy", musteriTable.addFloatProperty("oy").getProperty());

        // put tables and fields to appropriate maps
        entities.put(TABLE_NAME_MUSTERI, musteriTable);
        propertyMaps.put(musteriTable, properties);
    }

    private static void addMusteriIsletmeTable(Schema schema) {
        // generate the table
        Entity musteriIsletmeTable = schema.addEntity(TABLE_NAME_MUSTERI_ISLETME);

        // generate the fields
        Map<String, Property> properties = new HashMap<String, Property>(3);
        properties.put("id", musteriIsletmeTable.addIdProperty().getProperty());
        properties.put("musteri_id", musteriIsletmeTable.addLongProperty("musteri_id")
                .getProperty());
        properties.put("isletme_id", musteriIsletmeTable.addLongProperty("isletme_id")
                .getProperty());

        // put tables and fields to appropriate maps
        entities.put(TABLE_NAME_MUSTERI_ISLETME, musteriIsletmeTable);
        propertyMaps.put(musteriIsletmeTable, properties);
    }

    private static void addMusteriFirsatTable(Schema schema) {
        // generate the table
        Entity musteriFirsatTable = schema.addEntity(TABLE_NAME_MUSTERI_FIRSAT);

        // generate the fields
        Map<String, Property> properties = new HashMap<String, Property>(3);
        properties.put("id", musteriFirsatTable.addIdProperty().getProperty());
        properties.put("musteri_id", musteriFirsatTable.addLongProperty("musteri_id")
                .getProperty());
        properties.put("firsat_id", musteriFirsatTable.addLongProperty("firsat_id").getProperty());

        // put tables and fields to appropriate maps
        entities.put(TABLE_NAME_MUSTERI_FIRSAT, musteriFirsatTable);
        propertyMaps.put(musteriFirsatTable, properties);
    }

    private static void addFirsatlarimTable(Schema schema) {
        // generate the table
        Entity firsatlarimTable = schema.addEntity(TABLE_NAME_FIRSATLARIM);

        // generate the fields
        Map<String, Property> properties = new HashMap<String, Property>(4);
        properties.put("id", firsatlarimTable.addIdProperty().getProperty());
        properties.put("firsat_id", firsatlarimTable.addLongProperty("firsat_id").getProperty());
        properties.put("sure", firsatlarimTable.addIntProperty("sure").getProperty());
        properties.put("yararlanildi_mi", firsatlarimTable.addBooleanProperty("yararlanildi_mi")
                .getProperty());

        // put tables and fields to appropriate maps
        entities.put(TABLE_NAME_FIRSATLARIM, firsatlarimTable);
        propertyMaps.put(firsatlarimTable, properties);
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////
    // ADDING RELATIONSHIPS

    private static void addIsletmeRelationships() {
        Entity eIsletme = entities.get(TABLE_NAME_ISLETME);

        // with the entity "Firsat"
        eIsletme.addToMany(entities.get(TABLE_NAME_FIRSAT),
                propertyMaps.get(entities.get(TABLE_NAME_FIRSAT)).get("isletme_id"), "firsatlar");
        // with the entity "Musteri_Isletme"
        eIsletme.addToMany(entities.get(TABLE_NAME_MUSTERI_ISLETME),
                propertyMaps.get(entities.get(TABLE_NAME_MUSTERI_ISLETME)).get("isletme_id"),
                "musteriIsletmeler");
    }

    private static void addFirsatRelationships() {
        Entity eFirsat = entities.get(TABLE_NAME_FIRSAT);

        // with the entity "Musteri_Firsat"
        eFirsat.addToMany(entities.get(TABLE_NAME_MUSTERI_FIRSAT),
                propertyMaps.get(entities.get(TABLE_NAME_MUSTERI_FIRSAT)).get("firsat_id"),
                "musteriFirsatlar");
        // with the entity "Firsatlarim"
        eFirsat.addToMany(entities.get(TABLE_NAME_FIRSATLARIM),
                propertyMaps.get(entities.get(TABLE_NAME_FIRSATLARIM)).get("firsat_id"),
                "firsatlarimListesi");
    }

    private static void addMusteriRelationships() {
        Entity eMusteri = entities.get(TABLE_NAME_MUSTERI);

        // with the entity "MusteriIsletme"
        eMusteri.addToMany(entities.get(TABLE_NAME_MUSTERI_ISLETME),
                propertyMaps.get(entities.get(TABLE_NAME_MUSTERI_ISLETME)).get("musteri_id"),
                "musteriIsletmeler");
        // with the entity "MusteriFirsat"
        eMusteri.addToMany(entities.get(TABLE_NAME_MUSTERI_FIRSAT),
                propertyMaps.get(entities.get(TABLE_NAME_MUSTERI_FIRSAT)).get("musteri_id"),
                "musteriFirsatlar");
    }

    private static void addMusteriIsletmeRelationships() {
        Entity eMusteriIsletme = entities.get(TABLE_NAME_MUSTERI_ISLETME);

        // with the entity "Isletme"
        eMusteriIsletme.addToOne(entities.get(TABLE_NAME_ISLETME),
                propertyMaps.get(entities.get(TABLE_NAME_MUSTERI_ISLETME)).get("isletme_id"),
                "isletme");
        // with the entity "Musteri"
        eMusteriIsletme.addToOne(entities.get(TABLE_NAME_MUSTERI),
                propertyMaps.get(entities.get(TABLE_NAME_MUSTERI_ISLETME)).get("musteri_id"),
                "musteri");
    }

    private static void addMusteriFirsatRelationships() {
        Entity eMusteriFirsat = entities.get(TABLE_NAME_MUSTERI_FIRSAT);

        // with the entity "Musteri"
        eMusteriFirsat.addToOne(entities.get(TABLE_NAME_MUSTERI),
                propertyMaps.get(entities.get(TABLE_NAME_MUSTERI_FIRSAT)).get("musteri_id"),
                "musteri");
        // with the entity "Firsat"
        eMusteriFirsat.addToOne(entities.get(TABLE_NAME_FIRSAT),
                propertyMaps.get(entities.get(TABLE_NAME_MUSTERI_FIRSAT)).get("firsat_id"),
                "firsat");
    }

    private static void addFirsatlarimRelationships() {
        Entity eFirsatlarim = entities.get(TABLE_NAME_FIRSATLARIM);

        // with the entity "Firsat"
        eFirsatlarim.addToOne(entities.get(TABLE_NAME_FIRSAT),
                propertyMaps.get(entities.get(TABLE_NAME_FIRSATLARIM)).get("firsat_id"),
                "firsat");
    }
}
