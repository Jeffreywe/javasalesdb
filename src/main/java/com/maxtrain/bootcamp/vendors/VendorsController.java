package com.maxtrain.bootcamp.vendors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/vendors")
public class VendorsController {
	
	@Autowired
	private VendorsRepository vendRepo;

	
	// custom methods
	
	@GetMapping("code/{code}")
	public ResponseEntity<Vendors> getVendorByCode(@PathVariable String code) {
		var vend = vendRepo.findByCode(code);
		if(vend.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Vendors>(vend.get(), HttpStatus.OK);
	}
	
	
	// basic 5 methods
	
	@GetMapping 
	public ResponseEntity<Iterable<Vendors>> getVendors() {
		var vendors = vendRepo.findAll();
		return new ResponseEntity<Iterable<Vendors>>(vendors, HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Vendors> getVendor(@PathVariable int id) {
		var vendor = vendRepo.findById(id);
		if(vendor.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Vendors>(vendor.get(), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Vendors> postVendor(@RequestBody Vendors vendor) {
		if(vendor == null || vendor.getId() != 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var vend = vendRepo.save(vendor);
		return new ResponseEntity<Vendors>(vend, HttpStatus.CREATED);
	}
	
	@SuppressWarnings("rawtypes")
	@PutMapping("{id}")
	public ResponseEntity putVendor(@PathVariable int id, @RequestBody Vendors vendor) {
		if(vendor == null || vendor.getId() == 0) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		var vend = vendRepo.findById(vendor.getId());
		if(vend.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		vendRepo.save(vendor);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@SuppressWarnings("rawtypes")
	@DeleteMapping("{id}")
	public ResponseEntity deleteVendor(@PathVariable int id) {
		var vendor = vendRepo.findById(id);
		if(vendor.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		vendRepo.delete(vendor.get());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
