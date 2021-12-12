package com.example.travel4.model;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class TripDao_Impl implements TripDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Trip> __insertionAdapterOfTrip;

  private final EntityDeletionOrUpdateAdapter<Trip> __deletionAdapterOfTrip;

  private final EntityDeletionOrUpdateAdapter<Trip> __updateAdapterOfTrip;

  private final SharedSQLiteStatement __preparedStmtOfDeleteTripById;

  private final SharedSQLiteStatement __preparedStmtOfSetFavorite;

  private final SharedSQLiteStatement __preparedStmtOfRemoveFavorite;

  public TripDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTrip = new EntityInsertionAdapter<Trip>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR REPLACE INTO `Trip` (`id`,`name`,`destination`,`TripType`,`price`,`rating`,`start_date`,`end_date`,`image`,`isFavorite`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Trip value) {
        stmt.bindLong(1, value.getTripId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getDestination() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDestination());
        }
        if (value.getTripType() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getTripType());
        }
        stmt.bindLong(5, value.getPrice());
        stmt.bindDouble(6, value.getRating());
        if (value.getStartDate() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getStartDate());
        }
        if (value.getEndDate() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getEndDate());
        }
        if (value.getImage() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getImage());
        }
        final int _tmp;
        _tmp = value.isFavorite() ? 1 : 0;
        stmt.bindLong(10, _tmp);
      }
    };
    this.__deletionAdapterOfTrip = new EntityDeletionOrUpdateAdapter<Trip>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Trip` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Trip value) {
        stmt.bindLong(1, value.getTripId());
      }
    };
    this.__updateAdapterOfTrip = new EntityDeletionOrUpdateAdapter<Trip>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Trip` SET `id` = ?,`name` = ?,`destination` = ?,`TripType` = ?,`price` = ?,`rating` = ?,`start_date` = ?,`end_date` = ?,`image` = ?,`isFavorite` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Trip value) {
        stmt.bindLong(1, value.getTripId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getDestination() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDestination());
        }
        if (value.getTripType() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getTripType());
        }
        stmt.bindLong(5, value.getPrice());
        stmt.bindDouble(6, value.getRating());
        if (value.getStartDate() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getStartDate());
        }
        if (value.getEndDate() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getEndDate());
        }
        if (value.getImage() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getImage());
        }
        final int _tmp;
        _tmp = value.isFavorite() ? 1 : 0;
        stmt.bindLong(10, _tmp);
        stmt.bindLong(11, value.getTripId());
      }
    };
    this.__preparedStmtOfDeleteTripById = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Trip WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfSetFavorite = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE Trip SET isFavorite = 1 WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfRemoveFavorite = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE Trip SET isFavorite = 0 WHERE id = ?";
        return _query;
      }
    };
  }

  @Override
  public void addTrip(final Trip trip) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfTrip.insert(trip);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteTrip(final Trip trip) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfTrip.handle(trip);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateTrip(final Trip trip) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfTrip.handle(trip);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteTripById(final long tripId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteTripById.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, tripId);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteTripById.release(_stmt);
    }
  }

  @Override
  public void setFavorite(final long tripId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetFavorite.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, tripId);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfSetFavorite.release(_stmt);
    }
  }

  @Override
  public void removeFavorite(final long tripId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfRemoveFavorite.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, tripId);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfRemoveFavorite.release(_stmt);
    }
  }

  @Override
  public List<Trip> getAllFavoriteTrips() {
    final String _sql = "SELECT * FROM Trip WHERE isFavorite = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfTripId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfDestination = CursorUtil.getColumnIndexOrThrow(_cursor, "destination");
      final int _cursorIndexOfTripType = CursorUtil.getColumnIndexOrThrow(_cursor, "TripType");
      final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
      final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
      final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "start_date");
      final int _cursorIndexOfEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "end_date");
      final int _cursorIndexOfImage = CursorUtil.getColumnIndexOrThrow(_cursor, "image");
      final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
      final List<Trip> _result = new ArrayList<Trip>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Trip _item;
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final String _tmpDestination;
        if (_cursor.isNull(_cursorIndexOfDestination)) {
          _tmpDestination = null;
        } else {
          _tmpDestination = _cursor.getString(_cursorIndexOfDestination);
        }
        final String _tmpTripType;
        if (_cursor.isNull(_cursorIndexOfTripType)) {
          _tmpTripType = null;
        } else {
          _tmpTripType = _cursor.getString(_cursorIndexOfTripType);
        }
        final int _tmpPrice;
        _tmpPrice = _cursor.getInt(_cursorIndexOfPrice);
        final double _tmpRating;
        _tmpRating = _cursor.getDouble(_cursorIndexOfRating);
        final String _tmpStartDate;
        if (_cursor.isNull(_cursorIndexOfStartDate)) {
          _tmpStartDate = null;
        } else {
          _tmpStartDate = _cursor.getString(_cursorIndexOfStartDate);
        }
        final String _tmpEndDate;
        if (_cursor.isNull(_cursorIndexOfEndDate)) {
          _tmpEndDate = null;
        } else {
          _tmpEndDate = _cursor.getString(_cursorIndexOfEndDate);
        }
        final String _tmpImage;
        if (_cursor.isNull(_cursorIndexOfImage)) {
          _tmpImage = null;
        } else {
          _tmpImage = _cursor.getString(_cursorIndexOfImage);
        }
        _item = new Trip(_tmpName,_tmpDestination,_tmpTripType,_tmpPrice,_tmpRating,_tmpStartDate,_tmpEndDate,_tmpImage);
        final int _tmpTripId;
        _tmpTripId = _cursor.getInt(_cursorIndexOfTripId);
        _item.setTripId(_tmpTripId);
        final boolean _tmpIsFavorite;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
        _tmpIsFavorite = _tmp != 0;
        _item.setFavorite(_tmpIsFavorite);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Trip> getAllTrips() {
    final String _sql = "SELECT * FROM Trip ORDER BY id ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfTripId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfDestination = CursorUtil.getColumnIndexOrThrow(_cursor, "destination");
      final int _cursorIndexOfTripType = CursorUtil.getColumnIndexOrThrow(_cursor, "TripType");
      final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
      final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
      final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "start_date");
      final int _cursorIndexOfEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "end_date");
      final int _cursorIndexOfImage = CursorUtil.getColumnIndexOrThrow(_cursor, "image");
      final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
      final List<Trip> _result = new ArrayList<Trip>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Trip _item;
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final String _tmpDestination;
        if (_cursor.isNull(_cursorIndexOfDestination)) {
          _tmpDestination = null;
        } else {
          _tmpDestination = _cursor.getString(_cursorIndexOfDestination);
        }
        final String _tmpTripType;
        if (_cursor.isNull(_cursorIndexOfTripType)) {
          _tmpTripType = null;
        } else {
          _tmpTripType = _cursor.getString(_cursorIndexOfTripType);
        }
        final int _tmpPrice;
        _tmpPrice = _cursor.getInt(_cursorIndexOfPrice);
        final double _tmpRating;
        _tmpRating = _cursor.getDouble(_cursorIndexOfRating);
        final String _tmpStartDate;
        if (_cursor.isNull(_cursorIndexOfStartDate)) {
          _tmpStartDate = null;
        } else {
          _tmpStartDate = _cursor.getString(_cursorIndexOfStartDate);
        }
        final String _tmpEndDate;
        if (_cursor.isNull(_cursorIndexOfEndDate)) {
          _tmpEndDate = null;
        } else {
          _tmpEndDate = _cursor.getString(_cursorIndexOfEndDate);
        }
        final String _tmpImage;
        if (_cursor.isNull(_cursorIndexOfImage)) {
          _tmpImage = null;
        } else {
          _tmpImage = _cursor.getString(_cursorIndexOfImage);
        }
        _item = new Trip(_tmpName,_tmpDestination,_tmpTripType,_tmpPrice,_tmpRating,_tmpStartDate,_tmpEndDate,_tmpImage);
        final int _tmpTripId;
        _tmpTripId = _cursor.getInt(_cursorIndexOfTripId);
        _item.setTripId(_tmpTripId);
        final boolean _tmpIsFavorite;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
        _tmpIsFavorite = _tmp != 0;
        _item.setFavorite(_tmpIsFavorite);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Trip getTrip(final long tripId) {
    final String _sql = "SELECT * FROM Trip WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, tripId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfTripId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfDestination = CursorUtil.getColumnIndexOrThrow(_cursor, "destination");
      final int _cursorIndexOfTripType = CursorUtil.getColumnIndexOrThrow(_cursor, "TripType");
      final int _cursorIndexOfPrice = CursorUtil.getColumnIndexOrThrow(_cursor, "price");
      final int _cursorIndexOfRating = CursorUtil.getColumnIndexOrThrow(_cursor, "rating");
      final int _cursorIndexOfStartDate = CursorUtil.getColumnIndexOrThrow(_cursor, "start_date");
      final int _cursorIndexOfEndDate = CursorUtil.getColumnIndexOrThrow(_cursor, "end_date");
      final int _cursorIndexOfImage = CursorUtil.getColumnIndexOrThrow(_cursor, "image");
      final int _cursorIndexOfIsFavorite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavorite");
      final Trip _result;
      if(_cursor.moveToFirst()) {
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final String _tmpDestination;
        if (_cursor.isNull(_cursorIndexOfDestination)) {
          _tmpDestination = null;
        } else {
          _tmpDestination = _cursor.getString(_cursorIndexOfDestination);
        }
        final String _tmpTripType;
        if (_cursor.isNull(_cursorIndexOfTripType)) {
          _tmpTripType = null;
        } else {
          _tmpTripType = _cursor.getString(_cursorIndexOfTripType);
        }
        final int _tmpPrice;
        _tmpPrice = _cursor.getInt(_cursorIndexOfPrice);
        final double _tmpRating;
        _tmpRating = _cursor.getDouble(_cursorIndexOfRating);
        final String _tmpStartDate;
        if (_cursor.isNull(_cursorIndexOfStartDate)) {
          _tmpStartDate = null;
        } else {
          _tmpStartDate = _cursor.getString(_cursorIndexOfStartDate);
        }
        final String _tmpEndDate;
        if (_cursor.isNull(_cursorIndexOfEndDate)) {
          _tmpEndDate = null;
        } else {
          _tmpEndDate = _cursor.getString(_cursorIndexOfEndDate);
        }
        final String _tmpImage;
        if (_cursor.isNull(_cursorIndexOfImage)) {
          _tmpImage = null;
        } else {
          _tmpImage = _cursor.getString(_cursorIndexOfImage);
        }
        _result = new Trip(_tmpName,_tmpDestination,_tmpTripType,_tmpPrice,_tmpRating,_tmpStartDate,_tmpEndDate,_tmpImage);
        final int _tmpTripId;
        _tmpTripId = _cursor.getInt(_cursorIndexOfTripId);
        _result.setTripId(_tmpTripId);
        final boolean _tmpIsFavorite;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsFavorite);
        _tmpIsFavorite = _tmp != 0;
        _result.setFavorite(_tmpIsFavorite);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
