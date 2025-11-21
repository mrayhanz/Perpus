package backend;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Lenovo
 */

import java.util.ArrayList;
import java.sql.*;

public class Anggota {
    private int idanggota;
    private String nama;
    private String alamat;
    private String telepon;

    public Anggota() {}
    public Anggota(String nama, String alamat, String telepon) {
        this.nama = nama; this.alamat = alamat; this.telepon = telepon;
    }

    public int getIdanggota() { return idanggota; }
    public void setIdanggota(int idanggota) { this.idanggota = idanggota; }
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
    public String getAlamat() { return alamat; }
    public void setAlamat(String alamat) { this.alamat = alamat; }
    public String getTelepon() { return telepon; }
    public void setTelepon(String telepon) { this.telepon = telepon; }

    public Anggota getById(int id) {
        Anggota a = new Anggota();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM anggota WHERE idanggota = " + id);
        try {
            if (rs.next()) {
                a.setIdanggota(rs.getInt("idanggota"));
                a.setNama(rs.getString("nama"));
                a.setAlamat(rs.getString("alamat"));
                a.setTelepon(rs.getString("telepon"));
            }
            rs.getStatement().close();
            rs.close();
        } catch (Exception e) { e.printStackTrace(); }
        return a;
    }

    public ArrayList<Anggota> getAll() {
        ArrayList<Anggota> list = new ArrayList<>();
        ResultSet rs = DBHelper.selectQuery("SELECT * FROM anggota");
        try {
            while (rs.next()) {
                Anggota a = new Anggota();
                a.setIdanggota(rs.getInt("idanggota"));
                a.setNama(rs.getString("nama"));
                a.setAlamat(rs.getString("alamat"));
                a.setTelepon(rs.getString("telepon"));
                list.add(a);
            }
            rs.getStatement().close();
            rs.close();
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public ArrayList<Anggota> search(String keyword) {
        ArrayList<Anggota> list = new ArrayList<>();
        String sql = "SELECT * FROM anggota WHERE nama LIKE '%" + keyword + "%' OR alamat LIKE '%" + keyword + "%'";
        ResultSet rs = DBHelper.selectQuery(sql);
        try {
            while (rs.next()) {
                Anggota a = new Anggota();
                a.setIdanggota(rs.getInt("idanggota"));
                a.setNama(rs.getString("nama"));
                a.setAlamat(rs.getString("alamat"));
                a.setTelepon(rs.getString("telepon"));
                list.add(a);
            }
            rs.getStatement().close();
            rs.close();
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public void save() {
        if (this.idanggota == 0) {
            String sql = "INSERT INTO anggota (nama, alamat, telepon) VALUES ('" + this.nama + "', '" + this.alamat + "', '" + this.telepon + "')";
            int id = DBHelper.insertQueryGetId(sql);
            this.idanggota = id;
        } else {
            String sql = "UPDATE anggota SET nama = '" + this.nama + "', alamat = '" + this.alamat + "', telepon = '" + this.telepon + "' WHERE idanggota = " + this.idanggota;
            DBHelper.executeQuery(sql);
        }
    }

    public void delete() {
        String sql = "DELETE FROM anggota WHERE idanggota = " + this.idanggota;
        DBHelper.executeQuery(sql);
    }
}
