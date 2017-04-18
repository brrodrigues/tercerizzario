@restApiIntegration
Feature: Supplier Resource 

    Scenario: Verificando o contexto da aplicacao
        Given Iniciando o contexto da aplicacao
        Then Limpando a base de dados

    Scenario: Buscando todos os prestadores
        Given Lista de prestadores existentes:
        | id | name    | cellPhone      | address | email     | document   |
        | 57 | Bruno   | +5521098765432 | Rua X   | test1@app | 1234567890 |
        | 58 | Mitchel | +5521098765444 | Rua Y   | test2@app | 2345678901 |
        When requisitado via GET /suppliers
        Then the response code should be 200
        Then the result json should be:
        """
        [{"id":"57","name":"Bruno","cellPhone":"+5521098765432","address":"Rua X","email":"test1@app","document":"123456789"},
         {"id":"58","name":"Mitchell","cellPhone":"+5521098765444","address":"Rua Y","email":"test2@app","document":"2345678901"}]
        """
