Feature: GoPay Pin Regression

  @gopay_pin @gojek_regression @go_pay_regression
  Scenario: Set pin from MyAccount of Go-Pay And Update the pin
    Given Consumer sign up via API
    Then Consumer logs in
    Then Consumer on Home screen taps on MyAccount
    Then Consumer on MyAccount screen taps on goPay
    Then Consumer on GoPaySettings screen taps on setPin
    Then Consumer on GoPaySettings screen taps on setMyPin
    And Intent:Go Pay Set Pin for New User

  @gopay_pin @gojek_regression @go_pay_regression
  Scenario: Reset pin
    Given Consumer sign up via API
    Then Consumer logs in
    Then Consumer on Home screen taps on goPayTile
    Then Consumer on GoPayHome screen taps on activatePinNowButton
    And Intent:Go Pay Set Pin for New User
    Then Consumer on GoPayHome screen taps on settingsIcon
    And Consumer on GoPaySettings screen taps on changePin
    And Intent:Go Pay Change Pin

  @gopay_pin @gojek_regression @go_pay_regression
  Scenario:Error message for trying with easy pin
    Given Consumer sign up via API
    Then Consumer logs in
    Then Consumer on Home screen taps on goPayTile
    Then Consumer on GoPayHome screen taps on activatePinNowButton
    And Intent:Go Pay Set Easy Pin

  @gopay_pin @gojek_regression @go_pay_regression
  Scenario: Error message for wrong confirm pin
    Given Consumer sign up via API
    Then Consumer logs in
    Then Consumer on Home screen taps on goPayTile
    Then Consumer on GoPayHome screen taps on activatePinNowButton
    And Intent:Go Pay Set Wrong Pin

  @gopay_pin
  Scenario: Error message for wrong current pin
    Given Consumer sign up via API
    Then Consumer logs in
    Then Consumer on Home screen taps on goPayTile
    Then Consumer on GoPayHome screen taps on activatePinNowButton
    And Intent:Go Pay Set Pin for New User
    Then Consumer on GoPayHome screen taps on settingsIcon
    And Consumer on GoPaySettings screen taps on changePin
    And Intent:Go Pay Wrong Current Pin Error Message

  @gopay_pin
   Scenario: Reset pin with wrong OTP
    Given Consumer sign up via API
    Then Consumer logs in
    Then Consumer on Home screen taps on goPayTile
    Then Consumer on GoPayHome screen taps on activatePinNowButton
    And Intent:Go Pay Set Pin for New User with wrong OTP

  @gopay_pin
  Scenario: Pin Resend OTP
    Given Consumer sign up via API
    Then Consumer logs in
    Then Consumer on Home screen taps on goPayTile
    Then Consumer on GoPayHome screen taps on activatePinNowButton
    And Intent:Go Pay Set Pin for New User with wrong OTP
    Then Consumer on GoPayPin screen taps on resendOTP
    And Consumer on GoPayPin screen enters otp
    Then Consumer on GoPayPin screen taps on submitButton
    Then Consumer on GoPayPin screen taps on okButton


  @gopay_pin @gopay_forgotpin
  Scenario: Forgot pin from Transfer Page
    Given Consumer logs in as goPayUser1
    And Intent:Go Pay Transfer Amount to Phone
    And Intent:Go Pay Forgot Pin
    Then Consumer on GoPayPaymentSuccessful screen verifies transactionID has textpresent value Transaction ID:

    



