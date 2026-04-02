package com.fsad3.main;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.fsad3.entity.Product;
import com.fsad3.util.HibernateUtil;

public class HQLDemo {

    public static void main(String[] args) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        // Insert Sample Data
        session.persist(new Product("Laptop", "Electronics", 75000, 10));
        session.persist(new Product("Mouse", "Electronics", 500, 50));
        session.persist(new Product("Shampoo", "Personal Care", 250, 30));
        session.persist(new Product("Notebook", "Stationery", 40, 100));
        session.persist(new Product("Pen", "Stationery", 10, 0));
        session.persist(new Product("Shoes", "Fashion", 3000, 15));

        tx.commit();

        System.out.println("\n===============================");
        System.out.println("   SORT BY PRICE (ASC)");
        System.out.println("===============================");

        List<Product> ascList = session
                .createQuery("FROM Product p ORDER BY p.price ASC", Product.class)
                .list();

        for (Product p : ascList) {
            System.out.printf("%-10s | ₹%-8.2f | Qty: %-3d | %s%n",
                    p.getName(),
                    p.getPrice(),
                    p.getQuantity(),
                    p.getDescription());
        }

        System.out.println("\n===============================");
        System.out.println("   SORT BY PRICE (DESC)");
        System.out.println("===============================");

        List<Product> descList = session
                .createQuery("FROM Product p ORDER BY p.price DESC", Product.class)
                .list();

        for (Product p : descList) {
            System.out.printf("%-10s | ₹%-8.2f%n",
                    p.getName(),
                    p.getPrice());
        }

        System.out.println("\n===============================");
        System.out.println("   SORT BY QUANTITY (DESC)");
        System.out.println("===============================");

        List<Product> qtyList = session
                .createQuery("FROM Product p ORDER BY p.quantity DESC", Product.class)
                .list();

        for (Product p : qtyList) {
            System.out.printf("%-10s | Qty: %-3d%n",
                    p.getName(),
                    p.getQuantity());
        }

        System.out.println("\n===============================");
        System.out.println("   PAGINATION (First 3)");
        System.out.println("===============================");

        Query<Product> pageQuery = session.createQuery("FROM Product", Product.class);
        pageQuery.setFirstResult(0);
        pageQuery.setMaxResults(3);

        for (Product p : pageQuery.list()) {
            System.out.println(p.getName());
        }

        System.out.println("\n===============================");
        System.out.println("   AGGREGATE FUNCTIONS");
        System.out.println("===============================");

        Long total = session
                .createQuery("SELECT COUNT(p) FROM Product p", Long.class)
                .uniqueResult();

        Object[] minMax = session
                .createQuery("SELECT MIN(p.price), MAX(p.price) FROM Product p", Object[].class)
                .uniqueResult();

        System.out.println("Total Products  : " + total);
        System.out.println("Minimum Price   : ₹" + minMax[0]);
        System.out.println("Maximum Price   : ₹" + minMax[1]);

        session.close();
    }
}
