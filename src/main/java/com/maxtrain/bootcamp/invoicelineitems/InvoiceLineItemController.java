package com.maxtrain.bootcamp.invoicelineitems;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/invoiceliit")
public class InvoiceLineItemController {

	@Autowired
	private InvoiceLineItemRepository iliRepo;
	
	// basic 5 methods 
	
	@GetMapping
	public ResponseEntity<Iterable<InvoiceLineItem>> getInvoiceLiits() {
		var invliit = iliRepo.findAll();
		return new ResponseEntity<Iterable<InvoiceLineItem>>(invliit, HttpStatus.OK);
	}
		
	@GetMapping("{id}")
	public ResponseEntity<InvoiceLineItem> getInvoiceLiit(@PathVariable int id) {
		var invliit = iliRepo.findById(id);
		if(invliit.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<InvoiceLineItem>(invliit.get(), HttpStatus.OK);
	}
		
	@PostMapping
	public ResponseEntity<InvoiceLineItem> postInvoiceLiit(@RequestBody InvoiceLineItem invoiliit) {
		if(invoiliit == null || invoiliit.getId() != 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var invli = iliRepo.save(invoiliit);
		return new ResponseEntity<InvoiceLineItem>(invli, HttpStatus.CREATED);
	}
		
	@SuppressWarnings("rawtypes")
	@PutMapping("{id}")
	public ResponseEntity putInvoiceLiit(@PathVariable int id, @RequestBody InvoiceLineItem invoiliit) {
		if(invoiliit == null || invoiliit.getId() == 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}	
		var invili = iliRepo.findById(invoiliit.getId());
		if(invili.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		iliRepo.save(invoiliit);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
		
	@SuppressWarnings("rawtypes")
	@DeleteMapping("{id}")
	public ResponseEntity deleteInvoiceLiit(@PathVariable int id) {
		var invoiliit = iliRepo.findById(id);
		if(invoiliit.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		iliRepo.delete(invoiliit.get());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
