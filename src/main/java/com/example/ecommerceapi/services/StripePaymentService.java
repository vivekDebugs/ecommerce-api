package com.example.ecommerceapi.services;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentLink;
import com.stripe.model.Price;
import com.stripe.model.checkout.Session;
import com.stripe.param.PaymentLinkCreateParams;
import com.stripe.param.PriceCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StripePaymentService {
    @Value("${payments.stripe.apikey}")
    private String STRIPE_API_KEY;

    @Value("${payments.stripe.unsuccessfulKeyword}")
    private String STRIPE_UNSUCCESSFUL_PAYMENT_STATUS;

    public String createStripePaymentLink() throws StripeException {
        Stripe.apiKey = STRIPE_API_KEY;

        Price price = this.createStripePrice();

        PaymentLinkCreateParams params =
          PaymentLinkCreateParams.builder()
            .addLineItem(this.createStripeLineItem(price))
            .setAfterCompletion(this.createStripeAfterCompletion())
            .build();

        PaymentLink paymentLink = PaymentLink.create(params);

        return paymentLink.getUrl();
    }

    private PaymentLinkCreateParams.AfterCompletion createStripeAfterCompletion() {
        return PaymentLinkCreateParams.AfterCompletion.builder()
                .setType(PaymentLinkCreateParams.AfterCompletion.Type.REDIRECT)
                .setRedirect(this.createStripeRedirect()).build();
    }

    private PaymentLinkCreateParams.AfterCompletion.Redirect createStripeRedirect() {
        String STRIPE_AFTER_PAYMENT_REDIRECT_URL = "http://localhost:8080/fakestore/orders/after_completion/{CHECKOUT_SESSION_ID}";
        return PaymentLinkCreateParams.AfterCompletion.Redirect.builder().setUrl(STRIPE_AFTER_PAYMENT_REDIRECT_URL).build();
    }

    private PaymentLinkCreateParams.LineItem createStripeLineItem(Price price) {
        return PaymentLinkCreateParams.LineItem.builder()
                .setPrice(price.getId())
                .setQuantity(1L)
                .build();
    }

    private PriceCreateParams.ProductData createStripeProductData() {
        return PriceCreateParams.ProductData.builder().setName("Gold Plan").build();
    }

    private Price createStripePrice() throws StripeException {
        Stripe.apiKey = STRIPE_API_KEY;

        PriceCreateParams params =
                PriceCreateParams.builder()
                        .setCurrency("usd")
                        .setUnitAmount(1000L)
                        .setProductData(this.createStripeProductData())
                        .build();

        return Price.create(params);
    }

    public Boolean isPaymentSuccessful(String checkoutSesionId) throws StripeException {
        Session checkoutSession = com.stripe.model.checkout.Session.retrieve(checkoutSesionId);
        return checkoutSession.getPaymentStatus() != STRIPE_UNSUCCESSFUL_PAYMENT_STATUS;
    }
}
