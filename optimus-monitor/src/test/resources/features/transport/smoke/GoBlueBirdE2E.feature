Feature: Go-Bluebird E2E booking flows

  @go_ride_sanity @gojek_regression
  Scenario: Complete Go Bluebird go-pay booking and give rating
    Given Consumer logs in as GoBlueBirdUser
    And Consumer is in location CentralJakarta
    And Consumer disables ATs flow
    When Consumer on home screen taps on GO-BLUEBIRD tile
    And Consumer on Transport Home screen makes a booking
      | ServiceType  | Go-Bluebird           |
      | FromLocation | Oakwood Premier Cozmo |
      | ToLocation   | grandkemang           |
      | PaymentType  | Go-Pay                  |
    When Driver on go bluebird logs in as birdDriverApi via API
    And Driver accepts booking for Go-Bluebird made by Consumer
    Then Consumer on Transport OTW screen sees Go-Bluebird driver arriving
    When Driver Picks Up the order for Go-Bluebird
    Then Consumer on Transport OTW screen sees Go-Bluebird driver on the way with me
    When Driver Completes the order for Go-Bluebird with meter amount as 30000
    Then Consumer on Transport Summary screen rates the driver 5 stars

  @go_ride_sanity @gojek_regression
  Scenario: Complete Go Bluebird cash booking and give rating
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
    When Driver Picks Up the order for Go-Bluebird
    Then Consumer on Transport OTW screen sees Go-Bluebird driver on the way with me
    When Driver Completes the order for Go-Bluebird with meter amount as 30000
    Then Consumer on Transport Summary screen rates the driver 5 stars