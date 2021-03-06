package com.ct.db.db.db.phoneextensions.generated;

import com.ct.db.db.db.phoneextensions.Phoneextensions;
import com.ct.db.db.db.phoneextensions.PhoneextensionsImpl;
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
 * example, a row) in the Table db.db.db.phoneextensions.
 * <p>
 * This file has been automatically generated by Speedment. Any changes made
 * to it will be overwritten.
 * 
 * @author Speedment
 */
@Generated("Speedment")
public abstract class GeneratedPhoneextensionsManagerImpl extends AbstractSqlManager<Phoneextensions> implements GeneratedPhoneextensionsManager {
    
    private final static Tuple1<Class<String>> PRIMARY_KEY_CLASSES = Tuples.of(String.class);
    
    protected GeneratedPhoneextensionsManagerImpl(Speedment speedment) {
        super(speedment);
        setEntityMapper(this::newEntityFrom);
    }
    
    protected Phoneextensions newEntityFrom(ResultSet resultSet) throws SQLException, SpeedmentException {
        final Phoneextensions entity = newEmptyEntity();
        try {
            entity.setPhoneNumber(resultSet.getString(1));
            entity.setIsActive(getInt(resultSet, 2));
        }
        catch (SQLException sqle) {
            throw new SpeedmentException(sqle);
        }
        return entity;
    }
    
    @Override
    public Phoneextensions newEmptyEntity() {
        return new PhoneextensionsImpl() {
            @Override
            protected Speedment speedment() {
                return speedment;
            }
        };
    }
    
    @Override
    public Object get(Phoneextensions entity, FieldIdentifier<Phoneextensions> identifier) {
        switch ((Phoneextensions.Identifier) identifier) {
            case PHONE_NUMBER : return entity.getPhoneNumber();
            case IS_ACTIVE : return entity.getIsActive().orElse(null);
            default : throw new IllegalArgumentException("Unknown identifier '" + identifier + "'.");
        }
    }
    
    @Override
    public void set(Phoneextensions entity, FieldIdentifier<Phoneextensions> identifier, Object value) {
        switch ((Phoneextensions.Identifier) identifier) {
            case PHONE_NUMBER : entity.setPhoneNumber((String) value); break;
            case IS_ACTIVE : entity.setIsActive((Integer) value); break;
            default : throw new IllegalArgumentException("Unknown identifier '" + identifier + "'.");
        }
    }
    
    @Override
    public Stream<FieldTrait> fields() {
        return Stream.of(
            Phoneextensions.PHONE_NUMBER,
            Phoneextensions.IS_ACTIVE
        );
    }
    
    @Override
    public Stream<FieldTrait> primaryKeyFields() {
        return Stream.of(
            Phoneextensions.PHONE_NUMBER
        );
    }
    
    @Override
    public Tuple1<Class<String>> getPrimaryKeyClasses() {
        return PRIMARY_KEY_CLASSES;
    }
    
    @Override
    public Phoneextensions newCopyOf(Phoneextensions source) {
        final Phoneextensions copy = new PhoneextensionsImpl() {
            @Override
            protected final Speedment speedment() {
                return speedment;
            }
        };
        
        copy.setPhoneNumber(source.getPhoneNumber());
        source.getIsActive().ifPresent(copy::setIsActive);
        
        return copy;
    }
}