package com.maxtrain.bootcamp.invoice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

	@Autowired
	private InvoiceRepository invRepo;
	
	// custom methods
	
	@SuppressWarnings("rawtypes")
	@PutMapping("review/{id}")
	public ResponseEntity reviewInvoice(@PathVariable int id, @RequestBody Invoice invoice) {
		var statusValue = (invoice.getTotal() <= 50) ? "APPROVED" : "REVIEW";
		invoice.setStatus(statusValue);
		return putInvoice(id, invoice);
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping("approved/{id}")
	public ResponseEntity approvedInvoice(@PathVariable int id, @RequestBody Invoice invoice) {
		invoice.setStatus("APPROVED");
		return putInvoice(id, invoice);
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping("rejected/{id}")
	public ResponseEntity rejectedInvoice(@PathVariable int id, @RequestBody Invoice invoice) {
		invoice.setStatus("REJECTED");
		return putInvoice(id, invoice);
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping("reval/{id}")
	public ResponseEntity reevaluateInvoice(@PathVariable int id, @RequestBody Invoice invoice) {
		invoice.setStatus("REEVALUATE");
		return putInvoice(id, invoice);
	}
	
	@GetMapping("reviews")
	public ResponseEntity<Iterable<Invoice>> getInvoicesInReview() {
		var invois = invRepo.findByStatus("REVIEW");
		return new ResponseEntity<Iterable<Invoice>>(invois, HttpStatus.OK);
	}
	
	// basic 5 methods
	
	@GetMapping
	public ResponseEntity<Iterable<Invoice>> getInvoices() {
		var invois = invRepo.findAll();
		return new ResponseEntity<Iterable<Invoice>>(invois, HttpStatus.OK);
	}
		
	@GetMapping("{id}")
	public ResponseEntity<Invoice> getInvoices(@PathVariable int id) {
		var invoi = invRepo.findById(id);
		if(invoi.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Invoice>(invoi.get(), HttpStatus.OK);
	}
		
	@PostMapping
	public ResponseEntity<Invoice> postInvoice(@RequestBody Invoice invoice) {
		if(invoice == null || invoice.getId() != 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		invoice.setStatus("NEW");
		var invoi = invRepo.save(invoice);
		return new ResponseEntity<Invoice>(invoi, HttpStatus.CREATED);
	}
		
	@SuppressWarnings("rawtypes")
	@PutMapping("{id}")
	public ResponseEntity putInvoice(@PathVariable int id, @RequestBody Invoice invoice) {
		if(invoice == null || invoice.getId() == 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var invoi = invRepo.findById(invoice.getId());
		if(invoi.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		invRepo.save(invoice);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
		
	@SuppressWarnings("rawtypes")
	@DeleteMapping("{id}")
	public ResponseEntity deleteInvoice(@PathVariable int id) {
		var invoi = invRepo.findById(id);
		if(invoi.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		invRepo.delete(invoi.get());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}

