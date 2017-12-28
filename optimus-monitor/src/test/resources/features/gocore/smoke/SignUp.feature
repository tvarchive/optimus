Feature: Sign up

  @platform_sanity
  Scenario: Sign up happy path
    Given Consumer gets data for Sign up
    And Consumer on signup screen selects country Indonesia
    And Intent:Sign up for new user
    And Consumer on signUpOTP screen types OTP
    Then Consumer on signUpOTP screen taps on SubmitButton
    Then Intent:Verify consumer details on MyAccount page