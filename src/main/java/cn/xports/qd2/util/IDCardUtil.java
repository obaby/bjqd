package cn.xports.qd2.util;

import android.util.Log;
import cn.xports.base.Constant;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

public class IDCardUtil {
    private static final String BIRTH_DATE_FORMAT = "yyyyMMdd";
    private static final Date MIN_BIRTH_DATE = new Date(-2209017600000L);
    private static final int NEW_CARD_NUMBER_LENGTH = 18;
    private static final int OLD_CARD_NUMBER_LENGTH = 15;
    private static final char[] VERIFY_CODE = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};
    private static final int[] VERIFY_CODE_WEIGHT = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
    private Date cacheBirthDate = null;
    private Boolean cacheValidateResult = null;
    private final String cardNumber;
    private Date currentDate = new Date();

    public IDCardUtil(String str) {
        if (str != null) {
            str = str.trim();
            if (15 == str.length()) {
                str = contertToNewCardNumber(str);
            }
        }
        this.cardNumber = str;
    }

    public boolean validate() {
        if (this.cacheValidateResult == null) {
            boolean z = false;
            try {
                boolean z2 = (this.cardNumber != null) && 18 == this.cardNumber.length();
                for (int i = 0; i < 17; i++) {
                    char charAt = this.cardNumber.charAt(i);
                    z2 = z2 && charAt >= '0' && charAt <= '9';
                }
                boolean z3 = z2 && calculateVerifyCode(this.cardNumber) == this.cardNumber.charAt(17);
                Date birthDate = getBirthDate();
                boolean z4 = ((z3 && birthDate != null) && birthDate.before(this.currentDate)) && birthDate.after(MIN_BIRTH_DATE);
                String birthDayPart = getBirthDayPart();
                String format = createBirthDateParser().format(birthDate);
                if (z4 && birthDayPart.equals(format)) {
                    z = true;
                }
            } catch (Exception e) {
                Log.e("方法执行失败:validate()", e.toString());
            }
            this.cacheValidateResult = Boolean.valueOf(z);
        }
        return this.cacheValidateResult.booleanValue();
    }

    private static Hashtable<String, String> GetAreaCode() {
        Hashtable<String, String> hashtable = new Hashtable<>();
        hashtable.put(Constant.ECARD_PAY, "北京");
        hashtable.put(Constant.DEPOSIT_PAY, "天津");
        hashtable.put("13", "河北");
        hashtable.put("14", "山西");
        hashtable.put("15", "内蒙古");
        hashtable.put("21", "辽宁");
        hashtable.put(Constant.ALI_PAY, "吉林");
        hashtable.put("23", "黑龙江");
        hashtable.put("31", "上海");
        hashtable.put("32", "江苏");
        hashtable.put("33", "浙江");
        hashtable.put("34", "安徽");
        hashtable.put("35", "福建");
        hashtable.put("36", "江西");
        hashtable.put("37", "山东");
        hashtable.put("41", "河南");
        hashtable.put("42", "湖北");
        hashtable.put("43", "湖南");
        hashtable.put("44", "广东");
        hashtable.put("45", "广西");
        hashtable.put("46", "海南");
        hashtable.put("50", "重庆");
        hashtable.put("51", "四川");
        hashtable.put("52", "贵州");
        hashtable.put("53", "云南");
        hashtable.put("54", "西藏");
        hashtable.put("61", "陕西");
        hashtable.put("62", "甘肃");
        hashtable.put("63", "青海");
        hashtable.put("64", "宁夏");
        hashtable.put("65", "新疆");
        hashtable.put("71", "台湾");
        hashtable.put("81", "香港");
        hashtable.put("82", "澳门");
        hashtable.put("91", "国外");
        return hashtable;
    }

    public String getAddressCode() {
        checkIfValid();
        Hashtable<String, String> GetAreaCode = GetAreaCode();
        if (GetAreaCode.get(this.cardNumber.substring(0, 2)) != null) {
            return GetAreaCode.get(this.cardNumber.substring(0, 2));
        }
        throw new RuntimeException("身地区编码不正确！");
    }

    public Date getBirthDate() {
        if (this.cacheBirthDate == null) {
            try {
                this.cacheBirthDate = createBirthDateParser().parse(getBirthDayPart());
            } catch (ParseException e) {
                Log.e("解析生日失败!", e.toString());
            } catch (Exception e2) {
                Log.e("解析生日失败!", e2.toString());
            }
        }
        return new Date(this.cacheBirthDate.getTime());
    }

    public boolean isMale() {
        return 1 == getGenderCode();
    }

    public boolean isFemal() {
        return !isMale();
    }

    private int getGenderCode() {
        checkIfValid();
        return (this.cardNumber.charAt(16) - '0') & 1;
    }

    private String getBirthDayPart() {
        return this.cardNumber.substring(6, 14);
    }

    private SimpleDateFormat createBirthDateParser() {
        return new SimpleDateFormat(BIRTH_DATE_FORMAT);
    }

    private void checkIfValid() {
        if (!validate()) {
            throw new RuntimeException("身份证号码不正确！");
        }
    }

    private static char calculateVerifyCode(CharSequence charSequence) {
        int i = 0;
        for (int i2 = 0; i2 < 17; i2++) {
            i += (charSequence.charAt(i2) - '0') * VERIFY_CODE_WEIGHT[i2];
        }
        return VERIFY_CODE[i % 11];
    }

    private static String contertToNewCardNumber(String str) {
        StringBuilder sb = new StringBuilder(18);
        sb.append(str.substring(0, 6));
        sb.append("19");
        sb.append(str.substring(6));
        sb.append(calculateVerifyCode(sb));
        return sb.toString();
    }

    public String getCardNumber() {
        return this.cardNumber;
    }

    public static Date strToDate(String str, String str2) {
        if (str == null) {
            return null;
        }
        if (str2 == null) {
            str2 = "yyyy-MM-dd";
        }
        try {
            return new SimpleDateFormat(str2).parse(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean checkIDCard(String str) {
        return new IDCardUtil(str).validate();
    }
}
