@restApiIntegration
Feature: Teste de integracao Profession Resource 

    Scenario: Buscando todos os professions
        Given Iniciado o servidor e subido o contexto
        Given Conjunto de elementos :
        | id | name                 |
        | 1  | Faxineira            |
        | 2  | Analista de Suporte  |
        | 3  | Analista de Sistemas |
        When requisitado via GET /professions
        Then O codigo da resposta sera 200
        Then O resultado JSON sera :
        """
        [{"id": "1", "name": "Faxineira"},
         {"id": "2", "name": "Analista de Suporte"},
         {"id": "3", "name": "Analista de Sistemas"}]
        """
    
    Scenario: Atualizando professions
        Given Iniciado o servidor e subido o contexto
        Given Conjunto de elementos :
        | id | name                 |
        | 1  | Faxineira            |
        | 2  | Analista de Suporte  |
        | 3  | Analista de Sistemas |
        When requisitado via PUT /professions com formato json :
        """
        {"id": "1","name": "Analista de Suporte 2"}
        """
        Then O resultado JSON sera :
        """
        {"id": "1", "name": "Analista de Suporte 2"}
        """
        When requisitado via GET /professions
        Then O resultado JSON sera :
        """
        [{"id": "1", "name": "Analista de Suporte 2"},
         {"id": "2", "name": "Analista de Suporte"},
         {"id": "3", "name": "Analista de Sistemas"}]
        """

    Scenario: Buscando profissao por nome completo
        Given Iniciado o servidor e subido o contexto
        Given Conjunto de elementos :
        | id | name                 |
        | 1  | Faxineira            |
        | 2  | Analista de Suporte  |
        | 3  | Analista de Sistemas |
        When requisitado via GET /professions/search/bySimilarName?name=Faxineira
        Then O resultado JSON sera :
        """
        [{"id": "1", "name": "Faxineira"}]       
        """
    
    Scenario: Buscando profissao por nome por 3 letras iniciais 
        Given Iniciado o servidor e subido o contexto
        Given Conjunto de elementos :
        | id | name                 |
        | 1  | Faxineira            |
        | 2  | Analista de Suporte  |
        | 3  | Analista de Sistemas |
        When requisitado via GET /professions/search/bySimilarName?name=Fax
        Then O resultado JSON sera :
        """
        [{"id": "1", "name": "Faxineira"}]
        """

    Scenario: Buscando profissao por nome por 3 letras entre a palavra em caixa alta
        Given Iniciado o servidor e subido o contexto
        Given Conjunto de elementos :
        | id | name                 |
        | 1  | Faxineira            |
        | 2  | Analista de Suporte  |
        | 3  | Analista de Sistemas |
        When requisitado via GET /professions/search/bySimilarName?name=ANA
        Then O resultado JSON sera :
        """
        [
            {"id": "3", "name": "Analista de Sistemas"},
            {"id": "2", "name": "Analista de Suporte"},
        ]
        """
