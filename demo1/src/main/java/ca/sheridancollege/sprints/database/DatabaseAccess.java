package ca.sheridancollege.sprints.database;

import ca.sheridancollege.sprints.beans.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class DatabaseAccess {
    @Autowired
    protected NamedParameterJdbcTemplate jdbc;


    public User findUserAccount(String email){
        System.out.println(email);
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String q = "Select * from sec_user where email = :email";
        parameters.addValue("email", email);

        ArrayList<User> users = (ArrayList<User>) jdbc.query(q, parameters,
                new BeanPropertyRowMapper<User>(User.class));

        if(users.size() > 0){
            System.out.println(users.get(0));
            return users.get(0);
        }
        return null;
    }

    public List<String> getRolesById(long userId){
        ArrayList<String> roles = new ArrayList<>();

        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String q = "Select user_role.userid, sec_role.rolename"
                + " From user_role, sec_role"
                + " Where user_role.roleId = sec_role.roleId"
                + " And userId = :userId";
        parameters.addValue("userId", userId);

        List<Map<String, Object>> rows = jdbc.queryForList(q, parameters);
        for(Map<String, Object> row: rows)
            roles.add((String) row.get("roleName"));

        return roles;
    }



    public BCryptPasswordEncoder passworEncoder() {
        return new BCryptPasswordEncoder();
    }
    public void createNewUser(String email,String firstName, String lastName,long phone, String secondaryEmail,
                              String province, String city, String postalCode,String password){
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String q = "Insert into SEC_USER (email, firstName, lastName, phone, secondaryEmail, province," +
                "city,postalCode ,encryptedPassword, accountEnabled)"
                + " values (:email, :firstName, :lastName, :phone, :secondaryEmail, :province," +
                ":city, :postalCode, :password, 1)";
        parameters.addValue("email", email);
        parameters.addValue("firstName", firstName);
        parameters.addValue("lastName", lastName);
        parameters.addValue("phone", phone);
        parameters.addValue("secondaryEmail", secondaryEmail);
        parameters.addValue("province", province);
        parameters.addValue("city", city);
        parameters.addValue("postalCode", postalCode);
        parameters.addValue("password", passworEncoder().encode(password));
        jdbc.update(q, parameters);
    }

    public void addRole(long userId, long roleId){
        MapSqlParameterSource parameters = new MapSqlParameterSource();
        String q = "Insert into USER_ROLE (userId, roleId) values (:userId, :roleId)";
        parameters.addValue("userId", userId);
        parameters.addValue("roleId", roleId);
        jdbc.update(q, parameters);
    }
















}
