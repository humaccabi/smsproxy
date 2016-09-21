package com.ct.db.db.db.users.generated;

import com.ct.db.db.db.users.Users;
import com.ct.db.db.db.users.UsersManager;
import com.speedment.component.ProjectComponent;
import com.speedment.config.db.Table;
import com.speedment.internal.core.manager.sql.SqlManager;
import com.speedment.util.tuple.Tuple1;
import javax.annotation.Generated;

/**
 * The generated base manager representing an entity (for example, a row) in
 * the Table db.db.db.users.
 * <p>
 * This file has been automatically generated by Speedment. Any changes made
 * to it will be overwritten.
 * 
 * @author Speedment
 */
@Generated("Speedment")
public interface GeneratedUsersManager extends SqlManager<Users> {
    
    @Override
    default String primaryKeyFor(Users entity) {
        return entity.getPhoneNumber();
    }
    
    @Override
    default Table getTable() {
        return speedment().get(ProjectComponent.class).getProject().findTableByName("db.db.users");
    }
    
    @Override
    default Class<UsersManager> getManagerClass() {
        return UsersManager.class;
    }
    
    @Override
    default Class<Users> getEntityClass() {
        return Users.class;
    }
    
    @Override
    Tuple1<Class<String>> getPrimaryKeyClasses();
}