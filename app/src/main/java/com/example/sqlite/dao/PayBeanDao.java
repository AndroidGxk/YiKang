package com.example.sqlite.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.yikangcheng.admin.yikang.bean.PayBean;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PAY_BEAN".
*/
public class PayBeanDao extends AbstractDao<PayBean, Long> {

    public static final String TABLENAME = "PAY_BEAN";

    /**
     * Properties of entity PayBean.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Uid = new Property(0, long.class, "uid", true, "_id");
        public final static Property Amount = new Property(1, double.class, "amount", false, "AMOUNT");
        public final static Property Balance = new Property(2, String.class, "balance", false, "BALANCE");
        public final static Property OrderNo = new Property(3, String.class, "orderNo", false, "ORDER_NO");
        public final static Property BankAmount = new Property(4, double.class, "bankAmount", false, "BANK_AMOUNT");
        public final static Property Orderinfo = new Property(5, String.class, "orderinfo", false, "ORDERINFO");
        public final static Property OrderId = new Property(6, int.class, "orderId", false, "ORDER_ID");
        public final static Property TimeStamp = new Property(7, String.class, "timeStamp", false, "TIME_STAMP");
        public final static Property NonceStr = new Property(8, String.class, "nonceStr", false, "NONCE_STR");
        public final static Property Sign = new Property(9, String.class, "sign", false, "SIGN");
        public final static Property PrepayId = new Property(10, String.class, "prepayId", false, "PREPAY_ID");
        public final static Property IsZeroPurchase = new Property(11, int.class, "isZeroPurchase", false, "IS_ZERO_PURCHASE");
    }


    public PayBeanDao(DaoConfig config) {
        super(config);
    }
    
    public PayBeanDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PAY_BEAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY NOT NULL ," + // 0: uid
                "\"AMOUNT\" REAL NOT NULL ," + // 1: amount
                "\"BALANCE\" TEXT," + // 2: balance
                "\"ORDER_NO\" TEXT," + // 3: orderNo
                "\"BANK_AMOUNT\" REAL NOT NULL ," + // 4: bankAmount
                "\"ORDERINFO\" TEXT," + // 5: orderinfo
                "\"ORDER_ID\" INTEGER NOT NULL ," + // 6: orderId
                "\"TIME_STAMP\" TEXT," + // 7: timeStamp
                "\"NONCE_STR\" TEXT," + // 8: nonceStr
                "\"SIGN\" TEXT," + // 9: sign
                "\"PREPAY_ID\" TEXT," + // 10: prepayId
                "\"IS_ZERO_PURCHASE\" INTEGER NOT NULL );"); // 11: isZeroPurchase
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PAY_BEAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, PayBean entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getUid());
        stmt.bindDouble(2, entity.getAmount());
 
        String balance = entity.getBalance();
        if (balance != null) {
            stmt.bindString(3, balance);
        }
 
        String orderNo = entity.getOrderNo();
        if (orderNo != null) {
            stmt.bindString(4, orderNo);
        }
        stmt.bindDouble(5, entity.getBankAmount());
 
        String orderinfo = entity.getOrderinfo();
        if (orderinfo != null) {
            stmt.bindString(6, orderinfo);
        }
        stmt.bindLong(7, entity.getOrderId());
 
        String timeStamp = entity.getTimeStamp();
        if (timeStamp != null) {
            stmt.bindString(8, timeStamp);
        }
 
        String nonceStr = entity.getNonceStr();
        if (nonceStr != null) {
            stmt.bindString(9, nonceStr);
        }
 
        String sign = entity.getSign();
        if (sign != null) {
            stmt.bindString(10, sign);
        }
 
        String prepayId = entity.getPrepayId();
        if (prepayId != null) {
            stmt.bindString(11, prepayId);
        }
        stmt.bindLong(12, entity.getIsZeroPurchase());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, PayBean entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getUid());
        stmt.bindDouble(2, entity.getAmount());
 
        String balance = entity.getBalance();
        if (balance != null) {
            stmt.bindString(3, balance);
        }
 
        String orderNo = entity.getOrderNo();
        if (orderNo != null) {
            stmt.bindString(4, orderNo);
        }
        stmt.bindDouble(5, entity.getBankAmount());
 
        String orderinfo = entity.getOrderinfo();
        if (orderinfo != null) {
            stmt.bindString(6, orderinfo);
        }
        stmt.bindLong(7, entity.getOrderId());
 
        String timeStamp = entity.getTimeStamp();
        if (timeStamp != null) {
            stmt.bindString(8, timeStamp);
        }
 
        String nonceStr = entity.getNonceStr();
        if (nonceStr != null) {
            stmt.bindString(9, nonceStr);
        }
 
        String sign = entity.getSign();
        if (sign != null) {
            stmt.bindString(10, sign);
        }
 
        String prepayId = entity.getPrepayId();
        if (prepayId != null) {
            stmt.bindString(11, prepayId);
        }
        stmt.bindLong(12, entity.getIsZeroPurchase());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public PayBean readEntity(Cursor cursor, int offset) {
        PayBean entity = new PayBean( //
            cursor.getLong(offset + 0), // uid
            cursor.getDouble(offset + 1), // amount
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // balance
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // orderNo
            cursor.getDouble(offset + 4), // bankAmount
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // orderinfo
            cursor.getInt(offset + 6), // orderId
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // timeStamp
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // nonceStr
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // sign
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // prepayId
            cursor.getInt(offset + 11) // isZeroPurchase
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, PayBean entity, int offset) {
        entity.setUid(cursor.getLong(offset + 0));
        entity.setAmount(cursor.getDouble(offset + 1));
        entity.setBalance(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setOrderNo(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setBankAmount(cursor.getDouble(offset + 4));
        entity.setOrderinfo(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setOrderId(cursor.getInt(offset + 6));
        entity.setTimeStamp(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setNonceStr(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setSign(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setPrepayId(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setIsZeroPurchase(cursor.getInt(offset + 11));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(PayBean entity, long rowId) {
        entity.setUid(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(PayBean entity) {
        if(entity != null) {
            return entity.getUid();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(PayBean entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
