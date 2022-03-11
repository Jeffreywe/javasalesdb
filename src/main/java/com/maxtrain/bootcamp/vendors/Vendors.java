package com.maxtrain.bootcamp.vendors;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints=@UniqueConstraint(name="UIDX_code", columnNames={"code"}))
public class Vendors {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(length=30, nullable=false)
	private String name;
	@Column(length=30, nullable=false)
	private String code;
	@Column(columnDefinition="decimal(9,2) NOT NULL DEFAULT 0.0")
	private double total;
	private boolean active;

	public Vendors() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public boolean getActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
