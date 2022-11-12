package com.topprofessors.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.topprofessors.Data.ApplicationDbContextJDBC;
import com.topprofessors.Entity.Course;
import com.topprofessors.Factory.CourseFactory;

@Service
public class CourseService {

	@Autowired
	ApplicationDbContextJDBC applicationDbContextJDBC;

	//@Autowired
	//CourseRepository courseRepository;

	public Course findById(int id) {
		//return courseRepository.findById(id);
		
		try (Connection connection = ApplicationDbContextJDBC.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT * FROM xxxxxxxxxxxx WHERE id = ? ORDER BY id LIMIT 1");
			preparedStatement.setInt(1, id);
			preparedStatement.setMaxRows(1);
			ResultSet resultSet = preparedStatement.executeQuery();
			Course course = null;
			while(resultSet.next()) {
				CourseFactory courseFactory = new CourseFactory();
				course = courseFactory.createFromResultSet(resultSet);
			}
			resultSet.close();
			return course;
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Course> listAll(){
		
        ArrayList <Course> courses = new ArrayList<Course>();
        
		try (Connection connection = ApplicationDbContextJDBC.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT * FROM xxxxxxxxxxxx ORDER BY id DESC");
			ResultSet resultSet = preparedStatement.executeQuery();
			CourseFactory courseFacatory = new CourseFactory();
			while(resultSet.next()) {
				courses.add(courseFacatory.createFromResultSet(resultSet));
			}
			resultSet.close();
			return courses;
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Course> listAllActive(){
        ArrayList <Course> courses = new ArrayList<Course>();
        
		try (Connection connection = ApplicationDbContextJDBC.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT * FROM xxxxxxxxxxxx WHERE active = '1' ORDER BY id DESC");
			ResultSet resultSet = preparedStatement.executeQuery();
			CourseFactory courseFacatory = new CourseFactory();
			while(resultSet.next()) {
				courses.add(courseFacatory.createFromResultSet(resultSet));
			}
			resultSet.close();
			return courses;
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Course> listAllActiveAndVisible(){
		

        ArrayList <Course> courses = new ArrayList<Course>();
        
		try (Connection connection = ApplicationDbContextJDBC.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT * FROM xxxxxxxxxxxx WHERE active = '1' AND is_visible = '1' ORDER BY id DESC");
			ResultSet resultSet = preparedStatement.executeQuery();
			CourseFactory courseFacatory = new CourseFactory();
			while(resultSet.next()) {
				courses.add(courseFacatory.createFromResultSet(resultSet));
			}
			resultSet.close();
			return courses;
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public ArrayList<Course> listAllAvailableForUser(int userId){

        ArrayList <Course> courses = new ArrayList<Course>();
        
		try (Connection connection = ApplicationDbContextJDBC.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT course.* FROM xxxxxxxxxxxx "
					+ " INNER JOIN xxxxxxxxxxxx ON xxxxxxxxxxxx.courseId = course.id AND xxxxxxxxxxxx.userId = ?"
					+ " WHERE course.active = '1' AND course.is_visible = '1'"
					+ " ORDER BY course.id DESC");
			preparedStatement.setInt(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();
			CourseFactory courseFacatory = new CourseFactory();
			while(resultSet.next()) {
				courses.add(courseFacatory.createFromResultSet(resultSet));
			}
			resultSet.close();
			return courses;
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ArrayList<Course> listAllAvailableForUserAndNotDone(int userId){

        ArrayList <Course> courses = new ArrayList<Course>();
        
		try (Connection connection = ApplicationDbContextJDBC.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT course.* FROM xxxxxxxxxxxx "
					+ " INNER JOIN xxxxxxxxxxxx ON xxxxxxxxxxxx.course_id = course.id AND xxxxxxxxxxxx.user_id = ?"
					+ " AND xxxxxxxxxxxx.is_done = '0'"
					+ " WHERE course.active = '1' AND course.is_visible = '1'"
					+ " ORDER BY course.id DESC");
			preparedStatement.setInt(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();
			CourseFactory courseFacatory = new CourseFactory();
			while(resultSet.next()) {
				courses.add(courseFacatory.createFromResultSet(resultSet));
			}
			resultSet.close();
			return courses;
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public ArrayList<Course> listAllForUserWithAnyAccess(int userId){

        ArrayList <Course> courses = new ArrayList<Course>();
        
		try (Connection connection = ApplicationDbContextJDBC.getConnection()){
			PreparedStatement preparedStatement = connection.prepareStatement(
					"SELECT course.* FROM xxxxxxxxxxxx "
					+ " INNER JOIN xxxxxxxxxxxx ON xxxxxxxxxxxx.course_id = course.id AND xxxxxxxxxxxx.user_id = ?"
					+ " WHERE course.active = '1'"
					+ " ORDER BY course.id DESC");
			preparedStatement.setInt(1, userId);
			ResultSet resultSet = preparedStatement.executeQuery();
			CourseFactory courseFacatory = new CourseFactory();
			while(resultSet.next()) {
				courses.add(courseFacatory.createFromResultSet(resultSet));
			}
			resultSet.close();
			return courses;
		} catch(SQLException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
