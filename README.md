ZarinPal In App Billing - Purchase SDK | MPG
============================================
ZarinPal Purchase SDK Provides payment methods on your Android Application.
[پارسی](https://www.zarinpal.com/docs/sdk/android/installation.html)


Introduction
=============
ZarinPal in-app purchases are the simplest solution to selling digital products or content on Android apps. So many app developers who want to sell digital goods or offer premium membership to users can simply use the it, in-app billing process for smooth and easy checkouts.


<p align="center" width="100%">
<img src="https://raw.githubusercontent.com/alirezabashi98/zarinpal-sdk/main/new_logo.svg" alt="sample" width="300" height="100"/>
</p>


Requirements
============

*   Android 7.0 (API level 24) and above

Installation
============

**Step 1**

Add this to your root settings.gradle at the end of repositories.
```gradle
    dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        ...
        maven { url = uri("https://jitpack.io") }
        }
    }
```    

**Step 2**

Add the dependency:
```gradle
    dependencies {
        implementation("com.github.ZarinPal:Android-SDK-Kotlin:v.1.1.2")
    }
```

How to use
==========

*   add Permissions in your `Manifest.xml`:
```xml
   <uses-permission android:name="android.permission.INTERNET"/>
```    

Initialize the billing client
=============================

**Step 1**
* To start using the SDK, you need to configure the settings for `merchant_id` and `sandbox`. These settings allow you to use the SDK in either sandbox (testing) or live mode. In certain specific cases, such as refunds or transaction management, you may also need an `access_token`.
```kotlin
import com.example.zarinpal.ZarinPal
import com.example.zarinpal.data.remote.dto.Config

val zarinPal = ZarinPal(
    Config(
        merchantId = "your-merchant-id",
        packageName = "your.package.name",//required parameter
        token = "your-access-token",
        sandBox = true
    )
)
```    

**Step 2 - Payment Request**
* The `createPayment` method allows you to create a new payment request and redirect the user to the payment gateway. This method is used to send payment-related information and receive an `authority` to guide the user to the payment page.
```kotlin
import com.example.zarinpal.ZarinPal
import com.example.zarinpal.data.remote.dto.create.CreatePaymentDataResponse
import com.example.zarinpal.data.remote.dto.create.CreatePaymentRequest

val request = CreatePaymentRequest(
    amount = 20000,
    callbackUrl = "https://yourwebsite.com/callback",
    description = "test"
)

CoroutineScope(Dispatchers.IO).launch {
    try{
        val response =
            zarinPal.createPayment(request, redirectUrl = {  paymentGatewayUri, status ->
                if (status == 100)
                    Log.v("ZP_Log",paymentGatewayUri)
            })
        Log.v("ZP_Log","$response")
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
}
```    

**Payment Verification**
* The `paymentVerify` method allows you to check and verify the transaction status after the user returns from the payment gateway. Using this method, you can validate the payment details and confirm the transaction if the payment was successful.
```kotlin
import com.example.zarinpal.ZarinPal
import com.example.zarinpal.data.remote.dto.verification.PaymentVerificationDataResponse
import com.example.zarinpal.data.remote.dto.verification.PaymentVerifyRequest

val request = PaymentVerifyRequest(
    amount = amount,
    authority = "your authority"
)

CoroutineScope(Dispatchers.IO).launch {
    try{
        val response = zarinPal.paymentVerify(request)
        Log.v("ZP_Log","$response")
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
}
```   

**Transaction Inquiry**
* The `paymentInquiry` method allows you to check and inquire about the status of a transaction. This method is used when you want to obtain more detailed information about the transaction status after creating a payment request or confirming a payment.
```kotlin
import com.example.zarinpal.ZarinPal
import com.example.zarinpal.data.remote.dto.inquiry.PaymentInquiryDataResponse
import com.example.zarinpal.data.remote.dto.inquiry.PaymentInquiryRequest

val request = PaymentInquiryRequest(authority = "authority")

CoroutineScope(Dispatchers.IO).launch {
    try{
        val response = zarinPal.paymentInquiry(request)
        Log.v("ZP_Log","$response")
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
}
```  

**Inquiry of Unverified Transactions**

* The method for inquiring unverified transactions allows you to retrieve a list of unverified transactions from the payment gateway. This method can be used to review transactions whose status has not yet been determined.

```kotlin
import com.example.zarinpal.ZarinPal
import com.example.zarinpal.data.remote.dto.unVerified.PaymentUnVerifiedDataResponse
import com.example.zarinpal.data.remote.dto.unVerified.PaymentUnVerifiedRequest
import com.example.zarinpal.data.remote.dto.Config

val zarinPal = ZarinPal(
    Config(
        merchantId = "your-merchant-id",
    )
)

CoroutineScope(Dispatchers.IO).launch {
    try{
        val response = zarinPal.paymentUnVerified()
        Log.v("ZP_Log","$response")
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
}
```   

**Transaction Reversal**

* The `transactionReversal` method allows you to reverse successful transactions that occurred within 30 minutes of the payment, refunding the amount to the buyer's account without any fees.
* To use this service, your server's IP must be configured for the payment gateway. Otherwise, you will encounter error 62-.

```kotlin
import com.example.zarinpal.ZarinPal
import com.example.zarinpal.data.remote.dto.Config
import com.example.zarinpal.data.remote.dto.reverse.PaymentReverseDataResponse
import com.example.zarinpal.data.remote.dto.reverse.PaymentReverseRequest

val zarinPal = ZarinPal(
    Config(
        merchantId = "your-merchant-id",
    )
)


CoroutineScope(Dispatchers.IO).launch {
    try{
        val request = PaymentReverseRequest(
            authority = "authority"
        )
        val response = zarinPal.paymentReverse(request)
        Log.v("ZP_Log","$response")
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
}
```    

**Refund**

* The `refund` method allows you to instantly or in batch cycles refund the full amount or a part of it to the buyer's account in case of order changes or cancellations, incorrect payments, or any other requirement to return the funds.

```kotlin
import com.example.zarinpal.ZarinPal
import com.example.zarinpal.data.remote.dto.Config
import com.example.zarinpal.data.remote.dto.reverse.PaymentReverseDataResponse
import com.example.zarinpal.data.remote.dto.reverse.PaymentReverseRequest

val zarinPal = ZarinPal(
    Config(
        merchantId = "your-merchant-id",
    )
)


CoroutineScope(Dispatchers.IO).launch {
    try{
        val request = PaymentReverseRequest(
            authority = "authority"
        )
        val response = zarinPal.paymentReverse(request)
        Log.v("ZP_Log","$response")
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
}
```  

**Transaction List**

* The `transactionList` method allows you to retrieve all transactions related to a specific terminal. This method can be used to view the status of transactions and filter them.

```kotlin
import com.example.zarinpal.ZarinPal
import com.example.zarinpal.data.remote.dto.Config
import com.example.zarinpal.ZarinPal
import com.example.zarinpal.data.remote.dto.transaction.Session
import com.example.zarinpal.data.remote.dto.transaction.TransactionRequest
import com.example.zarinpal.data.remote.enum.FilterEnum

val zarinPal = ZarinPal(
    Config(
        token = "your-access-token",
    )
)

CoroutineScope(Dispatchers.IO).launch {
    try{
        val request = TransactionRequest(
            terminalId = textFieldTerminalId.text,
            filter = FilterEnum.ALL,
            limit = 25,
            offset = 0
        )
        val response = zarinPal.getTransactions(request)
        Log.v("ZP_Log","$response")
    } catch (ex: Exception) {
        ex.printStackTrace()
    }
}
```
