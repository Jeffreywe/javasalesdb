package com.maxtrain.bootcamp.invoicelineitems;

import javax.persistence.*;

import com.maxtrain.bootcamp.invoice.Invoice;
import com.maxtrain.bootcamp.product.Product;

@Entity
public class InvoiceLineItem {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private int quantity;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="invoiceId")
	private Invoice invoice;
	
	@ManyToOne(optional=false)
	@JoinColumn(name="productId")
	private Product product;
	
	public InvoiceLineItem() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
