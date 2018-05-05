package com.example.taegyunkim.qrcode;

import com.microsoft.windowsazure.mobileservices.table.DateTimeOffset;

/**
 * Created by cjw94 on 2018-05-02.
 */

public class CheckTable {

    @com.google.gson.annotations.SerializedName("id")
    private String id;

    @com.google.gson.annotations.SerializedName("date")
    private String date;

    @com.google.gson.annotations.SerializedName("hwaehwa_left_marks")
    private String hwaehwa_left_marks;

    @com.google.gson.annotations.SerializedName("hwaehwa_right_marks")
    private String hwaehwa_right_marks;

    @com.google.gson.annotations.SerializedName("hwaehwa_kildal_marks")
    private String hwaehwa_kildal_marks;

    @com.google.gson.annotations.SerializedName("hotplate_hwaehwaside_marks")
    private String hotplate_hwaehwaside_marks;

    @com.google.gson.annotations.SerializedName("hotplate_jaedang_left_marks")
    private String hotplate_jaedang_left_marks;

    @com.google.gson.annotations.SerializedName("hotplate_jaedang_right_marks")
    private String hotplate_jaedang_right_marks;

    @com.google.gson.annotations.SerializedName("hotplate_jeonboon_6_marks")
    private String hotplate_jeonboon_6_marks;

    @com.google.gson.annotations.SerializedName("waterbath_chungsin_marks")
    private String waterbath_chungsin_marks;

    @com.google.gson.annotations.SerializedName("waterbath_advantec_marks")
    private String waterbath_advantec_marks;

    @com.google.gson.annotations.SerializedName("waterbath_gagong_marks")
    private String waterbath_gagong_marks;

    @com.google.gson.annotations.SerializedName("aas_marks")
    private String aas_marks;

    @com.google.gson.annotations.SerializedName("autoclave_marks")
    private String autoclave_marks;

    @com.google.gson.annotations.SerializedName("flammable_marks")
    private String flammable_marks;

    @com.google.gson.annotations.SerializedName("hwaehwa_left")
    private int hwaehwa_left;

    @com.google.gson.annotations.SerializedName("hwaehwa_right")
    private int hwaehwa_right;

    @com.google.gson.annotations.SerializedName("hwaehwa_kildal")
    private int hwaehwa_kildal;

    @com.google.gson.annotations.SerializedName("hotplate_hwaehwaside")
    private int hotplate_hwaehwaside;

    @com.google.gson.annotations.SerializedName("hotplate_jaedang_left")
    private int hotplate_jaedang_left;

    @com.google.gson.annotations.SerializedName("hotplate_jaedang_right")
    private int hotplate_jaedang_right;

    @com.google.gson.annotations.SerializedName("hotplate_jeonboon_6")
    private int hotplate_jeonboon_6;

    @com.google.gson.annotations.SerializedName("waterbath_chungsin")
    private int waterbath_chungsin;

    @com.google.gson.annotations.SerializedName("waterbath_advantec")
    private int waterbath_advantec;

    @com.google.gson.annotations.SerializedName("waterbath_gagong")
    private int waterbath_gagong;

    @com.google.gson.annotations.SerializedName("aas")
    private int aas;

    @com.google.gson.annotations.SerializedName("autoclave")
    private int autoclave;

    @com.google.gson.annotations.SerializedName("flammable")
    private int flammable;


    public CheckTable() {
    }

