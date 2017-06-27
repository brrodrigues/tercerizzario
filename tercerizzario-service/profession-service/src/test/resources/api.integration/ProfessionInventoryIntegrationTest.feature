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
        {
            "_embedded" : {
              "professions" : [ {
                "name" : "Faxineira",
                "_links" : {
                  "self" : {
                    "href" : "http://localhost/professions/1"
                  },
                  "profession" : {
                    "href" : "http://localhost/professions/1"
                  }
                }
              }, {
                "name" : "Analista de Suporte",
                "_links" : {
                  "self" : {
                    "href" : "http://localhost/professions/2"
                  },
                  "profession" : {
                    "href" : "http://localhost/professions/2"
                  }
                }
              }, {
                "name" : "Analista de Sistemas",
                "_links" : {
                  "self" : {
                    "href" : "http://localhost/professions/3"
                  },
                  "profession" : {
                    "href" : "http://localhost/professions/3"
                  }
                }
              } ]
            },
            "_links" : {
              "self" : {
                "href" : "http://localhost/professions{?page,size,sort}",
                "templated" : true
              },
              "profile" : {
                "href" : "http://localhost/profile/professions"
              },
              "search" : {
                "href" : "http://localhost/professions/search"
              }
            },
            "page" : {
              "size" : 20,
              "totalElements" : 3,
              "totalPages" : 1,
              "number" : 0
            }
          } 
        """

        Scenario: Buscando profissao por nome completo
        Given Iniciado o servidor e subido o contexto
        Given Conjunto de elementos :
        | id | name                 |
        | 1  | Faxineira            |
        | 2  | Analista de Suporte  |
        | 3  | Analista de Sistemas |
        When requisitado via GET /professions/search/findSimilarByName?name=Faxineira
        Then O codigo da resposta sera 200
        Then O resultado JSON sera :
        """
        {
            "_embedded" : {
              "professions" : [ {
                "name" : "Faxineira",
                "_links" : {
                  "self" : {
                    "href" : "http://localhost/professions/1"
                  },
                  "profession" : {
                    "href" : "http://localhost/professions/1"
                  }
                }
              } ]
            },
            "_links" : {
              "self" : {
                "href" : "http://localhost/professions/search/findSimilarByName?name=Faxineira"
              }
            }
        }
        """
    
        Scenario: Buscando profissao por nome por 3 letras iniciais em caixa baixa
        Given Iniciado o servidor e subido o contexto
        Given Conjunto de elementos :
        | id | name                 |
        | 1  | Faxineira            |
        | 2  | Analista de Suporte  |
        | 3  | Analista de Sistemas |
        When requisitado via GET /professions/search/findSimilarByName?name=fax
        Then O codigo da resposta sera 200
        Then O resultado JSON sera :
        """
        {
            "_embedded" : {
              "professions" : [ {
                "name" : "Faxineira",
                "_links" : {
                  "self" : {
                    "href" : "http://localhost/professions/1"
                  },
                  "profession" : {
                    "href" : "http://localhost/professions/1"
                  }
                }
              }  
            ]
            },
            "_links" : {
              "self" : {
                "href" : "http://localhost/professions/search/findSimilarByName?name=fax"
              }
            }
        }
        """

        Scenario: Buscando profissao por nome por 3 letras em caixa alta
        Given Iniciado o servidor e subido o contexto
        Given Conjunto de elementos :
        | id | name                 |
        | 1  | Faxineira            |
        | 2  | Analista de Suporte  |
        | 3  | Analista de Sistemas |
        When requisitado via GET /professions/search/findSimilarByName?name=ANA
        Then O codigo da resposta sera 200
        Then O resultado JSON sera :
        """
        {
            "_embedded" : {
              "professions" : [ {
                "name" : "Analista de Suporte",
                "_links" : {
                  "self" : {
                    "href" : "http://localhost/professions/2"
                  },
                  "profession" : {
                    "href" : "http://localhost/professions/2"
                  }
                }
              }, {
                "name" : "Analista de Sistemas",
                "_links" : {
                  "self" : {
                    "href" : "http://localhost/professions/3"
                  },
                  "profession" : {
                    "href" : "http://localhost/professions/3"
                  }
                }
              } ]
            },
            "_links" : {
              "self" : {
                "href" : "http://localhost/professions/search/findSimilarByName?name=ANA"
              }
            }
        }
        """

