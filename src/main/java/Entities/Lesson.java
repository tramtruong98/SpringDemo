package Entities;

import java.sql.Date;
import java.sql.Time;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="Lessons")
public class Lesson {
	@Id
	@GeneratedValue
	private Long id;
	private Date date;
	private Time time;
	@ManyToMany(mappedBy = "lessons")
	private Set<Student> students;
	public Lesson(Date date, Time time, Set<Student> students) {
		this.date = date;
		this.time = time;
		this.students = students;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	public Set<Student> getStudents() {
		return students;
	}
	public void setStudents(Set<Student> students) {
		this.students = students;
	}

}
