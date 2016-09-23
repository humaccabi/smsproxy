package com.ct.db.db.db.sexters.generated;

import com.ct.db.db.db.sexters.Sexters;
import com.ct.db.db.db.sexters.SextersImpl;
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
 * example, a row) in the Table db.db.db.sexters.
 * <p>
 * This file has been automatically generated by Speedment. Any changes made
 * to it will be overwritten.
 * 
 * @author Speedment
 */
@Generated("Speedment")
public abstract class GeneratedSextersManagerImpl extends AbstractSqlManager<Sexters> implements GeneratedSextersManager {
    
    private final static Tuple1<Class<String>> PRIMARY_KEY_CLASSES = Tuples.of(String.class);
    
    protected GeneratedSextersManagerImpl(Speedment speedment) {
        super(speedment);
        setEntityMapper(this::newEntityFrom);
    }
    
    protected Sexters newEntityFrom(ResultSet resultSet) throws SpeedmentException, SQLException {
        final Sexters entity = newEmptyEntity();
        try {
            entity.setPhoneNumber(resultSet.getString(1));
            entity.setBalance(resultSet.getString(2));
            entity.setTimestamp(resultSet.getString(3));
            entity.setIsOnline(getInt(resultSet, 4));
            entity.setRegistrationPhase(resultSet.getString(5));
        }
        catch (SQLException sqle) {
            throw new SpeedmentException(sqle);
        }
        return entity;
    }
    
    @Override
    public Sexters newEmptyEntity() {
        return new SextersImpl() {
            @Override
            protected Speedment speedment() {
                return speedment;
            }
        };
    }
    
    @Override
    public Object get(Sexters entity, FieldIdentifier<Sexters> identifier) {
        switch ((Sexters.Identifier) identifier) {
            case PHONE_NUMBER : return entity.getPhoneNumber();
            case BALANCE : return entity.getBalance().orElse(null);
            case TIMESTAMP : return entity.getTimestamp().orElse(null);
            case IS_ONLINE : return entity.getIsOnline().orElse(null);
            case REGISTRATION_PHASE : return entity.getRegistrationPhase().orElse(null);
            default : throw new IllegalArgumentException("Unknown identifier '" + identifier + "'.");
        }
    }
    
    @Override
    public void set(Sexters entity, FieldIdentifier<Sexters> identifier, Object value) {
        switch ((Sexters.Identifier) identifier) {
            case PHONE_NUMBER : entity.setPhoneNumber((String) value); break;
            case BALANCE : entity.setBalance((String) value); break;
            case TIMESTAMP : entity.setTimestamp((String) value); break;
            case IS_ONLINE : entity.setIsOnline((Integer) value); break;
            case REGISTRATION_PHASE : entity.setRegistrationPhase((String) value); break;
            default : throw new IllegalArgumentException("Unknown identifier '" + identifier + "'.");
        }
    }
    
    @Override
    public Stream<FieldTrait> fields() {
        return Stream.of(
            Sexters.PHONE_NUMBER,
            Sexters.BALANCE,
            Sexters.TIMESTAMP,
            Sexters.IS_ONLINE,
            Sexters.REGISTRATION_PHASE
        );
    }
    
    @Override
    public Stream<FieldTrait> primaryKeyFields() {
        return Stream.of(
            Sexters.PHONE_NUMBER
        );
    }
    
    @Override
    public Tuple1<Class<String>> getPrimaryKeyClasses() {
        return PRIMARY_KEY_CLASSES;
    }
    
    @Override
    public Sexters newCopyOf(Sexters source) {
        final Sexters copy = new SextersImpl() {
            @Override
            protected final Speedment speedment() {
                return speedment;
            }
        };
        
        copy.setPhoneNumber(source.getPhoneNumber());
        source.getBalance().ifPresent(copy::setBalance);
        source.getTimestamp().ifPresent(copy::setTimestamp);
        source.getIsOnline().ifPresent(copy::setIsOnline);
        source.getRegistrationPhase().ifPresent(copy::setRegistrationPhase);
        
        return copy;
    }
}