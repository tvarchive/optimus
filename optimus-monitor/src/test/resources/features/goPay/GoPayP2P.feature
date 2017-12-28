Feature: Consumer logs in with different users


  @P2P
  Scenario: Login as ride consumer 1
    Given Consumer1 logs in as GoPayUser
    And Consumer1 On Home Screen reads the balance
#    Given Consumer2 logs in as GoPayUser
#    And Consumer2 On Home Screen reads the balance
    And Consumer1 on Home screen taps on Transfer
    And Consumer1 on GoPayTransfer screen transfers 10000 to Consumer2
