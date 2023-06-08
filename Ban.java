/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package POJO;

/**
 *
 * @author Admin
 */
public class Ban {

    private int soBan;
    private int soGhe;
    private String khuVuc;
    private boolean TrangThai;

    public Ban() {
    }

    public Ban(int soBan) {
        this.soBan = soBan;
    }

    public Ban(int soBan, int soGhe, String khuVuc, boolean TrangThai) {
        this.soBan = soBan;
        this.soGhe = soGhe;
        this.khuVuc = khuVuc;
        this.TrangThai = TrangThai;
    }

    public Ban(Ban ban) {
        this.soBan = ban.soBan;
        this.soGhe = ban.soGhe;
        this.khuVuc = ban.khuVuc;
        this.TrangThai = ban.TrangThai;
    }

    public int getSoBan() {
        return soBan;
    }

    public void setSoBan(int soBan) {
        this.soBan = soBan;
    }

    public int getSoGhe() {
        return soGhe;
    }

    public void setSoGhe(int soGhe) {
        this.soGhe = soGhe;
    }

    public String getKhuVuc() {
        return khuVuc;
    }

    public void setKhuVuc(String khuVuc) {
        this.khuVuc = khuVuc;
    }

    public boolean getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    @Override
    public String toString() {
        return String.valueOf(getSoBan());
    }


}
