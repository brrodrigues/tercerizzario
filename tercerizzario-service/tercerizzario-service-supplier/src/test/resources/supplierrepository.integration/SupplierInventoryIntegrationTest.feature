@restApiIntegration
Feature: Teste de Integracao de Supplier Resource

  Scenario: find all suppliers
    Given the web context is set
    Given the db is empty
    Given the following suppliers exist:
    | id     |    name     |    cellPhone              |    address            |    email                   |  document
    | 57     |    Bruno    |   +5521098765432          |   Rua X               |  test1@app   |  1234567890
    | 58     |    Mitchel  |   +5521098765444          |   Rua Y               |  test2@app   |  2345678901
    When client request GET /suppliers
    Then the response code should be 200
    Then the result json should be:
    """
    [{"id":"57","name":"Bruno","cellPhone":"+5521098765432","address":"Rua X","email":"test1@app","document":"123456789"},
     {"id":"58","name":"Mitchell","cellPhone":"+5521098765444","address":"Rua Y","email":"test2@app","document":"2345678901"},
    ]
    """
