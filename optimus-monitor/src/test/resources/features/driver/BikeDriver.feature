Feature: Bike Driver Tests

  @bike_driver_sanity @driver
  Scenario: Driver Complete booking with cash
    Then GoJekBikeDriver clears Active bookings for bike and loads Driver details
    Given GoJekBikeDriver on SignIn screen taps on LOGIN
    Then GoJekBikeDriver selects emailId
    Then Consumer logs in using api for Driver app
    Then Consumer makes a bike booking using cash
    Then GoJekBikeDriver accepts the booking from Consumer
    Then GoJekBikeDriver activates the driver
    Then GoJekBikeDriver verifies the Cash booking price with Consumer
    Then GoJekBikeDriver Picks up the order
    Then GoJekBikeDriver Completes the order
    Then GoJekBikeDriver Logs Out

  @bike_driver_sanity @driver
  Scenario: Driver accepts Cash booking and cancels
    Then GoJekBikeDriver clears Active bookings for bike and loads Driver details
    Given GoJekBikeDriver on SignIn screen taps on LOGIN
    Then GoJekBikeDriver selects emailId
    Then Consumer logs in using api for Driver app
    Then Consumer makes a bike booking using cash
    Then GoJekBikeDriver accepts the booking from Consumer
    Then GoJekBikeDriver activates the driver
    Then GoJekBikeDriver verifies the Cash booking price with Consumer
    Then GoJekBikeDriver Cancels the booking with Booking palsu
    Then GoJekBikeDriver Logs Out


  @bike_driver_sanity @driver
  Scenario: Driver Complete booking with GoPay
    Then GoJekBikeDriver clears Active bookings for bike and loads Driver details
    Given GoJekBikeDriver on SignIn screen taps on LOGIN
    Then GoJekBikeDriver selects emailId
    Then Consumer logs in using api for Driver app
    Then Consumer makes a bike booking using gopay
    Then GoJekBikeDriver accepts the booking from Consumer
    Then GoJekBikeDriver activates the driver
    Then GoJekBikeDriver verifies the GoPay booking price with Consumer
    Then GoJekBikeDriver Picks up the order
    Then GoJekBikeDriver Completes the order
    Then GoJekBikeDriver Logs Out

  @bike_driver_sanity @driver
  Scenario: Driver accepts GoPay booking and cancels
    Then GoJekBikeDriver clears Active bookings for bike and loads Driver details
    Given GoJekBikeDriver on SignIn screen taps on LOGIN
    Then GoJekBikeDriver selects emailId
    Then Consumer logs in using api for Driver app
    Then Consumer makes a bike booking using gopay
    Then GoJekBikeDriver accepts the booking from Consumer
    Then GoJekBikeDriver activates the driver
    Then GoJekBikeDriver verifies the GoPay booking price with Consumer
    Then GoJekBikeDriver Cancels the booking with Booking palsu
    Then GoJekBikeDriver Logs Out

  @bike_driver_sanity @driver
  Scenario: Driver rejects booking
    Then GoJekBikeDriver clears Active bookings for bike and loads Driver details
    Given GoJekBikeDriver on SignIn screen taps on LOGIN
    Then GoJekBikeDriver selects emailId
    Then Consumer logs in using api for Driver app
    Then Consumer makes a bike booking using gopay
    Then GoJekBikeDriver on Bid screen taps on TOLAK

