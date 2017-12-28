Feature: User Profile details

  @platform_sanity
  Scenario: Verify user details displayed on My Account page
    Given Consumer logs in as GoCoreUser
    Then Intent:Verify consumer details on MyAccount page
  