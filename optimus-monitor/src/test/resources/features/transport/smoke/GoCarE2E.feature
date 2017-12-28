Feature: Go Car E2E booking flows

  Background:
    Given No surge for GO-CAR in Central Jakarta

  @go_ride_sanity @gojek_regression
  Scenario: Complete go car go-pay booking and give rating
    Given Consumer logs in as GoCarUser
    And Consumer is in location CentralJakarta
    And Consumer disables ATs flow
    When Consumer on home screen taps on GO-CAR tile
    And Consumer on Transport Home screen makes a booking
      | ServiceType  | Go-Car                |
      | FromLocation | Oakwood Premier Cozmo |
      | ToLocation   | grandkemang           |
      | PaymentType  | Go-Pay                |
    When Driver on go car logs in as carDriverApi via API
    And Driver accepts booking for Go-Car made by Consumer
    Then Consumer on Transport OTW screen sees Go-Car driver arriving
    When Driver Picks Up the order for Go-Car
    Then Consumer on Transport OTW screen sees Go-Car driver on the way with me
    When Driver Completes the order for Go-Car
    Then Consumer on Transport Summary screen rates the driver 5 stars

  @go_ride_sanity @gojek_regression
  Scenario: Complete go car cash booking and give rating
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
    When Driver Picks Up the order for Go-Car
    Then Consumer on Transport OTW screen sees Go-Car driver on the way with me
    When Driver Completes the order for Go-Car
    Then Consumer on Transport Summary screen rates the driver 5 stars