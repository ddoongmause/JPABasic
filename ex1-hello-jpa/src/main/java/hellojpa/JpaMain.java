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
            /*List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(5)
                    .setMaxResults(8)
                    .getResultList();
            for (Member member : result) {
                System.out.println("member.name = " + member.getName());
            }*/
            //em.persist(member);
            /*//비영속
            Member member = new Member();
            member.setId(101L);
            member.setName("HelloJPA");

            //영속
            System.out.println("BEFORE");
            em.persist(member);
            System.out.println("AFTER");
            */
            /*Member findMember1 = em.find(Member.class, 101L);
            Member findMember2 = em.find(Member.class, 101L);*/
            /*Member member1 = new Member(150L, "A");
            Member member2 = new Member(160L, "B");*/

            //System.out.println("findMember.id = " + findMember1.getId());
            //System.out.println("findMember.name = " + findMember1.getName());

            //System.out.println("result = " + (findMember1 == findMember2));

            /*em.persist(member1);
            em.persist(member2);
            System.out.println("=========");*/


           /* Member member = em.find(Member.class, 150L);
            member.setName("zzzzz");*/
            //em.persist(member);

            /*Member member = new Member(200L, "member200");
            em.persist(member);
            //강제로 직접 호출(insert)
            em.flush();
            System.out.println("================");*/

            Member member = em.find(Member.class, 150L);
            member.setName("AAAA");
            //특정 엔티티만 준영속 상태로 전환
            //em.detach(member);
            //영속성 컨테스트를 완전히 초기화
            em.clear();
            //다시 조회함 영속성 컨테스트가 완전히 초기화 되었으므로
            Member member2 = em.find(Member.class, 150L);

            //hibernate.jdbc.batch_size를 통해서 모았다가 한방에도 가능
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
