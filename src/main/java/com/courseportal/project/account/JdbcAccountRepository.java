package com.courseportal.project.account;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class JdbcAccountRepository implements AccountRepository {

    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    @Transactional
    public void createAccount(Account account) {
        entityManager.persist(account);
    }

    @Override
    public Account findAccountByUsername(String username) {
        return (Account) entityManager.createQuery("SELECT a FROM Account a WHERE a.username = :username")
                .setParameter("username", username).getSingleResult();
    }

}
