package ua.com.owu.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.owu.models.Account;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class SearchDAO {
    private final EntityManager entityManager;

    @Autowired
    public SearchDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Account> getUsers(String username, String email, String role){
        String query = "from Account a where %s";
        String where = "";
        String replace="";
        if (!username.equals("test")) {
            where += String.format("a.username = '%s' and ", username);
        }
        if(!email.equals("test")){
            where += String.format("a.email = '%s' and ", email);
        }
        if(!role.equals("test")){
            where += String.format("a.role = '%s' and ", role);
        }
        if(where.endsWith("and ")){
            int lastA_ = where.lastIndexOf("a") - 1;
            int lastSpace = where.lastIndexOf("d") + 1;
            String substring = where.substring(lastA_, lastSpace);
//            String replace1 = substring.replace(" and ", "");
            System.out.println(substring);
            StringBuilder stringBuilder = new StringBuilder(where);
            StringBuilder delete = stringBuilder.delete(lastA_, lastSpace);
            where = delete.toString();



            System.out.println(where);
        }
        System.out.println(where);





        System.out.println(String.format(query, where));
        System.out.println(entityManager.createQuery(String.format(query, where)).getResultList());

        return entityManager.createQuery(String.format(query,where)).getResultList();
    }
}
