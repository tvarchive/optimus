@goride-rewrite
Feature: Go-Car User cancel scenarios

  Background:
    Given No surge for GO-CAR in Central Jakarta

  @go_ride_regression @gojek_regression
  Scenario: User cancel go car cash booking on finding driver #22-12604
    Given Consumer logs in as GoCarUser
    And Consumer is in location CentralJakarta
    And Consumer disables ATs flow
    When Consumer on home screen taps on GO-CAR tile
    And Consumer on Transport Home screen makes a booking
      | ServiceType  | Go-Car             |
      | FromLocation | Current Location   |
      | ToLocation   | Past used location |
      | PaymentType  | Cash               |
    When Consumer on Transport Finding Driver Screen cancels the booking
    Then Consumer on Transport Fare estimate screen sees the estimate of the ride

  @go_ride_regression @gojek_regression
  Scenario: User cancel go car go pay booking on finding driver #22-12603
    Given Consumer logs in as GoCarUser
    And Consumer is in location CentralJakarta
    And Consumer disables ATs flow
    When Consumer on home screen taps on GO-CAR tile
    And Consumer on Transport Home screen makes a booking
      | ServiceType  | Go-Car             |
      | FromLocation | Current Location   |
      | ToLocation   | Past used location |
      | PaymentType  | Go-Pay             |
    When Consumer on Transport Finding Driver Screen cancels the booking
    Then Consumer on Transport Fare estimate screen sees the estimate of the ride

  @go_ride_regression @gojek_regression
  Scenario: User cancel go car go pay booking after driver found
    Given Consumer logs in as GoCarUser
    And Consumer is in location CentralJakarta
    And Consumer disables ATs flow
    When Consumer on home screen taps on GO-CAR tile
    And Consumer on Transport Home screen makes a booking
      | ServiceType  | Go-Car             |
      | FromLocation | Current Location   |
      | ToLocation   | Past used location |
      | PaymentType  | Go-Pay             |
    When Driver on go car logs in as carDriverApi via API
    And Driver accepts booking for Go-Car made by Consumer
    Then Consumer on Transport OTW screen sees Go-Car driver arriving
    When Consumer on Transport OTW screen cancels the ride
    Then Consumer on Transport Fare estimate screen sees the estimate of the ride

  @go_ride_regression @gojek_regression
  Scenario: User cancel go car cash booking after driver found
    Given Consumer logs in as GoCarUser
    And Consumer is in location CentralJakarta
    And Consumer disables ATs flow
    When Consumer on home screen taps on GO-CAR tile
    And Consumer on Transport Home screen makes a booking
      | ServiceType  | Go-Car             |
      | FromLocation | Current Location   |
      | ToLocation   | Past used location |
      | PaymentType  | Cash               |
    When Driver on go car logs in as carDriverApi via API
    And Driver accepts booking for Go-Car made by Consumer
    Then Consumer on Transport OTW screen sees Go-Car driver arriving
    When Consumer on Transport OTW screen cancels the ride
    Then Consumer on Transport Fare estimate screen sees the estimate of the ride