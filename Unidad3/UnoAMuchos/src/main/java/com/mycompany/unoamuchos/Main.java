package com.mycompany.unoamuchos;

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
