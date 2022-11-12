package com.topprofessors.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.topprofessors.Component.HibernateUtil;
import com.topprofessors.Data.ApplicationDbContextJDBC;
import com.topprofessors.Entity.User;
import com.topprofessors.Factory.UserFactory;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	ApplicationDbContextJDBC applicationDbContextJDBC;
	   
	//@Autowired
	//UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//User user = userRepository.findByUsername(username);
		User user = findByUsername(username);
		if(user == null)throw new UsernameNotFoundException("not_found");
		return new org.springframework.security.core.userdetails.User(Integer.toString(user.getId()), user.getPassword(), new ArrayList<>());
	}

	public UserDetails loadUserById(int id) throws UsernameNotFoundException {
		//User user = userRepository.findById(id);
		User user = findById(id);
		if(user == null)throw new UsernameNotFoundException("not_found");
		return new org.springframework.security.core.userdetails.User(Integer.toString(user.getId()), user.getPassword(), new ArrayList<>());
	}
	
	public User findById(int id) {
		//return userRepository.findById(id);
		
		try (Connection connection = ApplicationDbContextJDBC.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT * FROM xxxxxxxxxxxx WHERE id = ? ORDER BY id LIMIT 1");
			preparedStatement.setInt(1, id);
			preparedStatement.setMaxRows(1);
			ResultSet resultSet = preparedStatement.executeQuery();
			User user = null;
			while(resultSet.next()) {
				UserFactory userFactory = new UserFactory();
				user = userFactory.createFromResultSet(resultSet);
			}
			resultSet.close();
			return user;
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public User findByUsername(String username) {
		//return userRepository.findByUsername(username);
		
		try (Connection connection = ApplicationDbContextJDBC.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT * FROM xxxxxxxxxxxx WHERE username = ? ORDER BY id LIMIT 1");
			preparedStatement.setString(1, username);
			preparedStatement.setMaxRows(1);
			ResultSet resultSet = preparedStatement.executeQuery();
			User user = null;
			while(resultSet.next()) {
				UserFactory userFactory = new UserFactory();
				user = userFactory.createFromResultSet(resultSet);
			}
			resultSet.close();
			return user;
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	

    public User findByUsernameExceptId(int userId, String username)
    {
    	/*
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            @SuppressWarnings({ "unchecked" })
			Query<User> query = session.createQuery("FROM " + User.class.getSimpleName() + " WHERE username = :username AND id != :userId order by id");
			query.setParameter("userId", userId);
			query.setParameter("username", username);
			User user = query.getSingleResult();
            
            return user;
        } catch(NoResultException noResultException) {
        	return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        */
		try (Connection connection = ApplicationDbContextJDBC.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT * FROM xxxxxxxxxxxx WHERE username = ? AND id != ? ORDER BY id LIMIT 1");
			preparedStatement.setString(1, username);
			preparedStatement.setInt(2, userId);
			preparedStatement.setMaxRows(1);
			ResultSet resultSet = preparedStatement.executeQuery();
			User user = null;
			while(resultSet.next()) {
				UserFactory userFactory = new UserFactory();
				user = userFactory.createFromResultSet(resultSet);
			}
			resultSet.close();
			return user;
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
        return null;
    }
	
	public User add(String username, String passwordHash) {
		Date dateOfAdd = new Date();
		
		User user = new User();
		user.setUsername(username);
		user.setPassword(passwordHash);
		user.setDateOfAdd(new Timestamp(dateOfAdd.getTime()));
		
		return add(user);
	}
	
	private User add(User user) {
		/*
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            session.close();
            return user;
        } catch (Exception e) {
        	System.out.println("Ошибка UserService add Exception: " + e.toString());
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return null;
        
        "INSERT INTO user(`username`, `password`, `auth_key`, `access_token`, `active`, `dateOfAdd`, `secondname`, `firstname`,"
				+ " `patronymic`, `datebirthday`, `placebirthday`, `nationality`, `document`, `whendocument`, `address`, `addressindex`"
				+ ", `snils`, `region_id`, `telephone`, `education`, `placeeducation`, `yeareducation`, `diplom`, `specialization`,"
				+ " `placework`, `office`, `uraddress`, `organizationemail`, `organizationtelfax`, `agreement`, `statusROP`, `granted_zayavki_courses`)"
				+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
        */
		
		try (Connection connection = ApplicationDbContextJDBC.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(
			"INSERT INTO user(username, password, auth_key, access_token, active, dateOfAdd, secondname, firstname,"
			+ " patronymic, datebirthday, placebirthday, nationality, document, whendocument, address, addressindex"
			+ ", snils, region_id, telephone, education, placeeducation, yeareducation, diplom, specialization,"
			+ " placework, office, uraddress, organizationemail, organizationtelfax, agreement, status_rop, granted_zayavki_courses"
			+ ", forget_last_try, forget_code, forget_try_count)"
			+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getAuthKey());
			preparedStatement.setString(4, user.getAccessToken());
			preparedStatement.setInt(5, user.getActive());
			preparedStatement.setTimestamp(6, user.getDateOfAdd());
			preparedStatement.setString(7, user.getSecondname());
			preparedStatement.setString(8, user.getFirstname());
			preparedStatement.setString(9, user.getPatronymic());
			preparedStatement.setDate(10, user.getDatebirthday());
			preparedStatement.setString(11, user.getPlacebirthday());
			preparedStatement.setString(12, user.getNationality());
			preparedStatement.setString(13, user.getDocument());
			preparedStatement.setString(14, user.getWhendocument());
			preparedStatement.setString(15, user.getAddress());
			preparedStatement.setString(16, user.getAddressindex());
			preparedStatement.setString(17, user.getSnils());
			preparedStatement.setInt(18, user.getRegionId());
			preparedStatement.setString(19, user.getTelephone());
			preparedStatement.setInt(20, user.getEducation());
			preparedStatement.setString(21, user.getPlaceeducation());
			preparedStatement.setString(22, user.getYeareducation());
			preparedStatement.setString(23, user.getDiplom());
			preparedStatement.setString(24, user.getSpecialization());
			preparedStatement.setString(25, user.getPlacework());
			preparedStatement.setString(26, user.getOffice());
			preparedStatement.setString(27, user.getUraddress());
			preparedStatement.setString(28, user.getOrganizationemail());
			preparedStatement.setString(29, user.getOrganizationtelfax());
			preparedStatement.setInt(30, user.getAgreement());
			preparedStatement.setInt(31, user.getStatusROP());
			preparedStatement.setInt(32, user.getGrantedZayavkiCourses());
			
			preparedStatement.setTimestamp(33, user.getForgetLastTry());
			preparedStatement.setString(34, user.getForgetCode());
			preparedStatement.setInt(35, user.getForgetTryCount());
			
			preparedStatement.executeUpdate();

			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				int newId = resultSet.getInt(1);
				user.setId(newId);
			} else {
				return null;
			}
			resultSet.close();
			return user;
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
        return null;
	}
	
    public boolean update(User user) {
    	/*
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return false;
        
        "UPDATE xxxxxxxxxxx SET `username` = ?, `password` = ?, `auth_key` = ?, `access_token` = ?, `active` = ?, `dateOfAdd` = ?, `secondname` = ?, `firstname` = ?,"
				+ " `patronymic` = ?, `datebirthday` = ?, `placebirthday` = ?, `nationality` = ?, `document` = ?, `whendocument` = ?, `address` = ?, `addressindex`"
				+ " = ?, `snils` = ?, `region_id` = ?, `telephone` = ?, `education` = ?, `placeeducation` = ?, `yeareducation` = ?, `diplom` = ?, `specialization` = ?,"
				+ " `placework` = ?, `office` = ?, `uraddress` = ?, `organizationemail` = ?, `organizationtelfax` = ?, `agreement` = ?, `statusROP` = ?, `granted_zayavki_courses` = ?"
				+ " WHERE id = ?"
        */

		try (Connection connection = ApplicationDbContextJDBC.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(
			"UPDATE xxxxxxxxxxxx SET username = ?, password = ?, auth_key = ?, access_token = ?, active = ?, date_of_add = ?, secondname = ?, firstname = ?,"
			+ " patronymic = ?, datebirthday = ?, placebirthday = ?, nationality = ?, document = ?, whendocument = ?, address = ?, addressindex"
			+ " = ?, snils = ?, region_id = ?, telephone = ?, education = ?, placeeducation = ?, yeareducation = ?, diplom = ?, specialization = ?,"
			+ " placework = ?, office = ?, uraddress = ?, organizationemail = ?, organizationtelfax = ?, agreement = ?, status_rop = ?, granted_zayavki_courses = ?"
			+ ", forget_last_try = ?, forget_code = ?, forget_try_count = ?"
			+ " WHERE id = ?");
			
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getAuthKey());
			preparedStatement.setString(4, user.getAccessToken());
			preparedStatement.setInt(5, user.getActive());
			preparedStatement.setTimestamp(6, user.getDateOfAdd());
			preparedStatement.setString(7, user.getSecondname());
			preparedStatement.setString(8, user.getFirstname());
			preparedStatement.setString(9, user.getPatronymic());
			preparedStatement.setDate(10, user.getDatebirthday());
			preparedStatement.setString(11, user.getPlacebirthday());
			preparedStatement.setString(12, user.getNationality());
			preparedStatement.setString(13, user.getDocument());
			preparedStatement.setString(14, user.getWhendocument());
			preparedStatement.setString(15, user.getAddress());
			preparedStatement.setString(16, user.getAddressindex());
			preparedStatement.setString(17, user.getSnils());
			preparedStatement.setInt(18, user.getRegionId());
			preparedStatement.setString(19, user.getTelephone());
			preparedStatement.setInt(20, user.getEducation());
			preparedStatement.setString(21, user.getPlaceeducation());
			preparedStatement.setString(22, user.getYeareducation());
			preparedStatement.setString(23, user.getDiplom());
			preparedStatement.setString(24, user.getSpecialization());
			preparedStatement.setString(25, user.getPlacework());
			preparedStatement.setString(26, user.getOffice());
			preparedStatement.setString(27, user.getUraddress());
			preparedStatement.setString(28, user.getOrganizationemail());
			preparedStatement.setString(29, user.getOrganizationtelfax());
			preparedStatement.setInt(30, user.getAgreement());
			preparedStatement.setInt(31, user.getStatusROP());
			preparedStatement.setInt(32, user.getGrantedZayavkiCourses());
			
			preparedStatement.setTimestamp(33, user.getForgetLastTry());
			preparedStatement.setString(34, user.getForgetCode());
			preparedStatement.setInt(35, user.getForgetTryCount());
			
			preparedStatement.setInt(36, user.getId());
			
			preparedStatement.executeUpdate();
			
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
        return false;
    }

    public boolean delete(int id) {
    	User user = findById(id);
    	if(user == null)return false;
    	return delete(user);
    }
    
    public boolean delete(User user) {
    	if(user == null)return false;
    	
    	/*
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.delete(user);
            //User User = session.get(User.class, id);
            //if (User != null) {
            //    session.delete(User);
            //    System.out.println("User is deleted");
            //}

            transaction.commit();
            session.close();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        return false;
        */

		try (Connection connection = ApplicationDbContextJDBC.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(
				"DELETE FROM user WHERE id = ?");
			preparedStatement.setInt(1, user.getId());
			preparedStatement.executeUpdate();
			return true;
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
        return false;
    }
    
    
    
    

	public ArrayList<User> listAll(){
		
        ArrayList <User> users = new ArrayList<User>();
        
		try (Connection connection = ApplicationDbContextJDBC.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT * FROM xxxxxxxxxxxx ORDER BY id DESC");
			ResultSet resultSet = preparedStatement.executeQuery();
			UserFactory userFactory = new UserFactory();
			while(resultSet.next()) {
				users.add(userFactory.createFromResultSet(resultSet));
			}
			resultSet.close();
			return users;
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

    /*
    public List<User> searchUsers(String queryString, int page)
    {
        page--;
        int take = 30;
        int skip = page * take;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        	
        	if (!queryString.isEmpty()) {
	            @SuppressWarnings({ "unchecked" })
				Query<User> query = session.createQuery("FROM " + User.class.getSimpleName() 
						+ " WHERE username LIKE :queryString OR secondname LIKE :queryString OR firstname LIKE :queryString OR telephone LIKE :queryString"
						+ " order by id DESC LIMIT :skip, :take");
				query.setParameter("queryString", "%"+queryString+"%");
				query.setParameter("skip", skip);
				query.setParameter("take", take);
	            List<User> users = query.getResultList();
	            
	            return users;
        	} else {
	            @SuppressWarnings({ "unchecked" })
				Query<User> query = session.createQuery("FROM " + User.class.getSimpleName() + " order by id DESC LIMIT :skip, :take");
				query.setParameter("skip", skip);
				query.setParameter("take", take);
	            List<User> users = query.getResultList();
	            
	            return users;
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



    public int searchCount(String queryString)
    {
        
		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        	
        	if (!queryString.isEmpty()) {
				@SuppressWarnings("rawtypes")
				Query query = session.createQuery("SELECT COUNT(*) FROM " + User.class.getSimpleName() 
						+ " WHERE username LIKE :queryString OR secondname LIKE :queryString OR firstname LIKE :queryString OR telephone LIKE :queryString"
						+ " order by id DESC");
				query.setParameter("queryString", "%"+queryString+"%");
				
	            return (int)query.uniqueResult();
        	} else {
				@SuppressWarnings("rawtypes")
				Query query = session.createQuery("SELECT COUNT(*) FROM " + User.class.getSimpleName() + " order by id DESC");
	            
	            return (int)query.uniqueResult();
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    */
    
}
