package test;


import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Named("testBean")
@Scope("request")
public class TestBean {

    private String name = "test";
    @PersistenceContext
    private EntityManager em;

    public TestBean() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @Transactional
    public void check(){

        Query query = em.createQuery("select r from TestEntity r");
        List<TestEntity> list = query.getResultList();

        em.merge(new TestEntity("test"));
        int a = 1;
    }
}
