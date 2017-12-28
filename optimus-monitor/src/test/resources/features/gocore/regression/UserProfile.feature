Feature: User Profile details

  @platform_regression
  Scenario: Change Customer Email on MyAccount page
    Given Consumer sign up via API
    Then Consumer logs in
    And Intent:Navigates to Edit Profile
    Then Consumer on edit profile screen changes email
    Then Consumer on EditProfile screen taps on Save
    And Consumer on editProfile screen verifies successmessage has textpresent value Verify Email
    And Consumer on editProfile screen taps on OK
    Then Consumer on editProfile screen taps on BackButton
    And Intent:Consumer logs out
    When Consumer on login screen logs in with unverifiedConsumerEmail
    Then Consumer on SignIn screen verifies ErrorMessage has textpresent value Email address not registered with GO-JEK
    And Consumer verifies email via API
    When Consumer logs in on login screen
    When Consumer on Home screen taps on MyAccount
    Then Consumer on MyAccount screen verifies UserEmail has textpresent value email

  @platform_regression
  Scenario: Change Customer Name on MyAccount page
    Given Consumer sign up via API
    Then Consumer logs in
    And Intent:Navigates to Edit Profile
    Then Consumer on editProfile screen types on ConsumerName value newConsumerName
    Then Consumer on editProfile screen taps on Save
    And Consumer on editProfile screen verifies successmessage has textpresent value Update Successful
    And Consumer on editProfile screen taps on OK
    Then Consumer on editProfile screen taps on BackButton
    When Consumer on Home screen taps on MyAccount
    Then Consumer on MyAccount screen verifies UserName has textpresent value newConsumerName

  @platform_regression
  Scenario: Change Customer phone on MyAccount page
    Given Consumer sign up via API
    Then Consumer logs in
    And Intent:Navigates to Edit Profile
    Then Consumer on edit profile screen changes phone
    Then Consumer on editProfile screen taps on Save
    And Consumer on editProfile screen verifies successmessage has textpresent value Verify Phone
    And Consumer on editProfile screen taps on OK
    And Consumer on phoneOTP screen types OTP for newConsumerPhone
    Then Consumer on phoneChangeOTP screen taps on SubmitButton
    And Consumer on phoneChangeOTP screen verifies successmessage has textpresent value Update Successful
    And Consumer on editProfile screen taps on OK
    Then Consumer on editProfile screen taps on BackButton
    When Consumer on Home screen taps on MyAccount
    Then Consumer on MyAccount screen verifies MobileNumber has textpresent value newConsumerPhone

