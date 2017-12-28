Feature: Go Ride Auto-apply voucher features

  Background:
    Given No surge for GO-RIDE in Central Jakarta

  @go_ride_regression @gojek_regression
  Scenario: User should be able to remove the auto-applied voucher #22-12596
    Given Consumer logs in as TransportVoucherUser
    And Consumer is in location CentralJakarta
    And Consumer disables ATs flow
    When Consumer on home screen taps on GO-RIDE tile
    Then Consumer on Transport Home screen closes the onboarding
    And Consumer on Transport Home screen searches and selects pick up location as Marina
    And Consumer on Transport Home screen searches and selects destination as Jalan Doktor Saharjo
    Then Consumer on Estimate screen should be able to see voucher applied with details
    When Consumer on Estimate screen removes the voucher
    Then Consumer on Estimate screen should not the see the voucher


  @go_ride_regression @gojek_regression
  Scenario: User should see free if voucher amount greater than fare #22-12597
    Given Consumer logs in as TransportVoucherUser
    And Consumer is in location CentralJakarta
    And Consumer disables ATs flow
    When Consumer on home screen taps on GO-RIDE tile
    Then Consumer on Transport Home screen closes the onboarding
    When Consumer on Transport Home screen sees pick up is set to Current location
    And Consumer on Transport Home screen searches and selects destination as Jalan Doktor Saharjo
    Then Consumer on Estimate screen should see that the fare is Free
    Then Consumer on Estimate screen should be able to see voucher applied with details
