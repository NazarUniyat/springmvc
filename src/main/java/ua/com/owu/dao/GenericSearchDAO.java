package ua.com.owu.dao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//@Repository
//class SearchDAO {
//    private final EntityManager entityManager;
//
//    @Autowired
//    public SearchDAO(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }
//
//    public List<Account> getUsers(String username, String email, String role){
//        String query = "from Account a where %s";
//        String where = "";
//        String replace="";
//        if (!username.equals("test")) {
//            where += String.format("a.username = '%s' and ", username);
//        }
//        if(!email.equals("test")){
//            where += String.format("a.email = '%s' and ", email);
//        }
//        if(!role.equals("test")){
//            where += String.format("a.role = '%s' and ", role);
//        }
//        if(where.endsWith("and ")){
//            int lastA_ = where.lastIndexOf("a") - 1;
//            int lastSpace = where.lastIndexOf("d") + 1;
//            String substring = where.substring(lastA_, lastSpace);
////            String replace1 = substring.replace(" and ", "");
//            System.out.println(substring);
//            StringBuilder stringBuilder = new StringBuilder(where);
//            StringBuilder delete = stringBuilder.delete(lastA_, lastSpace);
//            where = delete.toString();
//
//
//
//            System.out.println(where);
//        }
//        System.out.println(where);
//
//        System.out.println(String.format(query, where));
//        System.out.println(entityManager.createQuery(String.format(query, where)).getResultList());
//
//        return entityManager.createQuery(String.format(query,where)).getResultList();
//    }
//}

@Repository
public class GenericSearchDAO {

    //private final Class<T> clazz;

    private final EntityManager entityManager;

    @Autowired
    public GenericSearchDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public <T> List<T> get(Map<String, String> params, String table) {

        Character alias = Character.toLowerCase(table.charAt(0));
        String searchSQL = "from " + table + " " + alias  + " where ";

        String query = searchSQL + params
                .entrySet().stream()
                .filter(i -> !i.getValue().equals("test"))
                //[ a.role = 'role', a.name = 'Nazar']
                .map(i -> alias + "." + i.getKey() + " = '" + i.getValue() + "'")
                // a.role = 'role' and a.name = 'Nazar'
                .collect(Collectors.joining(" and "));

        System.out.println(query);
        return entityManager.createQuery(query).getResultList();
    }
}
