Feature: History Profile

  @platform_sanity
  Scenario: Validation of Completed Bookings on history screen
    Given Consumer logs in as GoCoreHistoryUser
    Then Consumer on home screen taps on history
    And Consumer on history screen taps on CompletedBooking
    And Consumer on history screen verifies BookingStatus has textpresent value Completed
