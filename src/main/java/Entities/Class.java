package Entities;

import java.util.Objects;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Classes")
public class Class {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	@OneToMany(mappedBy = "classe")
	private Set<Student> students;
	public Class(Long id, String name) {
		this.id = id;
		this.name = name;
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
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Class))
				return false;
		Class c = (Class) obj;
		return Objects.equals(this.id, c.id) && Objects.equals(this.name, c.name);
	}
	@Override
	public int hashCode() {
		return Objects.hash(this.id, this.name);
	}
	@Override
	public String toString() {
		return "Class: " + this.name;
	}

}
