package com.fastcampus.ch4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.Date;

@SpringBootApplication
public class Ch4Application implements CommandLineRunner {
    @Autowired
    EntityManagerFactory emf;
    public static void main(String[] args) {

        SpringApplication app = new SpringApplication(Ch4Application.class);
        // app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);

    }

    @Override
    public void run(String... args) throws Exception {
        EntityManager em = emf.createEntityManager();
        System.out.println("em = " + em);
        EntityTransaction tx = em.getTransaction();


        User user = new User();
        user.setId("aaa");
        user.setPassword("1234");
        user.setName("Lee");
        user.setEmail("aaa@aaa.com");
        user.setInDate(new Date());
        user.setUpDate(new Date());


        tx.begin();

        //1. 저장
        em.persist(user);

        //2. 변경
        user.setPassword("4321");
        tx.commit();

        //3. 조회
        User user2 = em.find(User.class, "aaa");
        System.out.println("user2==user = " + (user == user2));

        //4. 삭제
        tx.begin();
        em.remove(user);
        tx.commit();

    }
}
