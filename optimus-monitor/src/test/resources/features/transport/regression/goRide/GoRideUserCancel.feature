@goride-rewrite
Feature: Go-Ride User cancel scenarios

  Background:
    Given No surge for GO-RIDE in Central Jakarta

  @go_ride_regression @gojek_regression
  Scenario: User cancel go ride cash booking on finding driver #22-12604
    Given Consumer logs in as GoRideUser
    And Consumer is in location CentralJakarta
    And Consumer disables ATs flow
    When Consumer on home screen taps on GO-RIDE tile
    And Consumer on Transport Home screen makes a booking
      | ServiceType  | Go-Ride            |
      | FromLocation | Current Location   |
      | ToLocation   | Past used location |
      | PaymentType  | Cash               |
    When Consumer on Transport Finding Driver Screen cancels the booking
    Then Consumer on Transport Fare estimate screen sees the estimate of the ride

  @go_ride_regression @gojek_regression
  Scenario: User cancel go ride go pay booking on finding driver #22-12603
    Given Consumer logs in as GoRideUser
    And Consumer is in location CentralJakarta
    And Consumer disables ATs flow
    When Consumer on home screen taps on GO-RIDE tile
    And Consumer on Transport Home screen makes a booking
      | ServiceType  | Go-Ride            |
      | FromLocation | Current Location   |
      | ToLocation   | Past used location |
      | PaymentType  | Go-Pay             |
    When Consumer on Transport Finding Driver Screen cancels the booking
    Then Consumer on Transport Fare estimate screen sees the estimate of the ride

  @go_ride_regression @gojek_regression
  Scenario: User cancel go ride go pay booking after driver found #22-12602
    Given Consumer logs in as GoRideUser
    And Consumer is in location CentralJakarta
    And Consumer disables ATs flow
    When Consumer on home screen taps on GO-RIDE tile
    And Consumer on Transport Home screen makes a booking
      | ServiceType  | Go-Ride            |
      | FromLocation | Current Location   |
      | ToLocation   | Past used location |
      | PaymentType  | Go-Pay             |
    When Driver on go ride logs in as bikeDriverApi via API
    And Driver accepts booking for Go-Ride made by Consumer
    Then Consumer on Transport OTW screen sees Go-Ride driver arriving
    When Consumer on Transport OTW screen cancels the ride
    Then Consumer on Transport Fare estimate screen sees the estimate of the ride

  @go_ride_regression @gojek_regression
  Scenario: User cancel go ride cash booking after driver found #22-12601
    Given Consumer logs in as GoRideUser
    And Consumer is in location CentralJakarta
    And Consumer disables ATs flow
    When Consumer on home screen taps on GO-RIDE tile
    And Consumer on Transport Home screen makes a booking
      | ServiceType  | Go-Ride            |
      | FromLocation | Current Location   |
      | ToLocation   | Past used location |
      | PaymentType  | Cash               |
    When Driver on go ride logs in as bikeDriverApi via API
    And Driver accepts booking for Go-Ride made by Consumer
    Then Consumer on Transport OTW screen sees Go-Ride driver arriving
    When Consumer on Transport OTW screen cancels the ride
    Then Consumer on Transport Fare estimate screen sees the estimate of the ride