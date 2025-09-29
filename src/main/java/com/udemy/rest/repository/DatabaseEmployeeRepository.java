package com.udemy.rest.repository;

import com.udemy.rest.model.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.MutationQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DatabaseEmployeeRepository implements EmployeeRepository {
    private final SessionFactory sessionFactory;

    public DatabaseEmployeeRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Employee> findAll() {
        Session session = this.sessionFactory.getCurrentSession();
        return session.createQuery("from Employee", Employee.class).list();
    }

    @Override
    public void saveEmployee(Employee employee) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(employee);
    }

    @Override
    public void updateEmployee(Employee employee) {
        Session session = this.sessionFactory.getCurrentSession();
        session.merge(employee);
    }

    @Override
    public Employee findById(int id) {
        return sessionFactory.getCurrentSession().find(Employee.class, id);
    }

    @Override
    public void remove(int id) {
        Session session = sessionFactory.getCurrentSession();
        MutationQuery query = session.createMutationQuery("delete from Employee where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

}
