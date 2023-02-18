/* use this file to populate production database*/
/* ensure session = production : productionSeeder */
/* ensure schema = mindmatters */

use mindmatters;

#### Populate categories table ####
INSERT INTO categories (category)
VALUES('Default'),
      ('Work'),
      ('Outdoors'),
      ('Social'),
      ('Exercise'),
      ('Education'),
      ('Hobbies'),
      ('Chores'),
      ('Recreation'),
      ('On Screens');

#### Populate Providers ####
INSERT INTO users (is_provider, is_verified, password, username, provider_id)
VALUES(true, true, '$2a$10$0PtfEbzDp033Q5FKZTj9ye3YYuwV.E5RtIx8.g.c/WEAGW.IyIogu', 1, 'Admin'),
      (true, true, '$2a$10$m.qrHYwq9cJC9FviKIWD7.vwhzg1SthPxgVgLfj51vHwHGeYQwbgG', 1, 'Miles'),
      (true, true, '$2a$10$b7r9dwM3k.MhhLPbF6MP6uDkyeCccp8FTfUErIKEJsX2UaV3cFbya', 1, 'Andres'),
      (true, true, '$2a$10$CcMAJGDmtCMAWoBD525nBOa7hg3CwwKWmIt44Vp3e8lRpWf7qEoWK', 1, 'Kiley'),
      (true, true, '$2a$10$N5UJkkSh0frezU7DgCzoEOvDhCxayivxuQcaDt5H4rxdbX6dSM/qO', 1, 'Kenneth')

#### Populate Patients ####
INSERT INTO users (is_provider, is_verified, password, username, provider_id)
VALUES(false, false, '$2a$10$.Ppkwsq95AOAoraXL/xJMeChNZFVMvvMhkre2kq7NKFcY24x.awXm', 2, 'Laura' ),
      (false, false, '$2a$10$cAQobEN4GrRvA9cR76qObOi8zO/W3NR/u1G16DDaFleOTva87kS5y', 3, 'Nathaniel'),
      (false, false, '$2a$10$dBXtyE6Y/rq7LTgeSKuBmOH1i/Gw/VB9EUDq1tMCbRrIfRh.QV5Ca', 4, 'Bob'),
      (false, false, '$2a$10$QWxjAVVEk6YqeY3Smpt97.OV989kKL5C.tOdJbNT1KHwxc9N9mRrm', 3, 'Emily'),
      (false, false, '$2a$10$EA7/dHnNDkgotUGnWMfjru.aw/kUZocgMv9NxZy96fhGZzGP8mRm.', 5, 'Wilson'),
      (false, false, '$2a$10$Sq/Lw63F5lW2itki/IK25OsrnWayal3hyD2RtIxgCucoQFrSjcvOq', 2, 'Jordan'),
      (false, false, '$2a$10$vW328pfCesE9X3.vVTDKr.5WUZ09dS8z/B3OPQOXw7yOghAFEWPZe', 5, 'Anna'),
      (false, false, '$2a$10$4dEpqzCouBFJwlMpbtMtMu19l/UORR7NqAV1PVf1OxP/3v304u5Sa', 3, 'Jeffery'),
      (false, false, '$2a$10$a3r/pp79GMokOyctta7i1O14LDUQLvmcpBsBfTWBEH8j9Wv2Pqvb.', 5, 'Ethan'),
      (false, false, '$2a$10$cgkVuiHQVsnvNPdW/HvoYe8GYvAZ1fW5LDGsq7qVujfyhyjzpUc2u', 4, 'Bill'),
      (false, false, '$2a$10$ZgwLVhnuX3ZTeJAa52.7FO0Dle3h3dCCMHkDpDeKqCIrOYzqEv1kW', 4, 'Katherine'),
      (false, false, '$2a$10$APaDJtQWRrDX2l/32ryTzOy8F1YjDg8c62f8MM869q8P9JA4hj4Ga', 5, 'Elizabeth'),
      (false, false, '$2a$10$3Jizlhq8ZMtqjL.aJYgWmevT8mwg.ydKylwCROy9Muk2q1eUKXl1C', 3, 'Norma'),
      (false, false, '$2a$10$nGwJyQUqigjVR8hE0s52jOtWH9D.G.iG/dK0PJ1tceRMCkYVyydqe', 5, 'Steven'),
      (false, false, '$2a$10$j862YeTdbi1ESNJhaivgpercCFhuHtXRWV9RXfNeSxA4CnKDILJhG', 2, 'Kari'),
      (false, false, '$2a$10$O15dHRV.3BtY9O9JgZ05L.SiaT4rKzOhmiDNaK73dDzcdN.YFt0K2', 3, 'Theresa'),
      (false, false, '$2a$10$ltG3b.Im5hHmp0dxAWUPZ.uSAz9i9s/oMc2RubQPhDP9xrUCceGxy', 5, 'Sarah')

#### Populate Mood Data ####
    ## User ID = 6 ##
