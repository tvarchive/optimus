Feature: Car Driver Tests

  @car_driver_sanity @driver
  Scenario: Driver Complete booking with cash
    Then GoJekCarDriver clears Active bookings for car and loads Driver details
    Given GoJekCarDriver on SignIn screen taps on LOGIN
    Then GoJekCarDriver selects emailId
    Then Consumer logs in using api for Driver app
    Then Consumer makes a car booking using cash
    Then GoJekCarDriver accepts the booking from Consumer
    Then GoJekCarDriver activates the driver
    Then GoJekCarDriver verifies the Cash booking price with Consumer
    Then GoJekCarDriver Picks up the order
    Then GoJekCarDriver Completes the order
    Then GoJekCarDriver Logs Out

