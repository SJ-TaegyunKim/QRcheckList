package com.example.taegyunkim.qrcode.Etc;


// 싱글톤 패턴
public class Singleton {
    private static volatile Singleton instance = null;

    private boolean checkFail;
    private boolean dateCheck;

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

    public boolean getDateCheck() { return this.dateCheck; }

    public void setDateCheck(boolean dateCheck) { this.dateCheck = dateCheck; }

}