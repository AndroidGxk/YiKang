package com.example.sqlite.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.yikangcheng.admin.yikang.bean.LoginBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "LOGIN_BEAN".
*/
public class LoginBeanDao extends AbstractDao<LoginBean, Long> {

    public static final String TABLENAME = "LOGIN_BEAN";

    /**
     * Properties of entity LoginBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Uid = new Property(0, long.class, "uid", true, "_id");
        public final static Property Status = new Property(1, int.class, "status", false, "STATUS");
        public final static Property LastLoginTime = new Property(2, String.class, "lastLoginTime", false, "LAST_LOGIN_TIME");
        public final static Property Nickname = new Property(3, String.class, "nickname", false, "NICKNAME");
        public final static Property MemTime = new Property(4, String.class, "memTime", false, "MEM_TIME");
        public final static Property ThemeColors = new Property(5, String.class, "themeColors", false, "THEME_COLORS");
        public final static Property Id = new Property(6, int.class, "id", false, "ID");
        public final static Property EnterId = new Property(7, int.class, "enterId", false, "ENTER_ID");
        public final static Property Mobile = new Property(8, String.class, "mobile", false, "MOBILE");
        public final static Property IsZeroPurchase = new Property(9, int.class, "isZeroPurchase", false, "IS_ZERO_PURCHASE");
    }


    public LoginBeanDao(DaoConfig config) {
        super(config);
    }
    
    public LoginBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"LOGIN_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY NOT NULL ," + // 0: uid
                "\"STATUS\" INTEGER NOT NULL ," + // 1: status
                "\"LAST_LOGIN_TIME\" TEXT," + // 2: lastLoginTime
                "\"NICKNAME\" TEXT," + // 3: nickname
                "\"MEM_TIME\" TEXT," + // 4: memTime
                "\"THEME_COLORS\" TEXT," + // 5: themeColors
                "\"ID\" INTEGER NOT NULL ," + // 6: id
                "\"ENTER_ID\" INTEGER NOT NULL ," + // 7: enterId
                "\"MOBILE\" TEXT," + // 8: mobile
                "\"IS_ZERO_PURCHASE\" INTEGER NOT NULL );"); // 9: isZeroPurchase
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"LOGIN_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, LoginBean entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getUid());
        stmt.bindLong(2, entity.getStatus());
 
        String lastLoginTime = entity.getLastLoginTime();
        if (lastLoginTime != null) {
            stmt.bindString(3, lastLoginTime);
        }
 
        String nickname = entity.getNickname();
        if (nickname != null) {
            stmt.bindString(4, nickname);
        }
 
        String memTime = entity.getMemTime();
        if (memTime != null) {
            stmt.bindString(5, memTime);
        }
 
        String themeColors = entity.getThemeColors();
        if (themeColors != null) {
            stmt.bindString(6, themeColors);
        }
        stmt.bindLong(7, entity.getId());
        stmt.bindLong(8, entity.getEnterId());
 
        String mobile = entity.getMobile();
        if (mobile != null) {
            stmt.bindString(9, mobile);
        }
        stmt.bindLong(10, entity.getIsZeroPurchase());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, LoginBean entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getUid());
        stmt.bindLong(2, entity.getStatus());
 
        String lastLoginTime = entity.getLastLoginTime();
        if (lastLoginTime != null) {
            stmt.bindString(3, lastLoginTime);
        }
 
        String nickname = entity.getNickname();
        if (nickname != null) {
            stmt.bindString(4, nickname);
        }
 
        String memTime = entity.getMemTime();
        if (memTime != null) {
            stmt.bindString(5, memTime);
        }
 
        String themeColors = entity.getThemeColors();
        if (themeColors != null) {
            stmt.bindString(6, themeColors);
        }
        stmt.bindLong(7, entity.getId());
        stmt.bindLong(8, entity.getEnterId());
 
        String mobile = entity.getMobile();
        if (mobile != null) {
            stmt.bindString(9, mobile);
        }
        stmt.bindLong(10, entity.getIsZeroPurchase());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public LoginBean readEntity(Cursor cursor, int offset) {
        LoginBean entity = new LoginBean( //
            cursor.getLong(offset + 0), // uid
            cursor.getInt(offset + 1), // status
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // lastLoginTime
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // nickname
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // memTime
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // themeColors
            cursor.getInt(offset + 6), // id
            cursor.getInt(offset + 7), // enterId
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // mobile
            cursor.getInt(offset + 9) // isZeroPurchase
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, LoginBean entity, int offset) {
        entity.setUid(cursor.getLong(offset + 0));
        entity.setStatus(cursor.getInt(offset + 1));
        entity.setLastLoginTime(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setNickname(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setMemTime(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setThemeColors(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setId(cursor.getInt(offset + 6));
        entity.setEnterId(cursor.getInt(offset + 7));
        entity.setMobile(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setIsZeroPurchase(cursor.getInt(offset + 9));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(LoginBean entity, long rowId) {
        entity.setUid(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(LoginBean entity) {
        if(entity != null) {
            return entity.getUid();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(LoginBean entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
