package no.vipps.Models.Checkout.GetSession;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.internal.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import no.vipps.Models.VippsResponse;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetSessionInfoResponse extends VippsResponse {
    @NotNull
    private String sessionId;
    private String merchantSerialNumber;

    @NotNull
    private ExternalSessionState sessionState;
    private PaymentMethod paymentMethod;
    private ResponsePaymentDetails paymentDetails;
    private UserInfo userInfo;
    private ShippingDetails shippingDetails;
    private BillingDetails billingDetails;

    @NotNull
    private String reference;
}

@Data
class BillingDetails {
    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("streetAddress")
    private String streetAddress;

    @JsonProperty("postalCode")
    private String postalCode;

    @JsonProperty("region")
    private String region;

    @JsonProperty("country")
    private String country;
}

enum PaymentState {
    CREATED,
    AUTHORIZED,
    TERMINATED
}

@Data
class PickupPoint {
    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("postalCode")
    private String postalCode;

    @JsonProperty("city")
    private String city;

    @JsonProperty("country")
    private String country;
}

@Data
class ShippingDetails {
    @JsonProperty("firstName")
    private String firstName;

    @JsonProperty("lastName")
    private String lastName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("streetAddress")
    private String streetAddress;

    @JsonProperty("postalCode")
    private String postalCode;

    @JsonProperty("region")
    private String region;

    @JsonProperty("country")
    private String country;

    @JsonProperty("shippingMethodId")
    private String shippingMethodId;

    @JsonProperty("pickupPoint")
    private PickupPoint pickupPoint;
}

enum ExternalSessionState {
    SessionCreated,
    PaymentInitiated,
    SessionExpired,
    PaymentSuccessful,
    PaymentTerminated
}

enum PaymentMethod {
    Wallet,
    Card
}

@Data
class UserInfo {
    @NotNull
    private String sub;
    private String email;
}

@Data
class ResponsePaymentDetails {
    @NotNull
    private Amount amount;

    @NotNull
    private PaymentState state;
    private TransactionAggregate aggregate;
}

@Data
class Amount {
    private long value;
    private String currency;
}

@Data
class TransactionAggregate {
    private long id;
}