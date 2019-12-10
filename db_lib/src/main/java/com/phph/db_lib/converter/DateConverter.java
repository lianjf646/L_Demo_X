package com.phph.db_lib.converter;

import androidx.room.TypeConverter;

import java.util.Date;

/**
 * Created by v on 2019/12/10.
 */
public class DateConverter {
    @TypeConverter
    public static Date revertDate(long value) {
        return new Date(value);
    }

    @TypeConverter
    public static long converterDate(Date value) {
        return value.getTime();
    }
}
