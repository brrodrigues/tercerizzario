@restApiIntegration
Feature: Supplier Resource 

    Scenario: Buscando todos os prestadores
        Given Iniciado o servidor e subido o contexto
        Given Lista de prestadores:
        | id | name    | x          | y          | cellPhone      | address | email     | document   |
        | 57 | Bruno   | -22.86571  | 43.3456137 | +5521098765432 | RuaX    | test1@app | 1234567890 |
        | 58 | Mitchel | -22.86371  | 43.3456198 | +5521098765444 | RuaY    | test2@app | 2345678901 |
        When requisitado via GET /suppliers
        Then O codigo da resposta sera 200
        Then O resultado JSON sera :
        """
        [
        {"id": "57", "name": "Bruno", "location": [-22.86571, 43.3456137], "cellPhone": "+5521098765432", "address": "RuaX", "email": "test1@app", "document": "1234567890"},
        {"id": "58", "name": "Mitchel", "location": [-22.86371, 43.3456198], "cellPhone": "+5521098765444", "address": "RuaY", "email": "test2@app", "document": "2345678901"}]
        """