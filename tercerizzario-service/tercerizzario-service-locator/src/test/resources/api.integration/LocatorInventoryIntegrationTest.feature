@restApiIntegration
Feature: Teste de integracao servico de localizacao

    Scenario: Encontrar um profissional em um raio de 1 KM
        Given Iniciado o servidor e subido o contexto
        Given Conjunto de elementos :
        | id | name    | longitude   | latitude    | cellPhone      | address | email     | document   |
        | 57 | Bruno   | -22.865660  | -43.344552  | +5521098765432 | RuaX    | test1@app | 1234567890 |
        | 58 | Mitchel | -22.8720364 | -43.3399386 | +5521098765444 | RuaY    | test2@app | 2345678901 |
        | 58 | Marlon  | -22.905200  | -43.244853  | +5521098765444 | RuaZ    | test3@app | 3456789012 |
        When requisitado via GET /locator?x=-22.865660&y=-43.344552&z=1000
        Then O codigo da resposta sera 200
        Then O resultado JSON sera :
        """
        [
            {"id": "57", "name": "Bruno", "location": [-22.86571, 43.3456137], "cellPhone": "+5521098765432", "address": "RuaX", "email": "test1@app", "document": "1234567890"},
            {"id": "58", "name": "Mitchel", "location": [-22.86371, 43.3456198], "cellPhone": "+5521098765444", "address": "RuaY", "email": "test2@app", "document": "2345678901"}
        ]
        """
    