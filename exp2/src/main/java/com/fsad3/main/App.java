package com.fsad3.main;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.fsad3.entity.Product;
import com.fsad3.util.HibernateUtil;

public class App {
    public static void main(String[] args) {

        /* ================= INSERT ================= */
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Product p1 = new Product("Laptop", "Dell i5", 55000, 10);
        Product p2 = new Product("Laptop", "HP i3", 42000, 8);

        session.save(p1);
        session.save(p2);
//exp2
        tx.commit();
        session.close();

        System.out.println("----- PRODUCTS INSERTED -----");

        /* ================= RETRIEVE ================= */
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();

        int id = p1.getId();
        Product product = session.get(Product.class, id);

        if (product != null) {
            System.out.println("\n----- PRODUCT DETAILS -----");
            System.out.println("ID        : " + product.getId());
            System.out.println("Name      : " + product.getName());
            System.out.println("Desc      : " + product.getDescription());
            System.out.println("Price     : " + product.getPrice());
            System.out.println("Quantity  : " + product.getQuantity());

            /* ================= UPDATE ================= */
            product.setPrice(52000);
            product.setQuantity(15);
            session.update(product);

            System.out.println("\n----- AFTER UPDATE -----");
            System.out.println("Updated Price    : " + product.getPrice());
            System.out.println("Updated Quantity : " + product.getQuantity());
        }

        tx.commit();
        session.close();

        /* ================= DELETE ================= */
        session = HibernateUtil.getSessionFactory().openSession();
        tx = session.beginTransaction();

        int deleteId = p2.getId();
        Product del = session.get(Product.class, deleteId);

        if (del != null) {
            session.delete(del);
            System.out.println("\n----- DELETE -----");
            System.out.println("Product with ID " + deleteId + " deleted successfully");
        }

        tx.commit();
        session.close();
    }
}
