package com.quodex.dineboard.service;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RazorpayService {
    @Value("${razorpay.key_id}")
    private String keyId;

    @Value("${razorpay.key_secret}")
    private String keySecret;

    public JSONObject createOrder(int amount) throws RazorpayException {
        RazorpayClient client = new RazorpayClient(keyId, keySecret);
        JSONObject options = new JSONObject();
        options.put("amount", amount * 100); // in paise
        options.put("currency", "INR");
        options.put("receipt", UUID.randomUUID().toString());
        options.put("payment_capture", true);
        Order order = client.orders.create(options);
        return order.toJson();
    }
}
