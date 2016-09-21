package com.ct.db.db.db.sexters.generated;

import com.ct.db.db.db.activesessions.Activesessions;
import com.ct.db.db.db.sexters.Sexters;
import com.speedment.Speedment;
import com.speedment.internal.core.code.AbstractBaseEntity;
import java.util.Objects;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.stream.Stream;
import javax.annotation.Generated;

/**
 * The generated base implementation representing an entity (for example, a
 * row) in the Table db.db.db.sexters.
 * <p>
 * This file has been automatically generated by Speedment. Any changes made
 * to it will be overwritten.
 * 
 * @author Speedment
 */
@Generated("Speedment")
public abstract class GeneratedSextersImpl extends AbstractBaseEntity<Sexters> implements Sexters {
    
    private String phoneNumber;
    private String balance;
    private String timestamp;
    private Integer isOnline;
    private String registrationPhase;
    
    protected GeneratedSextersImpl() {
        
    }
    
    @Override
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    @Override
    public Optional<String> getBalance() {
        return Optional.ofNullable(balance);
    }
    
    @Override
    public Optional<String> getTimestamp() {
        return Optional.ofNullable(timestamp);
    }
    
    @Override
    public Optional<Integer> getIsOnline() {
        return Optional.ofNullable(isOnline);
    }
    
    @Override
    public Optional<String> getRegistrationPhase() {
        return Optional.ofNullable(registrationPhase);
    }
    
    @Override
    public final Sexters setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }
    
    @Override
    public final Sexters setBalance(String balance) {
        this.balance = balance;
        return this;
    }
    
    @Override
    public final Sexters setTimestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }
    
    @Override
    public final Sexters setIsOnline(Integer isOnline) {
        this.isOnline = isOnline;
        return this;
    }
    
    @Override
    public final Sexters setRegistrationPhase(String registrationPhase) {
        this.registrationPhase = registrationPhase;
        return this;
    }
    
    @Override
    public Stream<Activesessions> findActivesessionsesBySextersPhoneNumber() {
        return managerOf_(Activesessions.class)
                .stream().filter(Activesessions.SEXTERS_PHONE_NUMBER.equal(this.getPhoneNumber()));
    }
    
    @Override
    public String toString() {
        final StringJoiner sj = new StringJoiner(", ", "{ ", " }");
        sj.add("phoneNumber = "+Objects.toString(getPhoneNumber()));
        sj.add("balance = "+Objects.toString(getBalance().orElse(null)));
        sj.add("timestamp = "+Objects.toString(getTimestamp().orElse(null)));
        sj.add("isOnline = "+Objects.toString(getIsOnline().orElse(null)));
        sj.add("registrationPhase = "+Objects.toString(getRegistrationPhase().orElse(null)));
        return "SextersImpl "+sj.toString();
    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) { return true; }
        if (!(that instanceof Sexters)) { return false; }
        final Sexters thatSexters = (Sexters)that;
        if (!Objects.equals(this.getPhoneNumber(), thatSexters.getPhoneNumber())) {return false; }
        if (!Objects.equals(this.getBalance(), thatSexters.getBalance())) {return false; }
        if (!Objects.equals(this.getTimestamp(), thatSexters.getTimestamp())) {return false; }
        if (!Objects.equals(this.getIsOnline(), thatSexters.getIsOnline())) {return false; }
        if (!Objects.equals(this.getRegistrationPhase(), thatSexters.getRegistrationPhase())) {return false; }
        return true;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(getPhoneNumber());
        hash = 31 * hash + Objects.hashCode(getBalance());
        hash = 31 * hash + Objects.hashCode(getTimestamp());
        hash = 31 * hash + Objects.hashCode(getIsOnline());
        hash = 31 * hash + Objects.hashCode(getRegistrationPhase());
        return hash;
    }
    
    @Override
    public Class<Sexters> entityClass() {
        return Sexters.class;
    }
    
    @Override
    public Stream<Activesessions> findActivesessionses() {
        return findActivesessionsesBySextersPhoneNumber();
    }
}