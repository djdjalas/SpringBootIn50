package com.yourname.dao;

import com.yourname.entity.Student;
import com.yourname.queries.StudentsQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

@Repository("mysql")
public class MySqlStudentDaoImpl implements StudentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private StudentsQueries studentsQueries;

//    @Value("${yourname.database.students.query.selectall}")
//    private String SELECT_ALL;
//
//    @Value("${yourname.database.students.query.selectallbyid}")
//    private String SELECT_ALL_BY_ID;
//
//    @Value("${yourname.database.students.query.delete}")
//    private String DELETE;
//
//    @Value("${yourname.database.students.query.update}")
//    private String UPDATE;
//
//    @Value("${yourname.database.students.query.insert}")
//    private String INSERT;


    private static class StudentRowMapper implements RowMapper<Student> {

        @Override
        public Student mapRow(ResultSet resultSet, int i) throws SQLException {
            Student student = new Student();
            student.setId(resultSet.getInt("id"));
            student.setName(resultSet.getString("name"));
            student.setCourse(resultSet.getString("course"));
            return student;
        }
    }


    @Override
    public Collection<Student> getAllStudents() {
        // SELECT column_name(s) FROM table_name
        List<Student> students = jdbcTemplate.query(studentsQueries.getSelectall(), new StudentRowMapper());
        return students;
    }

    @Override
    public Student getStudentById(int id) {
        // SELECT column_name(s) FROM table_name where column = value
        Student student = jdbcTemplate.queryForObject(studentsQueries.getSelectallbyid(), new StudentRowMapper(), id);
        return student;
    }

    @Override
    public void removeStudentById(int id) {
        // DELETE FROM table_name
        // WHERE some_column = some_value
        jdbcTemplate.update(studentsQueries.getDelete(), id);
    }

    @Override
    public void updateStudent(Student student) {
        // UPDATE table_name
        // SET column1=value, column2=value2,...
        // WHERE some_column=some_value
        final int id = student.getId();
        final String name = student.getName();
        final String course = student.getCourse();
        jdbcTemplate.update(studentsQueries.getUpdate(), new Object[]{name, course, id});
    }

    @Override
    public void insertStudentToDb(Student student) {
        // INSERT INTO table_name (column1, column2, column3,...)
        // VALUES (value1, value2, value3,...)
        final String name = student.getName();
        final String course = student.getCourse();
        jdbcTemplate.update(studentsQueries.getInsert(), new Object[]{name, course});

    }
}
