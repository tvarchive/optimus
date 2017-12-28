Feature: GoPay Transfer

  @Transfer @gojek_regression @gpBank
  Scenario: Sender transfers amount to receiver using his balance
    Given Consumer logs in as GoPayUser
    And Consumer On Home Screen reads the balance
    And Consumer on Home screen taps on Transfer
    And Consumer on GoPayTransfer screen transfers 10000 to Consumer2


  @Transfer @gojek_regression
  Scenario: Sender transfers amount to receiver on topup of balance
    Given Consumer logs in as GoPayUser
    And Consumer On Home Screen reads the balance
    And Consumer on Home screen taps on Transfer
    And Consumer on GoPayTransfer screen transfers 50000000 to Consumer2
