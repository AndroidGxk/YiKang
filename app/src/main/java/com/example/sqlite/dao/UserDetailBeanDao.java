package com.example.sqlite.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.yikangcheng.admin.yikang.bean.UserDetailBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USER_DETAIL_BEAN".
*/
public class UserDetailBeanDao extends AbstractDao<UserDetailBean, Long> {

    public static final String TABLENAME = "USER_DETAIL_BEAN";

    /**
     * Properties of entity UserDetailBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, long.class, "id", true, "_id");
        public final static Property UserId = new Property(1, int.class, "userId", false, "USER_ID");
        public final static Property Status = new Property(2, int.class, "status", false, "STATUS");
        public final static Property RealName = new Property(3, String.class, "realName", false, "REAL_NAME");
        public final static Property Gender = new Property(4, int.class, "gender", false, "GENDER");
        public final static Property UserInfo = new Property(5, String.class, "userInfo", false, "USER_INFO");
        public final static Property Avatar = new Property(6, String.class, "avatar", false, "AVATAR");
        public final static Property NickName = new Property(7, String.class, "nickName", false, "NICK_NAME");
        public final static Property Email = new Property(8, String.class, "email", false, "EMAIL");
        public final static Property Mobile = new Property(9, String.class, "mobile", false, "MOBILE");
    }


    public UserDetailBeanDao(DaoConfig config) {
        super(config);
    }
    
    public UserDetailBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER_DETAIL_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY NOT NULL ," + // 0: id
                "\"USER_ID\" INTEGER NOT NULL ," + // 1: userId
                "\"STATUS\" INTEGER NOT NULL ," + // 2: status
                "\"REAL_NAME\" TEXT," + // 3: realName
                "\"GENDER\" INTEGER NOT NULL ," + // 4: gender
                "\"USER_INFO\" TEXT," + // 5: userInfo
                "\"AVATAR\" TEXT," + // 6: avatar
                "\"NICK_NAME\" TEXT," + // 7: nickName
                "\"EMAIL\" TEXT," + // 8: email
                "\"MOBILE\" TEXT);"); // 9: mobile
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER_DETAIL_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, UserDetailBean entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
        stmt.bindLong(2, entity.getUserId());
        stmt.bindLong(3, entity.getStatus());
 
        String realName = entity.getRealName();
        if (realName != null) {
            stmt.bindString(4, realName);
        }
        stmt.bindLong(5, entity.getGender());
 
        String userInfo = entity.getUserInfo();
        if (userInfo != null) {
            stmt.bindString(6, userInfo);
        }
 
        String avatar = entity.getAvatar();
        if (avatar != null) {
            stmt.bindString(7, avatar);
        }
 
        String nickName = entity.getNickName();
        if (nickName != null) {
            stmt.bindString(8, nickName);
        }
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(9, email);
        }
 
        String mobile = entity.getMobile();
        if (mobile != null) {
            stmt.bindString(10, mobile);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, UserDetailBean entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
        stmt.bindLong(2, entity.getUserId());
        stmt.bindLong(3, entity.getStatus());
 
        String realName = entity.getRealName();
        if (realName != null) {
            stmt.bindString(4, realName);
        }
        stmt.bindLong(5, entity.getGender());
 
        String userInfo = entity.getUserInfo();
        if (userInfo != null) {
            stmt.bindString(6, userInfo);
        }
 
        String avatar = entity.getAvatar();
        if (avatar != null) {
            stmt.bindString(7, avatar);
        }
 
        String nickName = entity.getNickName();
        if (nickName != null) {
            stmt.bindString(8, nickName);
        }
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(9, email);
        }
 
        String mobile = entity.getMobile();
        if (mobile != null) {
            stmt.bindString(10, mobile);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public UserDetailBean readEntity(Cursor cursor, int offset) {
        UserDetailBean entity = new UserDetailBean( //
            cursor.getLong(offset + 0), // id
            cursor.getInt(offset + 1), // userId
            cursor.getInt(offset + 2), // status
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // realName
            cursor.getInt(offset + 4), // gender
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // userInfo
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // avatar
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // nickName
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // email
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9) // mobile
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, UserDetailBean entity, int offset) {
        entity.setId(cursor.getLong(offset + 0));
        entity.setUserId(cursor.getInt(offset + 1));
        entity.setStatus(cursor.getInt(offset + 2));
        entity.setRealName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setGender(cursor.getInt(offset + 4));
        entity.setUserInfo(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setAvatar(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setNickName(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setEmail(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setMobile(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(UserDetailBean entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(UserDetailBean entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(UserDetailBean entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
