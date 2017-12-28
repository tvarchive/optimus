Feature: GoPay Set Pin From Settings

  @gopay_set_pin @gopay_smoke
  Scenario: Set Pin for New User from Settings
    Given Consumer sign up via API
    Then Consumer logs in
    Then Consumer on Home screen taps on goPayTile
    Then Consumer on GoPayHome screen taps on activatePinNowButton
    And Intent:Go Pay Set Pin for New User



