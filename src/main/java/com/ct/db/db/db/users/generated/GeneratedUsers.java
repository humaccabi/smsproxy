package com.ct.db.db.db.users.generated;

import com.ct.db.db.db.users.Users;
import com.speedment.Entity;
import com.speedment.config.db.mapper.identity.DoubleIdentityMapper;
import com.speedment.config.db.mapper.identity.StringIdentityMapper;
import com.speedment.field.ComparableField;
import com.speedment.field.FieldIdentifier;
import com.speedment.field.StringField;
import com.speedment.internal.core.field.ComparableFieldImpl;
import com.speedment.internal.core.field.StringFieldImpl;
import java.util.Optional;
import javax.annotation.Generated;

/**
 * The generated base interface representing an entity (for example, a row)
 * in the Table db.db.db.users.
 * <p>
 * This file has been automatically generated by Speedment. Any changes made
 * to it will be overwritten.
 * 
 * @author Speedment
 */
@Generated("Speedment")
public interface GeneratedUsers extends Entity<Users> {
    
    /**
     * This Field corresponds to the {@link Users} field that can be obtained
     * using the {@link Users#getPhoneNumber()} method.
     */
    final StringField<Users, String> PHONE_NUMBER = new StringFieldImpl<>(Identifier.PHONE_NUMBER, Users::getPhoneNumber, Users::setPhoneNumber, new StringIdentityMapper(), true);
    /**
     * This Field corresponds to the {@link Users} field that can be obtained
     * using the {@link Users#getCredit()} method.
     */
    final ComparableField<Users, Double, Double> CREDIT = new ComparableFieldImpl<>(Identifier.CREDIT, o -> o.getCredit().orElse(null), Users::setCredit, new DoubleIdentityMapper(), false);
    /**
     * This Field corresponds to the {@link Users} field that can be obtained
     * using the {@link Users#getTimestamp()} method.
     */
    final StringField<Users, String> TIMESTAMP = new StringFieldImpl<>(Identifier.TIMESTAMP, o -> o.getTimestamp().orElse(null), Users::setTimestamp, new StringIdentityMapper(), false);
    /**
     * This Field corresponds to the {@link Users} field that can be obtained
     * using the {@link Users#getRegistrationPhase()} method.
     */
    final StringField<Users, String> REGISTRATION_PHASE = new StringFieldImpl<>(Identifier.REGISTRATION_PHASE, o -> o.getRegistrationPhase().orElse(null), Users::setRegistrationPhase, new StringIdentityMapper(), false);
    
    /**
     * Returns the phoneNumber of this Users. The phoneNumber field corresponds
     * to the database column db.db.users.phoneNumber.
     * 
     * @return the phoneNumber of this Users
     */
    String getPhoneNumber();
    
    /**
     * Returns the credit of this Users. The credit field corresponds to the
     * database column db.db.users.credit.
     * 
     * @return the credit of this Users
     */
    Optional<Double> getCredit();
    
    /**
     * Returns the timestamp of this Users. The timestamp field corresponds to
     * the database column db.db.users.timestamp.
     * 
     * @return the timestamp of this Users
     */
    Optional<String> getTimestamp();
    
    /**
     * Returns the registrationPhase of this Users. The registrationPhase field
     * corresponds to the database column db.db.users.registrationPhase.
     * 
     * @return the registrationPhase of this Users
     */
    Optional<String> getRegistrationPhase();
    
    /**
     * Sets the phoneNumber of this Users. The phoneNumber field corresponds to
     * the database column db.db.users.phoneNumber.
     * 
     * @param phoneNumber to set of this Users
     * @return this Users instance
     */
    Users setPhoneNumber(String phoneNumber);
    
    /**
     * Sets the credit of this Users. The credit field corresponds to the
     * database column db.db.users.credit.
     * 
     * @param credit to set of this Users
     * @return this Users instance
     */
    Users setCredit(Double credit);
    
    /**
     * Sets the timestamp of this Users. The timestamp field corresponds to the
     * database column db.db.users.timestamp.
     * 
     * @param timestamp to set of this Users
     * @return this Users instance
     */
    Users setTimestamp(String timestamp);
    
    /**
     * Sets the registrationPhase of this Users. The registrationPhase field
     * corresponds to the database column db.db.users.registrationPhase.
     * 
     * @param registrationPhase to set of this Users
     * @return this Users instance
     */
    Users setRegistrationPhase(String registrationPhase);
    
    enum Identifier implements FieldIdentifier<Users> {
        
        PHONE_NUMBER ("phoneNumber"),
        CREDIT ("credit"),
        TIMESTAMP ("timestamp"),
        REGISTRATION_PHASE ("registrationPhase");
        
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
            return "users";
        }
        
        @Override
        public String columnName() {
            return this.columnName;
        }
    }
}