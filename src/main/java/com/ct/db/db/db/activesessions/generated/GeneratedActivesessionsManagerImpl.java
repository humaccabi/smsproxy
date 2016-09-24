package com.ct.db.db.db.activesessions.generated;

import com.ct.db.db.db.activesessions.Activesessions;
import com.ct.db.db.db.activesessions.ActivesessionsImpl;
import com.speedment.Speedment;
import com.speedment.exception.SpeedmentException;
import com.speedment.field.FieldIdentifier;
import com.speedment.field.trait.FieldTrait;
import com.speedment.internal.core.manager.sql.AbstractSqlManager;
import com.speedment.util.tuple.Tuple1;
import com.speedment.util.tuple.Tuples;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.stream.Stream;
import javax.annotation.Generated;
import static com.speedment.internal.util.sql.ResultSetUtil.*;

/**
 * The generated base manager implementation representing an entity (for
 * example, a row) in the Table db.db.db.activesessions.
 * <p>
 * This file has been automatically generated by Speedment. Any changes made
 * to it will be overwritten.
 * 
 * @author Speedment
 */
@Generated("Speedment")
public abstract class GeneratedActivesessionsManagerImpl extends AbstractSqlManager<Activesessions> implements GeneratedActivesessionsManager {
    
    private final static Tuple1<Class<Integer>> PRIMARY_KEY_CLASSES = Tuples.of(Integer.class);
    
    protected GeneratedActivesessionsManagerImpl(Speedment speedment) {
        super(speedment);
        setEntityMapper(this::newEntityFrom);
    }
    
    protected Activesessions newEntityFrom(ResultSet resultSet) throws SQLException, SpeedmentException {
        final Activesessions entity = newEmptyEntity();
        try {
            entity.setId(resultSet.getInt(1));
            entity.setUsersPhoneNumber(resultSet.getString(2));
            entity.setPhoneextensionsPhoneNumber(resultSet.getString(3));
            entity.setSextersPhoneNumber(resultSet.getString(4));
        }
        catch (SQLException sqle) {
            throw new SpeedmentException(sqle);
        }
        return entity;
    }
    
    @Override
    public Activesessions newEmptyEntity() {
        return new ActivesessionsImpl() {
            @Override
            protected Speedment speedment() {
                return speedment;
            }
        };
    }
    
    @Override
    public Object get(Activesessions entity, FieldIdentifier<Activesessions> identifier) {
        switch ((Activesessions.Identifier) identifier) {
            case ID : return entity.getId();
            case USERS_PHONE_NUMBER : return entity.getUsersPhoneNumber();
            case PHONEEXTENSIONS_PHONE_NUMBER : return entity.getPhoneextensionsPhoneNumber();
            case SEXTERS_PHONE_NUMBER : return entity.getSextersPhoneNumber();
            default : throw new IllegalArgumentException("Unknown identifier '" + identifier + "'.");
        }
    }
    
    @Override
    public void set(Activesessions entity, FieldIdentifier<Activesessions> identifier, Object value) {
        switch ((Activesessions.Identifier) identifier) {
            case ID : entity.setId((Integer) value); break;
            case USERS_PHONE_NUMBER : entity.setUsersPhoneNumber((String) value); break;
            case PHONEEXTENSIONS_PHONE_NUMBER : entity.setPhoneextensionsPhoneNumber((String) value); break;
            case SEXTERS_PHONE_NUMBER : entity.setSextersPhoneNumber((String) value); break;
            default : throw new IllegalArgumentException("Unknown identifier '" + identifier + "'.");
        }
    }
    
    @Override
    public Stream<FieldTrait> fields() {
        return Stream.of(
            Activesessions.ID,
            Activesessions.USERS_PHONE_NUMBER,
            Activesessions.PHONEEXTENSIONS_PHONE_NUMBER,
            Activesessions.SEXTERS_PHONE_NUMBER
        );
    }
    
    @Override
    public Stream<FieldTrait> primaryKeyFields() {
        return Stream.of(
            Activesessions.ID
        );
    }
    
    @Override
    public Tuple1<Class<Integer>> getPrimaryKeyClasses() {
        return PRIMARY_KEY_CLASSES;
    }
    
    @Override
    public Activesessions newCopyOf(Activesessions source) {
        final Activesessions copy = new ActivesessionsImpl() {
            @Override
            protected final Speedment speedment() {
                return speedment;
            }
        };
        
        copy.setId(source.getId());
        copy.setUsersPhoneNumber(source.getUsersPhoneNumber());
        copy.setPhoneextensionsPhoneNumber(source.getPhoneextensionsPhoneNumber());
        copy.setSextersPhoneNumber(source.getSextersPhoneNumber());
        
        return copy;
    }
}