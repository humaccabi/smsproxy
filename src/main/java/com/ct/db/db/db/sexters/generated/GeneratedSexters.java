package com.ct.db.db.db.sexters.generated;

import com.ct.db.db.db.activesessions.Activesessions;
import com.ct.db.db.db.sexters.Sexters;
import com.speedment.Entity;
import com.speedment.config.db.mapper.identity.StringIdentityMapper;
import com.speedment.field.FieldIdentifier;
import com.speedment.field.StringField;
import com.speedment.internal.core.field.StringFieldImpl;
import java.util.Optional;
import java.util.stream.Stream;
import javax.annotation.Generated;

/**
 * The generated base interface representing an entity (for example, a row)
 * in the Table db.db.db.sexters.
 * <p>
 * This file has been automatically generated by Speedment. Any changes made
 * to it will be overwritten.
 * 
 * @author Speedment
 */
@Generated("Speedment")
public interface GeneratedSexters extends Entity<Sexters> {
    
    /**
     * This Field corresponds to the {@link Sexters} field that can be obtained
     * using the {@link Sexters#getPhoneNumber()} method.
     */
    final StringField<Sexters, String> PHONE_NUMBER = new StringFieldImpl<>(Identifier.PHONE_NUMBER, Sexters::getPhoneNumber, Sexters::setPhoneNumber, new StringIdentityMapper(), true);
    /**
     * This Field corresponds to the {@link Sexters} field that can be obtained
     * using the {@link Sexters#getBalance()} method.
     */
    final StringField<Sexters, String> BALANCE = new StringFieldImpl<>(Identifier.BALANCE, o -> o.getBalance().orElse(null), Sexters::setBalance, new StringIdentityMapper(), false);
    /**
     * This Field corresponds to the {@link Sexters} field that can be obtained
     * using the {@link Sexters#getTimestamp()} method.
     */
    final StringField<Sexters, String> TIMESTAMP = new StringFieldImpl<>(Identifier.TIMESTAMP, o -> o.getTimestamp().orElse(null), Sexters::setTimestamp, new StringIdentityMapper(), false);
    
    /**
     * Returns the phoneNumber of this Sexters. The phoneNumber field corresponds
     * to the database column db.db.sexters.phoneNumber.
     * 
     * @return the phoneNumber of this Sexters
     */
    String getPhoneNumber();
    
    /**
     * Returns the balance of this Sexters. The balance field corresponds to the
     * database column db.db.sexters.balance.
     * 
     * @return the balance of this Sexters
     */
    Optional<String> getBalance();
    
    /**
     * Returns the timestamp of this Sexters. The timestamp field corresponds to
     * the database column db.db.sexters.timestamp.
     * 
     * @return the timestamp of this Sexters
     */
    Optional<String> getTimestamp();
    
    /**
     * Sets the phoneNumber of this Sexters. The phoneNumber field corresponds to
     * the database column db.db.sexters.phoneNumber.
     * 
     * @param phoneNumber to set of this Sexters
     * @return this Sexters instance
     */
    Sexters setPhoneNumber(String phoneNumber);
    
    /**
     * Sets the balance of this Sexters. The balance field corresponds to the
     * database column db.db.sexters.balance.
     * 
     * @param balance to set of this Sexters
     * @return this Sexters instance
     */
    Sexters setBalance(String balance);
    
    /**
     * Sets the timestamp of this Sexters. The timestamp field corresponds to the
     * database column db.db.sexters.timestamp.
     * 
     * @param timestamp to set of this Sexters
     * @return this Sexters instance
     */
    Sexters setTimestamp(String timestamp);
    
    /**
     * Creates and returns a {@link Stream} of all {@link Activesessions}
     * Entities that references this Entity by the foreign key field that can be
     * obtained using {@link Activesessions#getSextersPhoneNumber()}. The order
     * of the Entities are undefined and may change from time to time.
     * <p>
     * Using this method, you may "walk the graph" and jump directly between
     * referencing Entities without using {@code JOIN}s.<p> N.B. The current
     * implementation supports lazy-loading of the referencing Entities.
     * 
     * @return a {@link Stream} of all {@link Activesessions} Entities that
     * references this Entity by the foreign key field that can be obtained using
     * {@link Activesessions#getSextersPhoneNumber()}
     */
    Stream<Activesessions> findActivesessionsesBySextersPhoneNumber();
    
    /**
     * Creates and returns a <em>distinct</em> {@link Stream} of all {@link
     * Activesessions} Entities that references this Entity by a foreign key. The
     * order of the Entities are undefined and may change from time to time.
     * <p>
     * Note that the Stream is <em>distinct</em>, meaning that referencing
     * Entities will only appear once in the Stream, even though they may
     * reference this Entity by several columns.
     * <p>
     * Using this method, you may "walk the graph" and jump directly between
     * referencing Entities without using {@code JOIN}s.
     * <p>
     * N.B. The current implementation supports lazy-loading of the referencing
     * Entities.
     * 
     * @return a <em>distinct</em> {@link Stream} of all {@link Activesessions}
     * Entities that references this Entity by a foreign key
     */
    Stream<Activesessions> findActivesessionses();
    
    enum Identifier implements FieldIdentifier<Sexters> {
        
        PHONE_NUMBER ("phoneNumber"),
        BALANCE ("balance"),
        TIMESTAMP ("timestamp");
        
        private final String columnName;
        
        Identifier(String columnName) {
            this.columnName = columnName;
        }
        
        @Override
        public String dbmsName() {
            return "db";
        }
        
        @Override
        public String schemaName() {
            return "db";
        }
        
        @Override
        public String tableName() {
            return "sexters";
        }
        
        @Override
        public String columnName() {
            return this.columnName;
        }
    }
}