    public CheckTable(String id, String date, String hwaehwa_left_marks, String hwaehwa_right_marks, String hwaehwa_kildal_marks,
                      String hotplate_hwaehwaside_marks, String hotplate_jaedang_left_marks, String hotplate_jaedang_right_marks, String hotplate_jeonboon_6_marks,
                      String waterbath_chungsin_marks, String waterbath_advantec_marks, String waterbath_gagong_marks,
                      String aas_marks, String autoclave_marks, String flammable_marks,
                      int hwaehwa_left, int hwaehwa_right, int hwaehwa_kildal,
                      int hotplate_hwaehwaside, int hotplate_jaedang_left, int hotplate_jaedang_right, int hotplate_jeonboon_6,
                      int waterbath_chungsin, int waterbath_advantec, int waterbath_gagong,
                      int aas, int autoclave, int flammable) {
        this.id = id;
        this.date = date;
        this.hwaehwa_left_marks = hwaehwa_left_marks;
        this.hwaehwa_right_marks = hwaehwa_right_marks;
        this.hwaehwa_kildal_marks = hwaehwa_kildal_marks;
        this.hotplate_hwaehwaside_marks = hotplate_hwaehwaside_marks;
        this.hotplate_jaedang_left_marks = hotplate_jaedang_left_marks;
        this.hotplate_jaedang_right_marks = hotplate_jaedang_right_marks;
        this.hotplate_jeonboon_6_marks = hotplate_jeonboon_6_marks;
        this.waterbath_chungsin_marks = waterbath_chungsin_marks;
        this.waterbath_advantec_marks = waterbath_advantec_marks;
        this.waterbath_gagong_marks = waterbath_gagong_marks;
        this.aas_marks = aas_marks;
        this.autoclave_marks = autoclave_marks;
        this.flammable_marks = flammable_marks;
        this.hwaehwa_left = hwaehwa_left;
        this.hwaehwa_right = hwaehwa_right;
        this.hwaehwa_kildal = hwaehwa_kildal;
        this.hotplate_hwaehwaside = hotplate_hwaehwaside;
        this.hotplate_jaedang_left = hotplate_jaedang_left;
        this.hotplate_jaedang_right = hotplate_jaedang_right;
        this.hotplate_jeonboon_6 = hotplate_jeonboon_6;
        this.waterbath_chungsin = waterbath_chungsin;
        this.waterbath_advantec = waterbath_advantec;
        this.waterbath_gagong = waterbath_gagong;
        this.aas = aas;
        this.autoclave = autoclave;
        this.flammable = flammable;
    }

    public String getId() { return id;}
    public final void setId(String id) { this.id = id;}

    public String getDate() {
        return date;
    }

    public final void setDate(String date) {
        this.date = date;
    }

    public String getHwaehwa_left_marks() {
        return hwaehwa_left_marks;
    }

    public final void setHwaehwa_left_marks(String hwaehwa_left_marks) {
        this.hwaehwa_left_marks = hwaehwa_left_marks;
    }

    public String getHwaehwa_right_marks() {
        return hwaehwa_right_marks;
    }

    public final void setHwaehwa_right_marks(String hwaehwa_right_marks) {
        this.hwaehwa_right_marks = hwaehwa_right_marks;
    }

    public String getHwaehwa_kildal_marks() {
        return hwaehwa_kildal_marks;
    }

    public final void setHwaehwa_kildal_marks(String hwaehwa_kildal_marks) {
        this.hwaehwa_kildal_marks = hwaehwa_kildal_marks;
    }

    public String getHotplate_jaedang_left_marks() {
        return hotplate_jaedang_left_marks;
    }

    public final void setHotplate_jaedang_left_marks(String hotplate_jaedang_left_marks) {
        this.hotplate_jaedang_left_marks = hotplate_jaedang_left_marks;
    }

    public String getHotplate_jaedang_right_marks() {
        return hotplate_jaedang_right_marks;
    }

    public final void setHotplate_jaedang_right_marks(String hotplate_jaedang_right_marks) {
        this.hotplate_jaedang_right_marks = hotplate_jaedang_right_marks;
    }

    public String getHotplate_hwaehwaside_marks() {
        return hotplate_hwaehwaside_marks;
    }

    public final void setHotplate_hwaehwaside_marks(String hotplate_hwaehwaside_marks) {
        this.hotplate_hwaehwaside_marks = hotplate_hwaehwaside_marks;
    }

    public String getHotplate_jeonboon_6_marks() {
        return hotplate_jeonboon_6_marks;
    }

