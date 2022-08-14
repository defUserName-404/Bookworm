package com.defusername.bookworm.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", nullable = false, unique = true)
	private Long id;

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "contact", nullable = false)
	private String contact;

	@Column(name = "date_of_birth", nullable = false)
	private Date dateOfBirth;

	@Column(name = "membership_date", nullable = false)
	private Date dateOfMembership;

	@Column(name = "gender", nullable = false)
	private Gender gender;

	@Column(name = "user_type", nullable = false)
	private UserType userType;

	public User(
			String name,
			String email,
			String contact,
			Date dateOfBirth,
			Date dateOfMembership,
			Gender gender,
			UserType userType
	) {
		this.name = name;
		this.email = email;
		this.contact = contact;
		this.dateOfBirth = dateOfBirth;
		this.dateOfMembership = dateOfMembership;
		this.gender = gender;
		this.userType = userType;
	}
}
