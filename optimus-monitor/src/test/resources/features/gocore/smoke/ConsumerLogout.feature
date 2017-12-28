Feature: Login as consumer and logout


  @platform_sanity
  Scenario: Login as consumer and logout
    Given Consumer logs in as GoCoreUser
    And Intent:Consumer logs out
