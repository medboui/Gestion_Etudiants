package com.me.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
@Entity
@Table
public class Etudiant implements Serializable {
	static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long id;
	@Column(name="NOM",length=30)
	 @NotNull
	 @NotEmpty
	 @Size(max=30,min=5)
	private String nom;
	@DateTimeFormat(pattern="yyyy-MM-dd") //il faut avoir toujours mettre cette annotations sinon tu aura une exception par la suite 
	private Date dateNaissance;
	@NotNull
	@NotEmpty
	private String email;
	private String photo;
	private String file;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public Date getDateNaissance() {
		return dateNaissance;
	}
	public void setDateNaissance(Date dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public Etudiant(String nom, Date dateNaissance, String email, String photo, String file) {
		super();
		this.nom = nom;
		this.dateNaissance = dateNaissance;
		this.email = email;
		this.photo = photo;
		this.file = file;
	}
	public Etudiant() {
		super();
	}
	
	
}
