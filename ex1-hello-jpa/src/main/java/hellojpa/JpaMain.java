package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        //트랜잭션 안에서 작업이 일어남
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            //테이블이 아니고 객체를 다 가져와
            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(5)
                    .setMaxResults(8)
                    .getResultList();
            for (Member member : result) {
                System.out.println("member.name = " + member.getName());
            }
            //em.persist(member);

            tx.commit();
        }catch (Exception e){
            tx.rollback();
        } finally {
            //꼭 해야함 물고 있으면 안됨
           em.close();
        }

        emf.close();
    }
}
