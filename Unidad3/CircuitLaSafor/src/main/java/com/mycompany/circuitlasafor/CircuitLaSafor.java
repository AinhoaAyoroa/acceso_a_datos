package com.mycompany.circuitlasafor;

/**
 *
 * @author dani
 */
public class CircuitLaSafor {

    public static void main(String[] args) {
        HibernateUtil.getSessionFactory().getCurrentSession();
        System.out.println("Hibernate funcionando!");
    }
}
