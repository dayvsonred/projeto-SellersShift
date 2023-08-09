package com.core.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "TB_USER_DETAILS", schema = "core")
public class UserDetails implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private Boolean emailValid;
	private UUID emailValidCode;
	private LocalDateTime dueEmailValid;
	private Boolean cpfValid;
	private UUID cpfValidCode;
	private LocalDateTime dueCpfValid;


	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
}
