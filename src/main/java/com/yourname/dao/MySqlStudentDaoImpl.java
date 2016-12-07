package com.yourname.dao;

import com.yourname.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
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
        final String sql = "SELECT id, name, course FROM students";
        List<Student> students = jdbcTemplate.query(sql, new StudentRowMapper());
        return students;
    }

    @Override
    public Student getStudentById(int id) {
        // SELECT column_name(s) FROM table_name where column = value
        final String sql = "SELECT id, name, course FROM students where id = ?";
        Student student = jdbcTemplate.queryForObject(sql, new StudentRowMapper(), id);
        return student;
    }

    @Override
    public void removeStudentById(int id) {
        // DELETE FROM table_name
        // WHERE some_column = some_value
        final String sql = "DELETE FROM students WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public void updateStudent(Student student) {
        // UPDATE table_name
        // SET column1=value, column2=value2,...
        // WHERE some_column=some_value
        final String sql = "UPDATE students SET name = ?, course = ? WHERE id = ?";
        final int id = student.getId();
        final String name = student.getName();
        final String course = student.getCourse();
        jdbcTemplate.update(sql, new Object[]{name, course, id});
    }

    @Override
    public void insertStudentToDb(Student student) {
        // INSERT INTO table_name (column1, column2, column3,...)
        // VALUES (value1, value2, value3,...)
        final String sql = "INSERT INTO students (name, course) VALUES (?, ?)";
        final String name = student.getName();
        final String course = student.getCourse();
        jdbcTemplate.update(sql, new Object[]{name, course});

    }
}
