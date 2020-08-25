package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.serializer.BeanContext;
import com.alibaba.fastjson.serializer.ContextObjectSerializer;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Locale;

public class Jdk8DateCodec extends ContextObjectDeserializer implements ObjectSerializer, ContextObjectSerializer, ObjectDeserializer {
    private static final DateTimeFormatter ISO_FIXED_FORMAT = DateTimeFormatter.ofPattern(defaultPatttern).withZone(ZoneId.systemDefault());
    private static final DateTimeFormatter defaultFormatter = DateTimeFormatter.ofPattern(defaultPatttern);
    private static final String defaultPatttern = "yyyy-MM-dd HH:mm:ss";
    private static final DateTimeFormatter formatter_d10_cn = DateTimeFormatter.ofPattern("yyyy年M月d日");
    private static final DateTimeFormatter formatter_d10_de = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final DateTimeFormatter formatter_d10_eur = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter formatter_d10_in = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter formatter_d10_kr = DateTimeFormatter.ofPattern("yyyy년M월d일");
    private static final DateTimeFormatter formatter_d10_tw = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    private static final DateTimeFormatter formatter_d10_us = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private static final DateTimeFormatter formatter_d8 = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter formatter_dt19_cn = DateTimeFormatter.ofPattern("yyyy年M月d日 HH:mm:ss");
    private static final DateTimeFormatter formatter_dt19_cn_1 = DateTimeFormatter.ofPattern("yyyy年M月d日 H时m分s秒");
    private static final DateTimeFormatter formatter_dt19_de = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
    private static final DateTimeFormatter formatter_dt19_eur = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private static final DateTimeFormatter formatter_dt19_in = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    private static final DateTimeFormatter formatter_dt19_kr = DateTimeFormatter.ofPattern("yyyy년M월d일 HH:mm:ss");
    private static final DateTimeFormatter formatter_dt19_tw = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private static final DateTimeFormatter formatter_dt19_us = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss");
    private static final DateTimeFormatter formatter_iso8601 = DateTimeFormatter.ofPattern(formatter_iso8601_pattern);
    private static final String formatter_iso8601_pattern = "yyyy-MM-dd'T'HH:mm:ss";
    public static final Jdk8DateCodec instance = new Jdk8DateCodec();

