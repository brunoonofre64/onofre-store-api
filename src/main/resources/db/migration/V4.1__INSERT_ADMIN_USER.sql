INSERT INTO TBL_USER (ID, EMAIL, USUARIO, SENHA, UUID, INC_DATE)
SELECT 1,
       'admin@admin',
       'admin',
       '$2a$12$FTaAku.nwfOHyjxLSaRmk.53cnomZpGGej5ITo05DC5O9R10eYofi',
       '68e86feb-8fc9-4a43-b47b-d85fc008bfe9',
       CURRENT_TIMESTAMP
WHERE NOT EXISTS(SELECT * FROM TBL_USER);