Feature: Go Pay Bank Scenarios

  @GoPayBanks @gojek_regression @fragmentTest @distrib @gpBank
  Scenario: Add Bank For an Existing user and delete it
    Given Consumer logs in as goPayUser
    And Intent:Consumer Navigates to Add New Bank Screen
    And DataIntent:Consumer Adds New Bank
      |BankName|AccountName   |AccountNumber|
      |  HSBC  |optimumBank   | 78788989    |
    And Consumer on GoPayBankList screen verifies Bank with bankName HSBC and bankAccountName optimumBank and bankAccountNumber 78788989
    And Consumer logs in via API
    And Consumer deletes bank with bankName HSBC and bankAccountNumber 78788989 via API





