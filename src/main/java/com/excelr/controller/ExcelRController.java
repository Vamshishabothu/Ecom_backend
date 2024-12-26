package com.excelr.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.excelr.model.Headphones;
import com.excelr.model.Laptops;
import com.excelr.model.Login;
import com.excelr.model.Mobiles;
import com.excelr.model.Pods;
import com.excelr.model.Smartwatches;
import com.excelr.repo.SmartwatchesRepo;
import com.excelr.service.ExcelRService;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin("*")
public class ExcelRController {
	@Autowired
	private ExcelRService excelRService;
	@GetMapping("/laptops")
	public List<Laptops> allLaptops(){
		return excelRService .getAllLaptops();
	}
    @GetMapping("/mobiles")
    public List<Mobiles> allMobiles(){
    	return excelRService.getAllMobiles();
    }
    @GetMapping("/headphones")
    public List<Headphones> allHeadphones(){
    	return excelRService.getAllHeadphones();
    }
   @GetMapping("/smartwatches")
   public List<Smartwatches> allSmartwatches(){
	   return excelRService.getAllSmartwatches();
   }
   @GetMapping("/pods")
   public List<Pods> allPods(){
	   return excelRService.getAllPods();
   }
   

    
    
    
    //for
    
    @GetMapping("/laptops/{pid}")
    public Optional<Laptops> getLaptopById(@PathVariable int pid){
    	return excelRService.getSingleRecord(pid);
    }
    
    @GetMapping("/mobiles/{pid}")
    public Optional<Mobiles> getMobileById(@PathVariable int pid){
    	return excelRService.getSingleRecord1(pid);
    }
    
    @GetMapping("/headphones/{pid}")
    public Optional<Headphones> getHeadphonesById(@PathVariable int pid){
    	return excelRService.getSingleRecord2(pid);
    }
    @GetMapping("/smartwatches/{pid}")
    public Optional<Smartwatches> getSmartwatchesById(@PathVariable int pid){
    	return excelRService.getSingleRecord3(pid);
    }
    @GetMapping("/pods/{pid}")
    public Optional<Pods> getPodsById(@PathVariable int pid){
    	return excelRService.getSingleRecord4(pid);
    }
    
    @PostMapping("/login")
    public ResponseEntity<?> login_fn(@RequestBody Login log){
    	Optional<Login> Login=excelRService.Login(log.getUsername());
    	HashMap<String, String> response =new HashMap<>();
    	if(Login.isPresent() && Login.get().getPassword().equals(log.getPassword())) {
    		response.put("login", "success");
    	}else {
    		response.put("login", "Failed");
    	}
    	return ResponseEntity.ok(response);
    	
    }
    
    
    
    /*
	 * razorpay
	 */
	@PostMapping("/create-order")
    public ResponseEntity<?> createOrder(@RequestBody Map<String, Object> data) {
        try {
            int amount = (int) data.get("amount");
            String currency = (String) data.get("currency");
            String receipt = (String) data.get("receipt");

            String order = excelRService.createOrder(amount, currency, receipt);
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to create order");
        }
    }
	@PostMapping("/verify-payment")
    public ResponseEntity<?> verifyPayment(@RequestBody Map<String, String> data) {
        String orderId = data.get("razorpay_order_id");
        String paymentId = data.get("razorpay_payment_id");
        String signature = data.get("razorpay_signature");

        boolean isValid = excelRService.verifyPayment(orderId, paymentId, signature);

        if (isValid) {
            return ResponseEntity.ok("Payment Verified");
        } else {
            return ResponseEntity.badRequest().body("Payment Verification Failed");
        }
    }
    
   
}
