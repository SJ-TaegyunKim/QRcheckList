package com.example.taegyunkim.qrcode.Etc;


// 싱글톤 패턴
public class Singleton {
    private static volatile Singleton instance = null;

    private boolean checkFail;
    private String checkEdit;
    // 회화로
    private boolean hwaehwa_left;
    private String hwaehwa_left_remarks;
    private boolean hwaehwa_right;
    private String hwaehwa_right_remarks;
    private boolean hwaehwa_kildal;
    private String hwaehwa_kildal_remarks;

    // Hot Plate
    private boolean hotplate_hwaehwaside;
    private String hotplate_hwaehwaside_remarks;
    private boolean hotplate_jaedang_left;
    private String hotplate_jaedang_left_remarks;
    private boolean hotplate_jaedang_right;
    private String hotplate_jaedang_right_remarks;
    private boolean hotplate_jeonboon_6;
    private String hotplate_jeonboon_6_remarks;

    // WaterBath
    private boolean waterbath_chungsin;
    private String waterbath_chungsin_remarks;
    private boolean waterbath_advantec;
    private String waterbath_advantec_remarks;
    private boolean waterbath_gagong;
    private String waterbath_gagong_remarks;

    // AAS
    private boolean aas;
    private String aas_remarks;
    private boolean autoclave;
    private String autoclave_remarks;

    // Flammable
    private boolean flammable;
    private String flammable_remarks;


    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    // checkFail Get, Set
    public boolean getCheckFail() {
        return this.checkFail;
    }

    public void setCheckFail(boolean checkFail) {
        this.checkFail = checkFail;
    }

    public String getCheckEdit() {
        return this.checkEdit;
    }

    public void setCheckEdit(String checkEdit) {
        this.checkEdit = checkEdit;
    }

    // Hwaehwa Get, Set
    public boolean getHwaehwa_left() {
        return this.hwaehwa_left;
    }

    public void setHwaehwa_left(boolean hwaehwa_left) {
        this.hwaehwa_left = hwaehwa_left;
    }

    public String getHwaehwa_left_remarks() {
        return this.hwaehwa_left_remarks;
    }

    public void setHwaehwa_left_remarks(String hwaehwa_left_remarks) {
        this.hwaehwa_left_remarks = hwaehwa_left_remarks;
    }

    public boolean getHwaehwa_right() {
        return this.hwaehwa_right;
    }

    public void setHwaehwa_right(boolean hwaehwa_right) {
        this.hwaehwa_right = hwaehwa_right;
    }

    public String getHwaehwa_right_remarks() {
        return this.hwaehwa_right_remarks;
    }

    public void setHwaehwa_right_remarks(String hwaehwa_right_remarks) {
        this.hwaehwa_right_remarks = hwaehwa_right_remarks;
    }

    public boolean getHwaehwa_kildal() {
        return this.hwaehwa_kildal;
    }

    public void setHwaehwa_kildal(boolean hwaehwa_kildal) {
        this.hwaehwa_kildal = hwaehwa_kildal;
    }

    public String getHwaehwa_kildal_remarks() {
        return this.hwaehwa_kildal_remarks;
    }

    public void setHwaehwa_kildal_remarks(String hwaehwa_kildal_remarks) {
        this.hwaehwa_kildal_remarks = hwaehwa_kildal_remarks;
    }

    // Hot Plate
    public boolean getHotplate_hwaehwaside() {
        return this.hotplate_hwaehwaside;
    }

    public void setHotplate_hwaehwaside(boolean hotplate_hwaehwaside) {
        this.hotplate_hwaehwaside = hotplate_hwaehwaside;
    }

    public String getHotplate_hwaehwaside_remarks() {
        return this.hotplate_hwaehwaside_remarks;
    }

    public void setHotplate_hwaehwaside_remarks(String hotplate_hwaehwaside_remarks) {
        this.hotplate_hwaehwaside_remarks = hotplate_hwaehwaside_remarks;
    }

    public boolean getHotplate_jaedang_left() {
        return this.hotplate_jaedang_left;
    }

    public void setHotplate_jaedang_left(boolean hotplate_jaedang_left) {
        this.hotplate_jaedang_left = hotplate_jaedang_left;
    }

