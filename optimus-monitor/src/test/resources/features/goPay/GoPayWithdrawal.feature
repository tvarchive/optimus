Feature: Go Pay Withdrawl Feature


  @go_pay_withdraw @gojek_regression @distrib
    Scenario: Withdraw Amount From Default Bank
      Given Consumer logs in as goPayWithdrawAmountUser
      And Consumer on Home screen taps on goPayTile
      And Consumer on GoPayHome screen taps on withdrawIcon
      Then Consumer on GoPayWithdraw withdraws amount 2000
      And Consumer on GoPayWithdrawConfirmation screen verify total deductions
      And Consumer on GoPayWithdrawConfirmation screen taps on confirmButton
      And Consumer on GoPayPin screen types on pin value consumerWalletPin
      Then Consumer on GoPayWithdrawConfirmation screen verifies requestRegisteredMsg has textpresent value registeredSuccessMessage
      And Consumer on GoPayWithdrawConfirmation screen taps on okButton
