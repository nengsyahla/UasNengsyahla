package com.UAs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class CRUD {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/katalog_product";
    static final String USER = "root";
    static final String PASS = "";

    static Connection conn;
    static Statement stmt;
    static ResultSet rs;
    static InputStreamReader inputStreamReader = new InputStreamReader(System.in);
    static BufferedReader input = new BufferedReader(inputStreamReader);

    // METHOD TAMPILKAN DATA //
    static void TampilkanData() {

        String sql = "SELECT * FROM product";

        try {
            rs = stmt.executeQuery(sql);

            System.out.println("==================== PRODUCT ====================");

            while (rs.next()) {
                String id = rs.getString("id");
                String category = rs.getString("category");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String price = rs.getString("price");
                String status = rs.getString("status");

                System.out.println(String.format("%s              %s             %s          %s           %s           %s",id,category,name,description,price,status));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // METHOD INSERT//
    public static void MasukanData() {
        try {
            // ambil input dari user

            System.out.print("id            : ");
            String id = input.readLine();
            System.out.print("category      : ");
            String category = input.readLine().trim();
            System.out.print("name          : ");
            String name = input.readLine().trim();
            System.out.print("description   : ");
            String description = input.readLine().trim();
            System.out.print("price         :");
            String price = input.readLine().trim();
            System.out.print("status        :");
            String status = input.readLine().trim();

            // query simpan
            String sql = "INSERT INTO product (id,category,name,description,price,status) VALUES('%s','%s', '%s','%s','%s','%s')";
            sql = String.format(sql,id,category,name,description,price,status);

            // simpan
            stmt.execute(sql);
            System.out.println("Data berhasil d insert... ");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // METHOD UPDATE //
    static void EditData() {
        try {

            // ambil input dari user
            System.out.print("ID yang mau diedit: ");
            String id = (input.readLine().trim());
            System.out.print("Name    : ");
            String name = input.readLine().trim();
            System.out.print("price   : ");
            String price = input.readLine().trim();

            // query update
            String sql = "UPDATE product SET name='%s', price='%s' WHERE id=%s";
            sql = String.format(sql,name,price,id);

            // update data 
            stmt.execute(sql);
            System.out.println("Data Berhasil di update...");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // METHOD DELETE//
    static void HapusData() {
        try {

            // ambil input dari user
            System.out.print("ID yang mau dihapus: ");
            int id = Integer.parseInt(input.readLine());

            // buat query hapus
            String sql = String.format("DELETE FROM product WHERE id=%d", id);

            // hapus data
            stmt.execute(sql);

            System.out.println("Data telah terhapus...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        try {
            // register driver
            Class.forName(JDBC_DRIVER);

            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            while (!conn.isClosed()) {
                Menu();
            }

            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    static void Menu() {
        System.out.println("\n=== PEMROGRAMAN BERORIENTASI OBJECT ===");
        System.out.println("********************************");
        System.out.println("||1. Masukan Data           ||");        
        System.out.println("||2. Tampilkan Data         ||");
        System.out.println("||3. Edit Data              ||");
        System.out.println("||4. Hapus Data             ||");
        System.out.println("||0. Keluar                 ||");
        System.out.println("*********************************");
        System.out.println("");
        System.out.print("Masukan Pilihan Anda : ");

        try {
            int pilihan = Integer.parseInt(input.readLine());

            if (pilihan == 1) {
                MasukanData();
            } else if (pilihan == 2) {
                TampilkanData();
            } else if (pilihan == 3) {
                EditData();
            } else if (pilihan == 4) {
                HapusData();
            } else if (pilihan == 0) {
                System.exit(0);
            } else {
                System.out.println("Pilihan salah. Silahkan masukan ulang pilihan anda!");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
