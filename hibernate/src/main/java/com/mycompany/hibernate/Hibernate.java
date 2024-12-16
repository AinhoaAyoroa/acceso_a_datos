

package com.mycompany.hibernate;


/**
 *
 * @author noa
 */
public class Hibernate {

    public static void main(String[] args) {
        HibernateUtil.getSessionFactory().getCurrentSession();
        System.out.println("Hibernate funcionando");
    }
}
