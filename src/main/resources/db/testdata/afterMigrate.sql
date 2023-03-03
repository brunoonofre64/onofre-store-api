-- INICIA APLICACAO JA COM DADOS DE ACESSO ADMIN

INSERT INTO TBL_USER (ID, EMAIL, USUARIO, CPF, AGE, NOME_COMPLETO, SENHA, UUID, INC_DATE)

SELECT 1,
       'admin@admin',
       'admin',
       '00000000000',
       '0',
       'ADMIN ADMIN ADMIN',
       '$2a$12$FTaAku.nwfOHyjxLSaRmk.53cnomZpGGej5ITo05DC5O9R10eYofi',
       '68e86feb-8fc9-4a43-b47b-d85fc008bfe9',
       CURRENT_TIMESTAMP WHERE NOT EXISTS(SELECT * FROM TBL_USER
       );


-- INICIA APLICACAO JA COM O PERFIL DE ADMIN

DO
$$
BEGIN
  IF
NOT EXISTS (SELECT * FROM TBL_ROLE WHERE PROFILE = 'ADMIN') THEN
    INSERT INTO TBL_ROLE (ID, PROFILE, UUID, INC_DATE)
      VALUES (NEXTVAL('SQ_ROLE'), 'ADMIN', '45170d36-b4a8-4e19-85dc-ec5bea0ef3f0', CURRENT_TIMESTAMP);
END IF;
END $$;


-- TABELA ASSOCIATIVA DE TBL_ROLE E TBL_USER

INSERT INTO TBL_USER_ROLES (USER_ID, ROLE_ID)
SELECT u.ID, r.ID
FROM TBL_USER u,
     TBL_ROLE r
WHERE u.USUARIO = 'admin'
  AND r.PROFILE = 'ADMIN';

-- TABELA DO OAUTH2 ARMAZENA DETALHE DOS CLIENTES

INSERT INTO oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types,
                                  web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity,
                                  additional_information, autoapprove)

-- INSERE DADOS INICIAS DE CLIENTES NA TABELA DO OAUTH2

SELECT 'onofre-web', null, '$2a$12$v8C4sl.elzHtqiEZoW5OXOM5y3JvYM7p7OVIbTMBUghpoSyB0vrka', 'READ,WRITE', 'password',
       null, null, 60 * 60 * 6, 60 * 24 * 60 * 60, null, null
    WHERE NOT EXISTS (SELECT * FROM oauth_client_details WHERE client_id = 'onofre-web')

UNION ALL

SELECT 'faturamento', null, '$2a$12$v8C4sl.elzHtqiEZoW5OXOM5y3JvYM7p7OVIbTMBUghpoSyB0vrka', 'READ,WRITE', 'client_credentials',
       null, null, null, null, null, null
    WHERE NOT EXISTS (SELECT * FROM oauth_client_details WHERE client_id = 'faturamento')

UNION ALL

SELECT 'onofre-analytics', null, '$2a$12$v8C4sl.elzHtqiEZoW5OXOM5y3JvYM7p7OVIbTMBUghpoSyB0vrka', 'READ,WRITE', 'authorization_code',
      'http://www.onofrestore-analytics.com', null, null, null, null, null
    WHERE NOT EXISTS (SELECT * FROM oauth_client_details WHERE client_id = 'onofre-analytics');

