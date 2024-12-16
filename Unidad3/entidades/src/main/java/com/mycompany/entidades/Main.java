package com.mycompany.entidades;


/**
 *
 * @author noa
 */
public class Main {

    public static void main(String[] args) {
        HibernateUtil.getSessionFactory().getCurrentSession();
        System.out.println("Hibernate funcionando!");
    }
}
