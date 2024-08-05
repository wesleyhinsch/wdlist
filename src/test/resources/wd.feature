Feature: O cliente precisa resgatar usuario
  Scenario: cliente chama GET /user
    When o cliente chama user
    Then o cliente recebe status code of 415