    public String getHotplate_jaedang_left_remarks() {
        return this.hotplate_jaedang_left_remarks;
    }

    public void setHotplate_jaedang_left_remarks(String hotplate_jaedang_left_remarks) {
        this.hotplate_jaedang_left_remarks = hotplate_jaedang_left_remarks;
    }

    public boolean getHotplate_jaedang_right() {
        return this.hotplate_jaedang_right;
    }

    public void setHotplate_jaedang_right(boolean hotplate_jaedang_right) {
        this.hotplate_jaedang_right = hotplate_jaedang_right;
    }

    public String getHotplate_jaedang_right_remarks() {
        return this.hotplate_jaedang_right_remarks;
    }

    public void setHotplate_jaedang_right_remarks(String hotplate_jaedang_right_remarks) {
        this.hotplate_jaedang_right_remarks = hotplate_jaedang_right_remarks;
    }

    public boolean getHotplate_jeonboon_6() {
        return this.hotplate_jeonboon_6;
    }

    public void setHotplate_jeonboon_6(boolean hotplate_jeonboon_6) {
        this.hotplate_jeonboon_6 = hotplate_jeonboon_6;
    }

    public String getHotplate_jeonboon_6_remarks() {
        return this.hotplate_jeonboon_6_remarks;
    }

    public void setHotplate_jeonboon_6_remarks(String hotplate_jeonboon_6_remarks) {
        this.hotplate_jeonboon_6_remarks = hotplate_jeonboon_6_remarks;
    }

    // WaterBath
    public boolean getWaterbath_chungsin() {
        return this.waterbath_chungsin;
    }

    public void setWaterbath_chungsin(boolean waterbath_chungsin) {
        this.waterbath_chungsin = waterbath_chungsin;
    }

    public String getWaterbath_chungsin_remarks() {
        return this.waterbath_chungsin_remarks;
    }

    public void setWaterbath_chungsin_remarks(String waterbath_chungsin_remarks) {
        this.waterbath_chungsin_remarks = waterbath_chungsin_remarks;
    }

    public boolean getWaterbath_advantec() {
        return this.waterbath_advantec;
    }

    public void setwaterbath_advantec(boolean waterbath_advantec) {
        this.waterbath_advantec = waterbath_advantec;
    }

    public String getWaterbath_advantec_remarks() {
        return this.waterbath_advantec_remarks;
    }

    public void setWaterbath_advantec_remarks(String waterbath_advantec_remarks) {
        this.waterbath_advantec_remarks = waterbath_advantec_remarks;
    }

    public boolean getWaterbath_gagong() {
        return this.waterbath_gagong;
    }

    public void setWaterbath_gagong(boolean waterbath_gagong) {
        this.waterbath_gagong = waterbath_gagong;
    }

    public String getWaterbath_gagong_remarks() {
        return this.waterbath_gagong_remarks;
    }

    public void setWaterbath_gagong_remarks(String waterbath_gagong_remarks) {
        this.waterbath_gagong_remarks = waterbath_gagong_remarks;
    }

    // aas
    public boolean getAas() {
        return this.aas;
    }

    public void setAas(boolean aas) {
        this.aas = aas;
    }

    public String getAas_remarks() {
        return this.aas_remarks;
    }

    public void setAas_remarks(String aas_remarks) {
        this.aas_remarks = aas_remarks;
    }

    public boolean getAutoclave() {
        return this.autoclave;
    }

    public void setAutoclave(boolean autoclave) {
        this.autoclave = autoclave;
    }

    public String getAutoclave_remarks() {
        return this.autoclave_remarks;
    }

    public void setAutoclave_remarks(String autoclave_remarks) {
        this.autoclave_remarks = autoclave_remarks;
    }

    // flammable
    public boolean getFlammable() {
        return this.flammable;
    }

    public void setFlammable(boolean flammable) {
        this.flammable = flammable;
    }

    public String getFlammable_remarks() {
        return this.flammable_remarks;
    }

    public void setFlammable_remarks(String flammable_remarks) {
        this.flammable_remarks = flammable_remarks;
    }
}