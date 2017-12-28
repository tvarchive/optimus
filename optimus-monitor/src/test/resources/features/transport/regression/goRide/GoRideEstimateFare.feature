Feature: Go Ride Estimate Fare scenarios

  Background:
    Given No surge for GO-RIDE in Central Jakarta

  @go_ride_regression @gojek_regression
  Scenario: Go Ride Validation conditions - Distance Limit
    Given Consumer logs in as GoRideUser
    And Consumer is in location CentralJakarta
    And Consumer disables ATs flow
    When Consumer on home screen taps on GO-RIDE tile
    Then Consumer on Transport Home screen closes the onboarding
    When Consumer on Transport Home screen sees pick up is set to Current location
    And Consumer on Transport Home screen searches and selects destination as Bogor
    Then Consumer on Transport Fare estimate screen sees error message for 25K Distance Limit


  @go_ride_regression @gojek_regression
  Scenario: Go Ride Validation conditions - Low go pay
    Given Consumer logs in as UserWithZeroGoPay
    And Consumer is in location CentralJakarta
    And Consumer disables ATs flow
    When Consumer on home screen taps on GO-RIDE tile
    Then Consumer on Transport Home screen closes the onboarding
    When Consumer on Transport Home screen sees pick up is set to Current location
    And Consumer on Transport Home screen searches and selects destination as Jalan Doktor Saharjo
    Then Consumer on Transport Fare estimate screen sees Cash as selected payment type
    Then Consumer on Transport Fare estimate screen sees top up option on Go-Pay payment type
    Then Consumer on Transport Fare estimate screen sees Go-Pay balance on Go-Pay payment type