package com.softdesign.devintensive.data.storage.models;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.softdesign.devintensive.data.storage.models.Repositories;
import com.softdesign.devintensive.data.storage.models.User;

import com.softdesign.devintensive.data.storage.models.RepositoriesDao;
import com.softdesign.devintensive.data.storage.models.UserDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig repositoriesDaoConfig;
    private final DaoConfig userDaoConfig;

    private final RepositoriesDao repositoriesDao;
    private final UserDao userDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        repositoriesDaoConfig = daoConfigMap.get(RepositoriesDao.class).clone();
        repositoriesDaoConfig.initIdentityScope(type);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        repositoriesDao = new RepositoriesDao(repositoriesDaoConfig, this);
        userDao = new UserDao(userDaoConfig, this);

        registerDao(Repositories.class, repositoriesDao);
        registerDao(User.class, userDao);
    }
    
    public void clear() {
        repositoriesDaoConfig.getIdentityScope().clear();
        userDaoConfig.getIdentityScope().clear();
    }

    public RepositoriesDao getRepositoriesDao() {
        return repositoriesDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

}