    public int getFastMatchToken() {
        return 4;
    }

    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj, String str, int i) {
        DateTimeFormatter dateTimeFormatter;
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        if (jSONLexer.token() == 8) {
            jSONLexer.nextToken();
            return null;
        } else if (jSONLexer.token() == 4) {
            String stringVal = jSONLexer.stringVal();
            jSONLexer.nextToken();
            if (str != null) {
                dateTimeFormatter = defaultPatttern.equals(str) ? defaultFormatter : DateTimeFormatter.ofPattern(str);
            } else {
                dateTimeFormatter = null;
            }
            if ("".equals(stringVal)) {
                return null;
            }
            if (type == LocalDateTime.class) {
                if (stringVal.length() == 10 || stringVal.length() == 8) {
                    return LocalDateTime.of(parseLocalDate(stringVal, str, dateTimeFormatter), LocalTime.MIN);
                }
                return parseDateTime(stringVal, dateTimeFormatter);
            } else if (type == LocalDate.class) {
                if (stringVal.length() != 23) {
                    return parseLocalDate(stringVal, str, dateTimeFormatter);
                }
                LocalDateTime parse = LocalDateTime.parse(stringVal);
                return LocalDate.of(parse.getYear(), parse.getMonthValue(), parse.getDayOfMonth());
            } else if (type == LocalTime.class) {
                if (stringVal.length() != 23) {
                    return LocalTime.parse(stringVal);
                }
                LocalDateTime parse2 = LocalDateTime.parse(stringVal);
                return LocalTime.of(parse2.getHour(), parse2.getMinute(), parse2.getSecond(), parse2.getNano());
            } else if (type == ZonedDateTime.class) {
                if (dateTimeFormatter == defaultFormatter) {
                    dateTimeFormatter = ISO_FIXED_FORMAT;
                }
                return parseZonedDateTime(stringVal, dateTimeFormatter);
            } else if (type == OffsetDateTime.class) {
                return OffsetDateTime.parse(stringVal);
            } else {
                if (type == OffsetTime.class) {
                    return OffsetTime.parse(stringVal);
                }
                if (type == ZoneId.class) {
                    return ZoneId.of(stringVal);
                }
                if (type == Period.class) {
                    return Period.parse(stringVal);
                }
                if (type == Duration.class) {
                    return Duration.parse(stringVal);
                }
                if (type == Instant.class) {
                    return Instant.parse(stringVal);
                }
                return null;
            }
        } else if (jSONLexer.token() == 2) {
            long longValue = jSONLexer.longValue();
            jSONLexer.nextToken();
            if (type == LocalDateTime.class) {
                return LocalDateTime.ofInstant(Instant.ofEpochMilli(longValue), JSON.defaultTimeZone.toZoneId());
            }
            if (type == LocalDate.class) {
                return LocalDateTime.ofInstant(Instant.ofEpochMilli(longValue), JSON.defaultTimeZone.toZoneId()).toLocalDate();
            }
            if (type == LocalTime.class) {
                return LocalDateTime.ofInstant(Instant.ofEpochMilli(longValue), JSON.defaultTimeZone.toZoneId()).toLocalTime();
            }
            throw new UnsupportedOperationException();
        } else {
            throw new UnsupportedOperationException();
        }
    }

    /* access modifiers changed from: protected */
    public LocalDateTime parseDateTime(String str, DateTimeFormatter dateTimeFormatter) {
        if (dateTimeFormatter == null) {
            if (str.length() == 19) {
                char charAt = str.charAt(4);
                char charAt2 = str.charAt(7);
                char charAt3 = str.charAt(10);
                char charAt4 = str.charAt(13);
                char charAt5 = str.charAt(16);
                if (charAt4 == ':' && charAt5 == ':') {
                    if (charAt == '-' && charAt2 == '-') {
                        if (charAt3 == 'T') {
                            dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
                        } else if (charAt3 == ' ') {
                            dateTimeFormatter = defaultFormatter;
                        }
                    } else if (charAt == '-' && charAt2 == '-') {
                        dateTimeFormatter = defaultFormatter;
                    } else if (charAt == '/' && charAt2 == '/') {
                        dateTimeFormatter = formatter_dt19_tw;
                    } else {
                        char charAt6 = str.charAt(0);
                        char charAt7 = str.charAt(1);
                        char charAt8 = str.charAt(2);
                        char charAt9 = str.charAt(3);
                        char charAt10 = str.charAt(5);
                        if (charAt8 == '/' && charAt10 == '/') {
                            int i = ((charAt9 - '0') * 10) + (charAt - '0');
                            if (((charAt6 - '0') * 10) + (charAt7 - '0') > 12) {
                                dateTimeFormatter = formatter_dt19_eur;
                            } else if (i > 12) {
                                dateTimeFormatter = formatter_dt19_us;
                            } else {
                                String country = Locale.getDefault().getCountry();
                                if (country.equals("US")) {
                                    dateTimeFormatter = formatter_dt19_us;
                                } else if (country.equals("BR") || country.equals("AU")) {
                                    dateTimeFormatter = formatter_dt19_eur;
                                }
                            }
                        } else if (charAt8 == '.' && charAt10 == '.') {
                            dateTimeFormatter = formatter_dt19_de;
                        } else if (charAt8 == '-' && charAt10 == '-') {
                            dateTimeFormatter = formatter_dt19_in;
                        }
                    }
                }
            }
            if (str.length() >= 17) {
                char charAt11 = str.charAt(4);
                if (charAt11 == 24180) {
                    if (str.charAt(str.length() - 1) == 31186) {
                        dateTimeFormatter = formatter_dt19_cn_1;
                    } else {
                        dateTimeFormatter = formatter_dt19_cn;
                    }
                } else if (charAt11 == 45380) {
                    dateTimeFormatter = formatter_dt19_kr;
                }
            }
        }
        if (dateTimeFormatter == null) {
            return LocalDateTime.parse(str);
        }
        return LocalDateTime.parse(str, dateTimeFormatter);
    }

    /* access modifiers changed from: protected */
    public LocalDate parseLocalDate(String str, String str2, DateTimeFormatter dateTimeFormatter) {
        DateTimeFormatter dateTimeFormatter2;
        if (dateTimeFormatter == null) {
            if (str.length() == 8) {
                dateTimeFormatter = formatter_d8;
            }
            if (str.length() == 10) {
                char charAt = str.charAt(4);
                char charAt2 = str.charAt(7);
                if (charAt == '/' && charAt2 == '/') {
                    dateTimeFormatter = formatter_d10_tw;
                }
                char charAt3 = str.charAt(0);
                char charAt4 = str.charAt(1);
                char charAt5 = str.charAt(2);
                char charAt6 = str.charAt(3);
                char charAt7 = str.charAt(5);
                if (charAt5 == '/' && charAt7 == '/') {
                    int i = ((charAt6 - '0') * 10) + (charAt - '0');
                    if (((charAt3 - '0') * 10) + (charAt4 - '0') > 12) {
                        dateTimeFormatter = formatter_d10_eur;
                    } else if (i > 12) {
                        dateTimeFormatter = formatter_d10_us;
                    } else {
                        String country = Locale.getDefault().getCountry();
                        if (country.equals("US")) {
                            dateTimeFormatter = formatter_d10_us;
                        } else if (country.equals("BR") || country.equals("AU")) {
                            dateTimeFormatter = formatter_d10_eur;
                        }
                    }
                } else {
                    if (charAt5 == '.' && charAt7 == '.') {
                        dateTimeFormatter2 = formatter_d10_de;
                    } else if (charAt5 == '-' && charAt7 == '-') {
                        dateTimeFormatter2 = formatter_d10_in;
                    }
                    dateTimeFormatter = dateTimeFormatter2;
                }
            }
            if (str.length() >= 9) {
                char charAt8 = str.charAt(4);
                if (charAt8 == 24180) {
                    dateTimeFormatter = formatter_d10_cn;
                } else if (charAt8 == 45380) {
                    dateTimeFormatter = formatter_d10_kr;
                }
            }
        }
        if (dateTimeFormatter == null) {
            return LocalDate.parse(str);
        }
        return LocalDate.parse(str, dateTimeFormatter);
    }

    /* access modifiers changed from: protected */
    public ZonedDateTime parseZonedDateTime(String str, DateTimeFormatter dateTimeFormatter) {
        if (dateTimeFormatter == null) {
            if (str.length() == 19) {
                char charAt = str.charAt(4);
                char charAt2 = str.charAt(7);
                char charAt3 = str.charAt(10);
                char charAt4 = str.charAt(13);
                char charAt5 = str.charAt(16);
                if (charAt4 == ':' && charAt5 == ':') {
                    if (charAt == '-' && charAt2 == '-') {
                        if (charAt3 == 'T') {
                            dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
                        } else if (charAt3 == ' ') {
                            dateTimeFormatter = defaultFormatter;
                        }
                    } else if (charAt == '-' && charAt2 == '-') {
                        dateTimeFormatter = defaultFormatter;
                    } else if (charAt == '/' && charAt2 == '/') {
                        dateTimeFormatter = formatter_dt19_tw;
                    } else {
                        char charAt6 = str.charAt(0);
                        char charAt7 = str.charAt(1);
                        char charAt8 = str.charAt(2);
                        char charAt9 = str.charAt(3);
                        char charAt10 = str.charAt(5);
                        if (charAt8 == '/' && charAt10 == '/') {
                            int i = ((charAt9 - '0') * 10) + (charAt - '0');
                            if (((charAt6 - '0') * 10) + (charAt7 - '0') > 12) {
                                dateTimeFormatter = formatter_dt19_eur;
                            } else if (i > 12) {
                                dateTimeFormatter = formatter_dt19_us;
                            } else {
                                String country = Locale.getDefault().getCountry();
                                if (country.equals("US")) {
                                    dateTimeFormatter = formatter_dt19_us;
                                } else if (country.equals("BR") || country.equals("AU")) {
                                    dateTimeFormatter = formatter_dt19_eur;
                                }
                            }
                        } else if (charAt8 == '.' && charAt10 == '.') {
                            dateTimeFormatter = formatter_dt19_de;
                        } else if (charAt8 == '-' && charAt10 == '-') {
                            dateTimeFormatter = formatter_dt19_in;
                        }
                    }
                }
            }
            if (str.length() >= 17) {
                char charAt11 = str.charAt(4);
                if (charAt11 == 24180) {
                    if (str.charAt(str.length() - 1) == 31186) {
                        dateTimeFormatter = formatter_dt19_cn_1;
                    } else {
                        dateTimeFormatter = formatter_dt19_cn;
                    }
                } else if (charAt11 == 45380) {
                    dateTimeFormatter = formatter_dt19_kr;
                }
            }
        }
        if (dateTimeFormatter == null) {
            return ZonedDateTime.parse(str);
        }
        return ZonedDateTime.parse(str, dateTimeFormatter);
    }

    public void write(JSONSerializer jSONSerializer, Object obj, Object obj2, Type type, int i) throws IOException {
        SerializeWriter serializeWriter = jSONSerializer.out;
        if (obj == null) {
            serializeWriter.writeNull();
            return;
        }
        if (type == null) {
            type = obj.getClass();
        }
        if (type == LocalDateTime.class) {
            int mask = SerializerFeature.UseISO8601DateFormat.getMask();
            LocalDateTime localDateTime = (LocalDateTime) obj;
            String dateFormatPattern = jSONSerializer.getDateFormatPattern();
            if ((dateFormatPattern == null && (mask & i) != 0) || jSONSerializer.isEnabled(SerializerFeature.UseISO8601DateFormat)) {
                dateFormatPattern = formatter_iso8601_pattern;
            }
            if (localDateTime.getNano() == 0 || dateFormatPattern != null) {
                if (dateFormatPattern == null) {
                    dateFormatPattern = JSON.DEFFAULT_DATE_FORMAT;
                }
                write(serializeWriter, (TemporalAccessor) localDateTime, dateFormatPattern);
            } else if (serializeWriter.isEnabled(SerializerFeature.WriteDateUseDateFormat)) {
                write(serializeWriter, (TemporalAccessor) localDateTime, JSON.DEFFAULT_DATE_FORMAT);
            } else {
                serializeWriter.writeLong(localDateTime.atZone(JSON.defaultTimeZone.toZoneId()).toInstant().toEpochMilli());
            }
        } else {
            serializeWriter.writeString(obj.toString());
        }
    }

    public void write(JSONSerializer jSONSerializer, Object obj, BeanContext beanContext) throws IOException {
        write(jSONSerializer.out, (TemporalAccessor) obj, beanContext.getFormat());
    }

    private void write(SerializeWriter serializeWriter, TemporalAccessor temporalAccessor, String str) {
        DateTimeFormatter dateTimeFormatter;
        if (str == formatter_iso8601_pattern) {
            dateTimeFormatter = formatter_iso8601;
        } else {
            dateTimeFormatter = DateTimeFormatter.ofPattern(str);
        }
        serializeWriter.writeString(dateTimeFormatter.format(temporalAccessor));
    }
}
