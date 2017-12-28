@goride-rewrite
Feature: Go-BlueBird User cancel scenarios

  @go_ride_regression @gojek_regression
  Scenario: User cancel go bluebird cash booking on finding driver #22-12604
    Given Consumer logs in as GoBlueBirdUser
    And Consumer is in location CentralJakarta
    And Consumer disables ATs flow
    When Consumer on home screen taps on GO-BLUEBIRD tile
    And Consumer on Transport Home screen makes a booking
      | ServiceType  | Go-Bluebird        |
      | FromLocation | Current Location   |
      | ToLocation   | Past used location |
      | PaymentType  | Cash               |
    When Consumer on Transport Finding Driver Screen cancels the booking
    Then Consumer on Transport Fare estimate screen sees the estimate of the ride

  @go_ride_regression @gojek_regression
  Scenario: User cancel go bluebird go pay booking on finding driver #22-12603
    Given Consumer logs in as GoBlueBirdUser
    And Consumer is in location CentralJakarta
    And Consumer disables ATs flow
    When Consumer on home screen taps on GO-BLUEBIRD tile
    And Consumer on Transport Home screen makes a booking
      | ServiceType  | Go-Bluebird        |
      | FromLocation | Current Location   |
      | ToLocation   | Past used location |
      | PaymentType  | Go-Pay             |
    When Consumer on Transport Finding Driver Screen cancels the booking
    Then Consumer on Transport Fare estimate screen sees the estimate of the ride

  @go_ride_regression @gojek_regression
  Scenario: User cancel go bluebird go pay booking after finding driver
    Given Consumer logs in as GoBlueBirdUser
    And Consumer is in location CentralJakarta
    And Consumer disables ATs flow
    When Consumer on home screen taps on GO-BLUEBIRD tile
    And Consumer on Transport Home screen makes a booking
      | ServiceType  | Go-Bluebird        |
      | FromLocation | Current Location   |
      | ToLocation   | Past used location |
      | PaymentType  | Go-Pay             |
    When Driver on go bluebird logs in as birdDriverApi via API
    Then Driver accepts booking for Go-Bluebird made by Consumer
    And Consumer on Transport OTW screen sees Go-Bluebird driver arriving
    When Consumer on Transport OTW screen cancels the ride
    Then Consumer on Transport Fare estimate screen sees the estimate of the ride

  @go_ride_regression @gojek_regression
  Scenario: User cancel go bluebird cash booking after finding driver
    Given Consumer logs in as GoBlueBirdUser
    And Consumer is in location CentralJakarta
    And Consumer disables ATs flow
    When Consumer on home screen taps on GO-BLUEBIRD tile
    And Consumer on Transport Home screen makes a booking
      | ServiceType  | Go-Bluebird        |
      | FromLocation | Current Location   |
      | ToLocation   | Past used location |
      | PaymentType  | Cash               |
    When Driver on go bluebird logs in as birdDriverApi via API
    Then Driver accepts booking for Go-Bluebird made by Consumer
    And Consumer on Transport OTW screen sees Go-Bluebird driver arriving
    When Consumer on Transport OTW screen cancels the ride
    Then Consumer on Transport Fare estimate screen sees the estimate of the ride
