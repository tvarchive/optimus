Feature: Go Ride E2E booking flows

  Background:
    Given No surge for GO-RIDE in Central Jakarta

  @go_ride_sanity @gojek_regression
  Scenario: Complete go ride go-pay booking and give rating
    Given Consumer logs in as GoRideUser
    And Consumer is in location CentralJakarta
    And Consumer disables ATs flow
    When Consumer on home screen taps on GO-RIDE tile
    And Consumer on Transport Home screen makes a booking
      | ServiceType  | Go-Ride               |
      | FromLocation | Oakwood Premier Cozmo |
      | ToLocation   | grandkemang           |
      | PaymentType  | Go-Pay                |
    When Driver on go ride logs in as bikeDriverApi via API
    And Driver accepts booking for Go-Ride made by Consumer
    Then Consumer on Transport OTW screen sees Go-Ride driver arriving
    When Driver Picks Up the order for Go-Ride
    Then Consumer on Transport OTW screen sees Go-Ride driver on the way with me
    When Driver Completes the order for Go-Ride
    Then Consumer on Transport Summary screen rates the driver 5 stars

  @go_ride_sanity @gojek_regression
  Scenario: Complete go ride cash booking and give rating
    Given Consumer logs in as GoRideUser
    And Consumer is in location CentralJakarta
    And Consumer enables ATs flow
    When Consumer on home screen taps on GO-RIDE tile
    And Consumer on Transport Home screen makes a booking
      | ServiceType  | Go-Ride            |
      | FromLocation | Current Location   |
      | ToLocation   | Past used location |
      | PaymentType  | Cash               |
    When Driver on go ride logs in as bikeDriverApi via API
    And Driver accepts booking for Go-Ride made by Consumer
    Then Consumer on Transport OTW screen sees Go-Ride driver arriving
    When Driver Picks Up the order for Go-Ride
    Then Consumer on Transport OTW screen sees Go-Ride driver on the way with me
    When Driver Completes the order for Go-Ride
    Then Consumer on Transport Summary screen rates the driver 5 stars