Feature: Go-Bluebird Driver cancel scenarios

  @go_ride_regression @gojek_regression
  Scenario:  Driver cancel go blue bird go pay booking with non reblast
    Given Consumer logs in as GoBlueBirdUser
    And Consumer is in location CentralJakarta
    And Consumer disables ATs flow
    When Consumer on home screen taps on GO-BLUEBIRD tile
    And Consumer on Transport Home screen makes a booking
      | ServiceType  | Go-Bluebird        |
      | FromLocation | Current Location   |
      | ToLocation   | Past Used Location |
      | PaymentType  | Go-Pay             |
    When Driver on go bluebird logs in as birdDriverApi via API
    And Driver accepts booking for Go-Bluebird made by Consumer
    Then Consumer on Transport OTW screen sees Go-Bluebird driver arriving
    When Driver cancels Go-Bluebird booking with non reblast reason
    Then Consumer on Transport OTW screen sees that driver has cancelled
    When Consumer on Transport OTW screen retry the order
    Then Consumer on Transport Fare estimate screen sees the estimate of the ride

  @go_ride_regression @gojek_regression
  Scenario: Driver cancel go blue bird cash booking with non reblast
    Given Consumer logs in as GoBlueBirdUser
    And Consumer is in location CentralJakarta
    And Consumer disables ATs flow
    When Consumer on home screen taps on GO-BLUEBIRD tile
    And Consumer on Transport Home screen makes a booking
      | ServiceType  | Go-Bluebird        |
      | FromLocation | Current Location   |
      | ToLocation   | Past Used Location |
      | PaymentType  | Cash               |
    When Driver on go bluebird logs in as birdDriverApi via API
    And Driver accepts booking for Go-Bluebird made by Consumer
    Then Consumer on Transport OTW screen sees Go-Bluebird driver arriving
    When Driver cancels Go-Bluebird booking with non reblast reason
    Then Consumer on Transport OTW screen sees that driver has cancelled
    When Consumer on Transport OTW screen retry the order
    Then Consumer on Transport Fare estimate screen sees the estimate of the ride

  @go_ride_regression @gojek_regression
  Scenario:  Driver cancel go blue bird go pay booking with reblast
    Given Consumer logs in as GoBlueBirdUser
    And Consumer is in location CentralJakarta
    And Consumer disables ATs flow
    When Consumer on home screen taps on GO-BLUEBIRD tile
    And Consumer on Transport Home screen makes a booking
      | ServiceType  | Go-Bluebird        |
      | FromLocation | Current Location   |
      | ToLocation   | Past Used Location |
      | PaymentType  | Go-Pay             |
    When Driver on go bluebird logs in as birdDriverApi via API
    And Driver accepts booking for Go-Bluebird made by Consumer
    Then Consumer on Transport OTW screen sees Go-Bluebird driver arriving
    When Driver cancels Go-Bluebird booking with reblast reason
    When Consumer on Transport Finding Driver Screen see waiting for new driver
    When Consumer on Transport Finding Driver Screen cancels the booking
    Then Consumer on Transport Fare estimate screen sees the estimate of the ride

  @go_ride_regression @gojek_regression
  Scenario: Driver cancel go blue bird cash booking with reblast
    Given Consumer logs in as GoBlueBirdUser
    And Consumer is in location CentralJakarta
    And Consumer disables ATs flow
    When Consumer on home screen taps on GO-BLUEBIRD tile
    And Consumer on Transport Home screen makes a booking
      | ServiceType  | Go-Bluebird        |
      | FromLocation | Current Location   |
      | ToLocation   | Past Used Location |
      | PaymentType  | Cash               |
    When Driver on go bluebird logs in as birdDriverApi via API
    And Driver accepts booking for Go-Bluebird made by Consumer
    Then Consumer on Transport OTW screen sees Go-Bluebird driver arriving
    When Driver cancels Go-Bluebird booking with reblast reason
    When Consumer on Transport Finding Driver Screen see waiting for new driver
    When Consumer on Transport Finding Driver Screen cancels the booking
    Then Consumer on Transport Fare estimate screen sees the estimate of the ride
