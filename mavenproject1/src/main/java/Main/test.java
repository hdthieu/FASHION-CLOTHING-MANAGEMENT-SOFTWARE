/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Main;

//import GUI.GUI_HomeMain;
//import GUI.Login;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

/**
 *
 * @author TriHieu
 */
public class test {

//    public static void main(String[] args) {
//        Login lg = new Login();
//        lg.setVisible(true);
//        GUI_HomeMain d = new GUI_HomeMain(lg.getUsername());
//        GUI_HomeMain d = new GUI_HomeMain("nguyenvan");
//        d.setLocationRelativeTo(null);
//        d.setVisible(true);
//        lg.setLocationRelativeTo(null);
//        lg.setVisible(true);
//    }
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-mssql");
        EntityManager em = emf.createEntityManager();
        EntityTransaction et = em.getTransaction();
        try {
            et.begin();
            et.commit();
        } catch (Exception e) {
            e.printStackTrace();
            et.rollback();
            // TODO: handle exception
        }
        emf.close();
    }
}
