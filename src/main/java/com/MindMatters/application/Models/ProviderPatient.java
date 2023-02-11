package com.MindMatters.application.Models;


import jakarta.persistence.*;

@Entity
@Table(name =  "relations")
public class ProviderPatient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private User provider;

    @OneToOne
    private User patient;

    public ProviderPatient() {
    }

    public ProviderPatient( User provider, User patient) {
        this.provider = provider;
        this.patient = patient;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getProvider() {
        return provider;
    }

    public void setProvider(User providerId) {
        this.provider = providerId;
    }

    public User getPatient() {
        return patient;
    }

    public void setPatient(User patientId) {
        this.patient = patientId;
    }
}

/* table development notes:
*
provider_patient
* pk | providerId | patientId
  1 | 1 | 2
  2 | 1 | 3
  3 | 1 | 4

    user
id | username | isProvider
1 | Andres | T
2 | Britt  | F
3 | Kiley  | F
4 | Miles  | F

* example SELECT statement:

SELECT user_name FROM users as u
JOIN provider_patient as p
   ON u.id = p.provider_id
JOIN users as u2
   ON p.patient_id = u2.id
WHERE u.id = 1
 */

