package com.spring.AirBnb.service;

import com.spring.AirBnb.entity.Booking;

public interface CheckoutService {
    String getCheckoutSession(Booking booking, String successUrl, String failureUrl);
}
