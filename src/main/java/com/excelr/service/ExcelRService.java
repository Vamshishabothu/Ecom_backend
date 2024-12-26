package com.excelr.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.excelr.model.Headphones;
import com.excelr.model.Laptops;
import com.excelr.model.Login;
import com.excelr.model.Mobiles;
import com.excelr.model.Pods;
import com.excelr.model.Smartwatches;
import com.excelr.repo.HeadphonesRepo;
import com.excelr.repo.LaptopsRepo;
import com.excelr.repo.LoginRepo;
import com.excelr.repo.MobilesRepo;
import com.excelr.repo.PodsRepo;
import com.excelr.repo.SmartwatchesRepo;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;

@Service
public class ExcelRService {
	@Autowired
	private LaptopsRepo laptopsRepo;
	@Autowired
	private MobilesRepo mobilesRepo;
	@Autowired
	private HeadphonesRepo headphonesRepo;
	@Autowired
	private LoginRepo loginRepo;
	@Autowired
	private SmartwatchesRepo smartwatchesRepo;
	@Autowired
	private PodsRepo podsRepo;

	public List<Laptops> getAllLaptops(){
		return laptopsRepo.findAll();
	}
	public List<Mobiles> getAllMobiles(){
		return mobilesRepo.findAll();
	}
	
	public List<Headphones> getAllHeadphones(){
		return headphonesRepo.findAll();
	}
	public List<Smartwatches> getAllSmartwatches(){
		return smartwatchesRepo.findAll();
	}
	public List<Pods> getAllPods(){
		return podsRepo.findAll();
	}
	
	
	public Optional<Laptops> getSingleRecord(int pid){
		return laptopsRepo.findById(pid);
	}
	public Optional<Mobiles> getSingleRecord1(int pid){
		return mobilesRepo.findById(pid);
	}
	public Optional<Headphones> getSingleRecord2(int pid){
		return headphonesRepo.findById(pid);
	}
	public Optional<Smartwatches> getSingleRecord3(int pid){
		return smartwatchesRepo.findById(pid);
	}
	public Optional<Pods> getSingleRecord4(int pid){
		return podsRepo.findById(pid);
	}
	public Optional<Login> Login(String username){
		return loginRepo.findByusername(username);
	}
	
	
	
	public String createOrder(int amount, String currency, String receipt) throws RazorpayException {
        RazorpayClient razorpay = new RazorpayClient("rzp_live_0CAWJFt3q8oaUX", "GbtM4BCQJJyyBA4L0NjnwmZV");

        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", amount * 100); // Amount in paise
        orderRequest.put("currency", currency);
        orderRequest.put("receipt", receipt);

        Order order = razorpay.orders.create(orderRequest);
        return order.toString();
    }

    public boolean verifyPayment(String orderId, String paymentId, String signature) {
        String generatedSignature = HmacSHA256(orderId + "|" + paymentId, "GbtM4BCQJJyyBA4L0NjnwmZV");
        return generatedSignature.equals(signature);
    }

    private String HmacSHA256(String data, String secret) {
        try {
            javax.crypto.Mac mac = javax.crypto.Mac.getInstance("HmacSHA256");
            mac.init(new javax.crypto.spec.SecretKeySpec(secret.getBytes(), "HmacSHA256"));
            byte[] hmacData = mac.doFinal(data.getBytes());
            return javax.xml.bind.DatatypeConverter.printHexBinary(hmacData).toLowerCase();
        } catch (Exception e) {
            throw new RuntimeException("Failed to calculate HMAC SHA256", e);
        }
    }



}
