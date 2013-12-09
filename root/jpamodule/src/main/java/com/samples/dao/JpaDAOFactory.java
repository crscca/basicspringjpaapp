package com.samples.dao;

import com.samples.entities.User;

;

/**
 * Returns Hibernate-specific instances of DAOs.
 * <p/>
 * If for a particular DAO there is no additional non-CRUD functionality, we use
 * a nested static class to implement the interface in a generic way. This
 * allows clean refactoring later on, should the interface implement business
 * data access methods at some later time. Then, we would externalize the
 * implementation into its own first-level class.
 * 
 * @author Christian Bauer
 */
public class JpaDAOFactory extends DAOFactory {

/*	public ItemDAO getItemDAO() {
		return (ItemDAO) instantiateDAO(ItemDAOHibernate.class);
	}*/

	private GenericJpaDAO instantiateDAO(Class daoClass) {
		try {
			System.out.println("Instantiating DAO: " + daoClass);
			return (GenericJpaDAO) daoClass.newInstance();
		} catch (Exception ex) {
			throw new RuntimeException("Can not instantiate DAO: " + daoClass,
					ex);
		}
	}

	// Inline concrete DAO implementations with no business-related data access
	// methods.
	// If we use public static nested classes, we can centralize all of them in
	// one source file.

	public static class UserDAOJpa extends
	GenericJpaDAO<User, Long> implements
	UserDAO {

		public UserDAOJpa() {
			super();
			// TODO Auto-generated constructor stub
		}

		public UserDAOJpa(Class<User> cls) {
			super(cls);
			// TODO Auto-generated constructor stub
		}
		
		
	}

	@Override
	public UserDAO getUserDAO() {
		return (UserDAO) instantiateDAO(UserDAOJpa.class);
	}

}
