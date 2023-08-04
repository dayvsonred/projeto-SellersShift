package com.product.entities;

import lombok.*;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "TB_USER", schema = "core")
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	private String name;

	@NotNull
	@Column(unique = true)
	private String email;
	@NotNull
	private String password;

	private String latitude;
	private String longitude;
	private String offshoot;
	private String cpf;

	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "TB_USER_ROLE",  schema = "core",
		joinColumns = @JoinColumn(name = "user_id"),
		inverseJoinColumns = @JoinColumn(name = "role_id")			
	)
	private Set<Role> roles = new HashSet<>();

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
	private UserDetails userDetails;
	
	public User() {
	}

	public User(UUID id, String name, String email, String password, String latitude, String longitude, String offshoot, String cpf ) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.latitude = latitude;
		this.longitude = longitude;
		this.offshoot = offshoot;
		this.cpf = cpf;
	}

	public User(String name, String email, String password, String latitude, String longitude, String offshoot, String cpf) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
		this.latitude = latitude;
		this.longitude = longitude;
		this.offshoot = offshoot;
		this.cpf = cpf;
		this.roles = this.defaultRolesNewUser();
	}

	private HashSet<Role> defaultRolesNewUser(){
		HashSet<Role> roles = new HashSet<Role>();
		roles.add(new Role(1l , "ROLE_OPERATOR"));
		return roles;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof User)) return false;
		User user = (User) o;
		return Objects.equals(getId(), user.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId());
	}
}
