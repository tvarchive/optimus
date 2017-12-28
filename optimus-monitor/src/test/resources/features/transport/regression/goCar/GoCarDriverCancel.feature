Feature: Go-Car Driver cancel scenarios

  Background:
    Given No surge for GO-CAR in Central Jakarta

  @go_ride_regression @gojek_regression
  Scenario:  Driver cancel go car go pay booking with non reblast
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
    When Driver cancels Go-Car booking with non reblast reason
    Then Consumer on Transport OTW screen sees that driver has cancelled
    When Consumer on Transport OTW screen retry the order
    Then Consumer on Transport Fare estimate screen sees the estimate of the ride

  @go_ride_regression @gojek_regression
  Scenario: Driver cancel go car cash booking with non reblast
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
    When Driver cancels Go-Car booking with non reblast reason
    Then Consumer on Transport OTW screen sees that driver has cancelled
    When Consumer on Transport OTW screen retry the order
    Then Consumer on Transport Fare estimate screen sees the estimate of the ride

  @go_ride_regression @gojek_regression
  Scenario:  Driver cancel go car go pay booking with reblast
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
    When Driver cancels Go-Car booking with reblast reason
    When Consumer on Transport Finding Driver Screen see waiting for new driver
    When Consumer on Transport Finding Driver Screen cancels the booking
    Then Consumer on Transport Fare estimate screen sees the estimate of the ride

  @go_ride_regression @gojek_regression
  Scenario: Driver cancel go car cash booking with rebast
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
    When Driver cancels Go-Car booking with reblast reason
    When Consumer on Transport Finding Driver Screen see waiting for new driver
    When Consumer on Transport Finding Driver Screen cancels the booking
    Then Consumer on Transport Fare estimate screen sees the estimate of the ride
