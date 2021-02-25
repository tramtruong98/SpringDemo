package Entities;

import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Students")
public class Student {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	@ManyToOne
	@JoinColumn(name = "id")
	private Class classe;
	@ManyToMany
	@JoinTable
	private Set<Lesson> lessons;
	
	public Student(String name, Class classe) {
		this.name = name;
		this.classe = classe;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Class getClasse() {
		return classe;
	}
	public void setClasse(Class classe) {
		this.classe = classe;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Student))
			return false;
		Student st = (Student) obj;
		return Objects.equals(this.id, st.id) && Objects.equals(this.name, st.name);
	}
	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.name);
	}
	@Override
	  public String toString() {
	    return "Student {" + "id= " + this.id + ", name= '" + this.name + ' ' + ", class='";
	  }
	
	
	

}
