package com.ct.db.generated;

import com.ct.db.DbApplication;
import com.ct.db.db.db.activesessions.ActivesessionsManagerImpl;
import com.ct.db.db.db.phoneextensions.PhoneextensionsManagerImpl;
import com.ct.db.db.db.sexters.SextersManagerImpl;
import com.ct.db.db.db.users.UsersManagerImpl;
import com.speedment.internal.core.runtime.SpeedmentApplicationLifecycle;
import javax.annotation.Generated;

/**
 * A generated base {@link
 * com.speedment.internal.core.runtime.SpeedmentApplicationLifecycle} class
 * for the {@link com.speedment.config.db.Project} named db.
 * <p>
 * This file has been automatically generated by Speedment. Any changes made
 * to it will be overwritten.
 * 
 * @author Speedment
 */
@Generated("Speedment")
public abstract class GeneratedDbApplication extends SpeedmentApplicationLifecycle<DbApplication> {
    
    protected GeneratedDbApplication() {
        setSpeedmentApplicationMetadata(new GeneratedDbApplicationMetadata());
    }
    
    @Override
    public void onLoad() {
        super.onLoad();
        loadAndSetProject();
        applyAndPut(ActivesessionsManagerImpl::new);
        applyAndPut(PhoneextensionsManagerImpl::new);
        applyAndPut(SextersManagerImpl::new);
        applyAndPut(UsersManagerImpl::new);
        loadCustomManagers();
    }
}