Feature: Go Pay Voucher Scenarios

  @go_pay_voucher
  Scenario: Redeem a new voucher and verify in the transactions
    Given Consumer logs in as goPayVoucherUser
    And Consumer on Home screen taps on goPayTile
    And Consumer on GoPayHome screen taps on voucherIcon
    And Consumer creates a new voucher and redeems the voucher
    And Intent:Verify Voucher Successful PopUp and click on Okay
