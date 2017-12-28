Feature: GoFood E2E Scenarios

  @go-food
  Scenario: User order with cash
    Given Consumer logs in as gofoodUser
    And Intent:Consumer opens the gofood home page
    And DataIntent:Consumer searches for the given restaurant
      | RestaurantName        |
      | Restaurant for dishes |
    And DataIntent:Consumer adds the given number of items to cart
      | NumberOfItems |
      | 2             |
    And DataIntent:Consumer places the order to given destination by given mode of payment
      | DestinationName | ModeOfPayment |
      | kemang          | Cash          |
    Then Consumer on GoFoodOrderPreview screen verify the message HUNGRY? DON’T WORRY!
    When Driver on go ride logs in as bikeDriverApi via API
    And Driver accepts booking for Go-Food made by Consumer
    Then Consumer on GoFoodOrder screen verify the order status Picking up food
    When Driver Picks Up the order for Go-Food with shopping price 25000
    Then Consumer on GoFoodOrder screen verify the order status Delivering food
    When Driver completes the Go Food order
    Then Consumer on GoFoodOrder screen gives 5 start rating to driver


  @go-food
  Scenario: User order with gopay
    Given Consumer logs in as gofoodUser
    And Intent:Consumer opens the gofood home page
    And DataIntent:Consumer searches for the given restaurant
      | RestaurantName   |
      | Banana Orgasm    |
    And DataIntent:Consumer adds the given number of items to cart
      | NumberOfItems |
      | 4             |
    And DataIntent:Consumer places the order to given destination by given mode of payment
      | DestinationName | ModeOfPayment |
      | kemang          | Gopay         |
    Then Consumer on GoFoodOrderPreview screen verify the message HUNGRY? DON’T WORRY!
    And Intent:Driver logs in and accepts the gofood booking
    Then Consumer on GoFoodOrder screen verify the order status Picking up food
    When Driver Picks Up the order for Go-Food with shopping price 25000
    Then Consumer on GoFoodOrder screen verify the order status Delivering food
    When Driver completes the Go Food order
    Then Consumer on GoFoodOrder screen gives 4 start rating to driver