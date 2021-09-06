package com.codegym.demo.constant;

public class Constant {
    public static String IMAGE_USER_DEFAULT = "https://firebasestorage.googleapis.com/v0/b/datn-7f8ad.appspot.com/o/person-icon.png?alt=media&token=56ae2587-5cc6-4870-921d-3dac542462b0";
    public static String IMAGE_COMPANY_DEFAULT = "https://firebasestorage.googleapis.com/v0/b/datn-7f8ad.appspot.com/o/company-building-icon.png?alt=media&token=759c3505-276b-4e01-b17d-4f6a106964dd";

    public enum UserStatus {
        ACTIVATE,
        INACTIVATE
    }
    public enum MerchantStatus {
        ACTIVATE,
        INACTIVATE
    }

    public enum FoodStatus {
        SERVING,
        STOP,
    }

    public enum TypeName {
        ADMIN,
        COMPANY,
        USER,
    }
}
