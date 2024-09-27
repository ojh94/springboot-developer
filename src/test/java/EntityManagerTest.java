import com.itschool.springbootdeveloper.entity.Member;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class EntityManagerTest {
    // 엔티티 매니저 팩토리는 하나만 생성해서 애플리케이션 전체에서 공유
    // 데이터베이스당 1개, persistence,xml에서 설정 정보를 UnitName을 통해 가져옴
    //EntityManagerFactory emf = Persistence.createEntityManagerFactory(UniName);

    // 주의사항 : 엔티티 매니저는 쓰레드 간에 공유X(사용하고 버려야 함)
    // 요청이 올 떄마다 하나 생성하고 쓰고 버림
    @Autowired
    EntityManager em; // = emf.createEntityManager();

    @Test
    public void example(){
        EntityTransaction tx = em.getTransaction();
        // 엔티티는 4가지 상태를 가짐
        // 분리(detached)상태, 관리(managed) 상태, 비영속(transient) 상태, 삭제된(removed) 상태

        try {
            // 비영속(transient) 상태
            Member member = new Member("홍길동");

            // 관리(managed) 상태 : 엔티티가 관리되는 상태
            em.persist(member);
        }catch (Exception e){
            tx.rollback();
        }
    }
}
