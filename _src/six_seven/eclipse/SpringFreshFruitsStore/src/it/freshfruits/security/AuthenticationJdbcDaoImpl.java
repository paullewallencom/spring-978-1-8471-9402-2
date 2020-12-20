package it.freshfruits.security;

import it.freshfruits.application.repository.CustomerRepository;
import it.freshfruits.util.Constants;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UsernameNotFoundException;
import org.springframework.security.userdetails.jdbc.JdbcDaoImpl;

public class AuthenticationJdbcDaoImpl extends JdbcDaoImpl {

    @SuppressWarnings("unchecked")
    public UserDetails loadUserByUsername(String username) {
        try {
            UserDetails user = super.loadUserByUsername(username);
            Map userInfo = new HashMap();
            userInfo.put(Constants.ID_CUSTOMER, repo.getIdCustomer(username));
            return new FreshFruitUser(user.getUsername(), user.getPassword(), user.isEnabled(), user.getAuthorities(), userInfo);
        } catch (UsernameNotFoundException ex1) {
            ex1.printStackTrace();
            throw ex1;
        } catch (DataAccessException ex2) {
            ex2.printStackTrace();
            throw ex2;
        }
    }

    public void setRepo(CustomerRepository repo) {
        this.repo = repo;
    }

    private CustomerRepository repo;
}
