package com.ct.db.db.db.phoneextensions.generated;

import com.ct.db.db.db.activesessions.Activesessions;
import com.ct.db.db.db.phoneextensions.Phoneextensions;
import com.speedment.Entity;
import com.speedment.config.db.mapper.identity.IntegerIdentityMapper;
import com.speedment.config.db.mapper.identity.StringIdentityMapper;
import com.speedment.field.ComparableField;
import com.speedment.field.FieldIdentifier;
import com.speedment.field.StringField;
import com.speedment.internal.core.field.ComparableFieldImpl;
import com.speedment.internal.core.field.StringFieldImpl;
import java.util.Optional;
import java.util.stream.Stream;
import javax.annotation.Generated;

/**
 * The generated base interface representing an entity (for example, a row)
 * in the Table db.db.db.phoneextensions.
 * <p>
 * This file has been automatically generated by Speedment. Any changes made
 * to it will be overwritten.
 * 
 * @author Speedment
 */
@Generated("Speedment")
public interface GeneratedPhoneextensions extends Entity<Phoneextensions> {
    
    /**
     * This Field corresponds to the {@link Phoneextensions} field that can be
     * obtained using the {@link Phoneextensions#getPhoneNumber()} method.
     */
    final StringField<Phoneextensions, String> PHONE_NUMBER = new StringFieldImpl<>(Identifier.PHONE_NUMBER, Phoneextensions::getPhoneNumber, Phoneextensions::setPhoneNumber, new StringIdentityMapper(), true);
    /**
     * This Field corresponds to the {@link Phoneextensions} field that can be
     * obtained using the {@link Phoneextensions#getIsActive()} method.
     */
    final ComparableField<Phoneextensions, Integer, Integer> IS_ACTIVE = new ComparableFieldImpl<>(Identifier.IS_ACTIVE, o -> o.getIsActive().orElse(null), Phoneextensions::setIsActive, new IntegerIdentityMapper(), false);
    
    /**
     * Returns the phoneNumber of this Phoneextensions. The phoneNumber field
     * corresponds to the database column db.db.phoneextensions.phoneNumber.
     * 
     * @return the phoneNumber of this Phoneextensions
     */
    String getPhoneNumber();
    
    /**
     * Returns the isActive of this Phoneextensions. The isActive field
     * corresponds to the database column db.db.phoneextensions.isActive.
     * 
     * @return the isActive of this Phoneextensions
     */
    Optional<Integer> getIsActive();
    
    /**
     * Sets the phoneNumber of this Phoneextensions. The phoneNumber field
     * corresponds to the database column db.db.phoneextensions.phoneNumber.
     * 
     * @param phoneNumber to set of this Phoneextensions
     * @return this Phoneextensions instance
     */
    Phoneextensions setPhoneNumber(String phoneNumber);
    
    /**
     * Sets the isActive of this Phoneextensions. The isActive field corresponds
     * to the database column db.db.phoneextensions.isActive.
     * 
     * @param isActive to set of this Phoneextensions
     * @return this Phoneextensions instance
     */
    Phoneextensions setIsActive(Integer isActive);
    
    /**
     * Creates and returns a {@link Stream} of all {@link Activesessions}
     * Entities that references this Entity by the foreign key field that can be
     * obtained using {@link Activesessions#getPhoneExtensionsPhoneNumber()}. The
     * order of the Entities are undefined and may change from time to time.
     * <p>
     * Using this method, you may "walk the graph" and jump directly between
     * referencing Entities without using {@code JOIN}s.<p> N.B. The current
     * implementation supports lazy-loading of the referencing Entities.
     * 
     * @return a {@link Stream} of all {@link Activesessions} Entities that
     * references this Entity by the foreign key field that can be obtained using
     * {@link Activesessions#getPhoneExtensionsPhoneNumber()}
     */
    Stream<Activesessions> findActivesessionsesByPhoneExtensionsPhoneNumber();
    
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
    
    enum Identifier implements FieldIdentifier<Phoneextensions> {
        
        PHONE_NUMBER ("phoneNumber"),
        IS_ACTIVE ("isActive");
        
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
            return "phoneextensions";
        }
        
        @Override
        public String columnName() {
            return this.columnName;
        }
    }
}