package org.dfir.harita.app.model.dao;

import org.dfir.harita.app.model.dao.DaoSession;
import de.greenrobot.dao.DaoException;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table MUSTERI__ISLETME.
 */
public class Musteri_Isletme {

    private Long id;
    private Long musteri_id;
    private Long isletme_id;

    /** Used to resolve relations */
    private transient DaoSession daoSession;

    /** Used for active entity operations. */
    private transient Musteri_IsletmeDao myDao;

    private Isletme isletme;
    private Long isletme__resolvedKey;

    private Musteri musteri;
    private Long musteri__resolvedKey;


    public Musteri_Isletme() {
    }

    public Musteri_Isletme(Long id) {
        this.id = id;
    }

    public Musteri_Isletme(Long id, Long musteri_id, Long isletme_id) {
        this.id = id;
        this.musteri_id = musteri_id;
        this.isletme_id = isletme_id;
    }

    /** called by internal mechanisms, do not call yourself. */
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getMusteri_IsletmeDao() : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMusteri_id() {
        return musteri_id;
    }

    public void setMusteri_id(Long musteri_id) {
        this.musteri_id = musteri_id;
    }

    public Long getIsletme_id() {
        return isletme_id;
    }

    public void setIsletme_id(Long isletme_id) {
        this.isletme_id = isletme_id;
    }

    /** To-one relationship, resolved on first access. */
    public Isletme getIsletme() {
        Long __key = this.isletme_id;
        if (isletme__resolvedKey == null || !isletme__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            IsletmeDao targetDao = daoSession.getIsletmeDao();
            Isletme isletmeNew = targetDao.load(__key);
            synchronized (this) {
                isletme = isletmeNew;
            	isletme__resolvedKey = __key;
            }
        }
        return isletme;
    }

    public void setIsletme(Isletme isletme) {
        synchronized (this) {
            this.isletme = isletme;
            isletme_id = isletme == null ? null : isletme.getId();
            isletme__resolvedKey = isletme_id;
        }
    }

    /** To-one relationship, resolved on first access. */
    public Musteri getMusteri() {
        Long __key = this.musteri_id;
        if (musteri__resolvedKey == null || !musteri__resolvedKey.equals(__key)) {
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            MusteriDao targetDao = daoSession.getMusteriDao();
            Musteri musteriNew = targetDao.load(__key);
            synchronized (this) {
                musteri = musteriNew;
            	musteri__resolvedKey = __key;
            }
        }
        return musteri;
    }

    public void setMusteri(Musteri musteri) {
        synchronized (this) {
            this.musteri = musteri;
            musteri_id = musteri == null ? null : musteri.getId();
            musteri__resolvedKey = musteri_id;
        }
    }

    /** Convenient call for {@link AbstractDao#delete(Object)}. Entity must attached to an entity context. */
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.delete(this);
    }

    /** Convenient call for {@link AbstractDao#update(Object)}. Entity must attached to an entity context. */
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.update(this);
    }

    /** Convenient call for {@link AbstractDao#refresh(Object)}. Entity must attached to an entity context. */
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }    
        myDao.refresh(this);
    }

}
