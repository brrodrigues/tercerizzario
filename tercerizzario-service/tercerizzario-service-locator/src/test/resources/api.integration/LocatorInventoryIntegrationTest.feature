@restApiIntegration
Feature: Teste de integracao servico de localizacao

    Scenario: Encontrar um profissional em um raio de 10 KM
        Given Iniciado o servidor e subido o contexto
        Given Conjunto de elementos :
        | id | name    | longitude   | latitude    | cellPhone      | address | email     | document   |
        | 57 | Bruno   | -22.865660  | -43.344552  | +5521098765432 | RuaX    | test1@app | 1234567890 |
        | 59 | Marlon  | -22.905200  | -43.244853  | +5521098765444 | RuaZ    | test3@app | 3456789012 |
        | 60 | S1      | -22.883927  | -43.326677  | +5521098765444 | RuaW    | test4@app | 2456789012 |
        When requisitado via GET /locator?x=-22.865660&y=-43.344552&z=10
        Then O codigo da resposta sera 200
        Then O resultado JSON sera :
        """
        [
            {"id": "60", "name": "S1",   "location": [-22.883927, -43.326677], "cellPhone": "+5521098765444", "address": "RuaW", "email": "test4@app", "document": "2456789012"},
        ]
        """
    
    Scenario: Encontrar um profissional em um raio de 10 KM
        Given Iniciado o servidor e subido o contexto
        Given Conjunto de elementos :
        | id | name    | longitude   | latitude    | cellPhone      | address | email     | document   |
        | 57 | Bruno   | -22.865660  | -43.344552  | +5521098765432 | RuaX    | test1@app | 1234567890 |
        | 59 | Marlon  | -22.905200  | -43.244853  | +5521000000000 | RuaZ    | test3@app | 3456789012 |
        | 60 | S1      | -22.883927  | -43.326677  | +5521098765444 | RuaW    | test4@app | 2456789012 |
        When requisitado via GET /locator?x=-22.865660&y=-43.344552&z=10
        Then O codigo da resposta sera 200
        Then O resultado JSON sera :
        """
        [
            {"id": "60", "name": "S1",   "location": [-22.883927, -43.326677], "cellPhone": "+5521098765444", "address": "RuaW", "email": "test4@app", "document": "2456789012"},
            {"id": "59", "name": "Marlon",   "location": [-22.905200, -43.244853], "cellPhone": "+5521000000000", "address": "RuaZ", "email": "test3@app", "document": "3456789012"},
        ]
        """
    