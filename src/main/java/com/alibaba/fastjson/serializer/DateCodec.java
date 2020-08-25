package com.alibaba.fastjson.serializer;

import cn.qqtheme.framework.BuildConfig;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONScanner;
import com.alibaba.fastjson.parser.deserializer.AbstractDateDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.IOUtils;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateCodec extends AbstractDateDeserializer implements ObjectSerializer, ObjectDeserializer {
    public static final DateCodec instance = new DateCodec();

    public int getFastMatchToken() {
        return 2;
    }

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        Date date;
        char[] cArr;
        JSONSerializer jSONSerializer2 = jSONSerializer;
        Object obj3 = obj;
        SerializeWriter serializeWriter = jSONSerializer2.out;
        if (obj3 == null) {
            serializeWriter.writeNull();
            return;
        }
        if (obj3 instanceof Date) {
            date = (Date) obj3;
        } else {
            date = TypeUtils.castToDate(obj);
        }
        if (serializeWriter.isEnabled(SerializerFeature.WriteDateUseDateFormat)) {
            DateFormat dateFormat = jSONSerializer.getDateFormat();
            if (dateFormat == null) {
                dateFormat = new SimpleDateFormat(JSON.DEFFAULT_DATE_FORMAT, jSONSerializer2.locale);
                dateFormat.setTimeZone(jSONSerializer2.timeZone);
            }
            serializeWriter.writeString(dateFormat.format(date));
        } else if (!serializeWriter.isEnabled(SerializerFeature.WriteClassName) || obj.getClass() == type) {
            long time = date.getTime();
            if (serializeWriter.isEnabled(SerializerFeature.UseISO8601DateFormat)) {
                int i2 = serializeWriter.isEnabled(SerializerFeature.UseSingleQuotes) ? 39 : 34;
                serializeWriter.write(i2);
                Calendar instance2 = Calendar.getInstance(jSONSerializer2.timeZone, jSONSerializer2.locale);
                instance2.setTimeInMillis(time);
                int i3 = instance2.get(1);
                int i4 = instance2.get(2) + 1;
                int i5 = instance2.get(5);
                int i6 = instance2.get(11);
                int i7 = instance2.get(12);
                int i8 = instance2.get(13);
                int i9 = instance2.get(14);
                if (i9 != 0) {
                    cArr = "0000-00-00T00:00:00.000".toCharArray();
                    IOUtils.getChars(i9, 23, cArr);
                    IOUtils.getChars(i8, 19, cArr);
                    IOUtils.getChars(i7, 16, cArr);
                    IOUtils.getChars(i6, 13, cArr);
                    IOUtils.getChars(i5, 10, cArr);
                    IOUtils.getChars(i4, 7, cArr);
                    IOUtils.getChars(i3, 4, cArr);
                } else if (i8 == 0 && i7 == 0 && i6 == 0) {
                    cArr = "0000-00-00".toCharArray();
                    IOUtils.getChars(i5, 10, cArr);
                    IOUtils.getChars(i4, 7, cArr);
                    IOUtils.getChars(i3, 4, cArr);
                } else {
                    cArr = "0000-00-00T00:00:00".toCharArray();
                    IOUtils.getChars(i8, 19, cArr);
                    IOUtils.getChars(i7, 16, cArr);
                    IOUtils.getChars(i6, 13, cArr);
                    IOUtils.getChars(i5, 10, cArr);
                    IOUtils.getChars(i4, 7, cArr);
                    IOUtils.getChars(i3, 4, cArr);
                }
                serializeWriter.write(cArr);
                int rawOffset = instance2.getTimeZone().getRawOffset() / 3600000;
                if (rawOffset == 0) {
                    serializeWriter.write(90);
                } else {
                    if (rawOffset > 9) {
                        serializeWriter.write(43);
                        serializeWriter.writeInt(rawOffset);
                    } else if (rawOffset > 0) {
                        serializeWriter.write(43);
                        serializeWriter.write(48);
                        serializeWriter.writeInt(rawOffset);
                    } else if (rawOffset < -9) {
                        serializeWriter.write(45);
                        serializeWriter.writeInt(rawOffset);
                    } else if (rawOffset < 0) {
                        serializeWriter.write(45);
                        serializeWriter.write(48);
                        serializeWriter.writeInt(rawOffset);
                    }
                    serializeWriter.append((CharSequence) ":00");
                }
                serializeWriter.write(i2);
                return;
            }
            serializeWriter.writeLong(time);
        } else if (obj.getClass() == Date.class) {
            serializeWriter.write("new Date(");
            serializeWriter.writeLong(((Date) obj3).getTime());
            serializeWriter.write(41);
        } else {
            serializeWriter.write((int) BuildConfig.VERSION_CODE);
            serializeWriter.writeFieldName(JSON.DEFAULT_TYPE_KEY);
            jSONSerializer2.write(obj.getClass().getName());
            serializeWriter.writeFieldValue(',', "val", ((Date) obj3).getTime());
            serializeWriter.write(125);
        }
    }

    public <T> T cast(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2) {
        if (obj2 == null) {
            return null;
        }
        if (obj2 instanceof Date) {
            return obj2;
        }
        if (obj2 instanceof Number) {
            return new Date(((Number) obj2).longValue());
        }
        if (obj2 instanceof String) {
            String str = (String) obj2;
            if (str.length() == 0) {
                return null;
            }
            JSONScanner jSONScanner = new JSONScanner(str);
            try {
                if (jSONScanner.scanISO8601DateIfMatch(false)) {
                    T calendar = jSONScanner.getCalendar();
                    if (type == Calendar.class) {
                        return calendar;
                    }
                    T time = calendar.getTime();
                    jSONScanner.close();
                    return time;
                }
                jSONScanner.close();
                if (str.length() == defaultJSONParser.getDateFomartPattern().length() || (str.length() == 22 && defaultJSONParser.getDateFomartPattern().equals("yyyyMMddHHmmssSSSZ"))) {
                    try {
                        return defaultJSONParser.getDateFormat().parse(str);
                    } catch (ParseException unused) {
                    }
                }
                if (str.startsWith("/Date(") && str.endsWith(")/")) {
                    str = str.substring(6, str.length() - 2);
                }
                if ("0000-00-00".equals(str) || "0000-00-00T00:00:00".equalsIgnoreCase(str) || "0001-01-01T00:00:00+08:00".equalsIgnoreCase(str)) {
                    return null;
                }
                int lastIndexOf = str.lastIndexOf(124);
                if (lastIndexOf > 20) {
                    TimeZone timeZone = TimeZone.getTimeZone(str.substring(lastIndexOf + 1));
                    if (!"GMT".equals(timeZone.getID())) {
                        JSONScanner jSONScanner2 = new JSONScanner(str.substring(0, lastIndexOf));
                        try {
                            if (jSONScanner2.scanISO8601DateIfMatch(false)) {
                                T calendar2 = jSONScanner2.getCalendar();
                                calendar2.setTimeZone(timeZone);
                                if (type == Calendar.class) {
                                    return calendar2;
                                }
                                T time2 = calendar2.getTime();
                                jSONScanner2.close();
                                return time2;
                            }
                            jSONScanner2.close();
                        } finally {
                            jSONScanner2.close();
                        }
                    }
                }
                return new Date(Long.parseLong(str));
            } finally {
                jSONScanner.close();
            }
        } else {
            throw new JSONException("parse error");
        }
    }
}
