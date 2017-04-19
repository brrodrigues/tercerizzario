@restApiIntegration
Feature: Supplier Resource 

    Scenario: Verificando o contexto da aplicacao

    Scenario: Buscando todos os prestadores
        Given Iniciado o servidor e subido o contexto
        Given Lista de prestadores:
        | id | name    | cellPhone      | address | email     | document   |
        | 57 | Bruno   | +5521098765432 | Rua X   | test1@app | 1234567890 |
        | 58 | Mitchel | +5521098765444 | Rua Y   | test2@app | 2345678901 |
        When requisitado via GET /suppliers
        Then O codigo da resposta sera 200
        Then O resultado da string devera ser :
        """
        [{"id":"57","name":"Bruno","cellPhone":"+5521098765432","address":"Rua X","email":"test1@app","document":"123456789"},
         {"id":"58","name":"Mitchell","cellPhone":"+5521098765444","address":"Rua Y","email":"test2@app","document":"2345678901"}]
        """
   