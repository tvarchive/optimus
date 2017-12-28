Feature: GoPay Bank Regression

  @gopay_bank_regression @go_pay_regression
  Scenario: Adding a bank with already registered bank details
    Given Consumer logs in as goPayUser
    And Intent:Consumer Navigates to Add New Bank Screen
    And DataIntent:Consumer Adds New Bank
      | BankName | AccountName | AccountNumber |
      | CITIBANK | HDFC        | 787823423     |
    And Consumer on GoPayBankList screen verifies Bank with bankName CITIBANK and bankAccountName HDFC and bankAccountNumber 787823423
    And Intent:Consumer Navigates to Add New Bank Screen
    And DataIntent:Consumer Adds New Bank
      | BankName | AccountName | AccountNumber |
      | CITIBANK | HDFC        | 787823423     |
    Then Consumer on GoPayBankList screen verifies duplicateBankErrorMsg has textpresent value Bank account already registered
    And Consumer deletes bank with bankName CITIBANK and bankAccountNumber 78788989 via API