    public final void setHotplate_jeonboon_6_marks(String hotplate_jeonboon_6_marks) {
        this.hotplate_jeonboon_6_marks = hotplate_jeonboon_6_marks;
    }

    public String getWaterbath_chungsin_marks() {
        return waterbath_chungsin_marks;
    }

    public final void setWaterbath_chungsin_marks(String waterbath_chungsin_marks) {
        this.waterbath_chungsin_marks = waterbath_chungsin_marks;
    }

    public String getWaterbath_advantec_marks() {
        return waterbath_advantec_marks;
    }

    public final void setWaterbath_advantec_marks(String waterbath_advantec_marks) {
        this.waterbath_advantec_marks = waterbath_advantec_marks;
    }

    public String getWaterbath_gagong_marks() {
        return waterbath_gagong_marks;
    }

    public final void setWaterbath_gagong_marks(String waterbath_gagong_marks) {
        this.waterbath_gagong_marks = waterbath_gagong_marks;
    }

    public String getAas_marks() {
        return aas_marks;
    }

    public final void setAas_marks(String aas_marks) {
        this.aas_marks = aas_marks;
    }

    public String getAutoclave_marks() {
        return autoclave_marks;
    }

    public final void setAutoclave_marks(String autoclave_marks) {
        this.autoclave_marks = autoclave_marks;
    }

    public String getFlammable_marks() {
        return flammable_marks;
    }

    public final void setFlammable_marks(String flammable_marks) {
        this.flammable_marks = flammable_marks;
    }

    public int getHwaehwa_left() { return hwaehwa_left;}
    public final void setHwaehwa_left(int hwaehwa_left) { this.hwaehwa_left = hwaehwa_left;}

    public int getHwaehwa_right() { return hwaehwa_right; }
    public final void setHwaehwa_right(int hwaehwa_right) { this.hwaehwa_right = hwaehwa_right;}

    public int getHwaehwa_kildal() { return hwaehwa_kildal; }
    public final void setHwaehwa_kildal(int hwaehwa_kildal) { this.hwaehwa_kildal=hwaehwa_kildal; }

    public int getHotplate_hwaehwaside() { return hotplate_hwaehwaside;}
    public final void setHotplate_hwaehwaside(int hotplate_hwaehwaside) { this.hotplate_hwaehwaside = hotplate_hwaehwaside; }

    public int getHotplate_jaedang_left() {return hotplate_jaedang_left;}
    public final void setHotplate_jaedang_left(int hotplate_jaedang_left) { this.hotplate_jaedang_left=hotplate_jaedang_left;}

    public int getHotplate_jaedang_right() { return hotplate_jaedang_right;}
    public final void setHotplate_jaedang_right(int hotplate_jaedang_right) { this.hotplate_jaedang_right = hotplate_jaedang_right;}

    public int getHotplate_jeonboon_6() { return hotplate_jeonboon_6;}
    public final void setHotplate_jeonboon_6(int hotplate_jeonboon_6) { this.hotplate_jeonboon_6 = hotplate_jeonboon_6; }

    public int getWaterbath_chungsin() { return waterbath_chungsin;}
    public final void setWaterbath_chungsin(int waterbath_chungsin) { this.waterbath_chungsin = waterbath_chungsin;}

    public int getWaterbath_advantec() { return waterbath_advantec;}
    public final void setWaterbath_advantec(int waterbath_advantec) { this.waterbath_advantec= waterbath_advantec;}

    public int getWaterbath_gagong() { return waterbath_gagong;}
    public final void setWaterbath_gagong(int waterbath_gagong) { this.waterbath_gagong=waterbath_gagong;}

    public int getAas() { return aas;}
    public final void setAas(int aas) { this.aas = aas;}

    public int getAutoclave() { return autoclave;}
    public final void setAutoclave(int autoclave) { this.autoclave= autoclave;}

    public int getFlammable() { return flammable;}
    public final void setFlammable(int flammable) { this.flammable= flammable;}
}