INSERT INTO scaling_data (date, score, user_id)
VALUES ('2023-02-01 08:00:00', 4, 6),
       ('2023-02-02 08:00:00', 3, 6),
       ('2023-02-03 08:00:00', 4, 6),
       ('2023-02-04 08:00:00', 5, 6),
       ('2023-02-05 08:00:00', 4, 6),
       ('2023-02-06 08:00:00', 6, 6),
       ('2023-02-07 08:00:00', 5, 6),
       ('2023-02-08 08:00:00', 2, 6),
       ('2023-02-09 08:00:00', 3, 6),
       ('2023-02-10 08:00:00', 6, 6),
       ('2023-02-11 08:00:00', 5, 6),
       ('2023-02-12 08:00:00', 6, 6),
       ('2023-02-13 08:00:00', 5, 6),
       ('2023-02-14 08:00:00', 5, 6),
       ('2023-02-15 08:00:00', 6, 6),
       ('2023-02-16 08:00:00', 6, 6),
       ('2023-02-17 08:00:00', 7, 6),
       ('2023-02-18 08:00:00', 6, 6),
       ('2023-02-19 08:00:00', 7, 6),
       ('2023-02-20 08:00:00', 6, 6),
       ('2023-02-21 08:00:00', 6, 6),
       ('2023-02-22 08:00:00', 7, 6),
       ('2023-02-23 08:00:00', 8, 6),
       ('2023-02-24 08:00:00', 7, 6),
       ('2023-02-25 08:00:00', 8, 6),
       ('2023-02-26 08:00:00', 7, 6),
       ('2023-02-27 08:00:00', 7, 6),
       ('2023-02-28 08:00:00', 8, 6),
       ('2023-02-29 08:00:00', 8, 6);

    ## User ID = 7 ##
INSERT INTO scaling_data (date, score, user_id)
VALUES ('2023-02-01 08:00:00', 8, 7),
       ('2023-02-02 08:00:00', 7, 7),
       ('2023-02-03 08:00:00', 9, 7),
       ('2023-02-04 08:00:00', 8, 7),
       ('2023-02-05 08:00:00', 7, 7),
       ('2023-02-06 08:00:00', 6, 7),
       ('2023-02-07 08:00:00', 3, 7),
       ('2023-02-08 08:00:00', 6, 7),
       ('2023-02-09 08:00:00', 5, 7),
       ('2023-02-10 08:00:00', 6, 7),
       ('2023-02-11 08:00:00', 4, 7),
       ('2023-02-12 08:00:00', 3, 7),
       ('2023-02-13 08:00:00', 4, 7),
       ('2023-02-14 08:00:00', 3, 7),
       ('2023-02-15 08:00:00', 1, 7),
       ('2023-02-16 08:00:00', 2, 7),
       ('2023-02-17 08:00:00', 1, 7),
       ('2023-02-18 08:00:00', 2, 7),
       ('2023-02-19 08:00:00', 1, 7),
       ('2023-02-20 08:00:00', 4, 7),
       ('2023-02-21 08:00:00', 5, 7),
       ('2023-02-22 08:00:00', 6, 7),
       ('2023-02-23 08:00:00', 5, 7),
       ('2023-02-24 08:00:00', 6, 7),
       ('2023-02-25 08:00:00', 7, 7),
       ('2023-02-26 08:00:00', 8, 7),
       ('2023-02-27 08:00:00', 7, 7),
       ('2023-02-28 08:00:00', 8, 7),
       ('2023-02-29 08:00:00', 5, 7);

    ## User ID = 8 ##
INSERT INTO scaling_data (date, score, user_id)
VALUES ('2023-02-01 08:00:00', 8, 8),
       ('2023-02-02 08:00:00', 7, 8),
       ('2023-02-03 08:00:00', 9, 8),
       ('2023-02-04 08:00:00', 8, 8),
       ('2023-02-05 08:00:00', 7, 8),
       ('2023-02-06 08:00:00', 6, 8),
       ('2023-02-07 08:00:00', 7, 8),
       ('2023-02-08 08:00:00', 6, 8),
       ('2023-02-09 08:00:00', 7, 8),
       ('2023-02-10 08:00:00', 6, 8),
       ('2023-02-11 08:00:00', 6, 8),
       ('2023-02-12 08:00:00', 6, 8),
       ('2023-02-13 08:00:00', 7, 8),
       ('2023-02-14 08:00:00', 2, 8),
       ('2023-02-15 08:00:00', 3, 8),
       ('2023-02-16 08:00:00', 4, 8),
       ('2023-02-17 08:00:00', 3, 8),
       ('2023-02-18 08:00:00', 3, 8),
       ('2023-02-19 08:00:00', 4, 8),
       ('2023-02-20 08:00:00', 3, 8),
       ('2023-02-21 08:00:00', 2, 8),
       ('2023-02-22 08:00:00', 3, 8),
       ('2023-02-23 08:00:00', 4, 8),
       ('2023-02-24 08:00:00', 5, 8),
       ('2023-02-25 08:00:00', 6, 8),
       ('2023-02-26 08:00:00', 7, 8),
       ('2023-02-27 08:00:00', 7, 8),
       ('2023-02-28 08:00:00', 6, 8),
       ('2023-02-29 08:00:00', 7, 8);


#### Populate Events