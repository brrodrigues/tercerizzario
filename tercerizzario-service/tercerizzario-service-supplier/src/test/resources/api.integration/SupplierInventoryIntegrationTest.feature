@restApiIntegration
Feature: Teste de integracao Supplier Resource 

    Scenario: Buscando todos os suppliers
        Given Iniciado o servidor e subido o contexto
        Given Conjunto de elementos:
        | id | name    | longitude  | latitude   | cellPhone      | address | email     | document   |
        | 57 | Bruno   | -22.86571  | 43.3456137 | +5521098765432 | RuaX    | test1@app | 1234567890 |
        | 58 | Mitchel | -22.86371  | 43.3456198 | +5521098765444 | RuaY    | test2@app | 2345678901 |
        When requisitado via GET /suppliers
        Then O codigo da resposta sera 200
        Then O resultado JSON sera :
        """
        [
            {"id": "57", "name": "Bruno", "location": [-22.86571, 43.3456137], "cellPhone": "+5521098765432", "address": "RuaX", "email": "test1@app", "document": "1234567890"},
            {"id": "58", "name": "Mitchel", "location": [-22.86371, 43.3456198] , "cellPhone": "+5521098765444", "address": "RuaY", "email": "test2@app", "document": "2345678901"}
        ]
        """
    
    Scenario: Atualizando suppliers
        Given Iniciado o servidor e subido o contexto
        Given Conjunto de elementos:
        | id | name    | longitude  | latitude   | cellPhone      | address | email     | document   |
        | 57 | Bruno   | -22.86571  | 43.3456137 | +5521098765432 | RuaX    | test1@app | 1234567890 |
        | 58 | Mitchel | -22.86371  | 43.3456198 | +5521098765444 | RuaY    | test2@app | 2345678901 |
        When requisitado via PUT /suppliers com formato json :
        """
        {"id": "57", "name": "Bruno Rodrigues", "location": [ -22.86000, 43.3456137 ], "cellPhone": "+5521099999999", "address": "RuaX", "email": "test11@app", "document": "999999999"}
        """
        Then O resultado JSON sera :
        """
        {"id": "57", "name": "Bruno Rodrigues", "location": [-22.86000, 43.3456137], "cellPhone": "+5521099999999", "address": "RuaX", "email": "test11@app", "document": "999999999"}
        """
        When requisitado via GET /suppliers
        Then O resultado JSON sera :
        """
        [
            {"id": "57", "name": "Bruno Rodrigues", "location": [-22.86000, 43.3456137], "cellPhone": "+5521099999999", "address": "RuaX", "email": "test11@app", "document": "999999999"},
            {"id": "58", "name": "Mitchel", "location": [ -22.86371, 43.3456198 ], "cellPhone": "+5521098765444", "address": "RuaY", "email": "test2@app", "document": "2345678901"}
        ]
        """

    Scenario: Buscando supplier por email
        Given Iniciado o servidor e subido o contexto
        Given Conjunto de elementos:
        | id | name    | longitude  | latitude   | cellPhone      | address | email     | document   |
        | 57 | Bruno   | -22.86571  | 43.3456137 | +5521098765432 | RuaX    | test1@app | 1234567890 |
        | 58 | Mitchel | -22.86371  | 43.3456198 | +5521098765444 | RuaY    | test2@app | 2345678901 |
        When requisitado via GET /suppliers/search/byEmail?email=test2@app
        Then O resultado JSON sera :
        """
        {"id": "58", "name": "Mitchel", "location": [ -22.86371, 43.3456198 ] , "cellPhone": "+5521098765444", "address": "RuaY", "email": "test2@app", "document": "2345678901"}
        """